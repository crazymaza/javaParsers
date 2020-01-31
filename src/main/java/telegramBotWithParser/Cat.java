package telegramBotWithParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

public class Cat extends Bot{
    private final String FILTER_PRO_PLAN_MORE_THEN_4_KG = "https://goods.ru/catalog/suhie-korma-dlya-koshek/brand-pro-plan/#?filters%5B0%5D%5Bname%5D=B292C00B0AD3492E4150FE3EFB4B48BD&filters%5B0%5D%5Bvalue%5D=4%3B8";
    private final String FILTER_ONLY_PRO_PLAN = "https://goods.ru/catalog/suhie-korma-dlya-koshek/brand-pro-plan/";
    private Document document;
    private GoodsParser goodsParser = new GoodsParser();
    private long chatId;

//    public String selectUrl(String operation) {
//        String a = "";
//        switch (operation) {
//            case "Полный список" :
//                a = FILTER_ONLY_PRO_PLAN;
//                break;
//            case "Сортированный список" :
//                a = FILTER_PRO_PLAN_MORE_THEN_4_KG;
//                break;
//            default:
//                a = "Не нашел нужное значение.";
//        }
//        return a;
//    }

    public Cat(String msg) {
        connect(msg);
    }

    public void connect(String msg) {
        if (msg.contains("More than 4 kg")) {
            try {
                document = Jsoup.connect(FILTER_PRO_PLAN_MORE_THEN_4_KG).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (msg.contains("Pro Plan")) {
            try {
                document = Jsoup.connect(FILTER_ONLY_PRO_PLAN).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getCatProducts() {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId).enableMarkdown(true);

        Elements elements = document.getElementsByClass("favoritePrice");
        Elements elements1 = document.getElementsByClass("card-prod");
//        List<String> p = this.goodsParser.getPrice();
//        List<String> t = this.goodsParser.getTitle();

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






















}
