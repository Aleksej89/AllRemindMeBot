package org.allRemindMeBot.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BotUser extends AbstractEntity {

    @NonNull
    String userName;

    @NonNull
    Long userChatId;

    public BotUser(@NonNull String name, @NonNull Long chatId) {
        this.userName = name;
        this.userChatId = chatId;
    }
}
