package by.itacademy.servletproject.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansFactory {

    private static BeansFactory instance;

    public ApplicationContext appContext =
            new ClassPathXmlApplicationContext("mybeans.xml");

    public static synchronized BeansFactory getInstance(){
        if (instance == null) {
            instance = new BeansFactory();
        }

        return instance;
    }


    public <T> T getBeanInstance(Class<T> clazz) {

        return appContext.getBean(clazz);

    }



}
