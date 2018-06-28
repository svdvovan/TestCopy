package TestTest;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by SretenskyVD on 28.06.2018.
 */
public class TestDrive {
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
                y++;


            }
            Page++;
        }

    }
}
