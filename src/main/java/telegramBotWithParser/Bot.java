package telegramBotWithParser;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private GoodsParser goodsParser = new GoodsParser();

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId);

        sendMessage.setText(messageRequest(update.getMessage().getText()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "@PifAndLipaBot";
    }

    @Override
    public String getBotToken() {
        return "1083689035:AAHYBGIhiWXHY-5FIDF8dN-CyEMs5G4Ibus";
    }

    //Обработка сообщения.
    public String messageRequest(String msg) {
        if (msg.contains("Hi")
                || msg.contains("Привет")
                || msg.contains("Hello")) {
            return "Привет! Начнём искать еду для Пифа или Липы?";
        }
        if(msg.contains("Липы")) {
            return getCatProducts();
        }
        return "Это что за покемун?";
    }

    public String getCatProducts() {
        return goodsParser.getProduct();
    }
}
