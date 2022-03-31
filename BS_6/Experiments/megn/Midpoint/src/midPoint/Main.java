package midPoint;

import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter x1: ");
		double x1 = scan.nextDouble();
		System.out.println("Enter y1: ");
		double y1 = scan.nextDouble();
		System.out.println("Enter x2: ");
		double x2 = scan.nextDouble();
		System.out.println("Enter y2: ");
		double y2 = scan.nextDouble();
		Point one = new Point(x1, y1);
		Point two = new Point(x2, y2);
		Point mid = new Point(one.midpoint(two));
		System.out.println("The midpoint is: (" + mid.getX() + ", " + mid.getY() + ")");
	}
}
