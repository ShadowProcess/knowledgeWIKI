package cn.itcast.utils;

import java.io.File;
import java.util.UUID;

public class FileUploadUtils {

	// 得到上传文件真实名称 c:\a.txt a.txt
	public static String getRealName(String filename) {

		int index = filename.lastIndexOf("\\") + 1;

		return filename.substring(index);

	}

	// 获取随机名称 a.txt
	public static String getUUIDFileName(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {

			return UUID.randomUUID() + filename.substring(index);
		} else {
			return UUID.randomUUID().toString();
		}
	}

	// 目录分离算法
	public static String getRandomDirectory(String filename) {

		int hashcode = filename.hashCode();

		System.out.println(Integer.toBinaryString(hashcode));

		int a = hashcode & 0xf;

		hashcode = hashcode >>> 4;

		int b = hashcode & 0xf;

		return "/" + a + "/" + b;

	}

}
