package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Ex1.*;

class PolynomTest {

	@Test
	void testPolynom() {
		Polynom p1=new Polynom();
		String s1="0";
		Polynom zero=new Polynom(s1);

		String s2="054";
		Polynom notZero=new Polynom(s2);

		assertEquals(zero, p1);
		assertNotEquals(notZero, p1);
	}

	@Test
	void testF() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);		

		double expectedFp1=24881;
		double UnExpectedFp1=6546;

		assertEquals(expectedFp1, p1.f(5));
		assertNotEquals(UnExpectedFp1, p1.f(5));	}

	@Test
	void testAddPolynom_able() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);		
		String s2="98x^3+69x+87x^3";
		Polynom p2=new Polynom(s2);
		p1.add(p2);
		String expectP="259x^3+69x+5x^5+6";
		Polynom expectPoly=new Polynom(expectP);

		String notExpectP="259x^3+69x+5x^5";
		Polynom notExpectPoly=new Polynom(notExpectP); 

		assertEquals(expectPoly, p1);
		assertNotEquals(notExpectPoly, p1);
	}

	@Test
	void testAddMonom() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);		
		String s2="98x^3";
		Monom m1=new Monom(s2);	
		p1.add(m1);
		String expectP="172x^3+5x^5+6";
		Polynom expectPoly=new Polynom(expectP);
		String notExpectP="51x^3+8x^5+6";
		Polynom notExpectPoly=new Polynom(notExpectP);
		assertEquals(expectPoly, p1);
		assertNotEquals(notExpectPoly, p1);

		Monom m2=new Monom (5,3);
		Monom m3=new Monom (7,2);
		Polynom p2=new Polynom();	
		p2.add(m2);
		p2.add(m3);
		String expectP2="5x^3+7x^2";
		Polynom expectPoly2=new Polynom(expectP2);
		String notExpectP2="51x^3+8x^5+6";
		Polynom notExpectPoly2=new Polynom(notExpectP2);
		assertEquals(expectPoly2, p2);
		assertNotEquals(notExpectPoly2, p1);
		
		Monom m4=new Monom(0,0);
		Polynom p3=new Polynom();
		p3.add(m4);
		assertEquals(p3, p3);
		
		Polynom p4=new Polynom("5x^4+6x^2");
		p4.add(m4);
		assertEquals(p4, p4);

	}

	@Test
	void testSubstract() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);	
		String s2="74x^3+5x^5+6";
		Polynom p2=new Polynom(s2);		
		p1.substract(p2);
		Polynom expected1=new Polynom(); 

		assertEquals(expected1, p1);
		String s3="74x^3+5x^5+6";
		Polynom notExpected1=new Polynom(s3);

		assertNotEquals(notExpected1, p1);
	}

	@Test
	void testMultiplyPolynom_able() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);	
		String s2="4x^3+5x^5";
		Polynom p2=new Polynom(s2);		
		p1.multiply(p2);
		String expectMul="296x^6+370x^8+20x^8+25x^10+24x^3+30x^5";
		Polynom expected1=new Polynom(expectMul);
		assertEquals(expected1, p1);
		String s3="74x^3+5x^5+6";
		Polynom notExpected1=new Polynom(s3);
		assertNotEquals(notExpected1, p1);	}

	@Test
	void testEqualsObject() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);	
		String s2="4x^3+5x^5";
		Polynom p2=new Polynom(s2);		
		p1.multiply(p2);
		String expectMul="296x^6+370x^8+20x^8+25x^10+24x^3+30x^5";
		Polynom expected1=new Polynom(expectMul);
		if(!p1.equals(expected1)) {
			fail("");
		}
		else if(!p1.equals(p1))
			fail("");

		function copyP1=p1.copy();
		p1.multiply(new Monom(5,7));
		if(copyP1.equals(p1))
			fail("");
	}

	@Test
	void testRoot() {

		double[] roots = {0.2135162353515625,0.833343505859375}; 

		String s1="-6x^3+3x^2+9x-2";
		Polynom p1=new Polynom(s1);
		double rootP1=(p1.root(0, 1, 0.0001));	
		if(rootP1!=roots[0])
			fail("");	

		String s2="6x-5";
		Polynom p2=new Polynom(s2);
		double rootP2=(p2.root(0, 1, 0.0001));	
		if(rootP2!=roots[1])
			fail("");	
	}
	@Test
	void testCopy() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);
		String s2="4x^3+5x^5";
		Polynom p2=new Polynom(s2);


		function copyP1=p1.copy();
		p1.add(new Monom (4,2));

		if(p1.equals(copyP1))
			fail("");

		function copyP2=p2.copy();
		p2.add(new Monom(0.0,0));
		if(!p2.equals(copyP2))
			fail("");
	}

	@Test
	void testDerivative() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);
		p1.derivative();
		String s2="4x^3+5x^5";
		Polynom p2=new Polynom(s2);
		String s3="222x^2+25x^4";
		Polynom expected=new Polynom(s3);

		assertEquals(expected, p1);
		assertNotEquals(p2, p1);
	}

	@Test
	void testArea() {
		String s1="-6x^3+3x^2+9x-2";
		Polynom p1=new Polynom(s1);
		double areaP1=(p1.area(-1, 0, 0.0001));
		if(areaP1!=0)
			fail("");		
		

		String s2="6x-5";
		Polynom p2=new Polynom(s2);
		double areaP2=(p2.area(-1, 0, 0.0001));
		if(areaP2!=0)
			fail("");		
	}

	@Test
	void testMultiplyMonom() {
		String s1="74x^3+5x^5+6";
		Polynom p1=new Polynom(s1);	
		p1.multiply(new Monom(5, 7));;
		String expectMul="370x^10+25x^12+30x^7";
		Polynom expected1=new Polynom(expectMul);
		assertEquals(expected1, p1);

		p1.multiply(new Monom(0.0,0));
		assertNotEquals(expected1, p1);
		assertEquals(new Polynom(), p1);
	}

	@Test
	void testToString() {
		String s1="5.0x^5+3.0x^2+6.0";
		Polynom p1=new Polynom(s1);	
		String s2="5.0x^5+4.0x^3";
		Polynom p2=new Polynom(s2);	
		String s3="6598.0x^5+74.0x^3+6.0";
		Polynom p3=new Polynom(s3);	

		assertEquals(p1.toString(), s1);
		assertEquals(p2.toString(), s2);
		assertEquals(p3.toString(), s3);
	}

	@Test
	void testInitFromString() {
		System.out.println();
		System.out.println("Bad exampels on Polynoms :");
		System.out.println();
		String []  badStringsForPolynoms= {"8x^2+5x-5x^v4","8x^2+5x+","6^x56k+95x^2+6",
				"8x^100+k8x^5","8x7+6x^3","8x87^87+6x^5","85cx^7+6x^3"};
		for(int i=0;i<badStringsForPolynoms.length;i++) {
			try {
				System.out.println();
				System.out.println("The String is "+badStringsForPolynoms[i]+" so Polynom p"+i+" :");
				Polynom p=new Polynom(badStringsForPolynoms[i]);
			}
			catch (Exception e) {
				System.out.println("This polynim is incurrect so fix it please");
			}	
		}
	}
}
