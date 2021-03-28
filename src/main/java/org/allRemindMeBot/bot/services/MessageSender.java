package org.allRemindMeBot.bot.services;

import lombok.extern.java.Log;
import org.allRemindMeBot.bot.body.Bot;
import org.allRemindMeBot.enums.ServiceCounters;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log
@Component
public class MessageSender implements Runnable {

    private final Bot bot;

    public MessageSender(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        log.info("[STARTED] MessageSender. Bot class: " + this.bot);
        try {
            while (true) {
                for (Object object = bot.sendQueue.poll(); object != null; object = bot.sendQueue.poll()) {
                    this.send(object);
                }
                try {
                    Thread.sleep(ServiceCounters.SERVICE_SLEEP_MILLS_COUNTER.getCounter());
                } catch (InterruptedException exception) {
                    log.severe("[ERROR] MessageSender. Catch interrupt. Exit: " + exception.getMessage());
                }
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    private void send(Object object) {
        try {
            this.bot.execute((SendMessage) object);
        } catch (TelegramApiException exception) {
            log.severe("[ERROR] MessageSender. Cant execute: " + exception.getMessage());
        }
    }
}