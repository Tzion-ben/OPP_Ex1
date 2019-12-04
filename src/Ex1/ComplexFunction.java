package Ex1;

public class ComplexFunction implements complex_function{

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public void plus(function f1) {
		Polynom_able p1=new Polynom(f1.toString());
		Polynom_able p2=new Polynom(this.resultOperationFunction.toString());
		p2.add(p1);
		setResultOperationFunction(p2);
	}

	@Override
	public void mul(function f1) {
		Polynom_able p1=new Polynom(f1.toString());
		Polynom_able p2=new Polynom(this.resultOperationFunction.toString());
		p2.multiply(p1);
		setResultOperationFunction(p2);
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub

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
		setResultOperationFunction(left);
		whatOperation();
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
		Polynom p=new Polynom();
		this.operator=Operation.None;
	}

	/**
	 * 
	 */
	public void whatOperation () {
		switch (this.operator) {
		case Plus:

			plusLeftRight();break;

		case Times:
			mulLeftRight();break;
			//
			//		case Divid:
			//			divLeftRight;break;
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

	/**
	 * sending the rigth side to the add it to the left side
	 */
	public void plusLeftRight() {
		plus(this.right);}

	/**
	 * sending the rigth side to the add it to the left side
	 */
	public void mulLeftRight() {
		mul(this.right);}


	//****************** Sets the functions L&R *****************

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
