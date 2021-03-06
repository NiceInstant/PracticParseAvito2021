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

    public void updateXmlFile(List<AvitoAds> list) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        // создаем пустой объект Document, в котором будем создавать наш xml-файл
        Document doc = builder.newDocument();
        // создаем корневой элемент
        Element rootElement = doc.createElementNS("https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200", "AvitoAds");
        // добавляем корневой элемент в объект Document
        doc.appendChild(rootElement);

        // Добавляем список объявлений
        for (AvitoAds avitoAdsList : list) {

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