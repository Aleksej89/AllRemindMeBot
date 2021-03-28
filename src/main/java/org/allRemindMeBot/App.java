package org.allRemindMeBot;

import lombok.extern.java.Log;
import org.allRemindMeBot.bot.services.ApplicationSender;
import org.allRemindMeBot.bot.services.MessageReceiver;
import org.allRemindMeBot.bot.services.MessageSender;
import org.allRemindMeBot.enums.AppCounters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class App {
    private static MessageReceiver messageReceiver;
    private static ApplicationSender applicationSender;
    private static MessageSender messageSender;

    public App(MessageReceiver messageReceiver, ApplicationSender applicationSender, MessageSender messageSender) {
        App.messageReceiver = messageReceiver;
        App.applicationSender = applicationSender;
        App.messageSender = messageSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info("[STARTED] App was started.");

        Thread appSender = new Thread(applicationSender);
        appSender.setDaemon(true);
        appSender.setName("AppSender");
        appSender.setPriority(AppCounters.SENDER_PRIORITY_COUNTER.getCounter());
        appSender.start();
        log.info("[STARTED] AppSender. Thread started.");

        Thread msgSender = new Thread(messageSender);
        msgSender.setDaemon(true);
        msgSender.setName("MsgSender");
        msgSender.setPriority(AppCounters.SENDER_PRIORITY_COUNTER.getCounter());
        msgSender.start();
        log.info("[STARTED] MsgSender. Thread started.");

        Thread receiver = new Thread(messageReceiver);
        receiver.setDaemon(true);
        receiver.setName("MsgReceiver");
        receiver.setPriority(AppCounters.RECEIVER_PRIORITY_COUNTER.getCounter());
        receiver.start();
        log.info("[STARTED] MsgReceiver. Thread started.");
    }
}
