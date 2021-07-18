import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WriteDocumentElements {
    AvitoAds avitoAds ;

    private Document getDocument() throws IOException {

        File htmlFIleAvito = new File("C:/Users/User/IdeaProjects/АвитоДанные/AvitoVesiaon2.html");


        Document document = Jsoup.parse(htmlFIleAvito,"UTF-8");


        System.out.println(document.title()+"\n");
        return document;
    }

    public void  WriteElemetAvito() throws IOException, ParserConfigurationException, TransformerException {
        Document document = getDocument();
        CreateXmlFile createXmlFile = new CreateXmlFile();


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
            /*avitoAds = new AvitoAds(adsName,adsPrice);
            System.out.println(avitoAds.display());*/
        }

        System.out.println(avitoAdsList.toString());

        createXmlFile.setAvitoAdsList(avitoAdsList);

        System.out.println("===========================================================================================");

        createXmlFile.creatingXmlFile();



    }
}
