package com.hhp.spring.demo1;

import org.springframework.stereotype.Component;

/**
 * Created by Hu on 2018/5/6.
 */
@Component
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i / j;
    }
}
