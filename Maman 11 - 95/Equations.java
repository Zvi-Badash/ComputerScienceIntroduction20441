/*
* Zvi Badash 214553034
* This program get the coefficients of a system of 2 linear equations in this form: a11x + a12y = b1
*                                                                                   a21x + a22y = b2
*                                                                                  and output the solution(if exists).
* to check if solution exists we need to check if (a11*a22) - (a12*a21) isn't equal to 0.
* if a solution exist it is this one : ((b1*a22) - (b2*a12)) / ((a11*a22) - (a12*a21), (b2*a11) - (b1*a21)) / ((a11*a22) - (a12*a21))
* to check for infinite number of solutions the following conditions:
* (1) the first condition isn't true. (2) (b1*a22 - b2*a12) = 0. (3) (b2*a11 - b1*a21) = 0.
* (4) non of the equations is in the next form: 0x +0y = b (b != 0).
* to check for no solution we need to check if there is no infinite number of solutions and there is not a single solution.
 */

import java.util.Scanner; // import Scanner class
public class Equations{
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("This program solves a system of 2 linear equations\n" +
                           "Enter the coefficients a11 a12 a21 a22 b1 b2");
        int a11 = scan.nextInt(); // get a11
        int a12 = scan.nextInt(); // get a12
        int a21 = scan.nextInt(); // get a21
        int a22 = scan.nextInt(); // get a22
        int b1  = scan.nextInt(); // get b1
        int b2  = scan.nextInt(); // get b2

        System.out.printf("Eq1: %d*x1+%d*x2=%d\n",  a11, a12, b1); // print the first equation
        System.out.printf("Eq2: %d*x1+%d*x2=%d\n",  a21, a22, b2); // print the second equation

        double xSol = (double)((b1*a22) - (b2*a12)) / ((a11*a22) - (a12*a21)); // calculate the x value
        double ySol = (double)((b2*a11) - (b1*a21)) / ((a11*a22) - (a12*a21)); // calculate the y value

        if((a11*a22) - (a12*a21) != 0){ //check for one slution
            System.out.printf("Single Solution: (%.3f, %.3f)", xSol, ySol);
        }
        else if((b2*a11 - b1*a21) == 0 && (b1*a22 - b2*a12) == 0 && ((a11+a12 != 0 || a21+a22 != 0) && (b1 != 0|| b2 != 0))){ // check for many solutions
            System.out.println("Many Solutions");
        }
        else{ // check for no solution
            System.out.println("No Solution");
        }

    } // end of main method
} // end of Equations class