package org.allRemindMeBot.bot.handlers;

import org.allRemindMeBot.bot.handlers.userCommands.AddApplicationHandler;
import org.allRemindMeBot.bot.handlers.userCommands.DeleteApplicationHandler;
import org.allRemindMeBot.bot.handlers.userCommands.InfoApplicationHandler;
import org.allRemindMeBot.bot.handlers.userCommands.LookApplicationHandler;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.enums.Buttons;
import org.allRemindMeBot.enums.Messages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CommandHandler {
    private final InfoApplicationHandler infoApplicationHandler;
    private final LookApplicationHandler lookApplicationHandler;
    private final AddApplicationHandler addApplicationHandler;
    private final DeleteApplicationHandler deleteApplicationHandler;

    public CommandHandler(InfoApplicationHandler infoApplicationHandler, LookApplicationHandler lookApplicationHandler,
                          AddApplicationHandler addApplicationHandler, DeleteApplicationHandler deleteApplicationHandler) {
        this.infoApplicationHandler = infoApplicationHandler;
        this.lookApplicationHandler = lookApplicationHandler;
        this.addApplicationHandler = addApplicationHandler;
        this.deleteApplicationHandler = deleteApplicationHandler;
    }

    public void handle(SendMessage message, Update update, BotUser user) {
        message.setText(Messages.ERROR_MSG.getMessage());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String msgText = update.getMessage().getText();
            if (Buttons.INFO_BUTTON.getButton().equals(msgText)) {
                this.infoApplicationHandler.handle(message);
            } else if (Buttons.LOOK_BUTTON.getButton().equals(msgText)) {
                this.lookApplicationHandler.handle(message, user);
            } else if (Buttons.DELETE_BUTTON.getButton().equals(msgText)) {
                this.deleteApplicationHandler.handle(message, user);
            } else if (Buttons.DELETE_HIST_BUTTON.getButton().equals(msgText)) {
                this.deleteApplicationHandler.deleteHistApplications(message, user);
            } else {
                this.addApplicationHandler.handle(message, update, user);
            }
        }
        if (update.hasCallbackQuery()) {
            this.deleteApplicationHandler.deleteApplication(message, update);
        }
    }
}
