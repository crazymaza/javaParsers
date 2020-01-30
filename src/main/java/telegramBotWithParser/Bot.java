package telegramBotWithParser;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private long chatId;
    private GoodsParser goodsParser = new GoodsParser();
    private ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId);

        //Устанавливаем клавиатуру для пользователя.
        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(keyboard);

        try {
            sendMessage.setText(getMessage(text));
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

    public String getCatProducts() {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId).enableMarkdown(true);

        List<String> p = goodsParser.getPrice();
        List<String> t = goodsParser.getTitle();

        String df = "";
        int dss = p.size() / 2;

        for (int i = 0; i < p.size(); i++) {
            df += String.format("%s : *%s*\n", t.get(i), p.get(i));
            df += "==============\n";
            if (i == dss || i == p.size() - 1) {
                try {
                    sendMessage.setText(df);
                    execute(sendMessage);
                    df = "";
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Anything else?";
    }

    //Делаем клавиатуру и обработку сообщений через неё.
    public String getMessage(String msg) {
        List<KeyboardRow> buttons = new ArrayList<>();

        //Делаем 2 ряда кнопок.
        KeyboardRow zeroRow = new KeyboardRow();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();

        //Параметры клавиатуры.
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);

        if (msg.contains("/start")) {
            firstRow.add("Меню");
            buttons.add(firstRow);
            keyboard.setKeyboard(buttons);
            return "Привет! Я помогу найти зоотовары для Пифа и Липы.\n Чтобы начать наберите \"Привет\" или \"Меню\".";
        }
        if (msg.contains("Меню")) {
            buttons.clear();
            firstRow.clear();
            firstRow.add("Для Пифа");
            secondRow.add("Для Липы");
            buttons.add(firstRow);
            buttons.add(secondRow);
            keyboard.setKeyboard(buttons);
            return "Для кого ищем?";
        }
        if (msg.contains("Для Липы")) {
            return getCatProducts();
        }
        return "Это что за покемун?";
    }

}
