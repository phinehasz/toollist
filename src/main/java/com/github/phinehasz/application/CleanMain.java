package com.github.phinehasz.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.phinehasz.toollist.convertor.CsvConvertor;
import com.github.phinehasz.toollist.util.StringUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhhiyp
 * @version : CleanMain.java 2020-04-26 22:01
 */
public class CleanMain {

	public static void main(String[] args) throws Exception {
		createJson6("D:\\Desktop\\dataset\\6\\6\\rumor",
				"D:\\Desktop\\dataset\\6\\6\\non-rumor.json");
		System.out.println("done");
	}

	private static void createJson6(String dir, String name) throws IOException {
		File file = new File(dir);
		File[] files = file.listFiles();
		JSONArray array = new JSONArray();
		int a = 200;
		int j = 0;
		for (File child : files) {
			String content = FileUtils.readFileToString(child);
			JSONArray array1 = JSON.parseArray(StringUtil.decodeUnicode(content));
			array.addAll(array1);
			System.out.println("read " + child.getAbsolutePath());
			a--;

			if(a== 0){
				j++;
				FileUtils.writeStringToFile(new File(String.format("D:\\Desktop\\dataset\\6\\6\\rumor%d.json",j)),
						CsvConvertor.toString(array));
				a = 200;
			}
		}

	}

	private static void createJson(String dir, String name) throws IOException {
		File file = new File(dir);
		File[] files = file.listFiles();
		List<String> results = new ArrayList<>();
		for (File child : files) {
			File[] files1 = child.listFiles();
			String tweetId = child.getName();
			int reactionsNum = 0;
			System.out.println("read " + child.getAbsolutePath());
			for (File file1 : files1) {
				if("reactions".equals(file1.getName())){
					reactionsNum = file1.list().length;
				}else if ("source-tweet".equals(file1.getName())) {
					File[] sourceTweets = file1.listFiles();
					if(sourceTweets.length >0){
						File sourceTweet = sourceTweets[0];
						byte[] bytes = Files.readAllBytes(Paths.get(sourceTweet.toURI()));
						JSONObject jsonObject = JSON.parseObject(new String(bytes), Feature.OrderedField);
						jsonObject.put("reactions", reactionsNum);
						jsonObject.put("tweetId", tweetId);
						results.add(JSON.toJSONString(jsonObject, SerializerFeature.SortField));
					}
				}
			}
		}

		Files.write(Paths.get(name),
				results, StandardOpenOption.CREATE);
	}
}
