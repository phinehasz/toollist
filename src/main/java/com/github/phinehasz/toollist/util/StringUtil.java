package com.github.phinehasz.toollist.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhhiyp
 * @version : StringUtil.java 2020-04-28 22:02
 */
public class StringUtil {
	/**
	 * unicode转成中文
	 * @param unicode
	 * @return
	 */
	public static String decodeUnicode(String unicode) {
		List<String> list = new ArrayList<>();
		String reg= "\\\\u[0-9,a-f,A-F]{4}";
		Pattern p = Pattern.compile(reg);
		Matcher m=p.matcher(unicode);
		while (m.find()){
			list.add(m.group());
		}
		for (int i = 0, j = 2; i < list.size(); i++) {
			String code = list.get(i).substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			unicode = unicode.replace(list.get(i),String.valueOf(ch));
		}
		return unicode;
	}

	/**
	 * 中文转Unicode
	 * @param cn
	 * @return
	 */
	public static String cnToUnicode(String cn) {
		char[] chars = cn.toCharArray();
		String returnStr = "";
		for (int i = 0; i < chars.length; i++) {
			returnStr += "\\u" + Integer.toString(chars[i], 16);
		}
		return returnStr;
	}
}
