package org.allRemindMeBot.bot.workers;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.java.Log;
import org.allRemindMeBot.bot.converters.DateConverter;
import org.allRemindMeBot.dao.BotUserApplicationDao;
import org.allRemindMeBot.dao.BotUserDao;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.entity.BotUserApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@Component
public class BotUserApplicationWorker {
    private final BotUserApplicationDao applicationDao;

    private final static String DATE_REGEXP = "(\\d{2}[.]\\d{2}[.]\\d{4}|\\d{2}[.]\\d{2})";
    private final static String TIME_REGEXP = "(\\d{2}[:]\\d{2})";

    public BotUserApplicationWorker(BotUserApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public Optional<BotUserApplication> createNewApplication(Update update, BotUser user) {
        Optional<BotUserApplication> application = Optional.empty();
        String messageText = EmojiParser.removeAllEmojis(update.getMessage().getText());
        if (!messageText.isEmpty()) {
            Matcher date = Pattern.compile(DATE_REGEXP, Pattern.CASE_INSENSITIVE).matcher(messageText);
            Matcher time = Pattern.compile(TIME_REGEXP, Pattern.CASE_INSENSITIVE).matcher(messageText);
            if (date.find() && time.find()) {
                Optional<Date> dateOpt = DateConverter.getDate(date.group(1), time.group(1));
                if (dateOpt.isPresent()) {
                    application = Optional.of(BotUserApplication.builder().chatId(user.getUserChatId()).applicationText(messageText)
                            .applicationText(messageText).dateApplication(dateOpt.get()).build());

                }
            }
        }
        return application;
    }

    public void saveToBaseBotUserApplication(BotUserApplication application) {
        this.applicationDao.save(application);
    }

    public Optional<List<BotUserApplication>> getFromBaseAllApplicationByChatId(Long chatId) {
        return this.applicationDao.findAllByChatId(chatId);
    }

    public Optional<BotUserApplication> deleteApplication(Integer appId) {
        return this.applicationDao.deleteApplicationById(appId);
    }

    public void deleteHistory(Date date, Long chatId) {
        this.applicationDao.deleteHistoryApplication(date, chatId);
    }
}
