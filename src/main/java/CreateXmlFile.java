import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateXmlFile {
    Elements elements;
    List<AvitoAds> avitoAdsList ;
    public  void  setElements(Elements elements)
    {
        this.elements= elements;
    }
    public Elements getElements()
    {
        return elements;
    }

    public List<AvitoAds> getAvitoAdsList() {
        return avitoAdsList;
    }

    public void setAvitoAdsList(List<AvitoAds> avitoAdsList) {
        this.avitoAdsList = avitoAdsList;
    }

   public void  createXmlFile() throws ParserConfigurationException, TransformerException {
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder;
       builder = factory.newDocumentBuilder();
       Document doc = builder.newDocument();
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();
       DOMSource source = new DOMSource(doc);
       StreamResult file = new StreamResult(new File("resourseXml/AvitoAds.xml"));
       transformer.transform(source, file);

   }


    public void updateXmlFile() throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        // создаем пустой объект Document, в котором будем создавать наш xml-файл
        Document doc = builder.newDocument();
        // создаем корневой элемент
        Element rootElement = doc.createElementNS("https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200", "AvitoAds");
        // добавляем корневой элемент в объект Document
        doc.appendChild(rootElement);

        //Все объявления о продаже
        Elements elements = getElements();

        // Добавляем список объявлений
        for (AvitoAds avitoAdsList : avitoAdsList) {

            String adsName = avitoAdsList.getNameAds();
            int adsPrice = avitoAdsList.getPriceAds();

            rootElement.appendChild(getAvitoListXml(doc,adsName, String.valueOf(adsPrice)));
        }

        //создаем объект TransformerFactory для печати в консоль
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // для красивого вывода в консоль
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        //печатаем в консоль или файл
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(new File("resourseXml/AvitoAds.xml"));

        //записываем данные
        transformer.transform(source, console);
        transformer.transform(source, file);
        System.out.println("Создание XML файла закончено");
    }
    private static Node getAvitoListXml(Document doc, String name, String price) {
        Element adsAvitoListXml = doc.createElement("AdsAvitoXml");

        adsAvitoListXml.setAttribute("adsName", name);
        adsAvitoListXml.setAttribute("adsPrice", price);

        /*// создаем элемент name
        adsAvitoListXml.appendChild(getAvitoListXmlElements(doc, adsAvitoListXml, "adsName", name));

        // создаем элемент prise
        adsAvitoListXml.appendChild(getAvitoListXmlElements(doc, adsAvitoListXml, "adsPrice", price));*/
        return adsAvitoListXml;
    }
    private static Node getAvitoListXmlElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}