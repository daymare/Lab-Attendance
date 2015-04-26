/*
 * 
 * 
 */


public class Main {
	private static final String DEFAULT_FILEPATH = "res/user.txt";
	
	private static Attendance console;
	
	public static void main(String[] args) {
		if(args != null) {
			console = new Attendance(DEFAULT_FILEPATH);
		}else {
			console = new Attendance(args[0]);
		}
	}
}
