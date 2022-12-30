package com.example.excelHandling;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelData {

    public static Object getData(int rowNum, int colNum) throws IOException {
        File src = new File("C:\\Users\\Akhila.s\\Downloads\\Book.xlsx");
        FileInputStream fileInputStream = new FileInputStream(src);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter =new DataFormatter();
        Object value=dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        System.out.println(value);
        return value;

    }

}
