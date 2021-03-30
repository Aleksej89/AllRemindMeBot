package org.allRemindMeBot.bot.handlers.userCommands;

import org.allRemindMeBot.bot.workers.BotUserApplicationWorker;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.entity.BotUserApplication;
import org.allRemindMeBot.enums.Messages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;
import java.util.Optional;

@Component
public class AddApplicationHandler {
    private final BotUserApplicationWorker botUserApplicationWorker;

    public AddApplicationHandler(BotUserApplicationWorker botUserApplicationWorker) {
        this.botUserApplicationWorker = botUserApplicationWorker;
    }

    public void handle(SendMessage message, Update update, BotUser user) {
        message.setText(Messages.INFO_MSG.getMessage());
        Optional<BotUserApplication> application = this.botUserApplicationWorker.createNewApplication(update, user);
        if (application.isPresent()) {
            if (application.get().getDateApplication().after(new Date())) {
                this.botUserApplicationWorker.saveToBaseBotUserApplication(application.get());
                message.setText(Messages.ADD_SUCCESS_MSG.getMessage());
            }
        }
    }
}
