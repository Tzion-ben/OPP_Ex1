package Ex1Testing;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.*;
public class MonomTest {

	@Test
	public void testF() {
		String s1="12x^5";
		Monom m1=new Monom(s1);
		double x=2;
		double expected=384;
		assertEquals(expected, m1.f(x));
		assertNotEquals(398, m1.f(x), 0);//the delta is 0
	}

	@Test
	public void testIsZero() {
		//if it is good 
		String s1 ="0";
		Monom m1=new Monom(s1);
		Monom expcted=new Monom(0.0,0);
		if(m1.equals(expcted))
			System.out.println("IsZero works well");
		else
			fail("Is zero methos is worng !");

		//if it is bad 
		String s2 ="110x^5";
		Monom m2=new Monom(s2);
		Monom unExpcted=new Monom(0.0,0);
		if(m2.equals(unExpcted))
			fail("Is zero methos is worng !");	
		else
			System.out.println("IsZero works well");
	}

	@Test
	public void testCopy() {
		String s1="5x^3";
		String s2="10x^3";
		Monom m1 = new Monom(s1);
		Monom m2 = new Monom(s2);
		function copyM1=m1.copy();//make a deep copy of m1
		m1.add(m2);//add m2 to m1 
		if(copyM1.equals(m1)) {//if the cope of m1 was equal to m2 it is was not good 
			//because it is change the function after the copy
			fail("Fail at deep cope !");

			System.out.println("Deep cope is well");}
		else {
			System.out.println("Deep cope is well");
			System.out.println();
		}
	}

	@Test
	public void testAdd() {
		String s1="5x^3";
		String s2="10x^3";
		Monom m1 = new Monom(s1);
		Monom m2 = new Monom(s2);
		m1.add(m2);
		Monom expected=new Monom(15,3);
		Monom notExpected=new Monom (16,3);
		assertEquals(expected, m1);//expected the 10x^3+5x^3=15x^3
		assertNotEquals(notExpected, m1);//not expected that 10x^3+5x^3=16x^3

		s1="87x^53";
		s2="160x^33";
		Monom m3 = new Monom(s1);
		Monom m4 = new Monom(s2);
		m3.add(m4);
		Monom expected2=new Monom(87,53);
		Monom notExpected2=new Monom (247,7);
		assertEquals(expected2, m3);//expected the 87x^53+160x^33 is can't work because 
		//the powers are NOT equal and it will be polynom and not monom
		//if i let it make a addition
		assertNotEquals(notExpected2, m3);//not expected that 10x^3+5x^3=247x^7

		s1="57x^4";
		s2="109x^4";
		Monom m5 = new Monom(s1);
		Monom m6 = new Monom(s2);
		m5.add(m6);
		Monom expected3=new Monom(166,4);
		Monom notExpected3=new Monom (146,23);
		assertEquals(expected3, m5);//expected the 57x^4+109x^4=166x^4
		assertNotEquals(notExpected3, m5);//not expected that 57x^4+109x^4=146x^23
	}

	@Test
	public void testMultipy() {
		String s1="5x^3";
		String s2="10x^3";
		Monom m1 = new Monom(s1);
		Monom m2 = new Monom(s2);
		m1.multipy(m2);
		Monom expected=new Monom(50,6);
		Monom notExpected=new Monom (16,3);
		assertEquals(expected, m1);//expected the 10x^3*5x^3=50x^6
		assertNotEquals(notExpected, m2);//not expected that 10x^3*5x^3=16x^3

		s1="87x^53";
		s2="160x^33";
		Monom m3 = new Monom(s1);
		Monom m4 = new Monom(s2);
		m3.multipy(m4);
		Monom expected2=new Monom(13920,86);
		Monom notExpected2=new Monom (247,7);
		assertEquals(expected2, m3);//expected the 87x^53*160x^33=13920x^86
		assertNotEquals(notExpected2, m4);//not expected that 10x^3+5x^3=247x^7

		s1="0";
		s2="109x^4";
		Monom m5 = new Monom(s1);
		Monom m6 = new Monom(s2);
		m5.multipy(m6);
		Monom expected3=new Monom(0,0);
		Monom notExpected3=new Monom (146,23);
		assertEquals(expected3, m5);//expected the 0*109x^4=0
		assertNotEquals(notExpected3, m6);//not expected that 57x^4*109x^4=146x^23
	}

	@Test
	public void testInitFromString() {
		Monom m1=new Monom (5,7);
		function f1=m1.initFromString("12x^4");
		function fExpected=new Monom(12,4);
		function UnFExpected=new Monom(17,4);
		assertEquals(fExpected,f1);
		assertNotEquals(UnFExpected, f1);

		function f2=m1.initFromString("1987.3x^45");
		function fExpected2=new Monom(1987.3,45);
		function UnFExpected2=new Monom(11987.37,74);
		assertEquals(fExpected2,f2);
		assertNotEquals(UnFExpected2, f2);

		function f3=m1.initFromString("12986x^32");
		function fExpected3=new Monom(12986,32);
		function UnFExpected3=new Monom(175,49);
		assertEquals(fExpected3,f3);
		assertNotEquals(UnFExpected3, f3);
	}

	@Test
	public void testEqualsObject() {
		String[] goodMonoms = {"2x", "-x^5","-3.2x^2","0","6x^3","7x","3x^9"};
		Monom [] monoms=new Monom [7];
		for(int i=0;i<goodMonoms.length;i++) {
			Monom m = new Monom(goodMonoms[i]);
			monoms[i]=m;
		}

		Monom expectedM1=new Monom (2,1);
		Monom expectedM2=new Monom (-1,5);
		Monom expectedM3=new Monom (-3.2,2);
		Monom expectedM4=new Monom (0,0);
		Monom expectedM5=new Monom (6,3);
		Monom expectedM6=new Monom (7,1);
		Monom expectedM7=new Monom (3,9);

		assertEquals(expectedM1, monoms[0]);
		assertEquals(expectedM2, monoms[1]);
		assertEquals(expectedM3, monoms[2]);
		assertEquals(expectedM4, monoms[3]);
		assertEquals(expectedM5, monoms[4]);
		assertEquals(expectedM6, monoms[5]);
		assertEquals(expectedM7, monoms[6]);

		Monom unExpectedM1=new Monom(3,2);
		Monom unExpectedM2=new Monom(5,3);
		Monom unExpectedM3=new Monom(7,8);
		Monom unExpectedM4=new Monom(9,3);


		assertNotEquals(unExpectedM1, monoms[0]);
		assertNotEquals(unExpectedM2, monoms[1]);
		assertNotEquals(unExpectedM3, monoms[2]);
		assertNotEquals(unExpectedM4, monoms[5]);
	}

	@Test
	public void testToString() {
		//good examples
		String[] goodMonoms = {"2x", "-x^5","-3.2x^2","0","6x^3","7x","3x^9"};
		for(int i=0;i<goodMonoms.length;i++) {
			Monom m = new Monom(goodMonoms[i]);
			System.out.println("The monom m"+i+" is: "+m.toString()); 
		}

		String[] badMonoms = {"2x^", "-x^5+","-3.2ghx^2","ua0","6x54^3","-9^7x","35bvx^9"};
		for(int i=0;i<badMonoms.length;i++) {
			try {
				System.out.println();
				System.out.println("The String "+badMonoms[i]+" :is nos good so :");
				Monom k = new Monom(badMonoms[i]);
			}
			catch (Exception e) {
				System.out.println("The monom is incurrect because it have to be from a shape a*x^b"
						+ ", where a is a real number and"
						+ " b is an integer (summed a none negative)");	
			}
		}
	}
}
