package Ex1;

public class meTemptest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "+1.0x+1.0";
		//		String s5 = "5+2x-3.3x";
		String s6="-1.0x^4+2.4x^2+3.1";
		String s7="max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5"
				+ " -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x"
				+ " -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)"
				+ ",-1.0x^4 +2.4x^2 +3.1))"
				+ ",-1.0x^4 +2.4x^2 +3.1),+0.1x^5"
				+ " -1.2999999999999998x +5.0)";
		//		//String[] s3 = {"x+3","x-2", "x-4"};
		//		//String ZERO="0";
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		//		//Polynom p3 = new Polynom(s3[0]);
		//		//Polynom p4=new Polynom(ZERO);
		//		Polynom p5=new Polynom(s5);
		//ComplexFunction cf3 = new ComplexFunction(p3);
		//ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf = new ComplexFunction("mul", p1,p2);
		//		ComplexFunction cf = new ComplexFunction("div", p1,p5);
		//		ComplexFunction toCpoy=(ComplexFunction) cf.copy();
		//		cf.mul(p2);
		String s ="plus(div(+1.0x+1.0,mul(x^2+4,+1.0x-4.0)),2.0)";
		String s11="plus(x^5+5x+6,x^2+9x+8)";
		String s12="plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)";
		//function cf1 =cf.initFromString(s11);

		function cf2 =cf.initFromString(s7);
		String ans=cf2.toString();
		System.err.println();
		System.out.println("f(x): "+ans);
		double fX=cf2.f(2);
		System.out.println("f(2) :"+fX);
		
	}
}
