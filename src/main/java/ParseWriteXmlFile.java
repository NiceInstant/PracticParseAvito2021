import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseWriteXmlFile {
    CreateXmlFile createXmlFile = new CreateXmlFile();
    List<AvitoAds> avitoAdsXmlList = new ArrayList<>();
    public  void  parseAndWriteXmlDocument() throws ParserConfigurationException, IOException, SAXException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document =  builder.parse(new File("resourseXml/AvitoAds.xml"));



        // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList adsElements = document.getDocumentElement().getElementsByTagName("AdsAvitoXml");

        // Перебор всех элементов Объявлений ads
        for (int i = 0; i < adsElements.getLength(); i++) {
            Node ads = adsElements.item(i);

            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = ads.getAttributes();

            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            avitoAdsXmlList.add(new AvitoAds(attributes.getNamedItem("adsName").getNodeValue(), Integer.parseInt(attributes.getNamedItem("adsPrice").getNodeValue())));
        }
        for (AvitoAds avitoAds:avitoAdsXmlList) {
            System.out.println("Марка Авто: "+avitoAds.getNameAds()+"  Цена : "+avitoAds.getPriceAds());
        }

    }

    //Сравнение объявлений на наличие новых

    public void equalsLists(List<AvitoAds> avitoAdsList) throws ParserConfigurationException, IOException, TransformerException {
        for (AvitoAds avitoAds:avitoAdsList) {
            Iterator<AvitoAds> iterator = avitoAdsXmlList.iterator();
            while (iterator.hasNext()) {
                AvitoAds avitoAds1 = iterator.next();
                boolean metka = avitoAds1.equals(avitoAds);
                if (metka==true){
                    iterator.remove();
                }
            }
        }
        if (avitoAdsXmlList == null || avitoAdsXmlList.size()==0)
        {
            System.out.println("Новых Объявлений нет");
        }
        else {
            System.out.println("НОВЫЕ ОБЪЯВЛЕНИЯ");
            /*for (AvitoAds avitoAds:avitoAdsXmlList) {
                avitoAdsList.add(avitoAds);
            }*/
            System.out.println(avitoAdsList);
        }
    }
}
