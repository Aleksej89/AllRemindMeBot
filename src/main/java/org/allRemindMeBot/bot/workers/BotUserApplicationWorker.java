package org.allRemindMeBot.bot.workers;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.java.Log;
import org.allRemindMeBot.bot.converters.DateConverter;
import org.allRemindMeBot.dao.BotUserApplicationDao;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.entity.BotUserApplication;
import org.allRemindMeBot.enums.Delimiters;
import org.allRemindMeBot.enums.Regexps;
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

    public BotUserApplicationWorker(BotUserApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public Optional<BotUserApplication> createNewApplication(Update update, BotUser user) {
        Optional<BotUserApplication> application = Optional.empty();
        String messageText = EmojiParser.removeAllEmojis(update.getMessage().
                getText()).replaceAll(Regexps.CHARACTER_FILTER_REGEXP.getRegexp(), Delimiters.NON_WHITE_SPACE_DELIMITER.getDelimiter()).trim();
        if (!messageText.isEmpty()) {
            Matcher date = this.getMatcher(messageText, Regexps.DATE_REGEXP.getRegexp());
            Matcher time = this.getMatcher(messageText, Regexps.TIME_REGEXP.getRegexp());
            if (time.find()) {
                Optional<Date> dateOpt;
                if (!date.find()) {
                    dateOpt = DateConverter.getDate(null, time.group(1));
                } else {
                    dateOpt = DateConverter.getDate(date.group(1), time.group(1));
                }
                if (dateOpt.isPresent()) {
                    application = Optional.of(BotUserApplication.builder().chatId(user.getUserChatId()).applicationText(messageText)
                            .applicationText(messageText).dateApplication(dateOpt.get()).build());
                }
            }
        }
        return application;
    }

    private Matcher getMatcher(String messageText, String dateRegexp) {
        return Pattern.compile(dateRegexp, Pattern.CASE_INSENSITIVE).matcher(messageText);
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
