package com.shizhichao.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//文件工具类
public class FileUtil {

	/**
	 * 根据文件，截取扩展名
	 * @param fileName "aa.png"
	 * @return
	 */
	public static String getExtName(String fileName) {
		//处理空异常
		if(fileName==null || "".equals(fileName)) {
			throw new RuntimeException("文件名不能为空");
		}
		if(fileName.indexOf(".")<=-1) {
			throw new RuntimeException(fileName+":该文件名没有包含扩展名");
		}
		String extName = fileName.substring(fileName.lastIndexOf("."));
		return extName;
	}
	/**
	 * 获取系统当前用户目录
	 * @return
	 */
	public static String getSystemUserHome() {
		return System.getProperty("user.home");
	}
	/**
	 * @Title: getSystemTempDirectory   
	 * @Description: 操作系统临时目录
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}
	/**
	 * @Title: readTextFileByLine   
	 * @Description: 读取文件内容   
	 * @param: @param pathname
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	
	
//	public static String readTextFileByLine(String pathname) {
//		BufferedReader br = null;
//		StringBuffer sb = new StringBuffer();
//		String str="";
//		try {
//			br = new BufferedReader(new FileReader(new File(pathname)));
//		while((str=br.readLine())!=null) {
//			sb.append(str);
//			sb.append("\r\n");
//		}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}finally {
//			StreamUtil.closeAll(br);
//		}
//		return sb.toString();
//	}
	
	
	
	/*
	 * 方法1：批量关闭流，参数能传无限个。(3分) 可变参数 例如传入FileInputStream对象、JDBC中Connection对象都可以关闭。
	 */
	public static void closeAll(AutoCloseable... autoCloseable) {

		if (null != autoCloseable && autoCloseable.length > 0) {
			for (AutoCloseable autoCloseable2 : autoCloseable) {
				try {
					autoCloseable2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	/*
	 * 方法2：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容(3分)，要求方法内部调用上面第1个方法关闭流(3分)
	 */
	public static String readTextFile(InputStream src) {

		byte[] b = new byte[1024];
		int x = 0;
		String str = "";
		try {
			while ((x = src.read(b)) != -1) {
				str += new String(b, 0, x, "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关流
			closeAll(src);
		}
		return str;
	}

	/*
	 * 方法3：传入文本文件对象，返回该文件内容(3分)，并且要求内部调用上面第2个方法(5分)。* 这是典型的方法重载，记住了吗？少年…
	 */
	public static String readTextFileByLine(File txtFile) throws FileNotFoundException {
		return readTextFile(new FileInputStream(txtFile));
	}
	public static String readTextFileByLine(String pathname) throws FileNotFoundException {
		return readTextFileByLine(new File(pathname));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @Title: readTextFileOfList   
	 * @Description: 按行读取文件内容到list集合   
	 * @param: @param pathname
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	public static List<String> readTextFileOfList(String pathname) {
		BufferedReader br = null;
		List<String> strList = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(new File(pathname)));
			String i="";
			while((i=br.readLine())!=null){
				strList.add(i);
			};
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			StreamUtil.closeAll(br);
		}
		return strList;
	}
	/**
	 * @Title: deleteFile   
	 * @Description: 递归删除文件   
	 * @param: @param file      
	 * @return: void      
	 * @throws
	 */
	public static void deleteFile(File file) {
		if(file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for(File theFile:listFiles) {
				deleteFile(theFile);
			}
			file.delete();
		}else {
			file.delete();
		}
	}
	/**
	 * @Title: deleteFile   
	 * @Description: 递归删除文件  
	 * @param: @param filePath      
	 * @return: void      
	 * @throws
	 */
	public static void deleteFile(String filePath) {
		deleteFile(new File(filePath));
	}
	/**
	 * @Title: getFileSize   
	 * @Description: 获得文件大小
	 * 返回文件以指定单位大小表示
	 * File a.txt=2k  
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getFileSize(File file) {
		long length = file.length();
		double len = length/1024.0;
//		return Math.round((length/1024.0))+"kb";
		return String.format("%.2f",len)+"kb";
	}
	
	public static String getFileSize(String fileFullName) {
		return getFileSize(new File(fileFullName));
	}
	
	public static void main(String[] args) {
//		System.out.println(FileUtil.readTextFileOfList("C:\\Users\\VULCAN\\Desktop\\car.txt"));
		
		
		System.out.println(getExtName("C:\\\\Users\\\\VULCAN\\\\Desktop\\\\car.txt"));
		
	}
	
	
}
