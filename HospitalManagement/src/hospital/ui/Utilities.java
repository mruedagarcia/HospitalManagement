package hospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {
	private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	public static int readInt(String text) {
		int number;
		while (true) {
			try {

				number = Integer.parseInt(readString(text));
				return number;
			} catch (NumberFormatException error) {
				System.out.println("Error reading an int; please try again." + error);
			}
		}
	}

	public static String readString(String text) {
		System.out.print(text);
		while (true) {
			try {

				String stringReaded;
				stringReaded = console.readLine();
				return stringReaded;
			} catch (IOException error) {
				System.out.println("Error reading the String; please try again" + error);
			}
		}
	}
}
