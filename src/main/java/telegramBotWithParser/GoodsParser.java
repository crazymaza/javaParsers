package telegramBotWithParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsParser {
    private Document document;
    private String goodsList = "";

    public GoodsParser() {
        connect();
    }


    private void connect() {
        try {
            document = Jsoup.connect("").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Получаем название товара.
    public List<String> getTitle() {
        List<String> titleList = new ArrayList<>();
        Elements elements = document.getElementsByClass("card-prod--title");
        elements.forEach(element -> {
            titleList.add(element.text());
        });

        titleList.stream().filter(item -> {
            return item.length() > 1;
        });
        return titleList;
    }

    //Получаем цену товара.
    public List<String> getPrice() {
        List<String> priceList = new ArrayList<>();
        Elements elements = document.getElementsByClass("favoritePrice");
        elements.forEach(element -> {
            priceList.add(element.text());
        });
        return priceList;
    }

    public void getAllCards() {
        Map<String, String> df = new HashMap<>();
        Elements elements = document.getElementsByClass("card-prod");
        elements.forEach(element -> {

        });
    }

    public static void main(String[] args) {
//        GoodsParser gp = new GoodsParser();
//        String sdd = gp.getProduct();
//       gp int answerChar = 0;
//        int count = sdd.length() % 2000;
//        for (int i = 0; i < count; i++) {
//            System.err.println("i = " + i);
//            System.out.println(sdd.substring(answerChar, sdd.length() / count));
//        }

//        sdd
    }
}


