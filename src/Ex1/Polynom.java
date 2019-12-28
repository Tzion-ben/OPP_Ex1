package Ex1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import Ex1.Monom;
import Ex1.Monom_Comperator;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Tzion
 *

/**
 * I chose to work with ArrayList to represrnt my Polynom
 * @author Tzion
 *
 */
public class Polynom implements Polynom_able{
	/**
	 * Zero (empty polynom)
	 * In definition the Zero polynom is p(x)=0.
	 * So I will add just the  zero monom to the polynom 
	 */
	public Polynom() {
		Monom ZERO=new Monom(0.0,0);
		set_polynom();
		this.polynom.add(ZERO);
	}//ens Zero polynom
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 *  //this method get's a String and checking if it's a correct polynom or not
	 *  and then separate it to monoms
	 * @param s: is a string represents a Polynom
	 */

	public Polynom(String s) {
		String afterRMSpace=rmSpace(s);
		boolean flag=courect_polynom(afterRMSpace);
		if(!flag)
			throw new RuntimeException();
		else {
			set_polynom();
			peruk_to_monoms(afterRMSpace);
		}
	}
	/**
	 * this methos give's back the value of f(x) at x that i will give it based on the method 
	 * f(x) of the monom 
	 */
	@Override
	public double f(double x) {
		double ans=0;
		Iterator<Monom> poly=this.polynom.listIterator();
		while (poly.hasNext()) {
			Monom temp=poly.next();
			ans=ans+temp.f(x);
		}
		return ans;
	}
	/**
	 * this method adding polynom to polynom with the metod add of monom, that add just if the powers
	 * are equals and the rest move like it was, and the sorting it be the powers
	 */
	@Override
	public void add(Polynom_able p1) {
		int sizeThis=this.polynom.size();
		Iterator<Monom> poly_able=p1.iteretor();
		while (poly_able.hasNext()) {
			int countAdd=0;//to check if was a addition or nor	
			Monom tempAble=poly_able.next();	
			for(int i=0;i<sizeThis;i++) {
				if(this.polynom.get(i).get_power()==tempAble.get_power()) {
					this.polynom.get(i).add(tempAble);
					countAdd++;
				}
			}
			if(countAdd==0)//if was not addition so have to put the rest as is
				this.polynom.add(tempAble);
		}
		Collections.sort(this.polynom,new Monom_Comperator());
	}
	/**
	 * this method is adding a monom to the polynom , if the powers are equals it make
	 * a addition between the coefficients in other way it's add this monom to 
	 * the polynom iwith thw mwthos that chaeck s the power and saysif have other 
	 * monom with the same coefficients and if needed merge them
	 */
	@Override
	public void add(Monom m1) {
		if(this.polynom.size()==1 && this.polynom.get(0).get_coefficient()==0) {
			boolean foo=differentPowers(m1);
			int powert=m1.get_power();
			double coefft=m1.get_coefficient();
			Monom temp=new Monom (coefft,powert);
			this.polynom.add(temp);
			this.polynom.remove(0);
		}
		else if (m1.get_coefficient()==0) {
			return;
		}
		else {
			boolean flag=false;
			flag=differentPowers(m1);
			if(!flag)
				this.polynom.add(new Monom(m1));
			Collections.sort(this.polynom,new Monom_Comperator());
		}
	}

