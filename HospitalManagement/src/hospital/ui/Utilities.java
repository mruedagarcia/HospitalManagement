package hospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public static boolean readBoolean(String t) throws IOException {
		System.out.println(t);
		while (true) {
			String stringReaded;
			stringReaded = console.readLine();
			Boolean b;
			if (stringReaded.equalsIgnoreCase("no")) {
				b = false;
			} else if (stringReaded.equalsIgnoreCase("yes")) {
				b = true;
			}
		}
	}

	public static LocalDate readDate() {
		while (true) {
			try {
				String date = readString("Introduce a date (yyyy-mm-dd):");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dob = LocalDate.parse(date, dtf);
				return dob;
			} catch (DateTimeException e) {
				System.out.println("Incorrect date");
			}
		}
	}
}
