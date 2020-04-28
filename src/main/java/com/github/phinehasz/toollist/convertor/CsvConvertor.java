package com.github.phinehasz.toollist.convertor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * 利用fastjson的JSONArray去转换为csv
 * @author zhhiyp
 * @version : ConvertCsv.java 2020-04-28 22:35
 */
public class CsvConvertor {
	public CsvConvertor() {
	}

	/**
	 * json array转成 csv string
	 * @param ja
	 * @return
	 */
	public static String toString(JSONArray ja) {

		JSONObject jo = ja.getJSONObject(0);
		if (jo != null) {
			Set<String> strings = jo.keySet();
			JSONArray names = JSONArray.parseArray(JSON.toJSONString(strings));
			if (names != null) {
				return rowToString(names) + toString(names, ja);
			}
		}

		return null;
	}

	private static String rowToString(JSONArray jsonArray) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < jsonArray.size(); ++i) {
			if (i > 0) {
				sb.append(',');
			}
			Object object = jsonArray.get(i);
			if (object != null) {
				String string = object.toString();
				if (string.length() > 0 && (string.indexOf(44) >= 0 || string.indexOf(10) >= 0 || string.indexOf(13) >= 0 || string.indexOf(0) >= 0 || string.charAt(0) == '"')) {
					sb.append('"');
					int length = string.length();

					for (int j = 0; j < length; ++j) {
						char c = string.charAt(j);
						if (c >= ' ' && c != '"') {
							sb.append(c);
						}
					}

					sb.append('"');
				} else {
					sb.append(string);
				}
			}
		}

		sb.append('\n');
		return sb.toString();
	}

	private static String toString(JSONArray names, JSONArray ja) {
		if (names != null && names.size() != 0) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < ja.size(); ++i) {
				JSONObject jo = ja.getJSONObject(i);
				if (jo != null) {
					sb.append(rowToString(toJSONArray(jo, names)));
				}
			}

			return sb.toString();
		} else {
			return null;
		}
	}

	private static JSONArray toJSONArray(JSONObject obj, JSONArray names) {
		if (names != null && !names.isEmpty()) {
			JSONArray ja = new JSONArray();

			for (int i = 0; i < names.size(); ++i) {
				ja.add(obj.get(names.getString(i)));
			}

			return ja;
		} else {
			return null;
		}
	}
}


