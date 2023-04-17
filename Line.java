/***************************************************************** 
This program demonstrates a simple "Line" class. Here, a Line class
 is defined with its properties and interface (i.e., its methods). 
A main method (in TestLine) then creates instances of this Line
 class and calls on the methods to demonstrate its behavior. 
*****************************************************************/
package hibahpackage;
import java.io.*; 

/* Code Changes and Enhancements
 * Adding another constructor for Line class that calls the first previously defined constructor with 4 arguments.
 * The 2nd constructor simply takes input two objects of TwoDpoint class,  then extract the 4 variables and assign the values to the 4 variables of Line class.
 * Also adding the functionality, to throw an exception when the values of xOne, yOne,xTwo, yTwo are out of range.
 * There are two other methods called getAngle and getLength defined that return theangle between the line and horizontal linealong with the euclidean distance of the line.
 * Added print statements as well as commented the parts*/

public class Line
{

private	int x1, y1, x2, y2; //coordinates of the line 
//Constructor Receives 4 integers which are the Line's start and end points. 
public Line(int xOne, int yOne, int xTwo, int yTwo) 
{ 
// each of these validates its argument - see below. 
	setXOne(xOne); 
	setYOne(yOne); 
	setXTwo(xTwo); 
	setYTwo(yTwo);
} // end constructor

public Line(TwoDPoint t1, TwoDPoint t2){
	this(t1.x, t1.y, t2.x, t2.y);
}
//method draw() calls another method called drawLine(), 
//which is assumed to be a graphics primitive on the 
//system. However, since this program will be 
//run in console mode, a text description of the Line 
//will be displayed. // 
public void draw() 
{ 
	drawLine(x1, y1, x2, y2);
}

//method drawLine() simulates drawing of a line for console mode. 
//It should describe all the important attributes of the line. 
//In a graphics mode program, we would delete this and use the 
//system's Graphics library drawLine(). // 
private void drawLine(int x1, int y1, int x2, int y2) 
{ 
	System.out.println("Drawing a line from x of " + x1 + " and y of " + y1); 
	System.out.println("to x of " + x2 + " and y of " + y2 + " Success.\n");
} 
                  
//Method setLine() allows user to change the points of the 
//already existing Line. 
public void setLine(int xOne, int yOne, int xTwo, int yTwo) 
{ 
  setXOne(xOne);
  setYOne(yOne);
  setXTwo(xTwo);
  setYTwo(yTwo);
} 
// -- the individual setXXXX methods that prevent 
//  any line's coordinate from being offscreen.  
//  In the event of an invalid (offscreen) value,  
//  that value is (silently) set to 0. 
public void setXOne(int xOne)
{
if (xOne < 0 || xOne > 639)
	//if value is out of range i.e not in between 0 and 639 then throw exception
	throw new ArithmeticException("xOne has to be between 0 and 639.");
else
	x1 = xOne;
} 
public void setYOne(int yOne) 
{    
	if (yOne < 0 || yOne > 479)
		//if value doesnt lie between 0 and 479 then throw eception
		throw new ArithmeticException("yOne has to be between 0 and 479.");
	else
		y1 = yOne;
} 
public void setXTwo(int xTwo) 
{    
	if (xTwo > 639 || xTwo < 0)
		//if value for xtwo is not in the given range throw eception
		throw new ArithmeticException("xTwo has to be between 0 and 639.");
	else
		x2 = xTwo;
} 
public void setYTwo(int yTwo) 
{    
	if (yTwo > 479 || yTwo < 0)
		//if value for ytwo is not in between 0 and 479 then throw exception
		throw new ArithmeticException("yTwo has to be between 0 and 479.");
	else
		y2 = yTwo;
} 
//Now for some "get" Access methods to get individual values 
public int getXOne() 
{  
	return x1; 
}  
public int getYOne() 
{  
	return y1; 
}  
public int getXTwo()
{
	return x2; 
} 
public int getYTwo() 
{ 
	return y2; 
}
 
//Calculating the length of the line using the Euclidean distance formula
public double getLength(){
	return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
}
public double getAngle(){
	double length= getLength();
	
	//Calculating the length and typecast the (y2-y1) from int to double since the length is also a double datatype
	return Math.asin((double)(y2-y1)/length);
}
} // end class Line

