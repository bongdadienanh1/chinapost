package com.ylife.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by XiaoBiaoGe on 2017/1/20.
 */

/**
 * 当excel导出数据超出63225条时候，HSSFWORKBOOK超出数据量上限,用SXSSFXSSFWorkbook替代原工作薄
 */
public class ExcelUtilXssf {


    private static final DecimalFormat format = new DecimalFormat("0.###");

    public static CellStyle headStyle(XSSFWorkbook wb) {
        //HSSFXSSFWorkbook wbnew=(HSSFXSSFWorkbook)(XSSFWorkbook)wb;
        CellStyle headStyle = wb.createCellStyle();
        Font font = wb.createFont();
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
        return headStyle;
    }

    public static CellStyle bodyStyle(Workbook wb) {
        CellStyle bodyStyle = wb.createCellStyle();
        Font font = wb.createFont();
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
        return bodyStyle;
    }


    /**
     * 返回一个标准化的带边框工作薄，方便对复杂格式excel的再处理
     *
     * @param columNum 列数
     * @param rowNum   行数
     * @return
     */
    public static Workbook standardizedExcel(Workbook wb, int columNum, int rowNum, String sheetName) {
        Sheet sheet = wb.createSheet(sheetName);
        CellStyle bodyStyle = bodyStyle(wb);
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < columNum; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(bodyStyle);
            }
        }
        return wb;
    }

    public static SXSSFWorkbook excelExport(SXSSFWorkbook wb, List<String> heads, List<List<String>> contents, int ignoreColumn, int ignoreRow, String sheetName) {
        int bodyListSize;
        if (contents == null) {
            bodyListSize = 0;
        } else {
            bodyListSize = contents.size();
        }
        //wb = standardizedExcel(wb, heads.size() + ignoreColumn, bodyListSize + ignoreRow + 1, sheetName);
        Sheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 20);
        Row row = sheet.createRow(ignoreRow);
        // 填充表单内容
        CellStyle bodyStyle = bodyStyle(wb);
        // 定义表头
        for (int i = 0; i < heads.size(); i++) {
            Cell cell = row.createCell(i + ignoreColumn);
            String value = heads.get(i);
            cell.setCellValue(value == null ? "" : value);
            cell.setCellStyle(bodyStyle);
        }
        if (contents != null) {
            for (int i = 0; i < contents.size(); i++) {
                Row row1 = sheet.createRow(ignoreRow + i + 1);
                List<String> content = contents.get(i);
                for (int j = 0; j < content.size(); j++) {
                    Cell cell = row1.createCell(j + ignoreColumn);
                    String value = contents.get(i).get(j);
                    cell.setCellValue(value == null ? "" : value);
                    cell.setCellStyle(bodyStyle);
                }
            }
        }
        return wb;
    }


}
