package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Functions_GUI implements functions {

	//start of regular collections functions
	@Override
	public boolean add(function arg0) {
		functionsList.add(arg0);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return functionsList.addAll(arg0);

	}

	@Override
	public void clear() {
		functionsList.clear();		
	}

	@Override
	public boolean contains(Object arg0) {
		return functionsList.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return functionsList.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return functionsList.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return functionsList.listIterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return functionsList.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return functionsList.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return functionsList.retainAll(arg0);
	}

	@Override
	public int size() {
		return functionsList.size();
	}

	@Override
	public Object[] toArray() {
		return functionsList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return functionsList.toArray(arg0);
	}

	//end of regular collections functions


	@Override
	public void initFromFile(String file) throws IOException {
		set_functionsList();
		try {
			FileReader functions = new FileReader(file);
			BufferedReader brFunctions = new BufferedReader(functions); 
			String functionsFromFile="";
			String tempToRead;
			tempToRead=brFunctions.readLine();
			ComplexFunction newCF=new ComplexFunction(new Monom(1,1));
			while(tempToRead!=null) {
				function newFunction;
				if(tempToRead.length()!=0) {
					newFunction = newCF.initFromString(tempToRead);
					functionsList.add(newFunction);
				}
				functionsFromFile=functionsFromFile+tempToRead+"\n";
				tempToRead=brFunctions.readLine();
			}
			//			System.out.println("The file string is:");//to me, need to delete
			//			System.out.println(functionsFromFile);//to me, need to delete
			brFunctions.close();
			functions.close();
		}
		catch (IOException e) {
			System.out.println("The file is doesn't exist or wrong");
		}
	}

	@Override
	public void saveToFile(String file) throws IOException {
		FileWriter functions = new FileWriter(file);  
		PrintWriter outPutFunctions = new PrintWriter(functions);
		Iterator<function> func=this.functionsList.listIterator(); ;
		while(func.hasNext()) 
			outPutFunctions.println(func.next());
		outPutFunctions.close();
		functions.close();
	}
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		int n = resolution;
		StdDraw.setCanvasSize(width, height);



		int size = this.functionsList.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {
				yy[a][i] = this.functionsList.get(a).f(x[i]);
			}
			x0+=x_step;
		}

		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());

		//y axis
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		StdDraw.line(x[n/2]-0.03, ry.get_min(), x[n/2]-0.03, ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+1) {
			StdDraw.text(x[n/2]-0.07, i+0.07, Double.toString(i));
		}//end y axis

		//x axis
		StdDraw.line(rx.get_min(), 0-0.07, rx.get_max(), 0-0.07); // Draw x axis
		for (double i = rx.get_min(); i <= rx.get_max(); i=i+1) {
			StdDraw.text(i-0.7 ,-0.7 , Double.toString(i));
		}//end x axis
		
		
		//vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = 0; i <= n; i=i+10) {
			StdDraw.line(x[i], ry.get_min(), x[i], ry.get_max());
		}
		
		//horizontal lines
		for (int i =(int) ry.get_min(); i <= ry.get_max(); i++) 
		{
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
	

		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);

			System.out.println(a+") "+Colors[a]+"  f(x)= "+this.functionsList.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}			
	}

	@Override
	public void drawFunctions(String json_file) {
		Object obj = null;
		try {
			JsonParser jp = new JsonParser();
			FileReader fr = new FileReader(json_file);
			obj = jp.parse(fr);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		JsonObject jo = (JsonObject) obj;

		// getting firstName and lastName 
		JsonElement WidthJson =  jo.get("Width");
		int width=WidthJson.getAsInt();

		JsonElement HeightJson =  jo.get("Height");
		int height=HeightJson.getAsInt();

		JsonElement ResolutionJson =  jo.get("Resolution");
		int resolution=ResolutionJson.getAsInt();


		JsonElement ryJson =  jo.get("Range_Y");
		JsonArray ry=ryJson.getAsJsonArray();
		JsonElement minRyJson=ry.get(0);
		JsonElement maxRyJson=ry.get(1);
		double ryMin=minRyJson.getAsDouble();
		double ryMax=maxRyJson.getAsDouble();
		Range ryRange=new Range(ryMin, ryMax);

		JsonElement rxJson =  jo.get("Range_X");
		JsonArray rx=rxJson.getAsJsonArray();
		JsonElement minRxJson=rx.get(0);
		JsonElement maxRxJson=rx.get(1);
		double rxMin=minRxJson.getAsDouble();
		double rxMax=maxRxJson.getAsDouble();
		Range rxRange=new Range(rxMin, rxMax);


		//sending the paramiters to the 
		drawFunctions(width, height, rxRange, ryRange, resolution);

	}

	// ***************** My functions **********************
	/**My functions that I decided that they important**/

	/**
	 * this method counting if the numbers of parenthesis at the string is correct or not
	 * @param s
	 * @return
	 */
	public boolean countoingParenthesis(String s) {
		int count=0;
		boolean flag=true;
		for(int i=0; i<s.length();i++) {
			if(s.charAt(i)=='(')
				count++;
			if(s.charAt(i)==')')
				count--;
		}
		if(count!=0)
			flag=false;
		return flag;
	}

	/**
	 * default contractor
	 */
	public Functions_GUI() {
		set_functionsList();
	}
	//****************** Private Methods and Data *****************

	private void set_functionsList(){
		this.functionsList=new ArrayList<function>();//sets the ArrayList of functions
	}

	private ArrayList<function>functionsList;	
}