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

	// ��ȡĿ���ļ��еĹؼ���,����һ��list<String>
	private static List<String> KeyWords(File file) throws IOException {
		System.out.println("��ȡ�ؼ����ļ�......");
		List<String> list = new LinkedList<>(); // ���ڽ��չؼ��ֵ�����
		Scanner scan1 = new Scanner(file,"GBK"); // ����һ����ȡ����
		while (true) {
			if (scan1.hasNext() == false)
				break; // hasNext() �ж��Ƿ��к���
			String s = scan1.nextLine(); // ��ȡ��һ��
			//System.out.println(s);
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
			//System.out.println(s);
			if (s.contains(keyword)) { // �ж��Ƿ����
				k++;
				if(k > 0){
					System.out.println(keyword);
				}
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
				results.put(keyword, counts); // д������
			}
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
}