	/**
	 * this method is subtracting a polynom from polynom , if the powers are equals it make
	 * a subtracting between the coefficients in other way it's add this monom from the polynom
	 *  to the polynom
	 */  	 
	@Override
	public void substract(Polynom_able p1) {
		Monom minusOneMonom=new Monom(-1.0,0);
		Monom zeroAfterSub=new Monom(0.0,0);
		if(p1.equals(this)) {
			this.polynom.removeAll(polynom);
			this.polynom.add(zeroAfterSub);	
		}
		else {
			p1.multiply(minusOneMonom);
			int sizeThis=this.polynom.size();
			Iterator<Monom> poly_able=p1.iteretor();
			while (poly_able.hasNext()) {
				int countSub=0;//to check if was a subtracting or nor
				Monom tempAble=poly_able.next();
				for(int i=0;i<sizeThis;i++) {
					if(this.polynom.get(i).get_power()==tempAble.get_power()) {
						this.polynom.get(i).add(tempAble);
						countSub++;
						if(this.polynom.get(i).equals(zeroAfterSub)) {
							this.polynom.remove(i);
						}
					}
				}
				if(countSub==0)//was not subtracting then add the rest with '-'
					this.polynom.add(tempAble);

				Collections.sort(this.polynom,new Monom_Comperator());
			}
		}
	}
	/**
	 * this method multiply polynom by polynom base on the multipy method of  monom

	 * 1.running on the this.polynom that we got and, get the first place and  make from it a monom and put a duplicate
	 *    at the end of the polynom and then multypling the duplicate at the last position with p1
	 *    and after it remove the original monom at the 0 position  
	 * 2.  checking if have more then one monom with the same power and merge them at the same logical,
	 *     i take the first monom and put him at other monom and then remove from the polynom
	 *     and then sent the other monomm to check the powers if true then it merged them and i have more
	 *     then one from with the same power. else it false then no have anther monom with that power
	 *     and i pout bake the monom to the polynom 
	 * 3. sorting the polynom base on he's powers
	 */
	@Override
	public void multiply(Polynom_able p1) {
		//1.
		int sizeOfThis=this.polynom.size();
		for(int i=0;i<sizeOfThis;i++) {
			Iterator<Monom> poly_able=p1.iteretor();
			while(poly_able.hasNext()) {
				double coeff=this.polynom.get(0).get_coefficient();
				int poweerT=this.polynom.get(0).get_power();
				Monom tempIndexEfes=new Monom(coeff,poweerT);
				this.polynom.add(tempIndexEfes);
				int lastPosition=this.polynom.size();
				this.polynom.get(lastPosition-1).multipy(poly_able.next());
			}
			this.polynom.remove(0);
		}//end 1.
		//2.
		sizeOfThis=this.polynom.size();
		for (int i=0;i<sizeOfThis;i++) {
			double coeff=this.polynom.get(0).get_coefficient();
			int poweerT=this.polynom.get(0).get_power();
			Monom tempIndexEfes=new Monom(coeff,poweerT);
			this.polynom.remove(0);
			boolean flag=differentPowers(tempIndexEfes);
			if(!flag)
				this.polynom.add(tempIndexEfes);
		}//end 2.
		//3.
		Collections.sort(this.polynom,new Monom_Comperator());
	}
	/**
	 * this method checking if the polynoms are equals based on ther f(x)
	 * and returns true iff for any x: this.f(x) == p.f(x), this method is based on the
	 * equals of the monom
	 */
	@Override
	public boolean equals(Object p1) {
		boolean flag=true;
		if(p1!=null&&(p1 instanceof Polynom_able||p1 instanceof Monom)){
			Iterator<Monom> poly=this.polynom.listIterator();
			Iterator<Monom> poly_able=((Polynom_able) p1).iteretor();
			while (poly.hasNext()||poly_able.hasNext()) {
				if(poly.hasNext()&&poly_able.hasNext()) {
					Monom tempThisPoly=poly.next();
					Monom tempAble=poly_able.next();	
					boolean ans=tempThisPoly.equals(tempAble);
					if(!ans) {return false;}
				}
				else//returns false because if one bigger then the other they NOT EQUAL !!!!!!
					return false;
			}
		}
		else 
			return false;
		return flag;

	}
	/**
	 * this method take the polynom and checking if it's at size 1 he can be zero
	 * or not, then it's checking the only 1 monom at the polynom is equal to zero monom
	 */
	@Override
	public boolean isZero() {
		boolean flag=false;
		if(this.polynom.isEmpty())
			return flag;
		Monom ZERO=new Monom(0.0,0);
		if(this.polynom.size()==1) {
			if(this.polynom.get(0).equals(ZERO)) {
				flag=true;
				return flag;
			}
		}
		return flag;
	}
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps (that is the epsilon)
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function 
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double f_x0=f(x0);
		double f_x1=f(x1);
		double xPrime=1;
		try {
			if(((f_x0*f_x1))<=0) {
				xPrime=(x0+x1)/2;//we gut the center
				double f_xPrinme=f(xPrime);
				while(Math.abs(f_xPrinme)>=eps) {
					if((f_x0)*(f_xPrinme)<=0) {x1=xPrime;}
					else {x0=xPrime;}
					xPrime=(x0+x1)/2;
					f_xPrinme=f(xPrime);
				}
			}
			else
				throw new RuntimeException () ;
		}
		catch (RuntimeException e) {
			throw new RuntimeException () ;
		}
		return xPrime;
	}
	/**
	 * this method make a deep copy of the polynom
	 */
	@Override
	public function copy() {
		Polynom newPlynom=new Polynom();
		if(!this.polynom.get(0).isZero()) {
			int powert=this.polynom.get(0).get_power();
			double coefft=this.polynom.get(0).get_coefficient();
			Monom temp=new Monom (coefft,powert);
			newPlynom=new Polynom(temp.toString());	
		}
		for (int i=1;i<this.polynom.size();i++) {
			int powert=this.polynom.get(i).get_power();
			double coefft=this.polynom.get(i).get_coefficient();
			Monom temp=new Monom (coefft,powert);
			newPlynom.add(temp);
		}
		return (function)newPlynom;
	}
	/**
	 * this methos is return a new polynom that he the derivative of this.polynom based on 
	 * the derivative methos from the monom
	 */
	@Override
	public Polynom_able derivative() {
		Polynom_able afterDiretive=new Polynom();
		Monom zeroMonom=new Monom (0.0,0);
		int sizeOfThis=this.polynom.size();
		for(int i=0;i<sizeOfThis;i++) {
			Monom temp=this.polynom.get(0).derivative();
			if(temp.equals(zeroMonom)) 
				this.polynom.remove(0);

			else {	
				this.polynom.add(temp);
				this.polynom.remove(0);
				if(this.polynom.get(i).equals(zeroMonom))
					this.polynom.remove(i);
			}
		}
		Collections.sort(this.polynom,new Monom_Comperator());
		afterDiretive.add(this);
		return afterDiretive;
	}
	/**
	 * Compute a Riman's integral from x0 to x1 in eps (epsilon) steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double ans=0;
		double areaPo=0;
		if(x1<=x0)
			return ans;
		areaPo=f(x0)*eps;
		if(areaPo>0)
			ans+=areaPo;
		double xPrime=x0+eps;
		while(xPrime<x1) {

			areaPo=areaPo+(f(xPrime)*eps);
			xPrime+=eps;
		}
		if(areaPo>0)//only abode the x axis
			ans+=areaPo;
		return ans;//return a positive value because of the area 
		//be negative
	}
	/**
	 * this method return the iterator of the polynom by monoms
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return this.polynom.iterator();
	}//end Iterator
	@Override
	/**
	 * at this method i multiply the polynom by monom  based on the multiply 
	 * method from the monom
	 */
	public void multiply(Monom m1) {
		if(m1.isZero()) {
			this.polynom.removeAll(polynom);
			this.polynom.add(m1);
		}//if the monom that i will multiply the polynom by it is equal to 
		//zero then remuve all tnd put the zero monom at the polynom 
		//and thats it
		else {
			Iterator<Monom> poly=this.polynom.listIterator();
			while (poly.hasNext()) {
				Monom temp=poly.next();
				temp.multipy(m1);
			}
		}
	}//end multiply
	/**
	 * this methos returning a String of the polynom that can take to the init
	 * 
	 */
	public String toString() {
		String ans="";
		Iterator<Monom> poly=this.polynom.listIterator();
		int first=0;//to check if at the index 0 is bigger then 0 so no needed '+'
		while (poly.hasNext()) {
			Monom temp=poly.next();
			if( first==0) {//no needed '+' or '-' at the first place, if needed '-' it will came from 
				//the toString of the specific monom
				ans+=temp.toString();
				first=1;
			}
			else if(temp.get_coefficient()>0 && first!=0) {
				//if the coefficient is bigger the 0 and is not on the first position then it's puts '+' 
				ans+=(char)43;
				ans+=temp.toString(); 
			}
			else
				ans+=temp.toString();
		}
		return ans;
	}
	
	/**
	 *this method init a polynom as a function from a string 
	 */
	@Override
	public function initFromString(String s) {
		function polynomFunction=new Polynom(s);
		return polynomFunction;
	}
	// ***************** My functions **********************
	/**My functions that I decided that they important**/
	/**
	 * this method checking if at the polynom there is nothing except digits 
	 * and : 'x' ,'+', '-', '.' , '^' 
	 * @param s
	 * @return true or false
	 */
	public boolean courect_polynom(String s) {
		boolean flag=true;
		int last=s.length();
		if(s.charAt(last-1)==43||s.charAt(last-1)==45)
			return false;
		int i=0;
		while(s.length()>i) {
			char temp=s.charAt(i);
			if(temp<48||temp>57) {
				if(temp!=43&&temp!=45&&temp!=46&&temp!=94&&temp!=120) {
					flag=false;
					return flag;
				}
			}
			i++;
		}
		return flag;
	}
	/**
	 *this method is take a string and split it to monoms and then send them to 
	 *the monom class
	 * @param s
	 */
	public void peruk_to_monoms(String s) {
		String ans=null;
		int i=0;
		if(s.charAt(0)==45||s.charAt(0)==43)
			i++;
		for(int m=i;m<s.length();m++) {
			int start=m;
			i=m;
			while(s.length()>i&&(s.charAt(i)!=43&&s.charAt(i)!=45)) 
				i++;
			if(i==s.length()) {
				if(start!=0)
					ans=s.substring(start-1);
				else
					ans=s;
			}
			else {
				if(start!=0)
					ans=s.substring(start-1, i);
				else
					ans=s.substring(start, i);
			}
			if(ans.charAt(0)==43)//if some monom at the polynom have a '+' at the start the put it down
				//because my monom class is work's with out '+' and knows that it is positive
				ans=ans.substring(1);
			Monom mo=new Monom(ans);
			boolean flag=differentPowers(mo);
			if(!flag)
				this.polynom.add(mo);
			start=m=i;
		}
		Collections.sort(this.polynom,new Monom_Comperator());
	}
	/**
	 * this method cheking if the polynom have the same powers with different 
	 * coefficients then merge them and return's true, and if there is no monom with
	 * the same power at this polynom is's return's false
	 * @param mo
	 */
	public boolean differentPowers (Monom mo) {
		boolean flag=false;
		for(int z=0;z<this.polynom.size();z++) {
			if(this.polynom.get(z).get_power()==mo.get_power()) {
				this.polynom.get(z).add(mo);
				flag=true;
			}
		}
		return flag;
	}

	/**
	 * this method removing all the spaces at giving string
	 * @param s
	 * @return
	 */
	public String rmSpace(String s) {
		String ans="";
		String [] splitSpace=s.split(" ");
		for(int i=0;i<splitSpace.length;i++) {
			ans=ans+splitSpace[i];
		}
		return ans;
	}
	//****************** Private Methods and Data *****************

	private void set_polynom(){
		this.polynom=new ArrayList<Monom>();//sets the ArrayList

	}

	private ArrayList<Monom> polynom;//ArrayList that represents rhe polynom 
	//that every place in it is a monom
}