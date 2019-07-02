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

		// ����ļ��ľ���·��
		// ��Ҫ�������ļ���Ŀ¼ "E:/test/" ���Ŀ¼�µ������ļ����ᱻ����������ν���ٸ��ļ���
		List<String> fileList = findFiles("E:/test/");
		System.out.println(fileList.toString());
		
		// ���м���
		// �ؼ��ʵ�λ����д���ġ�
		String keyfile = "E:/xjxj.txt";
		for (String file : fileList) {
			Word(keyfile,file);
		}
	}
	/**
	 * �@�����ݔ��ÿ���ļ���ƥ��Y���ļ�
	 * @param keyfilePath
	 * @param filePath
	 */
	public static void Word(String keyfilePath,String filePath) {
		// TODO Auto-generated constructor stub
		Count = 0;
		temp = new StringBuffer();
		//ƥ��Ĵʿ��ļ�
		File keyfile = new File(keyfilePath);
		//����Ĵʿ��ļ�
		File file = new File(filePath);
		Map<String, Integer> result = wordutil(keyfile, file);
		//���ɵĽ���ļ�
		creatResult(filePath.replace(".", "result."), result);
		//���ɻ����ļ�
		fileCount("E:/test/tj.txt",file.getName());
		
		System.out.println(result);
	}
	
	

	// ��ȡĿ���ļ��еĹؼ���,����һ��list<String>
	private static List<String> KeyWords(File file) throws IOException {
		System.out.println("��ȡ�ؼ����ļ�......");
		List<String> list = new LinkedList<>(); // ���ڽ��չؼ��ֵ�����
		Scanner scan1 = new Scanner(file,"utf-8"); // ����һ����ȡ����
		while (true) {
			if (scan1.hasNext() == false)
				break; // hasNext() �ж��Ƿ��к���
			String s = scan1.nextLine(); // ��ȡ��һ��
			list.add(s.trim());
		}
		scan1.close();
		System.out.println("��ȡ�ؼ����ļ�����!");
		return list;
		
	}

	// ��Ŀ���ļ����м���,�����intֵ
	private static int Wordcount(File file, String keyword) throws IOException {
		Scanner scan = new Scanner(file, "utf-8"); // ���ı������ʽ���иĽ�
		int k = 0;
		while (true) {
			if (scan.hasNext() == false)
				break; // hasNext() �ж��Ƿ��к���
			String s = scan.nextLine(); // ��ȡ��һ��
			if (s.contains(keyword)) { // �ж��Ƿ����
				k++;
				System.out.println(s);
				temp.append(keyword+" ");
				System.out.println(keyword);
				System.out.println("�鵽��!");
			}

		}
		scan.close();
		return k;
	}

	// ��Ŀ���ļ�������keywordsֵ
	private static Map<String, Integer> wordutil(File keyfile, File file) {
		System.out.println("�ؼ��ּ�����.......");
		Map<String, Integer> results = new LinkedHashMap<>(); // ����һ�������
		List<String> KeyWords;
		try {
			KeyWords = KeyWords(keyfile);
			for (String keyword : KeyWords) {
				int counts = 0;
				counts = Wordcount(file, keyword);
				if(counts!=0){
					Count += counts;  // ͳ�����յļ������
					results.put(keyword, counts); // д������	
				}				
			}
			System.out.println(KeyWords);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("�ؼ��ּ�������!");
		return results;
	}

	// ���ָ��Ŀ¼�ļ�
	private static void creatResult(String path, Map<String, Integer> result) {
		System.out.println("��������.......");
		File file = new File(path); // ����һ��·��

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
		System.out.println("���������!");
	}
	
	// ��ӡ�����ļ�
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
	
	
	//���ɵĽ���ļ�
	private static void fileCount(String path,String name){
		File file = new File(path); // ����һ��·��
		String word = name+"��"+Count+"\r\n";
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
	
	//���ڶ�ȡĿ¼�µ��ļ�
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
