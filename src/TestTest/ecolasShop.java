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
 * Created by SretenskyVD on 23.07.2018.
 * keytool -import -v -file F:/Projects/TestCopy/cert/ecolashopru.crt -keystore F:/Projects/TestCopy/cert/ecolashopru.crt.jks -storepass drowssap
 */
public class ecolasShop {
    public static void main(String[] args) throws IOException {


        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("1лист");

            System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/ecolasShop/cert/ecolashopruru.crt.jks");
       // System.setProperty("javax.net.ssl.trustStore", "F:/Projects/TestCopy/cert/ecolashopru.crt.jks");
    //    String Path = "https://ecolashop.ru/lamps/";
     //   String Path = "https://ecolashop.ru/spotlight/";
     //   String Path =  "https://ecolashop.ru/lights/?limit=590";
      //  String Path =  "https://ecolashop.ru/lights/";
      //  String Path = "https://ecolashop.ru/led-stripe/";
//     String Path =  "https://ecolashop.ru/adapters/?limit=100";
     //  String Path = "https://ecolashop.ru/lampholders/?limit=30";
        String Path = "https://ecolashop.ru/led-panels/?limit=30";

            int Page = 1;

            for (int count = 1; count <= 1; count++) {
            //    Path = "https://ecolashop.ru/lights/?page=" + Page;
            //    Path = "https://ecolashop.ru/lamps/?page=" + Page;
            //    Path = "https://ecolashop.ru/spotlight/?page=" + Page;
           //     Path = "https://ecolashop.ru/led-stripe/?page=" + Page;
           //     Path = "https://ecolashop.ru/adapters/?limit=100";
         //       Path = "https://ecolashop.ru/lampholders/?limit=30";
                Path = "https://ecolashop.ru/led-panels/?limit=30";

                Document doc1 = (Document) Jsoup.connect(Path).get();
                Elements links1 = doc1.getElementsByClass("product-layout product-list col-xs-12");
                String Category = doc1.getElementsByClass("title-category").select("h1").text();


                int y = 0;


                for (Element link1 : links1) {
                    String addressUrl = (links1.get(y).select("a[href]").attr("abs:href"));
                    System.out.println(addressUrl);

                    Document doc2 = Jsoup.connect(addressUrl).get();

                    String Price = doc2.getElementsByClass("autocalc-product-price").text();
                    Elements Image = doc2.getElementsByClass("thumbnail");
                    String NameProduct5 = doc2.getElementsByClass("title-category").select("h1").text();
                    String Kod = doc2.getElementsByClass("list-unstyled product-view").select("span").text();
                    String Description = doc2.getElementsByClass("tab-pane active").text();

                    System.out.println(Category);


                    String NameProduct4 = NameProduct5.replace("/", "");
                    String NameProduct3 = NameProduct4.replace("\\", "");
                    String NameProduct2 = NameProduct3.replace("*", "x");
                    String NameProduct = NameProduct2.replace("\"", "");
                    System.out.println(NameProduct);

                    Row row = sheet.createRow(y);

                    Cell cell = row.createCell(0);
                    cell.setCellValue(Kod);

                    Cell cell1 = row.createCell(3);
                    cell1.setCellValue(NameProduct);

                    System.out.println(Price);

                    Cell cell2 = row.createCell(4);
                    cell2.setCellValue(Price);

                    Cell cell5 = row.createCell(5);
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


                    int Img = 0;
                    int y3 = 27;
                    for (Element Images : Image) {
                        String FileName = Image.get(Img).select("img").attr("src");
                        System.out.println(FileName);

                        Cell cell11 = row.createCell(y3);
                        cell11.setCellValue(FileName);
                        y3++;

                        File f = new File(FileName);

                        try {
                            //Копирование фото
                       //     String FILENAME = "F:/Projects/TestCopy/foto/" + Category + "/" + NameProduct + "/" + f.getName();
                            String FILENAME = "S:/ProjectJava/ecolasShop/foto/" + Category + "/" + NameProduct + "/" + f.getName();
                            String SvDPDFURL = FileName;
                            File file = new File(FILENAME);

                            URL url = new URL(SvDPDFURL);
                            FileUtils.copyURLToFile(url, file);
                        } catch (java.io.FileNotFoundException e) {
                            System.out.println("не найден путь ");
                        }


                        Img++;
                    }
                    System.out.println();

                    y++;

                }



         OutputStream fileOut = new FileOutputStream("S:/ProjectJava/ecolasShop/"+ Page+ "book.xls", true) ;
          try { wb.write(fileOut);
              fileOut.close();
        }


        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();

        }


                Page++;
            }
               }

    }



