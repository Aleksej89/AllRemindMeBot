package org.allRemindMeBot.bot.handlers;

import org.allRemindMeBot.enums.Messages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class InfoApplicationHandler {
    public void handle(SendMessage message) {
        message.setText(Messages.INFO_ALL_MSG.getMessage());
    }
}
