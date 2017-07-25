package com.ylife.utils;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by InThEnd on 2016/4/7.
 * Excel工具类。
 */
public class ExcelUtil {

    private static final DecimalFormat format = new DecimalFormat("0.###");

    public static HSSFCellStyle headStyle(HSSFWorkbook wb) {
        HSSFCellStyle headStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headStyle.setFont(font);
        DataFormat format = wb.createDataFormat();
        headStyle.setDataFormat(format.getFormat("@"));
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    //自动换行功能
	    headStyle.setWrapText(true);
        return headStyle;
    }

    public static HSSFCellStyle bodyStyle(HSSFWorkbook wb) {
        HSSFCellStyle bodyStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        bodyStyle.setFont(font);
        DataFormat format = wb.createDataFormat();
        bodyStyle.setDataFormat(format.getFormat("@"));
        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    //自动换行功能
	    bodyStyle.setWrapText(true);
	    return bodyStyle;
    }

    /**
     * CSV文件转List<T>
     *
     * @param is          输入流
     * @param clazz       T的类型
     * @param fieldsIndex T类型与CSV文件的列位置对应
     * @param ignore      要跳过的记录数
     */
    public static <T> List<T> readCsv(InputStream is, Class<T> clazz, String[] fieldsIndex, int ignore) throws IOException {
        try {
            Field[] fields = new Field[fieldsIndex.length];
            for (int i = 0; i < fieldsIndex.length; i++) {
                fields[i] = clazz.getDeclaredField(fieldsIndex[i]);
                fields[i].setAccessible(true);
            }
            CSVFormat format = CSVFormat.EXCEL;
            CSVParser parser = new CSVParser(new InputStreamReader(is, "GBK"), format);
            List<CSVRecord> recordList = parser.getRecords();
            List<T> list = new ArrayList<>();
            for (int i = ignore; i < recordList.size(); i++) {
                CSVRecord record = recordList.get(i);
                T t = clazz.newInstance();
                for (int j = 0; j < fields.length; j++) {
                    String value = record.get(j);
                    if (value != null && value.equals("")) {
                        value = null;
                    }
                    fields[j].set(t, value);
                }
                list.add(t);
            }
            return list;
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("fieldsIndex参数与clazz参数不匹配：fieldsIndex参数中\"" + e.getMessage() + "\"字段未在" + clazz.getName() + "类中找到。");
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(clazz.getName() + "类中未找到默认无参构造方法。");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("请保证" + clazz.getName() + "类中默认构造方法可见。");
        }
    }

    /**
     * CSV文件转List<T>
     *
     * @param is          输入流
     * @param clazz       T的类型
     * @param fieldsIndex T类型与CSV文件的列位置对应
     */
    public static <T> List<T> readCsv(InputStream is, Class<T> clazz, String[] fieldsIndex) throws IOException {
        return readCsv(is, clazz, fieldsIndex, 0);
    }

    /**
     * 导出简单表格
     *
     * @param maps 表头
     * @param list 表身的list
     * @param <T>
     * @return
     */
    public static <T> Workbook excelExport(Map<String, String> maps, List<T> list) {
        HSSFWorkbook wb = new HSSFWorkbook();
        return excelExport(wb, maps, list, 0);
    }

