/*
* Zvi Badash 214553034
* This program get the upper-left(x1, y1) and the lower-right(x2, y2) corners of a rectangle and
  output the radius, area and perimeter of the inner-circle and the external-circle.
* to calculate the inner-circle radius the program calculates its diameter = y1 - y2 (the distance from (x1, y1) to (x1, y2))) and then got the radius.
* to calculate the external-circle radius the program calculates its diameter
  = sqrt((x1 - x2)^2 + (y1 - y2)^2) (the distance from (x1, y1) to (x2, y2)) and then got the radius.
*/

import java.util.Scanner; // import Scanner class
public class Circle{
    public static void main (String[] args)
    {
        final double PI = Math.PI; // declare Pi as final variable.
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculate the areas" +
                " and the perimeters of the excircle and the incircle of a given rectangle ");

        System.out.println("Please enter the two coordinates of the left-upper point of the rectangle: ");
        int LeftUpX = scan.nextInt(); //get x1
        int LeftUpY = scan.nextInt(); //get y1

        System.out.println("Please enter the two coordinates of the right-lower point of the rectangle: ");
        int RightDownX = scan.nextInt(); // get x2
        int RightDownY = scan.nextInt(); // get y2

        double IncircleRadius = (double)(LeftUpY - RightDownY)/2; // calculation listed above
        double ExcircleRadius = Math.sqrt(Math.pow((LeftUpX - RightDownX), 2) + Math.pow((LeftUpY - RightDownY), 2))/2;  // calculation listed above
        
        double IncirclePerimeter = PI * 2 * IncircleRadius; //Perimeter = 2 * Pi * Radius
        double ExcirclePerimeter = PI * 2 * ExcircleRadius; //Perimeter = 2 * Pi * Radius

        double IncircleArea = PI * Math.pow(IncircleRadius, 2); //Area = Pi * Radius^2
        double ExcircleArea = PI * Math.pow(ExcircleRadius, 2); //Area = Pi * Radius^2

        System.out.println("Incircle : radius = " + IncircleRadius + ", area = " + IncircleArea + ", perimeter = " + IncirclePerimeter); //Output
        System.out.print("Excircle : radius = " + ExcircleRadius + ", area = " + ExcircleArea + ", perimeter = " + ExcirclePerimeter); //Output


    } // end of main method
} // end of Circle class