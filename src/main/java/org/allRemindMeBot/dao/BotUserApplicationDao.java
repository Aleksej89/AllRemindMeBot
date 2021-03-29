package org.allRemindMeBot.dao;

import org.allRemindMeBot.entity.BotUserApplication;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BotUserApplicationDao extends GenericDao<BotUserApplication, Integer> {
    default Optional<List<BotUserApplication>> findAllBetweenDates(Date startDate, Date endDate) {
        return Optional.empty();
    }

    default Optional<List<BotUserApplication>> findAllByChatId(Long chatId) {
        return Optional.empty();
    }

    default Optional<BotUserApplication> deleteApplicationById(Integer appId) {
        return Optional.empty();
    }

    default void deleteHistoryApplication(Date date, Long chatId) {

    }
}
