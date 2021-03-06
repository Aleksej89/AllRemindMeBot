package org.allRemindMeBot.bot.handlers.userCommands;


import org.allRemindMeBot.bot.workers.BotUserApplicationWorker;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.entity.BotUserApplication;
import org.allRemindMeBot.enums.AppCounters;
import org.allRemindMeBot.enums.Delimiters;
import org.allRemindMeBot.enums.Emoji;
import org.allRemindMeBot.enums.Messages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.Optional;

@Component
public class LookApplicationHandler {
    private final BotUserApplicationWorker botUserApplicationWorker;

    public LookApplicationHandler(BotUserApplicationWorker botUserApplicationWorker) {
        this.botUserApplicationWorker = botUserApplicationWorker;
    }

    public void handle(SendMessage message, BotUser user) {
        message.setText(String.valueOf(Messages.LOOK_EMPTY_MSG.getMessage()));
        Optional<List<BotUserApplication>> applications = this.botUserApplicationWorker.getFromBaseAllApplicationByChatId(user.getUserChatId());
        if (applications.isPresent()) {
            if (applications.get().size() > AppCounters.ZERO_COUNTER.getCounter()) {
                StringBuilder appMsg = new StringBuilder();
                for (BotUserApplication application : applications.get()) {
                    appMsg.append(Emoji.LOOK_EMOJI.getEmojiStr()).append(Delimiters.ONE_WHITE_SPACE_DELIMITER.getDelimiter()).append(application.getApplicationText()).append(Delimiters.TWO_LINE_DELIMITER.getDelimiter());
                }
                message.setText(String.valueOf(appMsg));
            }
        }
    }
}