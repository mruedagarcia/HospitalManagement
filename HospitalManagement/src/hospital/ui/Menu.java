package hospital.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to our Hospital");
		try {
			do {

				System.out.println("1.Register a patient:");
				System.out.println("2.Register a doctor:");
				System.out.println("3.Register a nurse");
				System.out.println("4.List the patients of a doctor:");
				System.out.println("5.List the doctors of a patient:");
				System.out.println("6.Diagnose a patient:");
				System.out.println("7.Call a family member:");
				System.out.println("8.Discharge a patient:");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Introduce an option:<------\n");
				switch (choice) {
				case 1: {

					break;
				}
				case 2: {

					break;
				}
				case 3: {

					break;
				}
				case 4: {

					break;
				}
				case 5: {

					break;
				}
				case 6: {
					break;
				}
				case 7: {
					break;
				}
				case 8: {
					break;
				}
				case 0: {
					System.exit(0);// close the menu
				}
				default: {

					break;// close the loop
				}
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
