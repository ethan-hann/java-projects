public class SecantData
{
    private int iterationNumber;
    private double x;
    private double fOfX;

    public SecantData(int n, double x, double fx)
    {
        this.iterationNumber = n;
        this.x = x;
        this.fOfX = fx;
    }

    public int getIterationNumber()
    {
        return iterationNumber;
    }

    public double getX()
    {
        return x;
    }

    public double getFOfX()
    {
        return fOfX;
    }
}
