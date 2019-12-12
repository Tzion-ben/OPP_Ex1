package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * this method returns the coefficient of the monom
	 * @return
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * this method returns the power of the ot monom
	 * @return
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		/**if the power is 0 then the derivative will be zero**/
		if(this.get_power()==0) {return getNewZeroMonom();}
		/**else: multiply the power with the coefficient and then subtract the power in 1  **/
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}

	/**
	 * this method receive a x and then multiply the coefficient with the given x after the x is
	 * in p power 
	 * @return 
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}

	/**
	 * this method gets the string and return's monom object from type function
	 */
	@Override
	public function initFromString(String s) {
		function newMononFunc= new Monom(s);	
		return newMononFunc;
	}

	/**
	 * this methos make's a cope of the monom
	 */
	@Override
	public function copy() {
		int powerTemp=this.get_power();
		double coefficentTemp=this.get_coefficient();
		function newMononFunc= new Monom(coefficentTemp,powerTemp);	
		return newMononFunc;
	}
	// ***************** add your code below **********************
	/**
	 * 1. if the string is not monom it is throw an ERR Exception
	 * 2. works on the cases the the monom starts with 'x' or -x' and there powers
	 * 3. works on all others cases that the monom starts with negative or positive integer or 
	 *    negative or positive decimal number
	 *  4. works with the split method of STRING and spit when it's 'x' and checking
	 *     if the coefficient and the power are correct numbers
	 * @param s
	 */
	public Monom(String s) {
		boolean flag=is_proper_Monom(s);
		if(!flag)
			throw new RuntimeException();

		int power;
		double coefficient;
		String x="x";
		int index=0;
		while(s.length()>index&&s.charAt(index)!=120)
			index++;

		if(index!=s.length()) {//if we found a 'x' index is smaller the s.length
			//else : s.length==index
			String [] s1=s.split(x);

			if(s1.length==1 && s1[0].length()==1 && s1[0].charAt(0)==45)
				Minuc_x_Case();// '-x' case

			else if(s.charAt(0)==x.charAt(0)&&s.length()==1)	
				Plus_x_Case();// '+x' case
			
			else if(s1.length==1) {//if there is just a number with 'x' case
				try {
					coefficient=coefficient(s1[0]);}
				catch(Exception e){
					throw new RuntimeException();
				}
				set_coefficient(coefficient);
				set_power(1);
			}
			else if (s1.length==2) {
				//the cases that is is '+x^' and number and '-x^' and number
				//and number'x^' number
				if(s1[1].charAt(0)!=94)//the case that it is not '^' after 'x'
					throw new RuntimeException();
				else if(s1[0].isEmpty()) {
					//case that 'x^' and number
					try {
						power=power(s1[1].substring(1));
					}
					catch(Exception e){
						throw new RuntimeException();
					}
					set_coefficient(1);
					set_power(power);
				}
				else if(s1[0].length()==1 &&s1[0].charAt(0)==45) {
					//case that '-x^' and number
					try {
						power=power(s1[1].substring(1));
					}
					catch(Exception e){
						throw new RuntimeException();
					}
					set_coefficient(-1);
					set_power(power);
				}
				else {
					//case that it's number 'x^' number
					try {
						coefficient=coefficient(s1[0]);
						power=power(s1[1].substring(1));
					}
					catch(Exception e){
						throw new RuntimeException();
					}
					set_coefficient(coefficient);
					set_power(power);
				}
			}
			else if(s1.length>2) 
				throw new RuntimeException();
		}

		//***********************************************************************************//
		else if(index==s.length()) {//if the coefficient is only a number , it's including
			//a number like for example 1234. that it's actually 1234.0 like the calculator
			try {
				coefficient=Double.parseDouble(s);
				set_coefficient(coefficient);
				set_power(0);
			}
			catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}//end Monom
	/**
	 * this method gets a monom and adding him to this.monom iff the have the same power only 
	 * and if not there are no it's throw a Run Time Exception
	 * @param m
	 */
	public void add(Monom m) {
		try {
			if(this.get_power()==m.get_power()) {
				double afterAddCoefficient=this.get_coefficient()+m.get_coefficient();
				if(afterAddCoefficient==0) {
					this.set_coefficient(0);
					this.set_power(0);
				}
				else if(afterAddCoefficient<1&&afterAddCoefficient>-1) {
					System.out.println(afterAddCoefficient);
					this.set_coefficient(afterAddCoefficient);
				}
				else
					this.set_coefficient(afterAddCoefficient);
			}
			else 
				throw new RuntimeException();
		}
		catch(  RuntimeException e) {
		
		}
	}//end add
	/**
	 * this method will do a multiply be the lows of math:
	 * 1. between the coefficients I will multiply
	 * 2. between the powers I will do a addition 
	 * @param d
	 */
	public void multipy(Monom d) {
		if(this.get_coefficient()!=0&&d.get_coefficient()!=0) {
			double afterMultipyCoefficient=this.get_coefficient()*d.get_coefficient();
			int afterMultipyPower=this.get_power()+d.get_power();
			this.set_coefficient(afterMultipyCoefficient);
			this.set_power(afterMultipyPower);
		}
		else {
			this.set_coefficient(0);
			this.set_power(0);
		}
	}//end multipy
	
	/**
	 * this method return's a string of the monom
	 */
	public String toString(){
		String ans = "";
		String coefficientString=Double.toString(this.get_coefficient());
		String powerString=Integer.toString(this.get_power());
		double coefficient=this.get_coefficient();
		int power=this.get_power();

		if(coefficient==0) {
			ans="0";	
		}
		else if(coefficient==1) {
			if(power==0)
				ans="1";
			else if(power!=1)
				ans="x^"+powerString;
			else
				ans="x";
		}
		else if(coefficient==-1) {
			if(power==0)
				ans="-1";
			else if(power!=1)
				ans="-x^"+powerString;	
			else
				ans="-x";
		}
		else  {
			if(power==0)
				ans=coefficientString;
			else if(power!=1)
				ans=coefficientString+"x^"+powerString;	
			else
				ans=coefficientString+"x";
		}
		return ans;
	}//end toString
	
	// you may (always) add other methods.
	// ***************** My functions **********************
	/**My functions that I decided that they important**/
	
	/**
	 * this method is checking if at the monom there is nothing then digits 
	 * and : 'x', '+', '-' ,'.' ,'^' and if not returns false
	 * 	  
	 */
	public boolean is_proper_Monom (String s) {
		boolean flag =true;
		//1.
		if(s.length()==0) {
			flag=false;
			return flag;
		}//end 1.
		//2.
		for (int i = 0; i < s.length(); i++) {
			int temp=s.charAt(i);
			if(temp<48||temp>57) {//it's not a digit , check it with aski code
				//the it's can be only 'x' or:'^' ,or:'-' ,or:'.'
				if(temp!=120&&temp!=94&&temp!=45&&temp!=46) {
					flag=false;
					return flag;
				}					
			}
		}//end 2.
		return flag;
	}
	
	/**
	 * this method is checking if the monoms are equal until EPSILON
	 * it means that monom with coefficent 2.9999999999 is equal to 
	 * 3.000000001 it's for example
	 */
	public boolean equals (Object obj) {//after explanation at the lesson Boaz show that 
		// to as and ew saw it at the  TIRGUL too and this method very similar, he said that
		//it is ok and no problem to use he's methods, just i hava to writh that
		boolean ans=false;
		if(obj!=null&&obj instanceof Monom){
			Monom m=(Monom)obj;
			if(this.isZero()&&m.isZero()) {
				return true;
			}
			else {
				if(Math.abs(this.get_coefficient()- m.get_coefficient())<=EPSILON)
					if(this.get_power()==m.get_power())
						return true;
			}
		}
		return ans;	
	}
	/**
	 * this method gets string and return a double coefficient and if it's incurrect 
	 * it's throw a Run Time Exception
	 * @param s
	 * @return double coefficient
	 */
	public double coefficient (String s) {
		double coefficient=-1;;
		try {
			coefficient= Double.parseDouble(s);
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
		return coefficient;
	}
	/**
	 * this method gets string and return a double coefficient and if it's incurrect 
	 * it's throw a Run Time Exception
	 * @param s
	 * @return int power
	 */
	public int power (String s) {
		int power=-1;;
		try {
			power= Integer.parseInt(s);
			if(power<0)
				throw new RuntimeException();
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
		return power;
	}
	/**
	 * this method gets '-x' and sets a coefficient to -1 and the power to 1
	 */
	public void Minuc_x_Case() {
		double a=-1;
		set_coefficient(a);
		set_power(1);
	}//end Minuc_x_Case

	/**
	 * this method gets 'x' and sets a coefficient to 1 and the power to 1
	 */
	public void Plus_x_Case() {
		double a=1;
		set_coefficient(a);
		set_power(1);
	}//end Plus_x_Case
	//	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
}
