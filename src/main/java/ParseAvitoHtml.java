import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ParseAvitoHtml {
    private static Document getDocument() throws IOException {
        String url = "https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200";
        Document document = Jsoup.connect(url).get();


                System.out.println(document.title());
                return document;

        }

            public static void main(String[] args) throws IOException {
                Document document = getDocument();
                Elements dives = document.select("a[data-marker=item-title]");

                Elements h3Titles = dives.select("h3");


                List<String> first_h3TitleList = new ArrayList<>();
                List<String> second_h3TitleList = new ArrayList<>();


                for(Element title:h3Titles)
                {
                    String stringTitle = title.text()+"\n";
                    first_h3TitleList.add(stringTitle);

                    /*System.out.println(stringTitle);*/
                }
                Collections.sort(first_h3TitleList);
                System.out.println(first_h3TitleList);
                System.out.println("===================================");

                Path fileTitleList = Paths.get("fileTitleList.txt");
                Files.write(fileTitleList,first_h3TitleList, StandardCharsets.UTF_8);

        }
    }