    /**
     * 返回一个标准化的带边框工作薄，方便对复杂格式excel的再处理
     *
     * @param columNum 列数
     * @param rowNum   行数
     * @return
     */
    public static HSSFWorkbook standardizedExcel(HSSFWorkbook wb, int columNum, int rowNum, String sheetName) {
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFCellStyle bodyStyle = bodyStyle(wb);
        for (int i = 0; i < rowNum; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < columNum; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(bodyStyle);
            }
        }
        return wb;
    }

    /**
     * Excel导出
     *
     * @param wb   已有的工作薄
     * @param maps <String,String> maps 属性表，成员属性为KEY，中文名称为VALUE
     * @param list <T> list 需要导出的数据列表对象
     * @return true 导出成功 false 导出失败
     */
    public static <T> Workbook excelExport(HSSFWorkbook wb, Map<String, String> maps, List<T> list, int ignore) {
        try {
            HSSFCellStyle style = headStyle(wb);
            HSSFCellStyle style2 = bodyStyle(wb);
            CreationHelper createHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("sheet1");
            sheet.setDefaultColumnWidth((short) 20);
            Set<String> sets = maps.keySet();
            Row row = sheet.createRow(ignore);

            int i = 0;
            // 定义表头
            for (String key : sets) {
                Cell cell = row.createCell(i++);
                cell.setCellStyle(style);
                cell.setCellValue(createHelper.createRichTextString(maps.get(key)));
            }
            // 填充表单内容
            for (int j = 0; j < list.size(); j++) {
                T p = list.get(j);
                Class classType = p.getClass();
                int index = 0;
                Row row1 = sheet.createRow(j + 1 + ignore);
                for (String key : sets) {
                    Field field = classType.getDeclaredField(key);
                    field.setAccessible(true);
                    Cell cell = row1.createCell(index++);
                    cell.setCellStyle(style2);
                    Object value = field.get(p);
                    /*if (field.getType() == BigDecimal.class) {
                        cell.setCellValue(BigDecimal.ZERO.toString());
                    }*/
                    if (value == null) {
                        if (field.getType() == BigDecimal.class) {
                            cell.setCellValue("");
                        } else if (field.getType() == Long.class) {
                            cell.setCellValue("");
                        } else if (field.getType() == Integer.class) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue("");
                        }
                    } else {
                        if (field.getType() == Date.class) {
                            Date date = (Date) value;
                            cell.setCellValue(DateUtil.formatToString(date, "yyyy/MM/dd HH:mm:ss"));
                        } else if (field.getType() == Boolean.class) {
                            Boolean booValue = (Boolean) value;
                            if (booValue) {
                                cell.setCellValue("是");
                            } else {
                                cell.setCellValue("否");
                            }
                        } else if (value.equals("0")) {
                            cell.setCellValue("否");
                        } else if (value.equals("1")) {
                            cell.setCellValue("是");
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }
            return wb;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("不可能出现这个错误！");
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("参数错误，输出Excel失败。");
        }
    }

    /**
     * Excel文件转List<List<String>>
     *
     * @param is     输入流
     * @param ignore 要跳过的记录数
     */
    public static List<List<String>> readExcel(InputStream is, int ignore) throws IOException, InvalidFormatException {
        Workbook book = WorkbookFactory.create(is);

        Sheet sheet = book.getSheetAt(0);

        List<List<String>> list = new ArrayList<>();
        Row row1 = sheet.getRow(sheet.getFirstRowNum());
        int min = row1.getFirstCellNum();
        int max = row1.getLastCellNum();
        for (int i = sheet.getFirstRowNum() + ignore; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> contentList = new ArrayList<>();
            for (int j = min; j < max; j++) {
                Cell cell = row.getCell(j);
                String stringValue = readCell(cell);
                contentList.add(stringValue);
            }
            list.add(contentList);
        }
        return list;
    }

    /**
     * Excel文件转List<List<String>>
     *
     * @param is 输入流
     */
    public static List<List<String>> readExcel(InputStream is) throws IOException, InvalidFormatException {
        return readExcel(is, 0);
    }

    public static HSSFWorkbook excelExport(List<String> heads, List<List<String>> contents) {
        HSSFWorkbook wb = new HSSFWorkbook();
        return excelExport(wb, heads, contents, 0, 0, "sheet1");
    }


    /**
     * Excel导出
     *
     * @param heads    表头
     * @param contents 表内容
     */
    public static HSSFWorkbook excelExport(HSSFWorkbook wb, List<String> heads, List<List<String>> contents, int ignoreColumn, int ignoreRow, String sheetName) {

        int bodyListSize;
        if (contents == null) {
            bodyListSize = 0;
        } else {
            bodyListSize = contents.size();
        }
        wb = standardizedExcel(wb, heads.size() + ignoreColumn, bodyListSize + ignoreRow + 1, sheetName);
        //HSSFCellStyle style0 = headStyle(wb);
        //HSSFCellStyle style1 = bodyStyle(wb);
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.getSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 20);
        Row row = sheet.getRow(ignoreRow);
        // 定义表头
        for (int i = 0; i < heads.size(); i++) {
            Cell cell = row.getCell(i + ignoreColumn);
            // cell.setCellStyle(style0);
            String value = heads.get(i);
            //cell.setCellValue(createHelper.createRichTextString(heads.get(i)));
            cell.setCellValue(value == null ? "" : value);
        }
        // 填充表单内容
        HSSFCellStyle bodyStyle = bodyStyle(wb);
        if (contents != null) {
            for (int i = 0; i < contents.size(); i++) {
                Row row1 = sheet.getRow(ignoreRow + i + 1);
                List<String> content = contents.get(i);
                for (int j = 0; j < content.size(); j++) {
                    Cell cell = row1.getCell(j + ignoreColumn);
                    //cell.setCellStyle(style1);
                    String value = contents.get(i).get(j);
                    cell.setCellValue(value == null ? "" : value);
                    cell.setCellStyle(bodyStyle);
                }
                if (i % 1000 == 0) {
                    System.out.println("处理到第#"+i+"#条");
                }
            }
        }
        return wb;
    }

    private static String readCell(Cell cell) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                return format.format(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    //根据数据量查询所在列
    public static String getcellColumnFlag(int num) {
        String columFiled = "";
        int chuNum;
        int yuNum;
        if (num >= 1 && num <= 26) {
            columFiled = doHandle(num);
        } else {
            chuNum = num / 26;
            yuNum = num % 26;

            columFiled += doHandle(chuNum);
            columFiled += doHandle(yuNum);
        }
        return columFiled;
    }

    public static String doHandle(final int num) {
        String[] charArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"
                , "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return charArr[num - 1];
    }


    /**
     * 根据传入的集合数据生成报表的方法
     *
     * @param list
     *            数据列表
     * @param titleName
     *              标题
     * @param titleTag
     *              表头列表
     * @param fieldsArr
     *              需要导出的字段名
     * @param fileName
     *            报表excel的文件名，不需要扩展名
     * @param response
     *            HttpServletResponse
     * @return 1表示成功 0表示失败
     * @throws IOException
     * @Create henry 2017-04-19
     * @change
     */
    @SuppressWarnings("unchecked")
    public static int createReport(List<Object> list,String titleName,String[] titleTag,String[] fieldsArr, String fileName,
                                   HttpServletResponse response) throws IOException {

        // 组装excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        // 设置Excel标题字体和样式
        HSSFFont headFont = workbook.createFont();
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headFont.setFontHeightInPoints((short) 16);
        HSSFCellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setFont(headFont);
        headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headCellStyle.setWrapText(true);
        headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 设置Excel表头的字体和样式
        HSSFFont thfont = workbook.createFont();
        thfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle thCellStyle = workbook.createCellStyle();
        thCellStyle.setFont(thfont);
        thCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        thCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        thCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        thCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        thCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        thCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        thCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置Excel内容的字体和样式
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        HSSFCellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setFont(font);
        titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        // 单元格居左样式
        HSSFCellStyle cellLeft = null;

        // 单元格居中样式
        HSSFCellStyle cellCenter = null;

        // 单元格居右样式
        HSSFCellStyle cellRight = null;

        // 单元格居左样式
        cellLeft = workbook.createCellStyle();
        cellLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        // 单元格居中样式
        cellCenter = workbook.createCellStyle();
        cellCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 单元格居右样式
        cellRight = workbook.createCellStyle();
        cellRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        // 第一行
        HSSFRow row = null;
        HSSFCell cell = null;
        int index = 0;
        if(titleName != null){
            // 合并标题单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
                    titleTag.length - 1));
            row = sheet.createRow(0);
            row.setHeightInPoints(30);
            cell = row.createCell(0);
            cell.setCellStyle(headCellStyle);
            cell.setCellValue(titleName);

            // 设置表头单元格
            int rownum = sheet.getLastRowNum();
            row = sheet.createRow(rownum + 1);
            index = 1;
        }else{
            row = sheet.createRow(0);
        }

        for (int i = 0; i < titleTag.length; i++) {
            sheet.setColumnWidth(i, 6000);
            cell = row.createCell(i);
            cell.setCellStyle(thCellStyle);
            cell.setCellValue(titleTag[i]);
        }

        // 遍历集合数据，产生数据行
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object t = (Object) it.next();

            for (int i=0;i<fieldsArr.length;i++){
                cell = row.createCell(i);
                cell.setCellStyle(cellCenter);
                String fieldName = fieldsArr[i];
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Class tCls = t.getClass();
                Method getMethod = null;
                Object value = null;
                try{
                    getMethod = tCls.getMethod(getMethodName,new Class[]{});
                    value = getMethod.invoke(t, new Object[]{});
                }catch (Exception e){

                }
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value == null) {
                    cell.setCellValue("");
                }
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    cell.setCellValue(intValue);
                } else if (value instanceof Float) {
                    float fValue = (Float) value;
                    cell.setCellValue(fValue);
                } else if (value instanceof Double) {
                    double dValue = (Double) value;
                    cell.setCellValue(dValue);
               /* } else if (value instanceof Long) {
                    long longValue = (Long) value;
                    cell.setCellValue(longValue);*/
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                    textValue = sdf.format(date);
                    cell.setCellValue(textValue);
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value==null? "":value.toString();
                    cell.setCellValue(textValue);
                }
            }
        }

        if(fileName == null ){
            fileName = String.valueOf(System.currentTimeMillis());
        }else {
            fileName = fileName+String.valueOf(System.currentTimeMillis());
        }

        response.setHeader("Content-disposition", "attachment; filename="
                + URLEncoder.encode(fileName, "utf-8") + ".xls");
        response.setContentType("application/msexcel;charset=utf-8");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.flush();
            workbook.write(out);
        } finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }
}