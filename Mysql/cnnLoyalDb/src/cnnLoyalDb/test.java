package cnnLoyalDb;

public class test {

	public static void main(String[] args) {
		
		String test = "ע����ʦ�ʸ���ʵʩ�취-(2007-01-11)";
		System.out.println(test);
		String[] mes = test.split("-[(]");
		for(String i : mes) {
			i = i.replace(")", "");
			System.out.println(i);
	    } 
	}



}
