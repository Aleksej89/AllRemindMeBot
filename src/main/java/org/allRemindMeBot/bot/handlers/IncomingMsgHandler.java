package org.allRemindMeBot.bot.handlers;

import org.allRemindMeBot.bot.menu.Menu;
import org.allRemindMeBot.bot.workers.BotUserWorker;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.enums.Messages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;


@Component
public class IncomingMsgHandler {
    private final BotUserWorker botUserWorker;
    private final CommandHandler commandHandler;

    public IncomingMsgHandler(BotUserWorker botUserWorker, CommandHandler commandHandler) {
        this.botUserWorker = botUserWorker;
        this.commandHandler = commandHandler;
    }

    public SendMessage handle(Update update) {
        SendMessage message = new SendMessage();
        message.setText(Messages.INFO_MSG.getMessage());
        message.setReplyMarkup(Menu.getReplyKeyboardMarkup());
        BotUser user = this.botUserWorker.createNewBotUser(update);
        Optional<BotUser> botUser = this.botUserWorker.getFromBaseBotUser(user);
        if (botUser.isEmpty()) {
            this.botUserWorker.saveToBaseBotUser(user);
        } else {
            user = botUser.get();
        }
        if (update.hasMessage() && update.getMessage().hasText() || update.hasCallbackQuery()) {
            this.commandHandler.handle(message, update, user);
        }
        message.setChatId(String.valueOf(user.getUserChatId()));
        return message;
    }
}
