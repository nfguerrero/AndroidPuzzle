package midPoint;

public class Point {
	double x;
	double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Point midpoint(Point p) {
		double xs = add(this.getX(), p.getX());
		double ys = add(this.getY(), p.getY());
		xs = xs/2;
		ys = ys/2;
		Point mid = new Point(xs, ys);
		return mid;
	}
	private double add(double x, double y) {
		return x+y;
	}

}
