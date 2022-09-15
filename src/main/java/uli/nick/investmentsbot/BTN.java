package uli.nick.investmentsbot;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.KeyboardButton;

public enum BTN {

    START("START"),
    INVESTMENTS("\uD83D\uDDA5 Инвестиции"),
    WALLET("\uD83D\uDCB3 Кошелёк"),
    SETTINGS("⚙️ Настройки"),
    PARTNERS("\uD83D\uDC54 Партнёрам"),
    CALCULATOR("\uD83D\uDCE0 Калькулятор"),
    LEARNING("\uD83D\uDDD3 Обучение"),
    INVEST("➕"),
    FLUSH("➖"),
    NOTICE("Уведомления"),
    OPERATIONS("Операции"),
    INFORMATION("Информация"),
    AGREEMENT("Соглашение");

    final String VAL;
    final KeyboardButton BTN;
    final InlineKeyboardButton CBQ;

    BTN(String nameButton) {
        this.VAL = nameButton;
        this.BTN = new KeyboardButton(VAL);
        this.CBQ = new InlineKeyboardButton(VAL).callbackData(VAL);
    }

    static String conform(String val) {
        for (BTN btn : values()) {
            if (btn.VAL.contains(val)) {
                return btn.name();
            }
        }
        return START.name();
    }
}
