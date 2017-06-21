package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.List;

/**
 * Created by kalinnikov_al on 10.06.2017.
 */
public class HtmlView implements View {
    private Controller controller;

//    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".","/") + "/vacancies.html";
    private final String filePath =  new File(".").getAbsolutePath().replace(".","")  + "4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".","/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
//        System.out.println(filePath);

        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {

        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){

        Element element = null;
        try {

            Document document = getDocument();
            element = document.getElementsByClass("template").first();
            Element copy = element.clone();
            copy.removeClass("template").removeAttr("style");
            document.getElementsByAttributeValue("class","vacancy").remove();

            Element template;
            Element temp;

            for (Vacancy vacancy: vacancies) {
                template = copy.clone();
                template.getElementsByClass("city").first().text(vacancy.getCity());
                template.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                template.getElementsByClass("salary").first().text(vacancy.getSalary());
                temp = template.getElementsByTag("a").first();
                temp.text(vacancy.getTitle());
                temp.attr("href",vacancy.getUrl());
                element.before(template.outerHtml());
            }

            return document.html();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

    }

    private void updateFile(String body){
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))){
            fos.write(body.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
