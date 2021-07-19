import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

public class ParseAvitoHtml {

            public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
                WriteDocumentElements writeDocumentElements = new WriteDocumentElements();
                writeDocumentElements.WriteElemetAvito();
            }
    }

