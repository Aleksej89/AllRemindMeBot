package org.allRemindMeBot.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BotUserApplication extends AbstractEntity {

    @NonNull
    Long chatId;

    @NonNull
    private String applicationText;

    @NonNull
    private Date dateApplication;

    public BotUserApplication(@NonNull Long chatId, @NonNull String text, @NonNull Date date) {
        this.chatId = chatId;
        this.applicationText = text;
        this.dateApplication = date;
    }
}
