package org.allRemindMeBot.bot.menu;

import org.allRemindMeBot.enums.Buttons;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class Menu {
    private final static ReplyKeyboardMarkup REPLY_KEYBOARD_MARKUP;

    static {
        REPLY_KEYBOARD_MARKUP = new ReplyKeyboardMarkup();
        REPLY_KEYBOARD_MARKUP.setSelective(true);
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        REPLY_KEYBOARD_MARKUP.setOneTimeKeyboard(false);
    }

    public static ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        ArrayList<KeyboardRow> rowBtnList = new ArrayList<>();
        for (Buttons buttons : Buttons.values()) {
            KeyboardRow buttonRow = new KeyboardRow();
            buttonRow.add(buttons.getButton());
            rowBtnList.add(buttonRow);
        }
        REPLY_KEYBOARD_MARKUP.setKeyboard(rowBtnList);
        return REPLY_KEYBOARD_MARKUP;
    }
}
