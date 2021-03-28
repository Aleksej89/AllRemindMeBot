package org.allRemindMeBot.bot.services;

import lombok.extern.java.Log;
import org.allRemindMeBot.bot.body.Bot;
import org.allRemindMeBot.bot.handlers.IncomingMsgHandler;
import org.allRemindMeBot.enums.ServiceCounters;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log
@Component
public class MessageReceiver implements Runnable {
    private final IncomingMsgHandler incomingMsgHandler;
    private final Bot bot;

    public MessageReceiver(Bot bot, IncomingMsgHandler incomingMsgHandler) {
        this.bot = bot;
        this.incomingMsgHandler = incomingMsgHandler;
    }

    @Override
    public void run() {
        log.info("[STARTED] MessageReceiver.  Bot class: " + this.bot);
        while (true) {
            for (Object object = bot.receiveQueue.poll(); object != null; object = bot.receiveQueue.poll()) {
                analyze(object);
            }
            try {
                Thread.sleep(ServiceCounters.SERVICE_SLEEP_MILLS_COUNTER.getCounter());
            } catch (InterruptedException exception) {
                log.severe("[ERROR] MessageReceiver. Catch interrupt. Exit: " + exception.getMessage());
                return;
            }
        }
    }

    private void analyze(Object object) {
        if (object instanceof Update) {
            this.bot.sendQueue.add(this.incomingMsgHandler.handle((Update) object));
        } else {
            log.severe("[ERROR] MessageReceiver. Cant operate type of object: " + object.toString());
        }
    }
}
