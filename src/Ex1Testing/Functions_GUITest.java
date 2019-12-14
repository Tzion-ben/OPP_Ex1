package Ex1Testing;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;
/**
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author Tzion
 *
 */
class Functions_GUITest {
	public static void main(String[] a) {
		functions data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

		String JSON_param_file = "GUI_params.txt";
		System.out.println();
		System.out.println("And now the test drawing again from the JSON file :\n");
		data.drawFunctions(JSON_param_file);

	}
	private functions _data=null;
	private functions ans=null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
		ans=FunctionsFactory();
	}

	@Test
	void testInitFromFile() {
		System.out.println();
		System.out.println("Test InitFromFile and drawing again"
				+ "\nso if at the filem have incurect Functions it will not "
				+ "\ndraw them and if all the file is incurect \nit will draw nothing:\n");
		String file = "function_file.txt";
		try {
			ans.initFromFile(file);
			//here i draw again to check if at the file was wrong functions
			//in functions GUI class i check if the number of scoops is correct or not
			if(ans!=null) {
				int width=1000, height=600, resolution=200;
				Range rx = new Range(-10,10);
				Range ry = new Range(-5,15);
				ans.drawFunctions(width, height, rx, ry, resolution);	
			}
			else
				System.out.println("All the functions at the file is "
						+ "incurect");
		}
		catch (Exception e) {
			fail();	
		}
	}

	@Test
	void testSaveToFile() {
		String file2 = "function_file2.txt";
		String file3 = "function_file3.txt";


		try {
			ans.saveToFile(file2);
			ans.saveToFile(file3);
			FileReader file2_read = new FileReader(file2);
			FileReader file3_read = new FileReader(file3);
			BufferedReader brFile2 = new BufferedReader(file2_read); 
			BufferedReader brFile3= new BufferedReader(file3_read); 

			String readFile2;
			String readFile3;
			readFile2=brFile2.readLine();
			readFile3=brFile3.readLine();
			int i=0;
			while(readFile2!=null&&readFile3!=null) {
				if(!readFile2.equals(readFile3))
					fail();	
				readFile2=brFile2.readLine();
				readFile3=brFile3.readLine();
			}
		}
		catch (Exception e) {
			fail();	
		}
	}

	@Test
	void testDrawFunctions() {
		System.out.println();
		System.out.println("Test DrawFunctions NOT from JSON FIle :\n");
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		_data.drawFunctions(w,h,rx,ry,res);
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		System.out.println();
		System.out.println("Test DrawFunctions from JSON FIle:\n");
		//i already have a try and catch ata the GUI class and if the file is 
		//not exist it will draw with the default parameters 
		_data.drawFunctions("GUI_paramgs.txt");
	}


	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}
}
