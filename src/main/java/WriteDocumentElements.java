import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WriteDocumentElements {
    CreateXmlFile createXmlFile = new CreateXmlFile();

    private Document getDocument() throws IOException {

        File htmlFIleAvito = new File("/home/student/Documents/AvitoVersion2.html");
        Document document = Jsoup.parse(htmlFIleAvito,"UTF-8");
        System.out.println(document.title()+"\n");
        return document;
    }

    public   void checkFile(String filename) throws ParserConfigurationException, TransformerException, IOException {
        final File file = new File(filename);

        if (file.exists()) {
            System.out.println("Файл существует.");
        } else {
            System.out.println("Файл не существует.");
            createXmlFile.updateXmlFile();
        }
    }


    public void  WriteElemetAvito() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        Document document = getDocument();
        ParseWriteXmlFile parseWriteXmlFile = new ParseWriteXmlFile();

        //Все объявления о продаже
        Elements elements = document.select("div[class=iva-item-body-NPl6W]");
        createXmlFile.setElements(elements);

        List<AvitoAds>  avitoAdsList = new ArrayList<>();

        for (Element elementAds : elements) {

            //Извлечение названий продоваемый машин
            String adsName = elementAds.select("h3").text();
            //Извлечение цены
            Elements adsPriceString = elementAds.select("span[data-marker=item-price]").select("meta[itemprop=price]");
            int adsPrice = Integer.parseInt(adsPriceString.attr("content"));

            avitoAdsList.add(new AvitoAds(adsName,adsPrice));

        }

        /*System.out.println(avitoAdsList.toString());*/
        System.out.println("Список объявлений на данных момент");
        for (AvitoAds avitoAds:avitoAdsList) {
            System.out.println("Марка Авто: "+avitoAds.getNameAds()+"  Цена : "+avitoAds.getPriceAds());
        }


      /*parseWriteXmlFile.testOnHaveFile(avitoAdsList);*/
        System.out.println("===========================================================================================");
        //создание пустого файла
        //------createXmlFile.createXmlFile();
        createXmlFile.setAvitoAdsList(avitoAdsList);
        checkFile("resourseXml/AvitoAds.xml");
        System.out.println("===========================================================================================");
        System.out.println("Список объявлений из документа ");
        parseWriteXmlFile.parseAndWriteXmlDocument();
        System.out.println("===========================================================================================");
        parseWriteXmlFile.equalsLists(avitoAdsList);
        createXmlFile.updateXmlFile();

        /*createXmlFile.setAvitoAdsList(avitoAdsList);
        createXmlFile.updateXmlFile();*/



    }
}