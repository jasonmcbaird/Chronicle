package utilities;

public class Logger {
	
	private static int printSeverity = 0;
	private static Logger instance = null;
	
	protected Logger() {
		// You can't instantiate Logger directly.
	}
	
	public static Logger get() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public static void print(String string, int severity) {
		if(severity >= printSeverity) {
			System.out.println(string);
		}
	}
	
	public static void print(String string) {
		print(string, 0);
	}
	
	public static void setSeverity(int i) {
		printSeverity = i;
	}

}