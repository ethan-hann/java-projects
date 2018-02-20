/*
 * Class that implements a hailstone sequence, which can be found in
 * Douglas Hofstadter’s book "Gödel, Escher, Bach"
 * The algorithm is as follows:
 *  Pick some positive integer and call it n.
    If n is even, divide it by two.
    If n is odd, multiply it by three and add one.
    Continue this process until n is equal to one.
 */
import java.util.Scanner;

public class HailStone
{
    private static int counter = 0;
    
    public static void main(String[] args)
    {
        boolean isValidInput;
        int n = 0;
        Scanner input = new Scanner(System.in);
        do
        {
            try
            {
                System.out.print("Enter a number: ");
                n = Integer.parseInt(input.next());
                isValidInput = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("You must enter an integer number!");
                isValidInput = false;
            }
        } while (!isValidInput);
    
        performHail(n);
        System.out.printf("That took %d steps to reach 1!\n", counter);
        
    }
    
    /**
     * Performs the hailstone sequence recursively
     * @param n : the positive integer to start with
     * @return either n/2 or 3n+1 depending on whether n is even or odd
     */
    private static int performHail(int n)
    {
        if (n == 1)
        {
            return 1;
        }
        
        if (n % 2 == 0) //if n is even
        {
            counter++;
            System.out.printf("%d is even, so I take half: %d\n", n, n / 2);
            return performHail(n / 2);
        }
        
        counter++;
        System.out.printf("%d is odd, so I do 3n + 1: %d\n", n, 3*n+1);
        return performHail(3 * n + 1);
    }
}
