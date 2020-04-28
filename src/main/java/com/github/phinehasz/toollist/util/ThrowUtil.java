package com.github.phinehasz.toollist.util;

/**
 * @author zhhiyp
 * @version : ThrowUtil.java 2020-04-28 23:31
 */
public class ThrowUtil {

	/**
	 * 除显式异常外,其他异常自动catch
	 * @param method
	 */
	public static void catchOf(Runnable method){
		try{
			method.run();
		}catch (Throwable e){
			e.printStackTrace();
		}
	}
}
