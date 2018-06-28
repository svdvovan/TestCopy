package TestTest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by SretenskyVD on 28.06.2018.
 */
public class Copy {


    // keytool -import -v -file S:/ProjectJava/certTestDrive/bibihouseru.crt -keystore S:/ProjectJava/certTestDrive/bibihouseru.crt.jks -storepass drowssap
    public static void main(String[] args) throws IOException {



        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/technodacha/technodacha.crt.jks");
        String Path = "https://www.technodacha.ru/catalog/silovaya_produktsiya/generatory_i_elektrostantsii/filter/brand_ref-is-yii000018/apply/";

        Document doc1 = (Document) Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("snippet__image-wrapper");
        Elements Price = doc1.getElementsByClass("price__text");

        int id = 6144;
        int Model = 7037;
        int y = 0;
        String Manuf = "STIGA";
        for (Element link1 : links1) {
            String addressUrl = (links1.get(y).select("a[class=link link_view_plain]").attr("abs:href"));
            Document doc2 = Jsoup.connect(addressUrl).get();

            Elements Description = doc2.getElementsByClass("text");
            Elements NameProduct = doc2.getElementsByClass("product__title");
            Elements Attribute = doc2.getElementsByClass("product__short-specs");
            Elements Unit = doc2.getElementsByClass("table__name");
            Elements Value = doc2.getElementsByClass("table__value");
            Elements Image = doc2.getElementsByClass("slider__image");

            String MainImage = Image.attr("src");
            File r = new File(MainImage);
            String FistImage = "data/image/auto/1/" + r.getName();


            System.out.println();
            String DesText = Description.select("[itemprop=description]").text();
            System.out.println(NameProduct.text() +"\n"+ Price.get(y).text()+"\n"+DesText+ "\n");
            //         System.out.print("Газонокосилки\n" +
            //                  "Газонокосилки|Газонокосилки бензиновые" + ";" + id + ";"+NameProduct.text() +";" + Model+ ";;;;;;;"+Manuf +";;;" + Price.get(y).text()+ ";0;1000;6;0;0;0;0;;;;"+DesText+ ";;"+FistImage+";0;1;;;; \n  =СЦЕПИТЬ( " );
            //    System.out.print(";");



            int Attr=0;
            for (Element Units: Unit){

                System.out.print("Характеристика|" + Unit.get(Attr).text()+ "|" +Value.get(Attr).text()+ "\n" );
                //              System.out.print(" \"Характеристика|" + Unit.get(Attr).text()+ "|" +Value.get(Attr).text() + " \" &СИМВОЛ(10)& " + "\n" );
                Attr++;

            }
            //          System.out.print("\");");
            int Img=0;
            for (Element Images: Image){
                String FileName = Image.get(Img).attr("src");
                File f = new File(FileName);
                //                System.out.print(Image.get(Img).attr("src")+ ";");
                System.out.print("data/image/auto/1/" + f.getName() + ",");

                //Копирование фото
                String FILENAME = "S:/ProjectJava/TestCopy/foto/"+NameProduct.text() + "/" + f.getName();
                String SvDPDFURL = "https://www.technodacha.ru/" +FileName ;
                File file = new File(FILENAME);
                URL url = new URL(SvDPDFURL);
                FileUtils.copyURLToFile(url, file);

                //конец теста

                Img++;
            }
            System.out.println();

//data/image/auto/1
            y = y + 2;
            id++;
            Model++;
        }



    }
}
