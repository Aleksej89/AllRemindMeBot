package org.allRemindMeBot.bot.handlers.userCommands;

import org.allRemindMeBot.bot.workers.BotUserApplicationWorker;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.entity.BotUserApplication;
import org.allRemindMeBot.enums.AppCounters;
import org.allRemindMeBot.enums.Messages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteApplicationHandler {
    private final BotUserApplicationWorker botUserApplicationWorker;

    public DeleteApplicationHandler(BotUserApplicationWorker botUserApplicationWorker) {
        this.botUserApplicationWorker = botUserApplicationWorker;
    }

    public void handle(SendMessage message, BotUser user) {
        message.setText(Messages.LOOK_EMPTY_MSG.getMessage());
        Optional<List<BotUserApplication>> applications = this.botUserApplicationWorker.getFromBaseAllApplicationByChatId(user.getUserChatId());
        if (applications.isPresent()) {
            if (applications.get().size() > AppCounters.ZERO_COUNTER.getCounter()) {
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                for (BotUserApplication application : applications.get()) {
                    List<InlineKeyboardButton> buttonRow = new ArrayList<>();
                    InlineKeyboardButton appButton = new InlineKeyboardButton();
                    appButton.setText(application.getApplicationText());
                    appButton.setCallbackData(String.valueOf(application.getId()));
                    buttonRow.add(appButton);
                    rowList.add(buttonRow);
                }
                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                keyboardMarkup.setKeyboard(rowList);
                message.setReplyMarkup(keyboardMarkup);
                message.setText(Messages.LOOK_ALL_MSG.getMessage());
            }
        }
    }

    public void deleteApplication(SendMessage message, Update update) {
        message.setText(Messages.DELETE_EMPTY_MSG.getMessage());
        try {
            if (this.botUserApplicationWorker.deleteApplication(Integer.valueOf(update.getCallbackQuery().getData())).isPresent()) {
                message.setText(Messages.DELETE_SUCCESS_MSG.getMessage());
            }
        } catch (Exception exception) {
            message.setText(Messages.ERROR_MSG.getMessage());
        }
    }

    public void deleteHistApplications(SendMessage message, BotUser user) {
        message.setText(Messages.DELETE_HIST_SUCCESS_MSG.getMessage());
        this.botUserApplicationWorker.deleteHistory(new Date(), user.getUserChatId());
    }
}
