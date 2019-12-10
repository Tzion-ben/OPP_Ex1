package Ex1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Functions_GUI implements functions {

	//start of regular collections functions
	@Override
	public boolean add(function arg0) {
		return functionsList.add(arg0);
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
		return functionsList.iterator();
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
		FileReader functions = new FileReader(file);
		BufferedReader brFunctions = new BufferedReader(functions); 
		String functionsFromFile="";
		String tempToRead;
		tempToRead=brFunctions.readLine();
		while(tempToRead!=null) {
			functionsFromFile=functionsFromFile+tempToRead;
			tempToRead=brFunctions.readLine();
		}
		System.out.println("The file string is : " +functionsFromFile);
		brFunctions.close();
		functions.close();


	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

	// ***************** My functions **********************
	/**My functions that I decided that they important**/
	
	/**
	 * this metod counting if the numbers of parenthesis at the string is currect or not
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

	//****************** Private Methods and Data *****************

	private void set_functionsList(){
		this.functionsList=new ArrayList<function>();//sets the ArrayList of functions
	}

	private ArrayList<function>functionsList;	
}