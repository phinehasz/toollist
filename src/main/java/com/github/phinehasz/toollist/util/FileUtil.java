package com.github.phinehasz.toollist.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhhiyp
 * @version : FileUtil.java 2020-04-28 23:27
 */
public class FileUtil {

	public static List<String> readLines(File file){
		try {
			return FileUtils.readLines(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static void writeStringToFile(String targetFileName, String content){
		try {
			FileUtils.writeStringToFile(new File(targetFileName), content);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
