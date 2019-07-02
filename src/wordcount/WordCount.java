package test;
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
	private static StringBuffer temp = new StringBuffer();
	private static int Count;

	public static void main(String[] args) {

		// 获得文件的绝对路径
		// 需要检索的文件总目录 "E:/test/" 这个目录下的所有文件都会被检索，无所谓多少个文件夹
		List<String> fileList = findFiles("E:/test/");
		System.out.println(fileList.toString());
		
		// 进行检索
		// 关键词的位置是写死的。
		String keyfile = "E:/xjxj.txt";
		for (String file : fileList) {
			Word(keyfile,file);
		}
	}
	/**
	 * 這個用於輸出每個文件的匹配結果文件
	 * @param keyfilePath
	 * @param filePath
	 */
	public static void Word(String keyfilePath,String filePath) {
		// TODO Auto-generated constructor stub
		Count = 0;
		temp = new StringBuffer();
		//匹配的词库文件
		File keyfile = new File(keyfilePath);
		//处理的词库文件
		File file = new File(filePath);
		Map<String, Integer> result = wordutil(keyfile, file);
		//生成的结果文件
		creatResult(filePath.replace(".", "result."), result);
		//生成汇总文件
		fileCount("E:/test/tj.txt",file.getName());
		
		System.out.println(result);
	}
	
	

	// 读取目标文件中的关键字,返回一个list<String>
	private static List<String> KeyWords(File file) throws IOException {
		System.out.println("读取关键字文件......");
		List<String> list = new LinkedList<>(); // 用于接收关键字的容器
		Scanner scan1 = new Scanner(file,"utf-8"); // 建立一个读取对象
		while (true) {
			if (scan1.hasNext() == false)
				break; // hasNext() 判断是否有后文
			String s = scan1.nextLine(); // 读取下一行
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
			if (s.contains(keyword)) { // 判断是否存在
				k++;
				System.out.println(s);
				temp.append(keyword+" ");
				System.out.println(keyword);
				System.out.println("查到了!");
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
				if(counts!=0){
					Count += counts;  // 统计最终的计数结果
					results.put(keyword, counts); // 写入结果集	
				}				
			}
			System.out.println(KeyWords);
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

		LCWJ(path,file);
		
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
	
	// 打印流程文件
	private static void LCWJ(String path,File file){
		System.out.println(path);		
		File files = new File(path.replace(".", "-R."));
		OutputStream os = null;
		BufferedOutputStream bos = null;
		System.out.println(temp.toString());
		try {
			if (!files.exists()) {
				files.createNewFile();
			}
			os = new FileOutputStream(files);
			bos = new BufferedOutputStream(os);
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			bos.write(temp.toString().getBytes("utf-8"));	
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
	}
	
	
	//生成的结果文件
	private static void fileCount(String path,String name){
		File file = new File(path); // 建立一个路径
		String word = name+"："+Count+"\r\n";
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file,true);
			bos = new BufferedOutputStream(os);
			bos.write(word.getBytes("utf-8"));
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
	}
	
	//用于读取目录下的文件
	private static List<String> findFiles(String path){
		List<String> list = new LinkedList<String>();
		File f = new File(path);
		if(f != null){
			File[] fs = f.listFiles();
			for (File fil : fs) {
				if(fil.isFile()){
					System.out.println(fil.getAbsolutePath());
					list.add(fil.getAbsolutePath());
				}else{
					findFiles(fil.getAbsolutePath());
				}
			}
		}
		return list;
	}
	
}
