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
import java.util.stream.Collectors;

public class WriteDocumentElements {

    private Document getDocument() throws IOException {
        /*String url = "https://www.avito.ru/naberezhnye_chelny/avtomobili?cd=1&radius=200";*/

        /*String url = "https://auto.ru/naberezhnye_chelny/cars/all/";*/

        File htmlFIleAvito = new File("/home/student/Documents/AvitoVesiaon2.html");


        Document document = Jsoup.parse(htmlFIleAvito,"UTF-8");


        System.out.println(document.title());
        return document;

    }

    public void  WriteElemetAvito() throws IOException
    {
        Document document = getDocument();

        //Все объявления о продаже
        Elements elements = document.select("div[class=iva-item-body-NPl6W]");

        //Извлечение названий продоваемый машин
        Elements h3Elements = elements.select("h3");
        //Извлечение цены из meta
        Elements span_metaElents = elements.select("span[class=price-price-32bra]").select("meta[itemprop=price]");


        String h3Text = h3Elements.first().text();
        int spanAttrPrice = Integer.parseInt(span_metaElents.attr("content"));

        AvitoAds avitoAds = new AvitoAds(h3Text,spanAttrPrice);
        avitoAds.display();


        /*System.out.println(h3Text);*/


        /*for (Element elemmentspan: span_metaElents ) {
            int spanAttrPrice = Integer.parseInt(elemmentspan.attr("content"));
            System.out.println(spanAttrPrice);
        }*/


        /*System.out.println(spanAttrPrice);*/

    }

   /* public void WriteElement() throws IOException {

        Document document = getDocument();
                *//*Elements dives = document.select("a[data-marker=item-title]");
                Elements h3Titles = dives.select("h3");*//*

        Elements divTitles = document.select("div[class = ListingItem-module__main]");

        List<String> first_divTitleList = new ArrayList<>();
        List<String> second_divTitleList = new ArrayList<>();

        //Добавление объявлений с списки
        for (Element title : divTitles) {

            String stringTitle = "Марка авто:" + title.select("a[class = Link ListingItemTitle__link]").text()
                    + "\t Цена:" + title.select("div[class = ListingItemPrice-module__content]").text();
            first_divTitleList.add(stringTitle);
            second_divTitleList.add(stringTitle);

        }
        //Сортировка списков
        Collections.sort(first_divTitleList);
        Collections.sort(second_divTitleList);


        //Считывание файла объявлений
        File file = new File("fileTitleList.txt");
        Reader reader = new FileReader(file);
        BufferedReader brReader = new BufferedReader(reader);

        //Добавление файла объвлений в список
        String line;
        List<String> lineList = new ArrayList<>();
        while ((line = brReader.readLine()) != null) {
            *//*System.out.println(line);*//*
            lineList.add(line);
        }
        brReader.close();


           *//* System.out.println("Есть новые объявления");*//*
        //Добавление в файл
            Path fileTitleList = Paths.get("fileTitleList.txt");
            Files.write(fileTitleList, first_divTitleList, StandardCharsets.UTF_8);

            second_divTitleList.forEach(s -> System.out.println("Из второго списка ->" + s + "\n"));
            System.out.println("------------------------------------------------------------");
            *//*lineList.forEach(s -> System.out.println("Из документа- > " + s + "\n"));*//*


        *//*List<String> searchResult = null;*//*

        boolean searchResult;
        List<List<String>> addSearshList = new ArrayList<>();
        for (String fileList:lineList) {
            *//*searchResult = second_divTitleList.stream().filter(second_divTitleLists->second_divTitleLists.contains(fileList)).collect(Collectors.toList());*//*

            *//*addSearshList.add(searchResult);*//*
            searchResult= second_divTitleList.stream().anyMatch(secondList->secondList.equals(fileList));
            if (searchResult == true)
            {
                second_divTitleList.remove(fileList);
            }

            System.out.println(searchResult);
        }
        System.out.println(second_divTitleList);
        *//*System.out.println(addSearshList.size());*//*


    }*/
}
