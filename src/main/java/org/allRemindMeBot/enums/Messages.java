package org.allRemindMeBot.enums;

public enum Messages {
    LOOK_EMPTY_MSG(Emoji.INFO_EMOJI.getEmojiStr() + "Список Ваших напоминаний пуст" + Emoji.INFO_EMOJI.getEmojiStr()),
    LOOK_ALL_MSG(Emoji.LISTS_EMOJI.getEmojiStr() + "Список Ваших напоминаний" + Emoji.LISTS_EMOJI.getEmojiStr()),
    ADD_SUCCESS_MSG(Emoji.SUCCESS_EMOJI.getEmojiStr() + "Напоминание успешно добавлено" + Emoji.SUCCESS_EMOJI.getEmojiStr()),
    DELETE_SUCCESS_MSG(Emoji.DELETE_SUCCESS_EMOJI.getEmojiStr() + "Напоминание успешно удалено" + Emoji.DELETE_SUCCESS_EMOJI.getEmojiStr()),
    DELETE_HIST_SUCCESS_MSG(Emoji.DELETE_SUCCESS_EMOJI.getEmojiStr() + "История напоминаний очищена" + Emoji.DELETE_SUCCESS_EMOJI.getEmojiStr()),
    DELETE_EMPTY_MSG(Emoji.DELETE_EMPTY_EMOJI.getEmojiStr() + "Заявка была уже удалена" + Emoji.DELETE_EMPTY_EMOJI.getEmojiStr()),
    ERROR_MSG(Emoji.ERROR_EMOJI.getEmojiStr() + "Что-то пошло не так - попробуйте еще раз, внимательно прочитав справочную информацию" + Emoji.ERROR_EMOJI.getEmojiStr()),
    INFO_MSG(Emoji.HART_EMOJI.getEmojiStr() + "Уважаемый пользователь!" + Emoji.HART_EMOJI.getEmojiStr() + "\n\n" +
            "Напоминания вводится в текстовом формате с указанием даты и времени в будущем, все Emoji очищаются:\n" +
            "Форматы даты: ДД.ММ, ДД.ММ.ГГГГ\n" +
            "Формат времени: ЧЧ: ММ\n" +
            "Например: 24.03 12:00 позвонить маме.\n"),
    INFO_ALL_MSG(Emoji.REF_INFO_EMOJI.getEmojiStr() + "Уважаемый пользователь!" + Emoji.REF_INFO_EMOJI.getEmojiStr() + "\n\n" +
            "Для создания напоминания Вам достаточно ввести его текст в чат и отправить боту.\n" +
            "Напоминания вводится в текстовом формате с указанием даты и времени в будущем, все Emoji очищаются:\n" +
            "Форматы даты: ДД.ММ, ДД.ММ.ГГГГ\n" +
            "Формат времени: ЧЧ: ММ\n" +
            "Например: 24.03 12:00 позвонить маме.\n\n" +
            Emoji.LISTS_EMOJI.getEmojiStr() + "В меню \"ПРОСМОТР НАПОМИНАНИЙ\" Вы можете ознакомиться с предстоящими напоминаниями.\n\n" +
            Emoji.LISTS_EMOJI.getEmojiStr() + "В меню \"УДАЛЕНИЕ НАПОМИНАНИЙ\" Вы можете ознакомиться со списком предстоящих напоминаний для их удаления.\n" +
            "Удаление напоминаний производится по средствам вызова соответствующего меню и кликом в чате на интересующее напоминание.\n\n" +
            Emoji.LISTS_EMOJI.getEmojiStr() + "В меню \"ОЧИСТКА ИСТОРИИ\" Вы можете удалить Вашу базу устаревших напоминаний.\n");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
