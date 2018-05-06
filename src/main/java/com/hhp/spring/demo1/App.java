package com.hhp.spring.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Hu on 2018/5/6.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(4,6);
        calculator.div(10, 0);
    }
}
