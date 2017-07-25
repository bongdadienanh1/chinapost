package com.ylife.expression;

import com.ylife.expression.exception.ParseException;

/**
 * Created by InThEnd on 2016/6/4.
 * 变量
 */
public class Variable extends Expression {

    private int id;

    protected Variable(String expression) {
        this.setValue(expression);
        char[] chars = expression.toCharArray();
        if (chars.length < 3) {
            throw new ParseException("变量无法解析:" + expression);
        }
        if (chars[0] != '{') {
            throw new ParseException("变量无法解析:" + expression);
        }
        if (chars[chars.length - 1] != '}') {
            throw new ParseException("变量无法解析:" + expression);
        }
        for (int i = 1; i < chars.length - 1; i++) {
            if (!isNumbers(chars[i])) {
                throw new ParseException("变量无法解析:" + expression);
            }
        }
        id = Integer.valueOf(expression.substring(1, expression.length() - 1));
    }

    public int getId() {
        return id;
    }
}
