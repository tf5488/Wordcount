package wordcount;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test {

	    static int m=1;
	    
	    static void search(File a,String x) throws IOException{//���ļ�a�е�ÿ���в���x
	        Scanner scan = new Scanner(a,"gbk");  // ���ı������ʽ���иĽ�
	        int k = 0;
	        while(true){    
	            if(scan.hasNext()==false) break;  // hasNext() �ж��Ƿ��к���
	            String s = scan.nextLine(); // ��ȡ��һ��
	            k++;
	            if(s.contains(x)){  // �ж��ǹ�����
	                String ss =m +".�ļ�:"+ a.getPath() + " ��" + k + "�� \n  ���ݣ�" + s;
	                System.out.println(ss);
	                m++;
	            }
	        } 
	        
	        Scanner scan1 = new Scanner(a,"utf-8");  // ���ı������ʽ���иĽ�
	        int k1 = 0;
	        while(true){    
	            if(scan1.hasNext()==false) break;
	            String s1 = scan1.nextLine();            
	            k1++;
	            if(s1.contains(x)){
	                String ss1 =m +".�ļ�:"+ a.getPath() + " ��" + k1 + "�� \n  ���ݣ�" + s1;
	                System.out.println(ss1);
	                m++;
	            }
	        } 
	    }
	    static void f(File a,String s)throws IOException{//��a�������ļ��в��Һ���s����
	        
	        File[] ff = a.listFiles();  // ��ȡĿ¼�µ��ļ��б�
	        if(ff==null) return;  // ���listΪnull,��������
	        for(File it : ff){
	            if(it.isFile()){//��a���ļ���ֱ�Ӳ���
	                search(it,s);
	            }
	            if(it.isDirectory()){//��a��Ŀ¼�������Ŀ¼�µ�Ŀ¼���ļ���������
	                f(it,s);
	            }
	        } 
	        
	    }

	    public static void main(String[] args)throws IOException {
	        f(new File("d:\\"),"����");

	    }

	
}
