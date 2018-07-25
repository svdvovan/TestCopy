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

/**
 * Created by SretenskyVD on 28.06.2018.
 */
public class TestDrive {
    public static void main(String[] args) throws IOException {


        String NameHost = "https://www.bibihouse.ru";
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("1лист");

        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/certTestDrive/bibihouseru.crt.jks");
        String Path = "https://www.bibihouse.ru/catalog/servizy_i_nabory/";




        int Page = 1;
    //    for (int count = 0; count <= 22; count++) {
            for (int count = 0; count <= 1; count++) {
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
                Elements Image = doc2.getElementsByClass("preview");
                String NameProduct = doc2.getElementsByClass("page-title  ").select("h1").text();

                System.out.println(NameProduct);

                Row row = sheet.createRow(y);
                Cell cell = row.createCell(0);
                cell.setCellValue(NameProduct);

                String NameProduct2 = NameProduct.replace("\"","");
                System.out.println(NameProduct2);
                String NameProduct3 = NameProduct2.replace("/","");
                System.out.println(NameProduct3);
                String NameProduct4 = NameProduct3.replace(" ","_");
                System.out.println(NameProduct4);
                String NameProduct5 = NameProduct4.replace(".","");
                System.out.println(NameProduct5);


                int Attr=0;
                for (Element Units: Unit) {
                    String  Opis = Unit.get(Attr).text() + "|" + Value.get(Attr).text();
                    System.out.print(Unit.get(Attr).text() + "|" + Value.get(Attr).text() + "\n");

                    Attr++;

                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(Opis);

                }

                int Img=0;
                for (Element Images: Image) {
                    String FileName = Image.get(Img).attr("src");
                    System.out.println(NameHost+FileName);

                    File f = new File(FileName);
                    //                System.out.print(Image.get(Img).attr("src")+ ";");
//                    System.out.print("data/image/auto/1/" + f.getName() + ",");


//try{
//                    //Копирование фото
//                    String FILENAME = "S:/ProjectJava/TestCopy/foto/"+NameProduct5+ "/" + f.getName();
//                    String SvDPDFURL = NameHost +FileName ;
//                    File file = new File(FILENAME);
//
//                    URL url = new URL(SvDPDFURL);
//                    FileUtils.copyURLToFile(url, file);}
//catch (java.io.FileNotFoundException e){
//    System.out.println("не найден путь ");
//}


                    Img++;
                }
                System.out.println();

                y++;
            }
            Page++;
        }
        try  (OutputStream fileOut = new FileOutputStream("book.xls")){
            wb.write(fileOut);}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
