package uli.nick.investmentsbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

public class InvestmentsBot {

    TelegramBot bot = new TelegramBot("");

    public InvestmentsBot() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::updateReceived);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        logOut("Bot started!");
    }

    public static void main(String[] args) {
        new InvestmentsBot();
    }

    public void updateReceived(Update update) {
        logOut("Event from user!");

        if (update.message() != null && !update.message().text().isEmpty()) {
            String command = BTN.conform(update.message().text());
            logOut("Command: " + command);

            bot.execute(MSG.valueOf(command).apply(update));
        } else if (update.callbackQuery() != null){
            String query = BTN.conform(update.callbackQuery().data());
            logOut("Query: " + query);

            bot.execute(CBQ.valueOf(query).apply(update));
            if (CBQ.valueOf(query).equals(CBQ.FLUSH)) {
                bot.execute(CBQ.FLUSH.deleteCBQ(update));
            }
        }
    }

    void logOut(String message) {
        System.out.println(message);
    }
}
