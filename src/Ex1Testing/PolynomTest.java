package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Ex1.*;

class PolynomTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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
		String s2="98x^3+69x+87x^3";
		Polynom p2=new Polynom(s2);

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
		assertEquals(expectPoly, p1);
	}

	@Test
	void testAddMonom() {
		fail("Not yet implemented");
	}

	@Test
	void testSubstract() {
		fail("Not yet implemented");
	}

	@Test
	void testMultiplyPolynom_able() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	void testRoot() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testDerivative() {
		fail("Not yet implemented");
	}

	@Test
	void testArea() {
		fail("Not yet implemented");
	}

	@Test
	void testMultiplyMonom() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromString() {
		fail("Not yet implemented");
	}

}
