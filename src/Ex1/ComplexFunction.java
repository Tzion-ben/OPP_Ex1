package Ex1;

import javax.management.RuntimeErrorException;

public class ComplexFunction implements complex_function{

	@Override
	public double f(double x) {
		double leftSide;
		double rigthSide;
		double sumOfOperation;

		switch (this.operator) {
		case Plus:
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			sumOfOperation=leftSide+rigthSide;
			return sumOfOperation;

		case Times:
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			sumOfOperation=leftSide*rigthSide;
			return sumOfOperation;

		case Divid:
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			if(this.rigth.f(x)!=0) {//because devision by zero is forbidden 
				if(this.left.equals(this.rigth))
					return 1;//if the functions are equal then the devision will equal
				// to 1 every time
				sumOfOperation=leftSide/rigthSide;
				return sumOfOperation;				
			}
			else {//if it is devision by zero then throw an exception 
				throw new RuntimeException();
			}

		case Max://returning the max side at the complex function
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			if(leftSide>=rigthSide)
				return leftSide;
			else
				return rigthSide;

		case Min://returning the min side at the complex function
			leftSide=this.left.f(x);
			rigthSide=this.rigth.f(x);
			if(leftSide<=rigthSide)
				return leftSide;
			else
				return rigthSide;

		case Comp:

		case None:


		}
		return x;

	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * this method make a copy of the ComplexFunction
	 */
	@Override
	public function copy() {
		String op=whatOperationToDo();
		function ComplexFunctionToCopy=new ComplexFunction(op,this.left,this.rigth);	
		return ComplexFunctionToCopy;
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
		setOp(op);
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
		switch (this.operator) {

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

	//****************** Sets the functions L&R and the operation*****************

	public void setLeft (function left) {
		this.left=left;
	}

	public void setRight (function right) {
		this.rigth=right;
	}

	public void setOp(String op) {
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
