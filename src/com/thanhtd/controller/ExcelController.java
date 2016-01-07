/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import com.thanhtd.model.CauHoi;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Thanh Tran
 */
public class ExcelController {

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return (int) cell.getNumericCellValue();
            default:
                return null;
        }
    }

    public static List<CauHoi> readFromExcelFile(File file) {
        try {
            List<CauHoi> listCauHoi = new LinkedList<>();
            FileInputStream inputStream = new FileInputStream(file);

            Workbook workBook = null;
            if (file.getName().endsWith("xlsx")) {
                workBook = new XSSFWorkbook(inputStream);
            } else {
                workBook = new HSSFWorkbook(inputStream);
            }
            Sheet firstSheet = workBook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                CauHoi cauHoi = new CauHoi();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            cauHoi.setNoiDung((String) getCellValue(nextCell));
                            break;
                        case 1:
                            cauHoi.setTraLoi1((String) getCellValue(nextCell));
                            break;
                        case 2:
                            cauHoi.setTraLoi2((String) getCellValue(nextCell));
                            break;
                        case 3:
                            cauHoi.setTraLoi3((String) getCellValue(nextCell));
                            break;
                        case 4:
                            cauHoi.setTraLoi4((String) getCellValue(nextCell));
                            break;
                        case 5:
                            cauHoi.setDapAn((int) getCellValue(nextCell));
                            break;
                    }
                }
                listCauHoi.add(cauHoi);
            }
            workBook.close();
            inputStream.close();
            return listCauHoi;
        } catch (IOException ex) {
            //Logger.getLogger(ExcelController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while executing readFromExcelFile function on ExcelController.");
            return null;
        }
    }
}
