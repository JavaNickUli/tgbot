package uli.nick.investmentsbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Properties;

public class DataAccount {
    static String PATH_ACCOUNTS_DB = "C:\\projects\\tgbot\\AccountsDB\\";

    long userId;
    double deposit;
    double savings;
    double balance;
    int partners;

    LocalDateTime creationTime = LocalDateTime.now();
    Time remainingTime = Time.valueOf(LocalTime.now());

    DataAccount(long UserId) {

        File file = new File(PATH_ACCOUNTS_DB + UserId + ".db");
        try {
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                writer.write(
                        String.format("""
                                userId = %d
                                deposit = 0
                                savings = 0
                                balance = 0
                                partners = 0
                                creationTime = %h
                                remainingTime = 00:00:00
                                """, UserId, LocalDateTime.now())
                );
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(PATH_ACCOUNTS_DB + UserId + ".db"));
            NumberFormat format = NumberFormat.getInstance(Locale.US);

            this.userId = format.parse(properties.getProperty("userId")).longValue();
            this.deposit = format.parse(properties.getProperty("deposit")).doubleValue();
            this.savings = format.parse(properties.getProperty("savings")).doubleValue();
            this.balance = format.parse(properties.getProperty("balance")).doubleValue();
            this.partners = format.parse(properties.getProperty("partners")).intValue();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
