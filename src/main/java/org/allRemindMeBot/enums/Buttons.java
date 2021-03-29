package org.allRemindMeBot.enums;

public enum Buttons {
    INFO_BUTTON("⚠ СПРАВОЧНАЯ ИНФОРМАЦИЯ ⚠"),
    LOOK_BUTTON("☝ ПРОСМОТР НАПОМИНАНИЙ ☝"),
    DELETE_BUTTON("☠ УДАЛЕНИЕ НАПОМИНАНИЙ ☠"),
    DELETE_HIST_BUTTON("❌ ОЧИСТКА ИСТОРИИ ❌");

    private final String button;

    Buttons(String button) {
        this.button = button;
    }

    public String getButton() {
        return this.button;
    }
}
