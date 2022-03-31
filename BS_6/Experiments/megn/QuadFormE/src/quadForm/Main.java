package quadForm;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter A:");
			int A = scan.nextInt();
			System.out.println("Enter B:");
			int B = scan.nextInt();
			System.out.println("Enter C:");
			int C = scan.nextInt();
			double result1 = ((-B) - Math.sqrt((B*B)-(4*A*C)))/(2*A);
			double result2 = ((-B) + Math.sqrt((B*B)-(4*A*C)))/(2*A);
			System.out.println("The results are: \n" + result1 + "\n" + result2);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
}
