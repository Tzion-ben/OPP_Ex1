package Ex1;

import java.util.Iterator;

public class ComplexFunction implements complex_function{
	/**  
	 * this method will calculate the f of the complex function at x that
	 * i will gave him in different oparation , depend on what oparation i will
	 * sent to it
	 */
	@Override
	public double f(double x) {
		double leftSide;
		double rigthSide;
		double sumOfOperation=0;;

		switch (this.operator) {
		case Plus:
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			sumOfOperation=leftSide+rigthSide;break;

		case Times:
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			sumOfOperation=leftSide*rigthSide;break;

		case Divid:
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			if(rigthSide!=0) {//because devision by zero is forbidden 
				sumOfOperation=leftSide/rigthSide;break;			
			}
			else //if it is devision by zero then throw an exception 
				throw new RuntimeException();

		case Max://returning the max side at the complex function
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			if(leftSide>=rigthSide)
				sumOfOperation=leftSide;
			else
				sumOfOperation=rigthSide;
			break;

		case Min://returning the min side at the complex function
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			if(leftSide<=rigthSide)
				sumOfOperation=leftSide;
			else
				sumOfOperation=rigthSide;
			break;

		case Comp:
			rigthSide=this.rigth.f(x);
			leftSide=this.left.f(rigthSide);
			sumOfOperation=leftSide;break;

		case None:
			sumOfOperation=this.left.f(x);break;
		}
		return sumOfOperation;
	}

	/**
	 * this method gets a string and make a complex function recursively
	 */
	@Override
	public function initFromString(String s) {
		boolean leftSideComp;
		boolean rigthSideComp;
		//if the string is just a left function and nothing else
		leftSideComp=isFunction(s);
		if(leftSideComp){
			function leftSide=new Polynom(s);
			return new ComplexFunction(leftSide);
		}	
		//if the string is left and right and more functions
		int i=0;
		while(s.charAt(i)!='(') //runs until it : (
			i++;

		int j=s.length()-1;
		while(s.charAt(j)!=')')//and from the other side runs until it : )
			j--;

		String tempString=s.substring(i+1, j);
		String [] tempSpilit =tempString.split(",");

		for(int g=1;g<tempSpilit.length-1;g++) {
			//checking it there is  ) after spliting and clean them
			int toCutparenthesis=tempSpilit[g].length();
			if(tempSpilit[g].contains(")"))
				tempSpilit[g]=tempSpilit[g].substring(0, toCutparenthesis-1);
		}//end for
		String op="";
		if(s.charAt(0)>96&&s.charAt(0)<123)//if it's immediately the operation
			op=s.substring(0, i);
		else {//if before the operation it contains function
			//so then run at while loop until its' just the operation
			int k=0;
			while((s.charAt(k)<97||s.charAt(k)>122)||(s.charAt(k)==120)) 
				k++;
			op=s.substring(k,i);
		}

		leftSideComp=isFunction(tempSpilit[0]);
		rigthSideComp=isFunction(tempSpilit[1]);
		int x=2;
		while((!leftSideComp&&!rigthSideComp)&&(x<tempString.length())) {
			if(x%2==0)//because the left side is on the even index at the array after the split
				leftSideComp=isFunction(tempSpilit[x]);
			else
				rigthSideComp=isFunction(tempSpilit[x]);
			x++;
		}
		if(x>=2) {//if the array of the string after the spliting is bigger then 2
			if(leftSideComp)
				x=x-2;
			else if (rigthSideComp)
				x--;
		}
		if(leftSideComp&&rigthSideComp) {//works when it got to the last deep part of the string 
			function leftSide=new Polynom(tempSpilit[0]);
			function rigthSide = new Polynom(tempSpilit[1]);
			return new ComplexFunction(op,leftSide,rigthSide);
		}
		if(!leftSideComp&&rigthSideComp) {//the right side is can be a function
			function rigthSide = new Polynom(tempSpilit[x]);
			return new ComplexFunction(op,initFromString(tempString),rigthSide);
		}
		if(leftSideComp&&!rigthSideComp) {//the left side is can be a function
			function leftSide=new Polynom(tempSpilit[x]);
			return new ComplexFunction(op,leftSide,initFromString(tempString));
		}

		return null;//it's null because the method can't be without a return, so i put 
		//null because i not need it
	}

