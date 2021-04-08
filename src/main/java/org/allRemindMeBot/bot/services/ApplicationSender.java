package org.allRemindMeBot.bot.services;

import lombok.extern.java.Log;
import org.allRemindMeBot.bot.body.Bot;
import org.allRemindMeBot.dao.BotUserApplicationDao;
import org.allRemindMeBot.entity.BotUserApplication;
import org.allRemindMeBot.enums.AppCounters;
import org.allRemindMeBot.enums.Delimiters;
import org.allRemindMeBot.enums.Emoji;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Log
@Component
public class ApplicationSender implements Runnable {
    private final static int DATE_MILLS_COUNTER = 500;
    private final BotUserApplicationDao applicationDao;
    private final Bot bot;

    public ApplicationSender(Bot bot, BotUserApplicationDao applicationDao) {
        this.bot = bot;
        this.applicationDao = applicationDao;
    }


    @Override
    public void run() {
        log.info("[STARTED] ApplicationSender. Bot class: " + this.bot);
        try {
            while (true) {
                this.send();
                try {
                    Thread.sleep(AppCounters.SERVICE_SLEEP_MILLS_COUNTER.getCounter());
                } catch (InterruptedException exception) {
                    log.severe("[ERROR] ApplicationSender. Catch interrupt. Exit: " + exception.getMessage());
                }
            }
        } catch (Exception exception) {
            log.severe("[ERROR] ApplicationSender. " + exception.getMessage());
        }
    }

    private void send() {
        Optional<List<BotUserApplication>> listApplications = this.applicationDao.findAllBetweenDates(new Date(System.currentTimeMillis() - DATE_MILLS_COUNTER),
                new Date(System.currentTimeMillis() + DATE_MILLS_COUNTER));
        if (listApplications.isPresent()) {
            if (listApplications.get().size() > AppCounters.ZERO_COUNTER.getCounter()) {
                for (BotUserApplication app : listApplications.get()) {
                    SendMessage message = new SendMessage();
                    message.setText(Emoji.APP_EMOJI.getEmojiStr() + Delimiters.TWO_WHITE_SPACE_DELIMITER.getDelimiter() + app.getApplicationText());
                    message.setChatId(String.valueOf(app.getChatId()));
                    try {
                        this.bot.execute(message);
                    } catch (TelegramApiException exception) {
                        log.severe("[ERROR] ApplicationSender. Cant execute: " + exception.getMessage());
                    }
                }
            }
        }
    }
}
