package utilities;

public class TextExtension {

	public static String toFirstCase(String string) {
		string = string.toLowerCase();
		return java.lang.Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
	
}
