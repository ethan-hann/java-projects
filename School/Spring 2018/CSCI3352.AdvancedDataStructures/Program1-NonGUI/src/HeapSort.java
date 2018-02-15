
public class HeapSort
{
	/**
     * Method to recursively sort the array using heapsort algorithm
     * @param a : array to sort
     */
    public static void sort(int a[])
    {
        int n = a.length;

		//rearrange the array to build the max heap
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(a, n, i);
        }

        for (int i = n - 1; i >= 0; i--)
        {
			//move current root node to the end
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            heapify(a, i, 0); //heapify the reduced heap
        }
    }

	/**
     * Method that will heapify a subtree with root at node i
     * @param a : array to sort
     * @param n : size of the heap
     * @param i : the root of the sub-tree; index in array a[]
     */
    private static void heapify(int a[], int n, int i)
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        //If left child is larger than the root of the heap
        if (left < n && a[left] > a[largest])
        {
            largest = left;
        }

        //If right child is larger than the root of the heap
        if (right < n && a[right] > a[largest])
        {
            largest = right;
        }

        //If largest is not the root
        if (largest != i)
        {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            //heapify the affected sub-tree
            heapify(a, n, largest);
        }
    }
}
