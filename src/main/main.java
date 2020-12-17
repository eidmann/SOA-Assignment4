package main;

public class main{
	public static void main(String []args) {
		test test = new test();
		test1 test1 = new test1();
		
		try {
		test.main(args);
		test1.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}