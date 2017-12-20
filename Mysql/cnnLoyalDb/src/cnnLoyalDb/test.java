package cnnLoyalDb;

public class test {

	public static void main(String[] args) {
		
		String test = "注册测绘师资格考试实施办法-(2007-01-11)";
		System.out.println(test);
		String[] mes = test.split("-[(]");
		for(String i : mes) {
			i = i.replace(")", "");
			System.out.println(i);
	    } 
	}



}
