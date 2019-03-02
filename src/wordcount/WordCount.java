package wordcount;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {
		File keyfile = new File("D:/J.txt");
		File file = new File("D:/1999.txt");
		Map<String, Integer> result = wordutil(keyfile, file);
		creatResult("D:/result.txt", result);
	}

	// 读取目标文件中的关键字,返回一个list<String>
	private static List<String> KeyWords(File file) throws IOException {
		System.out.println("读取关键字文件......");
		List<String> list = new LinkedList<>(); // 用于接收关键字的容器
		Scanner scan1 = new Scanner(file,"GBK"); // 建立一个读取对象
		while (true) {
			if (scan1.hasNext() == false)
				break; // hasNext() 判断是否有后文
			String s = scan1.nextLine(); // 读取下一行
			//System.out.println(s);
			list.add(s.trim());
		}
		scan1.close();
		System.out.println("读取关键字文件结束!");
		return list;
	}

	// 对目标文件进行检索,并输出int值
	private static int Wordcount(File file, String keyword) throws IOException {
		Scanner scan = new Scanner(file, "utf-8"); // 对文本编码格式进行改进
		int k = 0;
		while (true) {
			if (scan.hasNext() == false)
				break; // hasNext() 判断是否有后文
			String s = scan.nextLine(); // 读取下一行
			//System.out.println(s);
			if (s.contains(keyword)) { // 判断是否存在
				k++;
				if(k > 0){
					System.out.println(keyword);
				}
			}
		}
		scan.close();
		return k;
	}

	// 在目标文件中索引keywords值
	private static Map<String, Integer> wordutil(File keyfile, File file) {
		System.out.println("关键字检索中.......");
		Map<String, Integer> results = new LinkedHashMap<>(); // 建立一个结果集
		List<String> KeyWords;
		try {
			KeyWords = KeyWords(keyfile);
			for (String keyword : KeyWords) {
				int counts = 0;
				counts = Wordcount(file, keyword);
				results.put(keyword, counts); // 写入结果集
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("关键字检索结束!");
		return results;
	}

	// 输出指定目录文件
	private static void creatResult(String path, Map<String, Integer> result) {
		System.out.println("输出结果中.......");
		File file = new File(path); // 建立一个路径
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os);
			for (String keyword : result.keySet()) {
				String word = keyword + " " + result.get(keyword)+"\r\n";
				byte[] bt = word.getBytes("utf-8");
				bos.write(bt);
			}
			bos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("结果输出完成!");
	}
}
