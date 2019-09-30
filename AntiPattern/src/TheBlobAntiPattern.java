
public class TheBlobAntiPattern {

	private static int x;
	private static int y;
	private static int z;
	
	public static void setX(int num) {
		x = num;
	}
	
	public static void setY(int num) {
		y = num;
	}
	
	public static void setZ(int num) {
		z = num;
	}
	
	public int distXY() {
		return y-x;
	}
	
	public int distYZ() {
		return z-y;
	}
	
	public int distXZ() {
		return z-x;
	}
	
	public boolean isIsosceles() {
		if(x==y && x!=z && y!=z)
			return true;
		return false;
	}
	
	public boolean isEquilateral() {
		if(x==y && x==z && y==z)
			return true;
		return false;
	}
	
	public int pythagoreanTheorem() {
		return x*x + y*y + z*z;
	}
}
