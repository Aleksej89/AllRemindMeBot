package org.allRemindMeBot.enums;

public enum Emoji {
    APP_EMOJI("✉"),
    SUCCESS_EMOJI("✅"),
    ERROR_EMOJI("⚡"),
    LISTS_EMOJI("✏"),
    INFO_EMOJI("⚠"),
    REF_INFO_EMOJI("☎"),
    LOOK_EMOJI("⌕"),
    DELETE_EMOJI("❌"),
    DELETE_SUCCESS_EMOJI("❎"),
    DELETE_EMPTY_EMOJI("✂"),
    HART_EMOJI("♥"),
    CLOVER_EMOJI("☘"),
    EMPTY_EMOJI("⌛"),
    HAMMER_EMOJI("⚒");


    private final String emojiStr;

    Emoji(String emojiStr) {
        this.emojiStr = emojiStr;
    }

    public String getEmojiStr() {
        return this.emojiStr;
    }
}
