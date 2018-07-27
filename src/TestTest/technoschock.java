package TestTest;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Iterator;
/**
 * Created by SretenskyVD on 24.07.2018.
 */

//keytool -import -v -file S:/ProjectJava/technoschock/cert/acryl-groupru.crt -keystore S:/ProjectJava/technoschock/cert/acryl-groupru.crt.jks -storepass drowssap
    public class technoschock {


    public static void main(String[] args) throws IOException {


        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("1лист");

        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/technoschock/cert/acryl-groupru.crt.jks");

//        File input = new File("S:/ProjectJava/technoschock/moika2.htm");
//        Document doc1 = Jsoup.parse(input, "UTF-8");




        String Path = "https://technoschock.ru/catalog/moyki_dlya_kukhni/?n=ajaxpages_gmci&n=Y&n=ajaxpages_gmci&PAGEN_1=3";
    //    String Path = "https://technoschock.ru/catalog/roboty_dlya_doma/";
       // String Path = "https://technoschock.ru/catalog/moyki_dlya_kukhni/";

///https://technoschock.ru/catalog/moyki_dlya_kukhni/?n=ajaxpages_gmci&n=Y&n=ajaxpages_gmci&PAGEN_1=1
        //прокси http://spys.one/
        int Page = 2;

        for (int count = 1; count <= 23; count++) {
          //  Path = "https://technoschock.ru/catalog/moyki_dlya_kukhni/?n=ajaxpages_gmci&n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
          //  Path = "https://technoschock.ru/catalog/kukhonnye_smesiteli/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
          //  Path = "https://technoschock.ru/catalog/dukhovye_shkafy/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
          //  Path = "https://technoschock.ru/catalog/varochnye_poverkhnosti/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
         //   Path = "https://technoschock.ru/catalog/vytyazhki/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
          //  Path = "https://technoschock.ru/catalog/mikrovolnovye_pechi/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
          //  Path = "https://technoschock.ru/catalog/posudomoechnye_mashiny/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
            Path = "https://technoschock.ru/catalog/kholodilniki/?n=Y&n=ajaxpages_gmci&PAGEN_1=" + Page;
            //    System.out.println(Path);
            System.setProperty("https.proxyHost", "103.19.81.76");
            System.setProperty("https.proxyPort", "3128");

            Document doc1 = (Document) Jsoup.connect(Path).get();
            Elements links1 = doc1.getElementsByClass("more").select("a[class=more]");
            //    String Category = doc1.getElementsByClass("title-category").select("h1").text();


            int y = 0;


            for (Element link1 : links1) {
                String addressUrl = (links1.get(y).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl);


                Document doc2 = Jsoup.connect(addressUrl)
                        .timeout(50000)
                        //.ignoreHttpErrors(true)
                        .ignoreContentType(true)
                        .followRedirects(true)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                        .get();

                String Price = doc2.getElementsByClass("autocalc-product-price").text();
                Elements Image = doc2.getElementsByClass("changeimage scrollitem");
                //   String  MainImage = doc2.getElementsByClass("js_picture_glass genimage").select("src").attr("abs:href");
                String MainImage = "https://technoschock.ru" + doc2.getElementsByClass("js_picture_glass genimage").attr("src");
                String NameProduct5 = doc2.getElementsByTag("h1").text();
                String Kod = doc2.getElementsByClass("val").last().text();
                //  String Description = doc2.getElementsByClass("contentinner").select("br").append("\\n").first().text();
                //    Element DD = doc2.getElementsByClass("contentinner").first();
                Element DD = doc2.getElementsByClass("contentinner").first();
                String Description = DD.toString();
                // String Description = doc2.getElementsByClass("contentinner").first().text();
                String Category = doc2.getElementsByClass("first selected").get(1).text();

                System.out.println(Category);

                System.out.println(DD);
                System.out.println(Kod);
                System.out.println(MainImage);
                //  System.out.println(Image);


                String NameProduct4 = NameProduct5.replace("/", "");
                String NameProduct3 = NameProduct4.replace("\\", "");
                String NameProduct2 = NameProduct3.replace("*", "x");
                String NameProduct = NameProduct2.replace("\"", "");
                System.out.println(NameProduct);

                Row row = sheet.createRow(y);

                Cell cell = row.createCell(0);
                cell.setCellValue(Kod);

                Cell cell13 = row.createCell(1);
                cell13.setCellValue(Category);

                Cell cell1 = row.createCell(2);
                cell1.setCellValue(NameProduct);

                System.out.println(Price);

//                Cell cell2 = row.createCell(3);
//                cell2.setCellValue(Price);

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(Description);


                Elements table = doc2.select("table");
                //        Elements row1 = table.select("tr");
                Iterator<Element> ite = table.select("td").iterator();
                Elements row2 = table.select("td");

                int y2 = 8;
                for (Element rows : row2) {

                    // System.out.println(ite.next().text() + " ");
                    String Har = ite.next().text();
                    System.out.print(Har);
                    Cell cell10 = row.createCell(y2);
                    cell10.setCellValue(Har);
                    y2++;
                }

                System.out.println();

                int Img = 0;
                int y3 = 40;
                for (Element Images : Image) {
                    String FileName1 = "https://technoschock.ru" + Image.get(Img).select("img").attr("data-bigimage");
                    //Element FileName1 = Image.get(Img);
                    String FileName = FileName1.toString();

                    System.out.println(FileName);

                    Cell cell11 = row.createCell(y3);
                    cell11.setCellValue(FileName);
                    y3++;

                    //                File f = new File(FileName);

//                    try {
//                        //Копирование фото
//                        //     String FILENAME = "F:/Projects/TestCopy/foto/" + Category + "/" + NameProduct + "/" + f.getName();
//                        String FILENAME = "S:/ProjectJava/technoschock/foto/" + Category + "/" + NameProduct + "/" + f.getName();
//                        String SvDPDFURL = FileName;
//                        File file = new File(FILENAME);
//
//                        URL url = new URL(SvDPDFURL);
//                        FileUtils.copyURLToFile(url, file);
//                    } catch (java.io.FileNotFoundException e) {
//                        System.out.println("не найден путь ");
//                    }


                    Img++;
                }
                System.out.println();

                y++;

            }


          //  OutputStream fileOut = new FileOutputStream("S:/ProjectJava/technoschock/" + Page + "book.xls", false);
            OutputStream fileOut = new FileOutputStream("O:/YandexDisk/kwork/technoshok/" + Page + "book.xls", false);
            try {
                wb.write(fileOut);
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            Page++;
        }
    }
}