	/**
	 * this metod merge the right and the left side together to one string 
	 */
	@Override	
	public String toString() {
		if(this.operator==Operation.None)
		{
			//this if is if the fuction have just a left side
			String ans="";
			ans=this.left.toString();
			return ans;
		}
		String op=whatOperationToDo();
		String ans=op+"(";
		ans=ans+toStringLeft();
		ans=ans+","+toStringRight();
		return ans;
	}


	/**
	 * this metod returns the left string from the left side of the string
	 * recursively
	 * @returns
	 */
	public String toStringLeft() {
		String ans="";
		while(this.left instanceof Polynom) {
			ans=ans+this.left.toString();
			return ans;	
		}
		return this.left.toString();
	}
	/**
	 * this metod returns the right string from the right side of the string
	 * recursively
	 * @return
	 */
	public String toStringRight() {
		String ans="";
		while(this.rigth instanceof Polynom) {
			String op=whatOperationToDo();
			ans=ans+this.rigth.toString()+")";
			return ans;
		}
		return this.rigth.toString();	
	}

	/**
	 * this method make a deep copy of the ComplexFunction
	 */
	@Override
	public function copy() {
		String op=whatOperationToDo();
		ComplexFunction ComplexFunctionToCopy=new ComplexFunction(op,this.left,this.rigth);	
		return ComplexFunctionToCopy;
	}

	/**
	 * this methos is checkimg if two Complec Functions are equal or not
	 * at range -2000 to 2000 because we can't make a comparison between toe complex function 
	 * we not have a way to compare something like for example x^2/x and x, so i compare 
	 * the f(x) of the two Complex Functions , but we can't say that thay realy equal because 
	 * we need to compare all the numbers until infinity and it's effective, so we compare
	 * a some range and for that range they equal
	 */
	public boolean equals(Object cf) {
		boolean flag=true;
		if(cf!=null&&cf instanceof ComplexFunction){
			for(int x=-2000;x<=2000;x++) {
				ComplexFunction tempThis=new ComplexFunction(this.operator,this.left,this.rigth);
				double fXthis=Math.abs(tempThis.f(x));
				ComplexFunction tempCf=(ComplexFunction)cf;
				double fXcf=tempCf.f(x);
				if (fXthis!=(Math.abs(fXcf))) {
					flag=false;
					return flag;
				}
			}
		}
		return flag;	
	}
	/**
	 *this method put the this.rigth, and this.left to the left as a ComplexFunction
	 *and changes the this.rigth to f1 and the this.oparation to Plus
	 */
	@Override
	public void plus(function f1) {
		String op=whatOperationToDo();
		ComplexFunction plusLR=new ComplexFunction(op,this.left,this.rigth);
		this.left=plusLR;
		this.rigth=f1;
		this.operator=Operation.Plus;
	}
	/**
	 *this method put the this.rigth, and this.left to the left as a ComplexFunction
	 *and changes the this.rigth to f1 and the this.oparation to Times
	 */
	@Override
	public void mul(function f1) {
		String op=whatOperationToDo();
		ComplexFunction mulLR=new ComplexFunction(op,this.left,this.rigth);
		this.left=mulLR;
		this.rigth=f1;
		this.operator=Operation.Times;
	}

	/**
	 *this method put the this.rigth, and this.left to the left as a ComplexFunction
	 *and changes the this.rigth to f1 and the this.oparation to Divid
	 */
	@Override
	public void div(function f1) {
		String op=whatOperationToDo();
		ComplexFunction divLR=new ComplexFunction(op,this.left,this.rigth);
		this.left=divLR;
		this.rigth=f1;
		this.operator=Operation.Divid;
	}

