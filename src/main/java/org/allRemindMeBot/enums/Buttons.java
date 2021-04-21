package org.allRemindMeBot.enums;

public enum Buttons {
    INFO_BUTTON(Emoji.INFO_EMOJI.getEmojiStr() + " СПРАВОЧНАЯ ИНФОРМАЦИЯ " + Emoji.INFO_EMOJI.getEmojiStr()),
    LOOK_BUTTON(Emoji.CLOVER_EMOJI.getEmojiStr() + " ПРОСМОТР НАПОМИНАНИЙ " + Emoji.CLOVER_EMOJI.getEmojiStr()),
    DELETE_BUTTON(Emoji.DELETE_EMOJI.getEmojiStr() + " УДАЛЕНИЕ НАПОМИНАНИЙ " + Emoji.DELETE_EMOJI.getEmojiStr()),
    DELETE_HIST_BUTTON(Emoji.HAMMER_EMOJI.getEmojiStr() + " ОЧИСТКА ИСТОРИИ " + Emoji.HAMMER_EMOJI.getEmojiStr());

    private final String button;

    Buttons(String button) {
        this.button = button;
    }

    public String getButton() {
        return this.button;
    }
}
