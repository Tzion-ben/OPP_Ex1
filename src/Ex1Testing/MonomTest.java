package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.*;
public class MonomTest {

	private   Monom m, m1 ,m2,m3,m4,m5,m6,m7,m8;
	@BeforeEach
	public  void setUpBeforeAll() throws Exception {
		String s1="5x^3";
		String s2="10x^3";
		String s4="7x^3";
		String s5="6x^2";
		String s6="7x^3";
		String s8="6x^2"; 
		String s10="6x^2";
		String s12="6x^2";

		m1 = new Monom(s1);
		m2 = new Monom(s2);
		m3 = new Monom(s4);
		m4 = new Monom(s5);
		m5 = new Monom(s6);
		m6 = new Monom(s8);
		m7 = new Monom(s10);
		m8 = new Monom(s12);

		//good strings to monoms
		String[] goodMonoms = {"2x", "-x^5","-3.2x^2","0","6x^3","7x","3x^9"};
		for(int i=0;i<goodMonoms.length;i++) {
			m = new Monom(goodMonoms[i]);
			System.out.println("The monom m"+i+" is: "+m.toString()); 
		}

		//bad strings to monoms
		//HAVA TO FAIL !
		System.out.println();
		System.out.println("This have to FAIL !");
		String[] badMonoms = {"2x^", "-x^5+","-3.2ghx^2","ua0","6x54^3","-9^7x","35bvx^9"};
		for(int i=0;i<badMonoms.length;i++) {
			try {
				System.out.println();
				System.out.println("The String "+badMonoms[i]+" :is nos good so :");
				m = new Monom(badMonoms[i]);
			}
			catch (Exception e) {
				System.out.println("The monom is incurrect because it have to be from a shape a*x^b"
						+ ", where a is a real number and"
						+ " b is an integer (summed a none negative)");	
			}
		}
	}
//	@BeforeEach
//	public void setUp() throws Exception {
//	
//
//	}
//
//	@AfterEach
//	public void tearDown() throws Exception {
//
//
//
//	}
//
//	@Test
//	public void testF() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsZero() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCopy() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMonomString() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testAdd() {
		System.out.println("Monom m1 before addition to m2 :");
		System.out.println(m1.toString());System.out.println();
		System.out.println("Monom m2 :");
		System.out.println(m2.toString());
		m1.add(m2);
		System.out.println();
		System.out.println("Monom m1 after addition to m2 :");
		System.out.println(m1.toString());
		Monom expected=new Monom(15,3);
		assertEquals(expected, m1,"THe good");
		
	}

//	@Test
//	public void testMultipy() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIs_proper_Monom() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEqualsObject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCoefficient() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPower() {
//		fail("Not yet implemented");
//	}

}
