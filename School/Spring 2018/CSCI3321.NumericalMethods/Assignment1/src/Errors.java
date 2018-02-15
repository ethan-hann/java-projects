/**********************************************************************************
 * PROGRAMMER: Ethan Hann
 * COURSE: CSCI 3321.01
 * DATE: January 26, 2018
 * ASSIGNMENT: Number 1
 * FILES INCLUDED: Errors.java
 * PURPOSE: To visualize rounding errors and
 *          truncation errors by attempting to find an approximation
 *          of a derivative of f(x).
 * INPUT: h and x; Input from program. Defined as 1.0 and 2.0 at start.
 * OUTPUT: h value, approximated derivative, and error in approximation
 *         at each iteration.
 * ALGORITHM:
 * 1) Define h, x, and true derivative
 * 2) Find f'(x) using x and h
 * 3) Print out h, approximated derivative, and error in approximation
 * 4) Divide h by 2.0 and store in h
 * 5) Is h < 2.0e-12?
 * 6) If yes, END PROGRAM
 * 7) If no, goto Step 3.
 **********************************************************************************/

public class Errors
{
    public static void main(String[] args)
    {
        int counter = 0; //keeps track of number of iterations
        double h = 1.0; //initial h value
        double x = 2.0; //x-value to find the derivative at
        double true_answer = 4 + 12 * Math.cos(8); //the true derivative

        System.out.println("The true derivative of f(x) = sin(x^3) + x^2 at x = 2 is: \n" +
                true_answer);

        while (h >= 2.0e-12)
        {
            counter++;
            double approx = f_prime(x, h);
            double error = true_answer - approx;

            System.out.println("------------------------");
            System.out.println(
                    "Iteration: " + counter + "\n" +
                            "h: " + h + "\n" +
                            "f'(x): " + approx + "\n" +
                            "Error: " + error
            );

            h /= 2.0;
        }
    }

    //f(x) function: f(x) = sin(x^3) + x^2
    private static double f(double x)
    {
        return Math.sin(Math.pow(x, 3)) + Math.pow(x, 2);
    }

    //f'(x) function
    private static double f_prime(double x, double h)
    {
        return (f(x + h) - f(x)) / h;
    }
}
