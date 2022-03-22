package hospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.DateTimeException;

import hospital.pojos.DateOfBirth;

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
	
	public static DateOfBirth readDate(String text) {
		System.out.println(text);
		while(true) {
			try {
				int year;
				year = readInt("Introduce year: ");
				if(year<=0) {
					do {
						year=readInt("Incorrect year, please introduce a correct one:");
						
					}while(year<=0);
				}
				int month = readInt("Introduce month as a number:");
				int day = readInt("Introduce a day: ");
				DateOfBirth dob = new DateOfBirth(year, month, day);
				return dob;
			}catch(DateTimeException e) {
				System.out.println("Incorrect date");
			}
		}
	}
}
