package com.spring.practice.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spring.practice.model.Tab1;
import com.spring.practice.model.Tab2;

public class DataUtil {

	private static XSSFWorkbook workbook = new XSSFWorkbook();

	public static void writeDataToSheet(String sheetName, Map<Integer, Object[]> sheetData) {
		XSSFSheet spreadsheet = workbook.createSheet(sheetName);
		XSSFRow row;

		Set<Integer> mapKeys = sheetData.keySet();
		int rowId = 0;

		for (Integer key : mapKeys) {
			row = spreadsheet.createRow(rowId++);
			Object[] objectArr = sheetData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

	}

	public static void writeWorkBookToFile() {
		// .xlsx is the format for Excel Sheets...
		// writing the workbook into the file...
		try {
			String filePath = "C:/Users/amzads/eclipse-workspace/SpringBootDataCompareApp/src/main/resources/Data.xlsx";
			File file = new File(filePath);
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> tab1ListDataToHashMap(List<Tab1> listTab1) {
		HashMap<String, String> hashMapTab1 = new HashMap<String, String>();
		for (Tab1 tab1 : listTab1) {
			hashMapTab1.put(tab1.getPropId() + "-" + tab1.getAppId() + "-" + tab1.getAppName(), tab1.getPropValue());
		}
		return hashMapTab1;
	}
	
	public static HashMap<String, String> tab2ListDataToHashMap(List<Tab2> listTab2) {
		HashMap<String, String> hashMapTab2 = new HashMap<String, String>();
		for (Tab2 tab2 : listTab2) {
			hashMapTab2.put(tab2.getPropId() + "-" + tab2.getAppId() + "-" + tab2.getAppName(), tab2.getPropValue());
		}
		return hashMapTab2;
	}

}
