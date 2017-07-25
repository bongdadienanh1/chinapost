package com.ylife.utils;

import com.ylife.expression.exception.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/7/30.
 */
public class CheckIdcard {

    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 第18位校检码
    private String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};


    public static boolean isValid(String idcard) {
        if (idcard.length() != 18) {
            return false;
        }
        // 获取前17位
        String idcard17 = idcard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);
        char c[];
        String checkCode = "";

        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }

        //判断第十八位
        int bit[] = new int[idcard17.length()];
        bit = converCharToInt(c);
        int sum17 = 0;
        sum17 = getPowerSum(bit);
        // 将和值与11取模得到余数进行校验码判断
        checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        if (!idcard18Code.equalsIgnoreCase(checkCode)) {
            return false;
        }

        int year = Integer.parseInt(idcard.substring(6, 10));
        int month = Integer.parseInt(idcard.substring(10, 12));
        int day = Integer.parseInt(idcard.substring(12, 14));
        String birthday = idcard.substring(8, 14);


        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        if (birthDate == null || new Date().before(birthDate)) {
            return false;
        }

        GregorianCalendar curDay = new GregorianCalendar();
        int curYear = curDay.get(Calendar.YEAR);
        int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));

        // 判断该年份的两位表示法，小于50的和大于当前年份的，为假
        if ((year < 10 && year > year2bit)) {
            return false;
        }

        // 判断是否为合法的月份
        if (month < 1 || month > 12) {
            return false;
        }

        // 判断是否为合法的日期
        boolean mflag = false;
        curDay.setTime(birthDate); // 将该身份证的出生日期赋于对象curDay
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                mflag = (day >= 1 && day <= 31);
                break;
            case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                    mflag = (day >= 1 && day <= 29);
                } else {
                    mflag = (day >= 1 && day <= 28);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                mflag = (day >= 1 && day <= 30);
                break;
        }

        return mflag;
    }


    public static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    public static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    public static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }


    //判断是否是全数字
    private static boolean isDigital(String str) {
        return !(str == null || "".equals(str)) && str.matches("^[0  b -9]*$");
    }


}
