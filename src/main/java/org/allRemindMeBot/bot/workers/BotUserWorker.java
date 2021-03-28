package org.allRemindMeBot.bot.workers;

import org.allRemindMeBot.dao.BotUserDao;
import org.allRemindMeBot.entity.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class BotUserWorker {
    private final BotUserDao botUserDao;

    public BotUserWorker(BotUserDao botUserDao) {
        this.botUserDao = botUserDao;
    }

    public BotUser createNewBotUser(Update update) {
        if (update.hasCallbackQuery()) {
            return BotUser.builder().userChatId(update.getCallbackQuery().getMessage().getChatId()).userName(update.getCallbackQuery().getMessage().getFrom().getFirstName()).build();
        }
        return BotUser.builder().userChatId(update.getMessage().getChatId()).userName(update.getMessage().getFrom().getFirstName()).build();
    }

    public Optional<BotUser> getFromBaseBotUser(BotUser user) {
        return this.botUserDao.findUser(user);
    }

    public void saveToBaseBotUser(BotUser user) {
        this.botUserDao.save(user);
    }
}
