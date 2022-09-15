package uli.nick.investmentsbot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.DeleteMessage;

import java.util.function.Function;

public enum CBQ {

    START(update -> new AnswerCallbackQuery(update.callbackQuery().id())
            .text("Restart")
            .showAlert(true)),

    INVEST(update -> new AnswerCallbackQuery(update.callbackQuery().id())
            .text("\uD83D\uDEABПополните баланс, минимальная сумма для инвестиции: 100.0₽")
            .showAlert(true)),

    FLUSH(update -> {
        DataAccount account = new DataAccount(update.callbackQuery().message().chat().id());
        if (account.savings <= 0) {
            return new AnswerCallbackQuery(update.callbackQuery().id())
                    .text("\uD83D\uDEABМинимальная сумма сбора: 1.0₽")
                    .showAlert(true);
        } else {
            account.balance += account.savings;
            account.savings = 0;
            return new AnswerCallbackQuery(update.callbackQuery().id())
                    .text("✅Накопления успешно собраны!")
                    .showAlert(true);
        }
    }),

    NOTICE(update -> new AnswerCallbackQuery(update.callbackQuery().id())
            .text("Notice")
            .showAlert(true)),

    OPERATIONS(update -> new AnswerCallbackQuery(update.callbackQuery().id())
            .text("Operations")
            .showAlert(true)),

    INFORMATION(update -> new AnswerCallbackQuery(update.callbackQuery().id())
            .text("Information")
            .showAlert(true)),

    AGREEMENT(update -> new AnswerCallbackQuery(update.callbackQuery().id())
            .text("Agreement")
            .showAlert(true));

    private final Function<Update, AnswerCallbackQuery> function;

    CBQ(Function<Update, AnswerCallbackQuery> function) {
        this.function = function;
    }

    public AnswerCallbackQuery apply(Update update) {
        return function.apply(update);
    }

    public DeleteMessage deleteCBQ(Update update) {
        return new DeleteMessage(update.callbackQuery().from().id(), update.callbackQuery().message().messageId());
    }
}
