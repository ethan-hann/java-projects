import java.util.ArrayList;

public class SecantMethod
{
    public static final double TRUE_ANSWER = 0.549306144356;
    /**
     * Function that is called from the SecantGUI class. Calculates the
     * approximate root of the equation: x = x1 - ((f(x1) * (x1 - x0))/(f(x1) - f(x0)));
     * @param x0 : 1st initial guess
     * @param x1 : 2nd initial guess
     * @return : an arrayList of SecantData objects; each holding the iteration number (n),
     * the approximation to x, and the f(xn) value
     */
    public static ArrayList<SecantData> calculate(double x0, double x1)
    {
        int n = 1; //iteration number
        double x; //placeholder x value
        ArrayList<SecantData> data = new ArrayList<>();

        do
        {

            //System.out.println("Iteration: " + n);
            x = x1 - ((f(x1) * (x1 - x0))/(f(x1) - f(x0)));
            //System.out.println("x: " + x);
            //System.out.println("f(xn): " + f(x) + "\n");
            SecantData s = new SecantData(n, x, f(x));
            data.add(s);
            n++;
            x0 = x1;
            x1 = x;
        } while (Math.abs(f(x) - TRUE_ANSWER) > 0.000000001);
        return data;
    }

    /**
     * f(x) function: f(x) = tanh(x) - 0.5
     * @param x : x-value
     * @return double
     */
    private static double f(double x)
    {
        return Math.tanh(x) - 0.5;
    }
}
