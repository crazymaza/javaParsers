import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabrParser {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://habr.com/ru/")
                    .get();
            Map<String, String> titleAndLinkUrlMap = getTitleAndLinkURL(getAllHeader(doc));
            createAndWriteHTMLFile(titleAndLinkUrlMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Element> getAllHeader(Document doc) {
        return doc.select(".post__title");
    }

    private static Map<String, String> getTitleAndLinkURL(List<Element> elementList) {
        Map<String, String> titleAndLinkMap = new HashMap<>();
        elementList.forEach(element -> {
            titleAndLinkMap.put(element.text(), element.select("a").attr("href"));
        });
        return titleAndLinkMap;
    }

    private static void createAndWriteHTMLFile(Map<String, String> titleAndLinkUrlMap) {
        File file = new File("index.html");
        try {
            FileWriter fw = new FileWriter(file);
            fw.append(
                    "<!doctype html>" +
                            "<html lang=\"en\">" +
                            "<head>" +
                            "<meta charset=\"UTF-8\">" +
                            "<meta name=\"viewport\"" +
                            "content=\"width=device-width, user-scalable=no, " +
                            "initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">" +
                            "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">" +
                            "<title>Document</title>" +
                            "</head>" +
                            "<body>"
            );
            for (Map.Entry<String, String> string : titleAndLinkUrlMap.entrySet()) {
                fw.append(String.format(
                        "<a href=\"%s\" target = \"_blank\">%s</a><br>", string.getValue(), string.getKey()
                ));
            }
            fw.append("</body></html>");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
