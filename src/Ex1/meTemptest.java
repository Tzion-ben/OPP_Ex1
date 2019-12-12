package Ex1;

import java.io.IOException;
import java.util.Iterator;

public class meTemptest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String s15 = "3.1+2.4x^2-x^4";
		String s16 = "+1.0x+1.0";
//		//		String s5 = "5+2x-3.3x";
//		String s6="-1.0x^4+2.4x^2+3.1";
//		String s7="max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5"
//				+ " -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x"
//				+ " -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)"
//				+ ",-1.0x^4 +2.4x^2 +3.1))"
//				+ ",-1.0x^4 +2.4x^2 +3.1),+0.1x^5"
//				+ " -1.2999999999999998x +5.0)";
//		//		//String[] s3 = {"x+3","x-2", "x-4"};
//		//		//String ZERO="0";
		Polynom p1 = new Polynom(s15);
		Polynom p2 = new Polynom(s16);
//		//		//Polynom p3 = new Polynom(s3[0]);
//		//		//Polynom p4=new Polynom(ZERO);
//		//		Polynom p5=new Polynom(s5);
//		//ComplexFunction cf3 = new ComplexFunction(p3);
//		//ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
//		ComplexFunction cf1 = new ComplexFunction("mul", cf,p1);
//		try {
//			ComplexFunction cf3 = new ComplexFunction("mul", p1,p2);
//		}
//		catch (Exception e) {
//			System.out.println("Not good function !!");
//		}

		//boolean checkCF1CF3=cf1.equals(cf3);
		//System.out.println("checkCF1CF3  "+checkCF1CF3);
		//		ComplexFunction cf = new ComplexFunction("div", p1,p5);
		//		ComplexFunction toCpoy=(ComplexFunction) cf.copy();
		//		cf.mul(p2);
//		String s ="plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)";
//		String s11="plus(x^5+5x+6,x^2+9x+8)";
//		String s12="plus(x^5+5x+6,x^2+9x+8)";
//		String s13="plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)";
//		//function cf1 =cf.initFromString(s11);
//		function cf2 =cf.initFromString(s);
//		int x=10;
//
//		String ans=cf2.toString();
//		System.out.println();
//		System.out.println("f(x): "+ans);
//		double fX=cf2.f(3);
//		System.out.println("f(2) :"+fX);

//		Functions_GUI ansToGUI=new Functions_GUI();
//		ansToGUI.initFromFile("function_file.txt");
//		ansToGUI.saveToFile("newFunctios.txt");
//		Range rx=new Range(-10, 10);
//		Range ry=new Range(-5, 15);
//		ansToGUI.drawFunctions(1000, 600, rx, ry, 200);

		String s1="plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0)";

		String s2="plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)";
		String s3="div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)";
		String s4="-1.0x^4 +2.4x^2 +3.1";
		String s5="max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)";
		String s6="min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)";
		String s7="+0.1x^5 -1.2999999999999998x +5.0";
		Functions_GUI a=new Functions_GUI();
		a.add(cf.initFromString(s1));
		a.add(cf.initFromString(s2));
		a.add(cf.initFromString(s3));
		a.add(cf.initFromString(s4));
		a.add(cf.initFromString(s5));
		a.add(cf.initFromString(s6));
		a.add(cf.initFromString(s7));
		
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);

		a.drawFunctions(1000,600,rx,ry,res);
		
		int y=10;
		//System.out.println("y :"+y);


	}
}
