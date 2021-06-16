/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.apache.poi.hssf.usermodel.HSSFCellStyle
 *  org.apache.poi.hssf.usermodel.HSSFRichTextString
 *  org.apache.poi.hssf.usermodel.HSSFSheet
 *  org.apache.poi.hssf.usermodel.HSSFWorkbook
 *  org.apache.poi.ss.usermodel.BorderStyle
 *  org.apache.poi.ss.usermodel.Cell
 *  org.apache.poi.ss.usermodel.CellStyle
 *  org.apache.poi.ss.usermodel.FillPatternType
 *  org.apache.poi.ss.usermodel.HorizontalAlignment
 *  org.apache.poi.ss.usermodel.IndexedColors
 *  org.apache.poi.ss.usermodel.RichTextString
 *  org.apache.poi.ss.usermodel.Row
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgreport.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.jeecg.modules.online.cgreport.service.CgReportExcelServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CgReportExcelServiceImpl implements CgReportExcelServiceI {

    @Override
    public HSSFWorkbook exportExcel(String title, Collection<?> titleSet, Collection<?> dataSet) {
        HSSFWorkbook hSSFWorkbook = null;
        try {
            if (titleSet == null || titleSet.size() == 0) {
                throw new Exception("读取表头失败！");
            }
            if (title == null) {
                title = "";
            }
            hSSFWorkbook = new HSSFWorkbook();
            HSSFSheet hSSFSheet = hSSFWorkbook.createSheet(title);
            int n2 = 0;
            int n3 = 0;
            Row row = hSSFSheet.createRow(n2);
            row.setHeight((short) 450);
            HSSFCellStyle hSSFCellStyle = this.a(hSSFWorkbook);
            List<Map<String, String>> list = (List<Map<String, String>>) titleSet;
            Iterator<?> iterator = dataSet.iterator();
            for (Map<String, String> map : list) {
                String fieldTxt = map.get("field_txt");
                Cell object2 = row.createCell(n3);
                object2.setCellValue(new HSSFRichTextString(fieldTxt));
                object2.setCellStyle(hSSFCellStyle);
                ++n3;
            }
            HSSFCellStyle hSSFCellStyle2 = this.c(hSSFWorkbook);
            while (iterator.hasNext()) {
                Map map;
                n3 = 0;
                row = hSSFSheet.createRow(++n2);
                map = (Map) iterator.next();
                for (Map<String, String> t : list) {
                    String fieldName = t.get("field_name");
                    String string = map.get(fieldName) == null ? "" : map.get(fieldName).toString();
                    Cell cell = row.createCell(n3);
                    HSSFRichTextString hSSFRichTextString = new HSSFRichTextString(string);
                    cell.setCellStyle(hSSFCellStyle2);
                    cell.setCellValue(hSSFRichTextString);
                    ++n3;
                }
            }
            for (int i2 = 0; i2 < list.size(); ++i2) {
                hSSFSheet.autoSizeColumn(i2);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return hSSFWorkbook;
    }

    private HSSFCellStyle a(HSSFWorkbook hSSFWorkbook) {
        HSSFCellStyle hSSFCellStyle = hSSFWorkbook.createCellStyle();
        hSSFCellStyle.setBorderLeft(BorderStyle.THIN);
        hSSFCellStyle.setBorderRight(BorderStyle.THIN);
        hSSFCellStyle.setBorderBottom(BorderStyle.THIN);
        hSSFCellStyle.setBorderTop(BorderStyle.THIN);
        hSSFCellStyle.setAlignment(HorizontalAlignment.CENTER);
        hSSFCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        hSSFCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return hSSFCellStyle;
    }

    private void a(int n2, int n3, HSSFWorkbook hSSFWorkbook) {
        HSSFSheet hSSFSheet = hSSFWorkbook.getSheetAt(0);
        HSSFCellStyle hSSFCellStyle = this.c(hSSFWorkbook);
        for (int i2 = 1; i2 <= n2; ++i2) {
            Row row = hSSFSheet.createRow(i2);
            for (int i3 = 0; i3 < n3; ++i3) {
                row.createCell(i3).setCellStyle(hSSFCellStyle);
            }
        }
    }

    private HSSFCellStyle b(HSSFWorkbook hSSFWorkbook) {
        HSSFCellStyle hSSFCellStyle = hSSFWorkbook.createCellStyle();
        hSSFCellStyle.setBorderLeft(BorderStyle.THIN);
        hSSFCellStyle.setBorderRight(BorderStyle.THIN);
        hSSFCellStyle.setBorderBottom(BorderStyle.THIN);
        hSSFCellStyle.setBorderTop(BorderStyle.THIN);
        hSSFCellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        hSSFCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return hSSFCellStyle;
    }

    private HSSFCellStyle c(HSSFWorkbook hSSFWorkbook) {
        HSSFCellStyle hSSFCellStyle = hSSFWorkbook.createCellStyle();
        hSSFCellStyle.setBorderLeft(BorderStyle.THIN);
        hSSFCellStyle.setBorderRight(BorderStyle.THIN);
        hSSFCellStyle.setBorderBottom(BorderStyle.THIN);
        hSSFCellStyle.setBorderTop(BorderStyle.THIN);
        return hSSFCellStyle;
    }
}

