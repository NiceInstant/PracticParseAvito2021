import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ParseAvitoHtml {
    private static Document getDocument() throws IOException {
        /*String url = "https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200";*/

        String url = "https://auto.ru/naberezhnye_chelny/cars/all/";

        Document document = Jsoup.connect(url).get();


                System.out.println(document.title());
                return document;

        }

            public static void main(String[] args) throws IOException {
                Document document = getDocument();
                /*Elements dives = document.select("a[data-marker=item-title]");
                Elements h3Titles = dives.select("h3");*/

                Elements divTitles= document.select("div[class = ListingItem-module__main]");




                List<String> first_divTitleList = new ArrayList<>();
                List<String> second_divTitleList = new ArrayList<>();


                for(Element title:divTitles)
                {
                    /*String stringTitle = title.text()+"\n";*/
                    String stringTitle = "Марка авто:"+title.select("a[class = Link ListingItemTitle__link]").text()+"\t Цена:"
                            +title.select("div[class = ListingItemPrice-module__content]").text()+"\n";
                    first_divTitleList.add(stringTitle);

                    /*System.out.println(stringTitle);*/
                }
                Collections.sort(first_divTitleList);
                System.out.println(first_divTitleList);
                System.out.println("===================================");



               /* Path fileTitleList = Paths.get("fileTitleList.txt");
                Files.write(fileTitleList,first_divTitleList, StandardCharsets.UTF_8);*/

        }
    }

