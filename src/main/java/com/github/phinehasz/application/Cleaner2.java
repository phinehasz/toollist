package com.github.phinehasz.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.phinehasz.toollist.convertor.CsvConvertor;
import com.github.phinehasz.toollist.util.FileUtil;
import com.github.phinehasz.toollist.util.StringUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhhiyp
 * @version : Cleaner2.java 2020-04-28 23:02
 */
public class Cleaner2 {

	public static void main(String[] args) throws Exception {
		createCSV("D:\\Desktop\\dataset\\pheme-rnr-dataset-merge\\sydneysiege\\rumours.json",
				"D:\\Desktop\\dataset\\pheme-rnr-dataset-merge\\sydneysiege\\rumours.csv");
		createCSV("D:\\Desktop\\dataset\\pheme-rnr-dataset-merge\\sydneysiege\\non-rumours.json",
				"D:\\Desktop\\dataset\\pheme-rnr-dataset-merge\\sydneysiege\\non-rumours.csv");
		System.out.println("done");
	}

	private static void createCSV(String dir, String fileName) throws IOException {
		File file = new File(dir);
		JSONArray array = new JSONArray();
		List<String> lines = FileUtil.readLines(file);
		for (String line : lines) {
			JSONObject array1 = JSON.parseObject(line);
			array.add(array1);
		}
		FileUtil.writeStringToFile(fileName, CsvConvertor.toString(array));

	}

	private static void createJson6(String dir, String name) throws IOException {
		File file = new File(dir);
		File[] files = file.listFiles();
		JSONArray array = new JSONArray();
		for (File child : files) {
			String content = FileUtils.readFileToString(child);
			JSONObject array1 = JSON.parseObject(StringUtil.decodeUnicode(content));
			array.add(array1);
			System.out.println("read " + child.getAbsolutePath());
		}
		FileUtils.writeStringToFile(new File(name),
				CsvConvertor.toString(array));

	}
}
