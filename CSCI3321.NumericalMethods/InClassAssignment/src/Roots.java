/**
 * Class that can be used to find an approximate root of an equation.
 * In this case, the equation f(x) = sin(x + 0.45) is used and the interval
 * [-1.0, 2.5] is used. The interval can be adjusted however to find other roots.
 * Uses a combination of Newton's method and the Bisection method to approximate the roots.
 */
public class Roots
{
    public static void main(String[] args)
    {
        System.out.println("The root of the equation on the interval [-1.0, 2.5] is: " +
                newtons(bisection(-1.0, 2.5)));
    }

    private static double f(double x)
    {
        return Math.sin(x + 0.45);
    }

    private static double f_prime(double x)
    {
        return Math.cos(x + 0.45);
    }

    /**
     * Method for the bisection algorithm of finding a zero
     * @param a : negative interval bound
     * @param b : positive interval bound
     * @return c : an approximation of the zero
     */
    private static double bisection(double a, double b)
    {
        double c = 1.0;

        while (b - a >= 0.5) //continue iterations until the interval is less than 0.5
        {
            c = (a + b) / 2.0; //midpoint of interval
            if (f(c) < 0) //if the function evaluated at the midpoint is negative
            {
                a = c; //assign c to a
            }
            else
            {
                b = c; //otherwise, assign c to b
            }
        }
        return c;
    }

    /**
     * Method for Newton's method of finding a zero
     * @param x0 : the initial guess
     * @return x0 : the approximated root
     */
    private static double newtons(double x0)
    {
        double x1; //the x_n+1 value in the equation: x_n+1 = x_n - (f(x_n) / f'(x_n))
        do
        {
            x1 = x0 - (f(x0) / f_prime(x0));
            x0 = x1; //set initial guess to the calculated x_n+1 value
        }while (Math.abs(f(x0)) > 1.0e-9); //continue iterating until we are within an acceptable tolerance

        return x0;
    }
}
