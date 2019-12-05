package Ex1;

public class ComplexFunction implements complex_function{

	@Override
	public double f(double x) {
		Polynom p1 = new Polynom(this.resultOperationFunction.toString());
		double fResult=p1.f(x);
		if(this.operator==Operation.Divid) {
			//if it is the devision then it's have to compute the tow functions and
			//then 
			Polynom pL=new Polynom(this.left.toString());
			Polynom pR=new Polynom(this.right.toString());
			double fResultLeft=pL.f(x);
			double fResultRight=pR.f(x);
			fResult=fResultLeft/fResultRight;
			return fResult;
		}
		return fResult;

	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * this method gets the right function or a function from the main and
	 * make an addition to the resultFunction that holds the left function
	 * or the right and the left functions that was merged  
	 */
	@Override
	public void plus(function f1) {
		Polynom_able p1=new Polynom(f1.toString());
		Polynom_able p2=new Polynom(this.resultOperationFunction.toString());
		p2.add(p1);
		setResultOperationFunction(p2);
	}
	/**
	 * this metod gets the right function or a function from the main and
	 * multiply to the resultFunction that holds the left function
	 * or the right and the left functions that was merged 
	 */
	@Override
	public void mul(function f1) {
		Polynom_able p1=new Polynom(f1.toString());
		Polynom_able p2=new Polynom(this.resultOperationFunction.toString());
		p2.multiply(p1);
		setResultOperationFunction(p2);
	}
	/**
	 * 
	 */
	@Override
	public void div(function f1) {

		Polynom p1=new Polynom(f1.toString());
		Polynom p2=new Polynom(this.resultOperationFunction.toString());
		if(p1.equals(p2)) {
			//if the functions is equal then the devision between them gut us 1
			String one="1";
			Polynom ans=new Polynom(one);
			setResultOperationFunction(ans);
		}// end if
		try {
			if(p1.isZero()) {//if the divaider is ZERE it is forbidden to divide 
				throw new RuntimeException();
			}
		}
		catch(Exception e) {
			System.out.println("Division by ZERO is forbidden");
		}

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {

		return null;
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
	public ComplexFunction (String op,function left, function right) {
		setLeft(left);
		setRight(right);
		setOp(op);
		setResultOperationFunction(left);//puting at the result of the oparation 
		//automatically the left side and then make add or multiply or one of the operations
		// between the functions
		whatOperationToDo();
	}
	/**
	 * a constructor just with the left Polynom, so the operation is None and the 
	 * result function is equal just to left
	 * @param left
	 */
	public ComplexFunction (function left) {
		setLeft(left);
		setRight(null);
		setResultOperationFunction(left);
		this.operator=Operation.None;
	}

	/**
	 * checking what the operation the complex function have to do
	 */
	public void whatOperationToDo () {
		switch (this.operator) {

		case Plus:
			plus(this.right);break;

		case Times:
			mul(this.right);break;

		case Divid:
			div(this.right);break;
			//
			//		case Max:
			//			maxLeftRight;break;
			//
			//		case Min:
			//			minLeftRight;break;
			//
			//		case Comp:
			//			copmLeftRight;break;
			//		}
		}
	}

	//****************** Sets the functions L&R and the operation*****************

	public void setLeft (function left) {
		this.left=left;
	}

	public void setRight (function right) {
		this.right=right;
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
	}
	/**
	 * the function thet i gut after the operation, if it's just left function
	 * then it equal to the left function
	 * @param res
	 */
	public void setResultOperationFunction (function res) {
		this.resultOperationFunction=res;
	}

	//****************** Private Methods and Data *****************

	private function left;
	private function right;
	private function resultOperationFunction;
	private Operation operator;

}
