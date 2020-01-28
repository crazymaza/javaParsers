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

    public GoodsParser() {
        connect();
    }

    private void connect() {
        try {
            document = Jsoup.connect("https://goods.ru/catalog/suhie-korma-dlya-koshek/brand-pro-plan/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Получаем название товара.
    public List<String> getTitle() {
        List<String> titleList = new ArrayList<>();
        Elements elements = document.getElementsByClass("ddl_product_link");
        elements.forEach(element -> {
            titleList.add(element.text());
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

    //Получаем название товара.
    public Map<String, String> getProduct() {
        Map<String, String> productList = new HashMap<>();
        Elements titles = document.getElementsByClass("card-prod--title");
        Elements prices = document.getElementsByClass("favoritePrice");

        for (int i = 0; i != prices.size(); i++) {
            productList.put(titles.get(i).text(), prices.get(i).text());
        }

        return productList;
    }

    public static void main(String[] args) {
        GoodsParser gp = new GoodsParser();

        System.out.println(gp.getTitle().get(0));
    }

}


