package TestTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by SretenskyVD on 28.06.2018.
 */
//Спарсить товары с нескольких сайтов. Формат CSV. Что должно быть: 1- фото 2- описание и технические характеристики 3- наименование 4- артикул 5- цена 6- категория (полная ирархия)
// https://master-instrument.ru - здесь все кроме фирм JTC, KING TONY, mactak http://www.jtcrussia.ru http://car-tool.ru Скрыть

public class TestDrive2 {
    public static void main(String[] args) throws IOException {



        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/certTestDrive/bibihouseru.crt.jks");
        String Path = "https://www.bibihouse.ru/catalog/servizy_i_nabory/";




        int Page = 1;
        for (int count = 0; count <= 23; count++) {
            Path = "https://www.bibihouse.ru/catalog/servizy_i_nabory/?PAGEN_17="+Page;
            Document doc1 = (Document) Jsoup.connect(Path).get();
            System.out.println(Path);
            Elements links1 = doc1.getElementsByClass("products__name");

            int y = 0;
            for (Element link1 : links1) {
                String addressUrl = (links1.get(y).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl);

                Document doc2 = Jsoup.connect(addressUrl).get();

                Elements Unit = doc2.getElementsByClass("characteristics-list__label");
                Elements Value = doc2.getElementsByClass("characteristics-list__value");

                int Attr=0;
                for (Element Units: Unit) {

                    System.out.print(Unit.get(Attr).text() + "|" + Value.get(Attr).text() + "\n");

                    Attr++;
                }



                y++;


            }
            Page++;
        }

    }
}
