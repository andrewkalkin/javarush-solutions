package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalinnikov_al on 09.06.2017.
 */
public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {

        List<Vacancy> vacancies = new ArrayList<>();
        Document document;
        Elements elements;
        Vacancy vacancy;
        Elements salary;

        try {


            int page = 0;
            while ((elements = (document = getDocument(searchString, page++)).select("[data-qa=\"vacancy-serp__vacancy\"]")).size() !=0 ){

                for (Element element: elements) {
                    vacancy = new Vacancy();
                    vacancy.setCity(element.getElementsByAttributeValueMatching("data-qa","vacancy-serp__vacancy-address").first().textNodes().get(0).text().replace(",","").trim());
                    vacancy.setCompanyName(element.getElementsByAttributeValueMatching("data-qa","vacancy-serp__vacancy-employer").first().text());
                    vacancy.setTitle(element.getElementsByAttributeValueMatching("data-qa","vacancy-serp__vacancy-title").first().text());
                    vacancy.setSiteName(URL_FORMAT);
                    vacancy.setUrl(element.getElementsByAttributeValueMatching("data-qa","vacancy-serp__vacancy-title").first().attr("href"));
                    salary = element.getElementsByAttributeValueMatching("data-qa","vacancy-serp__vacancy-compensation");
                    vacancy.setSalary(salary.size() == 0? "":salary.first().text());
                    vacancies.add(vacancy);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {

        String url = String.format(URL_FORMAT, searchString, page);

        return Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(5000).referrer("").get();


    }
}
