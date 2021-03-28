package org.allRemindMeBot.dao;

import org.allRemindMeBot.entity.BotUser;

import java.util.Optional;

public interface BotUserDao extends GenericDao<BotUser, Integer> {
    default Optional<BotUser> findUser(BotUser botUser) {
        return Optional.empty();
    }
}
