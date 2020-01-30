package telegramBotWithParser;

public class Cat {
    private final String FILTER_PRO_PLAN_MORE_THEN_4_KG = "https://goods.ru/catalog/suhie-korma-dlya-koshek/brand-pro-plan/#?filters%5B0%5D%5Bname%5D=B292C00B0AD3492E4150FE3EFB4B48BD&filters%5B0%5D%5Bvalue%5D=4%3B8";
    private final String FILTER_ONLY_PRO_PLAN = "https://goods.ru/catalog/suhie-korma-dlya-koshek/brand-pro-plan/";



    public String selectUrl(String operation) {
        String a = "";
        switch (operation) {
            case "Полный список" :
                a = FILTER_ONLY_PRO_PLAN;
                break;
            case "Сортированный список" :
                a = FILTER_PRO_PLAN_MORE_THEN_4_KG;
                break;
            default:
                a = "Не нашел нужное значение.";
        }
        return a;
    }


























}
