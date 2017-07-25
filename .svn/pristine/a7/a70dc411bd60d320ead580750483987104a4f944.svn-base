package com.ylife.expression;

import com.googlecode.aviator.AviatorEvaluator;
import com.ylife.expression.exception.MagicException;
import com.ylife.expression.exception.ParseException;
import com.ylife.utils.StringUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by InThEnd on 2016/6/4.
 * 表达式
 */
public class Expression extends TextElement {

    private static Map<String, Expression> cache = new ConcurrentHashMap<>();

    private ExpressionList list = new ExpressionList();

    private List<Expression> expressions;

    private Collection<Variable> variables;

    protected Expression() {
        expressions = new ArrayList<>();
        variables = new ArrayList<>();
    }

    public static Expression create(String expression) {
        Expression expression1 = cache.get(expression);
        if (expression1 == null) {
            expression1 = new Expression(expression);
            cache.put(expression, expression1);
        }
        return expression1;
    }

    private Expression(String expression) {
        if (StringUtil.isBlank(expression)) {
            throw new ParseException("解析错误，表达式不能为空。");
        }
        setValue(expression);
        char[] chars = expression.toCharArray();
        char[] buff = new char[20];
        int start = 0;
        int leftCurves = 0;
        int rightCurves = 0;
        boolean inCurves = false;
        String part = "";
        for (int i = 0; i < chars.length; i++) {

            //如果超过buff长度。
            if (i - start == buff.length) {
                part = part + String.valueOf(buff);
                start = start + buff.length;
            }

            //如果未进入括号
            if (!inCurves) {
                //如果遇到'(',标记进入括号表达式。
                if (chars[i] == '(') {
                    leftCurves++;
                    if (i == chars.length - 1) {
                        throw new ParseException("末尾不能为" + chars[i]);
                    }
                    inCurves = true;
                    part = part + String.valueOf(buff, 0, i - start);
                    //解析Parameter并加入list中。
                    if (!part.equals("")) {
                        if (part.startsWith("{")) {
                            list.add(new Variable(part));
                        } else {
                            list.add(new Number(part));
                        }
                    }
                    part = "";
                    start = i;
                    buff[i - start] = chars[i];
                }

                //如果遇到操作符。
                else if (isOperators(chars[i])) {
                    if (i == chars.length - 1) {
                        throw new ParseException("末尾不能为" + chars[i]);
                    }
                    part = part + String.valueOf(buff, 0, i - start);
                    //解析Parameter并加入list中。
                    if (!part.equals("")) {
                        if (part.startsWith("{")) {
                            list.add(new Variable(part));
                        } else {
                            list.add(new Number(part));
                        }
                    }
                    //解析操作符。
                    Operator operator = new Operator(chars[i]);
                    list.add(operator);
                    part = "";
                    start = i + 1;
                } else {
                    buff[i - start] = chars[i];
                    if (i == chars.length - 1) {
                        part = part + String.valueOf(buff, 0, i - start + 1);
                        //解析Parameter并加入list中。
                        if (part.startsWith("{")) {
                            list.add(new Variable(part));
                        } else {
                            list.add(new Number(part));
                        }
                    }
                }
            }
            //如果进入括号
            else {
                if (chars[i] == '(') {
                    buff[i - start] = chars[i];
                    leftCurves++;
                } else if (chars[i] == ')') {
                    buff[i - start] = chars[i];
                    rightCurves++;
                    if (rightCurves == leftCurves) {
                        part = part + String.valueOf(buff, 0, i - start + 1);
                        if (part.length() < 3) {
                            throw new ParseException("括弧中内容不能为空。");
                        }
                        part = part.substring(1, part.length() - 1);
                        Expression expression1 = new Expression(part);
                        list.add(expression1);
                        part = "";
                        start = i + 1;
                        inCurves = false;
                    }
                } else {
                    buff[i - start] = chars[i];
                }

                if (i == chars.length - 1) {
                    if (rightCurves != leftCurves) {
                        throw new ParseException("解析错误，括弧数量不匹配。");
                    }
                }
            }
        }
        expressions = list.expressions;
        variables = getVariableMap(this).values();
    }

    private Map<Integer, Variable> getVariableMap(Expression expression) {
        Map<Integer, Variable> map = new HashMap<>();
        for (Expression expression1 : expression.getExpressions()) {
            if (expression1 instanceof Variable) {
                map.put(((Variable) expression1).getId(), (Variable) expression1);
            } else {
                map.putAll(getVariableMap(expression1));
            }
        }
        return map;
    }


    public <T> String magic(Map<Integer, T> values) throws MagicException {
        String ex = getValue();
        for (Variable variable : variables) {
            String toReplace = variable.getValue();
            T value = values.get(variable.getId());
            if (value == null) {
                throw new MagicException("变化失败，参数：" + toReplace + "的值未提供。");
            }
            String replace = value.toString();
            ex = ex.replace(toReplace, replace);
        }
        return ex;
    }

    public <T> BigDecimal compute(Map<Integer, T> values) throws MagicException {
        String ex = magic(values);
        com.googlecode.aviator.Expression compile = AviatorEvaluator.compile(ex);
        return new BigDecimal(compile.execute().toString());
    }


    public List<Expression> getExpressions() {
        return expressions;
    }


    public Collection<Variable> getVariables() {
        return variables;
    }

    private class ExpressionList extends ArrayList<TextElement> {

        private ExpressionList() {
        }

        private boolean isExpression = true;

        private List<Expression> expressions = new ArrayList<>();

        @Override
        public boolean add(TextElement element) {
            if (isExpression) {
                if (!(element instanceof Expression)) {
                    throw new ParseException("操作符不能相邻。");
                }
                expressions.add((Expression) element);
            } else {
                if (!(element instanceof Operator)) {
                    throw new ParseException("两个部分无操作符连接。");
                }
            }
            isExpression = !isExpression;
            return super.add(element);
        }
    }


    protected static boolean isOperators(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    protected static boolean isNumbers(char c) {
        return c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0';
    }

}
