package com.spring.practice.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.practice.model.Tab1;
import com.spring.practice.model.Tab2;
import com.spring.practice.service.DataService;
import com.spring.practice.util.DataUtil;

@RestController
@RequestMapping("/DataCompareApp")
public class DataCompareController {
	Object[] columnNames = new Object[] { "PROP_ID", "APP_ID", "APP_NAME", "PROP_VALUE" };

	Map<Integer, Object[]> sheet1Map = new TreeMap<Integer, Object[]>();
	Map<Integer, Object[]> sheet2Map = new TreeMap<Integer, Object[]>();
	Map<Integer, Object[]> sheet3Map = new TreeMap<Integer, Object[]>();
	Map<Integer, Object[]> sheet4Map = new TreeMap<Integer, Object[]>();

	int sheet1Key = 1, sheet2Key = 1, sheet3Key = 1, sheet4Key = 1;

	@Autowired
	private DataService dataService;

	@GetMapping("/tab1Data")
	public ResponseEntity<List<Tab1>> tab1Data() {
		List<Tab1> listTab1 = dataService.getTab1Data();
		for (Tab1 tab1 : listTab1) {
			System.out.println(tab1);
		}
		return new ResponseEntity<List<Tab1>>(listTab1, HttpStatus.OK);
	}

	@GetMapping("/tab2Data")
	public ResponseEntity<List<Tab2>> tab2Data() {
		List<Tab2> listTab2 = dataService.getTab2Data();
		for (Tab2 tab2 : listTab2) {
			System.out.println(tab2);
		}
		return new ResponseEntity<List<Tab2>>(listTab2, HttpStatus.OK);
	}

	@GetMapping("/exportDataToExcel")
	public ResponseEntity<Void> exportDataToExcel() {
		sheet1Map.put(0, columnNames);
		sheet2Map.put(0, columnNames);
		sheet3Map.put(0, columnNames);
		sheet4Map.put(0, columnNames);

		List<Tab1> tab1List = dataService.getTab1Data();
		Map<String, String> tab1Map = DataUtil.tab1ListDataToHashMap(tab1List);
		Iterator<String> iterator1 = tab1Map.keySet().iterator();

		List<Tab2> tab2List = dataService.getTab2Data();
		Map<String, String> tab2Map = DataUtil.tab2ListDataToHashMap(tab2List);
		Iterator<String> iterator2 = tab2Map.keySet().iterator();

		while (iterator1.hasNext()) {
			String key1 = iterator1.next();
			String val1 = tab1Map.get(key1);
			String val2 = tab2Map.get(key1);

			// Sheet1 - All those keys and values matching in Tab1 and Tab2
			if (tab2Map.containsKey(key1) && val1.equals(val2)) {
				sheet1Map.put(sheet1Key, new Object[] { key1.split("-")[0], key1.split("-")[1], key1.split("-")[2], val1 });
				sheet1Key++;
			}

			// Sheet4 - Same keys with different values
			if (tab2Map.containsKey(key1) && !val1.equals(val2)) {
				sheet4Map.put(sheet4Key, new Object[] { key1.split("-")[0], key1.split("-")[1], key1.split("-")[2], val1 });
				sheet4Key++;
			}

			// Sheet2 - Tab1 data missing in Tab2
			if (!tab2Map.containsKey(key1)) {
				sheet2Map.put(sheet2Key, new Object[] { key1.split("-")[0], key1.split("-")[1], key1.split("-")[2], val1 });
				sheet2Key++;
			}
		}

		while (iterator2.hasNext()) {
			String key2 = iterator2.next();
			String val2 = tab2Map.get(key2);

			// Sheet3 - Tab2 data missing in Tab1
			if (!tab1Map.containsKey(key2)) {
				sheet3Map.put(sheet3Key, new Object[] { key2.split("-")[0], key2.split("-")[1], key2.split("-")[2], val2 });
				sheet3Key++;
			}
		}

		DataUtil.writeDataToSheet("Sheet1", sheet1Map);
		DataUtil.writeDataToSheet("Sheet2", sheet2Map);
		DataUtil.writeDataToSheet("Sheet3", sheet3Map);
		DataUtil.writeDataToSheet("Sheet4", sheet4Map);
		DataUtil.writeWorkBookToFile();

		System.out.println("Data exported to Excel successfully");

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
