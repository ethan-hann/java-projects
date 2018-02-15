public class QuickSort
{
    /**
     * Method to partition array into two sub-arrays
     * @param a : array to partition
     * @param p : low value of array
     * @param r : high value of array
     * @return an integer representing the partition index
     */
    private static int partition(int a[], int p, int r)
    {
        int x = a[r]; //the pivot value
        int i = p - 1;
        for (int j = p; j < r; j++)
        {
            if (a[j] <= x)
            {
                i++;

                //swap values at a[i] and a[j]
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        //swap values at a[i + 1] and a[r]
        int temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;
        return i + 1;
    }

    /**
     * Method to recursively sort the array using quickSort algorithm
     * @param a : array to sort
     * @param p : the low value of the array
     * @param r : the high value of the array
     */
    public static void sort(int a[], int p, int r)
    {
        if (p < r)
        {
            int q = partition(a, p, r);
            sort(a, p, q - 1);
            sort(a, q + 1, r);
        }
    }
}
