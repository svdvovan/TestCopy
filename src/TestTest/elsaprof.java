package TestTest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import  java.awt.Dimension;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.io.*;
import java.util.Iterator;


public class elsaprof {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet1 = wb.createSheet("1лист");
        FileOutputStream fileOut = new FileOutputStream("S:/ProjectJava/elsaprof/book.xls");

        try {
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();


        }

        //    System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/technoschock/cert/acryl-groupru.crt.jks");

//        File input = new File("S:/ProjectJava/technoschock/moika2.htm");
//        Document doc1 = Jsoup.parse(input, "UTF-8");

String Hostname = "store.elsaprof.ru";
        JFrame frame = new JFrame("Идет парсинг сайта " + Hostname);

    //   String Path = "http://store.elsaprof.ru/product-category/gel-laki/odnofaznye/";
        String Path = "http://store.elsaprof.ru/product-category/aksessuary/pribory/";

        //    String Path = "https://technoschock.ru/catalog/roboty_dlya_doma/";
        // String Path = "https://technoschock.ru/catalog/moyki_dlya_kukhni/";

///https://technoschock.ru/catalog/moyki_dlya_kukhni/?n=ajaxpages_gmci&n=Y&n=ajaxpages_gmci&PAGEN_1=1
        //прокси http://spys.one/


        int Page = 1;
        int LastPage = 2;

        for (int count = 1; count <= LastPage; count++) {

            FileInputStream inp = new FileInputStream(new File("S:/ProjectJava/elsaprof/book.xls"));
            Workbook wb2 = WorkbookFactory.create(inp);
            Sheet sheet = wb2.getSheetAt(0);
                     Path = "http://store.elsaprof.ru/product-category/aksessuary/pribory/page/" + Page;

        //    Path = "http://store.elsaprof.ru/product-category/gel-laki/odnofaznye/page/" + Page;

            //    System.out.println(Path);
//            System.setProperty("https.proxyHost", "103.19.81.76");
//            System.setProperty("https.proxyPort", "3128");
//            System.setProperty("https.proxyHost", "103.69.220.13");
//            System.setProperty("https.proxyPort", "8080");
            try {
                Document doc1 = (Document) Jsoup.connect(Path).get();


                JProgressBar progressBar = new JProgressBar();
                progressBar.setIndeterminate(true);
                progressBar.setStringPainted(true);
                String DF = Integer.toString(Page);
                progressBar.setString(DF + " страница");
                progressBar.setValue(Page);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(progressBar);
                frame.setPreferredSize(new Dimension(500, 100));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                // Elements links1 = doc1.getElementsByClass("more").select("a[class=more]");
                Elements links1 = doc1.getElementsByClass("mpcth-post-thumbnail");
                //    String Category = doc1.getElementsByClass("title-category").select("h1").text();


                int y = 0;


                for (Element link1 : links1) {
                    String addressUrl = (links1.get(y).select("a[href]").attr("abs:href"));
                    System.out.println(addressUrl);


//                Document doc2 = Jsoup.connect(addressUrl)
////                        .timeout(50000)
////                        //.ignoreHttpErrors(true)
////                        .ignoreContentType(true)
////                        .followRedirects(true)
////                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
////                        .get();


                    Document doc2 = Jsoup.connect(addressUrl).get();
                    String NameProduct5 = doc2.getElementsByTag("h1").text();

                    // String Price = doc2.getElementsByClass("autocalc-product-price").text();
                    String Price = "0,01";

                    // String MainImage = "https://technoschock.ru" + doc2.getElementsByClass("js_picture_glass genimage").attr("src");
                    // String NameProduct5 = doc2.getElementsByTag("h1").text();
                    String Kod = doc2.getElementsByClass("mpcth-lightbox mpcth-lightbox-type-image").first().attr("title");
                    //    Element DD = doc2.getElementsByClass("contentinner").first();

                    Element DD = doc2.getElementsByClass("panel entry-content").select("p").first();
                    String Description = DD.toString();
                    String Category = doc2.getElementsByClass("posted_in").select("a").text();
                    System.out.println(Kod);

                    System.out.println(Category);

                    //   System.out.println(DD);
                    //   System.out.println(Kod);
                    //            System.out.println(MainImage);
                    System.out.println(Description);


                    String NameProduct4 = NameProduct5.replace("/", "");
                    String NameProduct3 = NameProduct4.replace("\\", "");
                    String NameProduct2 = NameProduct3.replace("*", "x");
                    String NameProduct = NameProduct2.replace("\"", "");
                    System.out.println(NameProduct);


                    int rowCount = sheet.getLastRowNum();
//                Row row = sheet.createRow(y);
                    Row row = sheet.createRow(++rowCount);


                    Cell cell = row.createCell(0);
                    cell.setCellValue(Kod);

                    Cell cell13 = row.createCell(1);
                    cell13.setCellValue(Category);

                    Cell cell1 = row.createCell(2);
                    cell1.setCellValue(NameProduct);

                    System.out.println(Price);

                    Cell cell2 = row.createCell(3);
                    cell2.setCellValue(Price);

                    Cell cell5 = row.createCell(4);
                    cell5.setCellValue(Description);

//
//                Elements table = doc2.select("table");
//                //        Elements row1 = table.select("tr");
//                Iterator<Element> ite = table.select("td").iterator();
//                Elements row2 = table.select("td");

//                int y2 = 8;
//                for (Element rows : row2) {
//
//                    // System.out.println(ite.next().text() + " ");
//                    String Har = ite.next().text();
//                    System.out.print(Har);
//                    Cell cell10 = row.createCell(y2);
//                    cell10.setCellValue(Har);
//                    y2++;
//                }

                    System.out.println();
                    Elements Image = doc2.getElementsByClass("mpcth-lightbox mpcth-lightbox-type-image");
                    int Img = 0;
                    int y3 = 20;
                    for (Element Images : Image) {
                        //    String FileName1 = "https://technoschock.ru" + Image.get(Img).select("img").attr("data-bigimage");
                        String FileName = Image.get(Img).attr("href");
                        //Element FileName1 = Image.get(Img);
                        //  String FileName = FileName1.toString();

                        System.out.println(FileName);

                        Cell cell11 = row.createCell(y3);
                        cell11.setCellValue(FileName);
                        y3++;

                        //                File f = new File(FileName);

//                    try {
//                        // опирование фото
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
                try {

                    FileOutputStream fileOut1 = new FileOutputStream("S:/ProjectJava/elsaprof/book.xls");

                    //     OutputStream fileOut = new FileOutputStream("O:/YandexDisk/kwork/technoshok/" + Page + "book.xls", false);

                    wb2.write(fileOut1);
                    fileOut1.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Page++;
            } catch (IOException e) {
                frame.dispose();

            }

            }

        frame.dispose();
    }

}
