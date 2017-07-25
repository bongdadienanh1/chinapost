package com.ylife.expression;

import com.ylife.expression.exception.ParseException;

import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/6/4.
 * 数字模型
 */
public class Number extends Expression {

    private BigDecimal numberValue;

    protected Number(String expression) {
        setValue(expression);
        try {
            numberValue = new BigDecimal(expression);
        } catch (NumberFormatException e) {
            throw new ParseException("数字无法解析：" + expression);
        }
    }

    public BigDecimal getNumberValue() {
        return numberValue;
    }

}
