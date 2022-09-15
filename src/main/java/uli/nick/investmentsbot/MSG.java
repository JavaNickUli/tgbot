package uli.nick.investmentsbot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.function.Function;

public enum MSG {

    START(update -> {
        String message = "©️Главное меню";

        return new SendMessage(update.message().chat().id(), message)
                .replyMarkup(new ReplyKeyboardMarkup
                        (BTN.INVESTMENTS.BTN, BTN.WALLET.BTN)
                        .addRow(BTN.SETTINGS.BTN, BTN.PARTNERS.BTN)
                        .addRow(BTN.CALCULATOR.BTN, BTN.LEARNING.BTN)
                        .resizeKeyboard(true));

    }),

    INVESTMENTS(update -> {
        DataAccount account = new DataAccount(update.message().chat().id());

        String message = "▪️ Открывайте свой вклад ниже," +
                " а после получайте прибыль с него и собирайте ее в данном разделе:\n" +
                "\n" +
                "\uD83D\uDCE0 Процент от вклада: 4.2%\n" +
                "⏱ Время доходности: 24 часа\n" +
                "\uD83D\uDCC6 Срок вклада: Пожизненно\n" +
                "\n" +
                String.format("\uD83D\uDCB3 Ваш вклад: %.2f₽\n", account.deposit) +
                String.format("\uD83D\uDCB5 Накопление: %.2f₽\n", account.savings) +
                "\n" +
                "\uD83E\uDDED Время до сбора средств: " + account.remainingTime;

        return new SendMessage(update.message().chat().id(), message)
                .replyMarkup(new InlineKeyboardMarkup().addRow(BTN.INVEST.CBQ, BTN.FLUSH.CBQ));
    }),

    WALLET(update -> {
        DataAccount account = new DataAccount(update.message().chat().id());

        String message = String.format("\uD83E\uDD16 Ваш ID: %d\n", account.userId) +
                String.format("\uD83D\uDCC6 Профиль создан: %s\n", account.creationTime) +
                String.format("\uD83D\uDCB3 Ваш баланс: %.2f₽\n", account.balance) +
                String.format("\uD83D\uDC65 Партнеров: %d чел.", account.partners);

        return new SendMessage(update.message().chat().id(), message);
    }),

    SETTINGS(update -> {
        String message = "▪️ Вы попали в раздел настройки бота, здесь вы можете посмотреть статистику," +
                " а также узнать нужную информацию или отключить новые уведомления в нашем боте " +
                "\n" +
                String.format("\uD83C\uDF10 Дней работаем: %d\n", 0) +
                String.format("▪️ Всего инвесторов: %d\n", 0) +
                String.format("▪️ Новых за 24 часа: %d\n", 0) +
                String.format("▪️ Онлайн: %d\n", 0);

        return new SendMessage(update.message().chat().id(), message)
                .replyMarkup(new InlineKeyboardMarkup().addRow(BTN.NOTICE.CBQ, BTN.OPERATIONS.CBQ)
                        .addRow(BTN.INFORMATION.CBQ, BTN.AGREEMENT.CBQ));
    }),

    PARTNERS(update -> {
        String message = "▪️ PARTNERS";

        return new SendMessage(update.message().chat().id(), message);
    }),

    CALCULATOR(update -> {
        String message = "▪️ CALCULATOR";

        return new SendMessage(update.message().chat().id(), message);
    }),

    LEARNING(update -> {
        String message = "▪️ LEARNING";

        return new SendMessage(update.message().chat().id(), message);
    });

    private final Function<Update, SendMessage> function;

    MSG(Function<Update, SendMessage> function) {
        this.function = function;
    }


    public SendMessage apply(Update update) {
        return function.apply(update);
    }
}

