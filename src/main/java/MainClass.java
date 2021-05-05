import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Properties;

public class MainClass {
    public static void main(String[] args)
    {
        Properties props = System.getProperties();
        props.setProperty("java.runtime.version", "11");
        System.setProperty("java.runtime.version", "11");
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new CovIndiaResource());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
