package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Ex1.*;
class ComplexFunctionText {

	@Test
	void testF() {
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "+1.0x+1.0";	
		function p1 = new Polynom(s1);
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction("plus", p1,p2);
		double fx10=cf1.f(10);
		double fxExpected=-9745.9;
		assertEquals(fxExpected,fx10);

		String s3="plus(div(x+1,mul(mul(x+3.0,x-2.0),x-4.0)),2.0)";
		function cf1String=cf1.initFromString(s3);
		double cf1StringFx10=cf1String.f(10);
		double cf1StringFxExpected=2.0176282051282053;
		assertEquals(cf1StringFxExpected,cf1StringFx10);

		function cf1String2=cf1.initFromString(s1);
		double cf1String2Fx10=cf1String2.f(10);
		double cf1String2FxExpected=-9756.9;
		assertEquals(cf1String2FxExpected,cf1String2Fx10);
	}

	@Test
	void testInitFromString() {
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "+1.0x+1.0";	
		function p1 = new Polynom(s1);
		function p2 = new Polynom(s2);
		String s3="plus(-x^4+2.4x^2+3.1,x+1)";
		ComplexFunction cf1 = new ComplexFunction("plus", p1,p2);
		function cf2 =cf1.initFromString(s3);
		assertEquals(cf1, cf2);
		try {
			ComplexFunction cf10=new ComplexFunction("dfv", p1, p2);
		}
		catch (Exception e) {
			//in Incurrect oparation
		}

		String s4 = "3.1+2.4x^2-x^4+9x+6x^2";
		function p4 = new Polynom(s4);
		ComplexFunction cf3 = new ComplexFunction(p4);
		String s5="-x^4+8.4x^2+9.0x+3.1";
		assertEquals(cf3.toString(), s5);
		assertNotEquals(cf3.toString(), s4);
		assertNotEquals(cf1, cf3);


		String s6 = "3.1";
		function p5 = new Polynom(s6);
		ComplexFunction cf4 = new ComplexFunction(p5);
		String s7="-x^4";

		assertEquals(cf4.toString(), s6);
		assertNotEquals(cf4.toString(), s7);
	}

	@Test
	void testToString() {
		String s1 = "-x^4+2.4x^2+3.1";
		String s2 = "+1.0x+1.0";	
		function p1 = new Polynom(s1);
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(p1);
		function temp=cf1.initFromString(s2);

		assertEquals(cf1.toString(), s1);

		String s3="max(max(max(max(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5"
				+ "-1.2999999999999998x+5.0),plus(div(+1.0x+1.0,mul(mul(+1.0x"
				+ "+3.0,+1.0x-2.0),+1.0x-4.0)),2.0)),div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5"
				+ "-1.2999999999999998x+5.0),-1.0x^4 +2.4x^2+3.1))"
				+ ",-1.0x^4 +2.4x^2 +3.1),+0.1x^5-1.2999999999999998x+5.0)";
		temp=cf1.initFromString(s3);

		assertEquals(temp, s3);

	}

	@Test
	void testCopy() {
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(p2);
		assertEquals(p2, cf1);
		function f1=cf1.copy();
		assertEquals(p2, f1);
		String s3="plus(-x^4+2.4x^2+3.1,x+1)";
		function f2=cf1.initFromString(s3);
		assertNotEquals(f1, f2);

	}

	@Test
	void testEqualsObject() {
		String s1 = "-x^4+2.4x^2+3.1";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Plus,p1,p2);
		ComplexFunction cf2 = new ComplexFunction(Operation.Plus,p1,p2);
		assertEquals(cf1, cf2);

		ComplexFunction cf3 = new ComplexFunction(Operation.Plus,p1,p1);
		assertNotEquals(cf3, cf2);

		Polynom zero1=new Polynom();
		Polynom zero2=new Polynom();

		ComplexFunction cf4 = new ComplexFunction(Operation.Plus,zero1,zero2);
		double fxCf4=cf4.f(65);
		double expected=0;
		assertEquals(expected, fxCf4);

		String s3="plus(div(x+1,mul(mul(x+3.0,x-2.0),x-4.0)),2.0)";
		ComplexFunction cf5 = new ComplexFunction(Operation.Times,new Polynom("+1.0x +3.0"),new Polynom("+1.0x -2.0"));
		ComplexFunction cf6 = new ComplexFunction(Operation.Times,cf5,new Polynom("+1.0x -4.0"));
		ComplexFunction cf7 = new ComplexFunction(Operation.Divid,new Polynom("+1.0x +1.0"),cf6);
		ComplexFunction cf8 = new ComplexFunction(Operation.Plus,cf7,new Monom (2,0));
		String cf8Expected=cf8.toString();

		assertEquals(s3, cf8Expected);
	}

	@Test
	void testPlus() {
		String s1 = "-x^4+2.4x^2+3.1";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Plus,p1,p2);
		double cf1F6=cf1.f(6);
		double expected1=-1199.5;
		assertEquals(expected1, cf1F6);	

		ComplexFunction cf2 = new ComplexFunction(p1);
		cf2.plus(p2);
		double cf2F6=cf2.f(6);
		assertEquals(expected1, cf2F6);		
	}

	@Test
	void testMul() {
		String s1 = "-x^4+2.4x^2+3.1";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Times,p1,p2);
		double cf1F6=cf1.f(6);
		double expected1=-8445.5;
		assertEquals(expected1, cf1F6);	

		ComplexFunction cf2 = new ComplexFunction(p1);
		cf2.mul(p2);
		double cf2F6=cf2.f(6);
		assertEquals(expected1, cf2F6);			
	}

	@Test
	void testDiv() {
		String s1 = "-x^4+2.4x^2+3.1";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Divid,p1,p2);
		double cf1F6=cf1.f(6);
		double expected1=-172.35714285714286;
		assertEquals(expected1, cf1F6);	

		ComplexFunction cf2 = new ComplexFunction(p1);
		cf2.div(p2);
		double cf2F6=cf2.f(6);
		assertEquals(expected1, cf2F6);				}

	@Test
	void testMax() {
		String s1 = "-x^4";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Max,p1,p2);
		double cf1F6=cf1.f(6);
		double expected1=7;
		assertEquals(expected1, cf1F6);	

		ComplexFunction cf2 = new ComplexFunction(p1);
		cf2.max(p2);
		double cf2F6=cf2.f(6);
		assertEquals(expected1, cf2F6);
	}

	@Test
	void testMin() {
		String s1 = "-x^4";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Min,p1,p2);
		double cf1F6=cf1.f(6);
		double expected1=-1296;
		assertEquals(expected1, cf1F6);	

		ComplexFunction cf2 = new ComplexFunction(p1);
		cf2.min(p2);
		double cf2F6=cf2.f(6);
		assertEquals(expected1, cf2F6);	}

	@Test
	void testComp() {
		String s1 = "-x^4";
		function p1 = new Polynom(s1);
		String s2 = "+1.0x+1.0";	
		function p2 = new Polynom(s2);
		ComplexFunction cf1 = new ComplexFunction(Operation.Comp,p1,p2);
		double cf1F6=cf1.f(6);
		double expected1=-2401;
		assertEquals(expected1, cf1F6);	

		ComplexFunction cf2 = new ComplexFunction(p1);
		cf2.comp(p2);
		double cf2F6=cf2.f(6);
		assertEquals(expected1, cf2F6);	
	}

}
