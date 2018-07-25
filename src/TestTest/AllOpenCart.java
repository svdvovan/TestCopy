package TestTest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Iterator;

/**
 * Created by SretenskyVD on 04.07.2018.
 */
public class AllOpenCart {
    public static void main(String[] args) throws IOException {
        String Path = "http://www.cmsmagazine.ru/catalogue/opencart-cms/works/";

        int Page = 1;
           for (int count = 0; count <= 1; count++) {
            Path = "http://www.cmsmagazine.ru/catalogue/opencart-cms/works/?pn=" + Page;
               System.out.println(Path);
            //       String Path = "http://www.cmsmagazine.ru/catalogue/opencart-cms/works/?pn=all";


            Document doc1 = (Document) Jsoup.connect(Path).get();

            Elements links1 = doc1.getElementsByClass("fl expIconYesNo");

               int y = 0;
               for (Element link1 : links1) {
                   String addressUrl = (links1.get(y).select("a").attr("_href"));
           //        System.out.println(addressUrl);

                   try{
                   Document doc2 = Jsoup.connect(addressUrl).get();

                   String Ssil = doc2.select("a").attr("href");
                   System.out.println(Ssil);}
                   catch (IOException e){

                   }



                   y++;
               }
               Page++;
        }

        }

        }


