package com.ylife.expression;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by InThEnd on 2016/6/4.
 * 操作符
 */
public class Operator extends TextElement {

    private static final Set<Character> values = new HashSet<>(Arrays.asList('+', '-', '*', '/'));

    protected Operator(char operator) {
        setValue(String.valueOf(operator));
        if (!values.contains(operator)) {
            throw new IllegalArgumentException("操作符解析错误：" + operator);
        }
        setValue(String.valueOf(operator));
    }
}