	/**
	 *this method put the this.rigth, and this.left to the left as a ComplexFunction
	 *and changes the this.rigth to f1 and the this.oparation to Max
	 */
	@Override
	public void max(function f1) {
		String op=whatOperationToDo();
		ComplexFunction maxLR=new ComplexFunction(op,this.left,this.rigth);
		this.left=maxLR;
		this.rigth=f1;
		this.operator=Operation.Max;
	}

	/**
	 *this method put the this.rigth, and this.left to the left as a ComplexFunction
	 *and changes the this.rigth to f1 and the this.oparation to Min
	 */
	@Override
	public void min(function f1) {
		String op=whatOperationToDo();
		ComplexFunction minLR=new ComplexFunction(op,this.left,this.rigth);
		this.left=minLR;
		this.rigth=f1;
		this.operator=Operation.Min;
	}

	/**
	 *this method put the this.rigth, and this.left to the left as a ComplexFunction
	 *and changes the this.rigth to f1 and the this.oparation to Comp
	 */
	@Override
	public void comp(function f1) {
		String op=whatOperationToDo();
		ComplexFunction compLR=new ComplexFunction(op,this.left,this.rigth);
		this.left=compLR;
		this.rigth=f1;
		this.operator=Operation.Comp;
	}

	@Override
	public function left() {
		return this.left;		
	}

	@Override
	public function right() {
		return this.rigth;
	}

	@Override
	public Operation getOp() {
		return this.operator;
	}
	// ***************** My functions **********************
	/**My functions that I decided that they important**/
	/**
	 * Contractor for the ComplexFunction from toe polynoms and string that
	 * represents an operator
	 * @param op
	 * @param left
	 * @param right
	 */
	public ComplexFunction (String op,function left, function rigth) {
		setLeft(left);
		setRight(rigth);
		setOpFromString(op);
	}

	/**
	 * Contractor for the ComplexFunction from toe polynoms and Operation that
	 * represents an operator
	 * @param op
	 * @param left
	 * @param rigth
	 */
	public ComplexFunction (Operation op,function left, function rigth) {
		setLeft(left);
		setRight(rigth);
		this.operator=op;
	}
	/**
	 * a constructor just with the left Polynom, so the operation is None and the 
	 * result function is equal just to left
	 * @param left
	 */
	public ComplexFunction (function left) {
		setLeft(left);
		setRight(null);
		this.operator=Operation.None;
	}

	/**
	 * checking what the operation the complex function have to do
	 */
	public String whatOperationToDo () {
		switch (this.operator) {//for the error there is no need a case

		case Plus:
			return "plus";

		case Times:
			return "mul";

		case Divid:
			return "div";

		case Max:
			return "max";

		case Min:
			return "min";

		case Comp:
			return "comp";

		}
		return "None";
	}

	/**
	 * this method checking if the string is can be at the future function 
	 * @param s
	 * @return
	 */
	public boolean isFunction (String s) {
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
				if(temp!=120&&temp!=94&&temp!=45&&temp!=46&&temp!=43&&temp!=' ') {
					flag=false;
					return flag;
				}					
			}
		}//end 2.
		return flag;
	}
	//****************** Sets the functions L&R and the operation*****************

	public void setLeft (function left) {
		this.left=left;
	}

	public void setRight (function right) {
		this.rigth=right;
	}

	public void setOpFromString(String op) {
		switch (op) {
		case "plus":
			this.operator=Operation.Plus;break;

		case "mul":
			this.operator=Operation.Times;break;

		case "div":
			this.operator=Operation.Divid;break;

		case "max":
			this.operator=Operation.Max;break;

		case "min":
			this.operator=Operation.Min;break;

		case "comp":
			this.operator=Operation.Comp;break;

		default:
			this.operator=Operation.Error;break;
		}
	}//end setOp

	//****************** Private Methods and Data *****************

	private function left;
	private function rigth;
	private Operation operator;
}