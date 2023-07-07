package by.itacademy.servletproject;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.sql.DataSource;


public class Main {


    public static void main(String[] args) throws TelegramApiException {

        ApplicationContext context = new ClassPathXmlApplicationContext("mybeans.xml");

        DataSource dataSource = context.getBean(DataSource.class);

        String name = dataSource.toString();
        System.out.println(name);


    }


}
