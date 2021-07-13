import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class ParseAvitoHtml {
    public static void main(String[] args) throws IOException {
       /* File htmlFile = new File("/home/student/Documents/Avito.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        String title = doc.title();
        System.out.println("Title : " + title);*/

        String url = "https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200";
        Document document = Jsoup.connect(url).get();

        System.out.println(document.title());
        System.out.println(document.body().select("h3").text());
        System.out.println("-------------------------------------------");


      /*  Elements listNews = document.select("div#items-items-38oUm");
        for (Element element : listNews.select("a"))
            System.out.println(element.text());       */

       /* *//*Elements paragraphs = document.getElementsByTag("a");
        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }*//*
        System.out.println("-------------------------------------------");

        Element link = document.select("a").first();

        System.out.println("Text " + link.text());*/
    }
}
