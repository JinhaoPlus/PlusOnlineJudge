package com.jinhaoplus.oj.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PropertiesUtil {

	private static java.util.Properties pros = new java.util.Properties();
	private static List<String> filePaths = new ArrayList<String>();
	static{
		filePaths.add("global.properties");
	}
	public PropertiesUtil() {
		if (filePaths != null) {
			for (int i = 0; i < filePaths.size(); i++) {
				String filePath = filePaths.get(i);
				InputStream in = null;
				URL url = Thread.currentThread().getContextClassLoader()
						.getResource(filePath);
				if (null == url) {
					url = this.getClass().getClassLoader()
							.getResource(filePath);
				}
				if (null == url) {
					url = this.getClass().getResource(filePath);
				}

				// 解决文件路径可能出现空格的问题
				String path = url.getFile();
				if (!"".equals(path)) {
					path = path.replace("%20", " ");
				}

				try {
					in = new BufferedInputStream(new FileInputStream(path));
					pros.load(in);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	public static String getProperty(String key) {
		return pros.getProperty(key);
	}

}
