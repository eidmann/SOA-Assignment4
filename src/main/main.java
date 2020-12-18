package main;

public class main {
	public static void main(String[] args) {
		test test = new test();
		test1 test1 = new test1();

		try {
			test.searchCourse();
			test1.searchCourseInfo();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}