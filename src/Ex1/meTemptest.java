package Ex1;

public class meTemptest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		//ComplexFunction cf3 = new ComplexFunction(p3);
		//ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf = new ComplexFunction("mul", p1,p2);

	}
}
