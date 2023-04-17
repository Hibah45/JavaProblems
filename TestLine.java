/**Now we will code a driver program (see below) called TestLine with main() where execution will begin.
 * It is this class, and this code, that will create instances of the Line and call its methods.
 * As a test module, this code would be improved with additional System.out.println() statements that explain what is being attempted and
what the results should be.
"About to change l1 to an invalid value and then redraw it. Line position should not change:
**/

/**Code enhancements
 *Main method for Line.java and TwoDpoints class where all the coordinates are tested with valid and invalid values.
 *Lines are created , set and redrawn again with set of values and eceptions are thrown if there is an illegal values
 * **/

import hibahpackage.*;


class TestLine
{
public static void main(String args[]) {
	String message="EXCEPTION:This try catch caught a Generic Exception for bad constructor -Failed to create a line with 4 invalid values";
	Line l1 = null, l2 = null,l3=null, l4 = null; //declare 2 instances of Line class 
	//create 1 Line object
	System.out.println("\n *********Create line 1**********\n");
	try {
		l1 = new Line (10, 20, 60, 90);
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	System.out.println("Draw line 1\n");
	l1.draw();
	System.out.println("Line coordinates are changed to 5, 5, " +
	l1.getXTwo() + ", "+l1.getYTwo());
	
	System.out.println("Change start point with valid values:\n");
	try {
		l1.setLine(5, 5, l1.getXTwo(), l1.getYTwo());
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	//draw it again with new start point
	System.out.println("Draw l1 again with new start point. \n");
	l1.draw();
//try to change xOne (x1) to an illegal value
	System.out.println("Line coordinates of xOne are changed to illegal values 3000, 5, " +
	l1.getXTwo() +", "+l1.getYTwo());
	try {
		l1.setXOne(3000);
	}
	catch(Exception e)
	{	
		System.out.println(message);
	}
//draw the line...x1 should now be zero
	System.out.println("\nRedraw line-line should not change");
	l1.draw();
	
	//create a second Line instance, or object
	System.out.println("\n **********Create second line*******\n");
	try {
		l2 = new Line(100, 100, 400, 400);
		System.out.println("Line two:");
		System.out.println("Line coordinates are 100, 100, 400, 400");
	}
	//draw 2nd line
	catch(Exception e){
		System.out.println(e.getMessage());
	}

	l2.draw();
	//set a new valid yTwo
	try {
		l2.setYTwo(479);
		System.out.println("Line coordinates are changed to 100, 100, 400,479");
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	//draw 2nd line again
	System.out.println("Redraw line 2");
	l2.draw();
	System.out.println("The Length of a line one is: " +l1.getLength());
	System.out.println("The Length of a line two is: " +l2.getLength() +"\n");
	System.out.println("The Angle of a line one is: " +l1.getAngle());
	System.out.println("The Angle of a line two is:" +l2.getAngle());

	System.out.println("\n **********Creating TwoDPoint*********\n");
	TwoDPoint twoDPoint1 = new TwoDPoint(10, 100);
	TwoDPoint twoDPoint2 = new TwoDPoint(5, 400);
	try {
	Line twoDLinePoint = new Line(twoDPoint1, twoDPoint2);
	System.out.println("Test 2D point constructor x1: " +twoDPoint1.x);
	System.out.println("Test 2D point constructor y1: " +twoDPoint1.y);
	System.out.println("Test 2D point constructor x2: " +twoDPoint2.x);
	System.out.println("Test 2D point constructor y2: " +twoDPoint2.y);
	}
	catch(Exception e){
		System.out.println(message);
	}
	try {
		l3 = new Line(10,5,100,400);
	}
	catch(Exception e){
		System.out.println(message);
	}
	System.out.println("\n*******Draw and measure line 3*******\n");
	l3.draw();
	System.out.println("The Length of a line three is: " +l3.getLength() +"\n");
	System.out.println("The Angle of a line one is: " +l3.getAngle());
	
	System.out.println("\n******creating line 4 with bad values*******\n");
	try{
		l4 = new Line(100, 500, 1000, 3000);
	}
	catch(Exception e){
		System.out.println(message);
		System.out.println("\n");
		System.exit(88);
	}
	

}
}
