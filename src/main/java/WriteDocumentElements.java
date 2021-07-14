import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriteDocumentElements {

    private Document getDocument() throws IOException {
        /*String url = "https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200";*/

        String url = "https://auto.ru/naberezhnye_chelny/cars/all/";

        Document document = Jsoup.connect(url).get();


        System.out.println(document.title());
        return document;

    }

    public void WriteElement() throws IOException {

        Document document = getDocument();
                /*Elements dives = document.select("a[data-marker=item-title]");
                Elements h3Titles = dives.select("h3");*/

        Elements divTitles= document.select("div[class = ListingItem-module__main]");

        List<String> first_divTitleList = new ArrayList<>();
        List<String> second_divTitleList = new ArrayList<>();


        for(Element title:divTitles)
        {

            String stringTitle = "Марка авто:"+title.select("a[class = Link ListingItemTitle__link]").text()
                            +"\t Цена:"+title.select("div[class = ListingItemPrice-module__content]").text();
            first_divTitleList.add(stringTitle);
            second_divTitleList.add(stringTitle);

        }
        Collections.sort(first_divTitleList);
        Collections.sort(second_divTitleList);
        System.out.println(first_divTitleList);
        System.out.println("===================================");
        System.out.println("Сравнение первого и второго списка"+first_divTitleList.equals(second_divTitleList));

        Path fileTitleList = Paths.get("fileTitleList.txt");
        Files.write(fileTitleList,first_divTitleList, StandardCharsets.UTF_8);

        File file = new File("fileTitleList.txt");

        Reader reader = new FileReader(file);
        BufferedReader brReader = new BufferedReader(reader);

        String line;
        List<String> lineList = new ArrayList<>();
        while((line = brReader.readLine()) != null) {
            System.out.println(line);
            lineList.add(line);
        }

        brReader.close();
        System.out.println(lineList.size());
        System.out.println("Список из документа"+lineList);
        System.out.println("=======================");
        System.out.println(second_divTitleList.size());
        System.out.println("Второй список"+second_divTitleList);

        /*System.out.println("Сравнение второго списка со списком из документа :"+second_divTitleList.equals(lineList));*/

        boolean equals = second_divTitleList.equals(lineList);
        if (equals == true) System.out.println("Объявлений новых нет");

    }
}
