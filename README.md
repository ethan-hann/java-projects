# How About a Nice Cup of _Java_?
```java
public class HelloWorld
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
    }
}
```
Welcome to my Java Project Repository!

Here you will find some of my Java projects that I have worked on over the years. These include personal projects and school projects. See below for descriptions of projects if you don't want to go through the repository :smile:

_Interested in other projects I've worked on not Java-related? Check out my other [GitHub Repositories](https://github.com/ethan-hann)._

## School Projects
### Fall 2016
* **CSCI 1471 - ComputerScience2**
  * Area Calculator
  * Car Trip V2
  * Caesar Cipher
  * Grade Level
  * Palindrome Checker

### Spring 2018
* **CSCI 3321 - Numerical Methods**
  * Approximating the Derivative of ![f(x)](http://latex.codecogs.com/gif.latex?f%28x%29) using the definition of the derivative _(Assignment 1)_:

     ![equation](http://latex.codecogs.com/gif.latex?f%27%28x%29%20%3D%20%5Clim_%7Bh%20%5Cto%200%7D%20%5Cfrac%7Bf%28x&plus;h%29%20-%20f%28x%29%7D%7Bh%7D)

  * Approximating a Root of ![f(x)](http://latex.codecogs.com/gif.latex?f%28x%29) using Secant Method _(Assignment 2)_:

     ![equation](http://latex.codecogs.com/gif.latex?x_%7Bn&plus;1%7D%20%3D%20x_%7Bn%7D%20-%20%5Cfrac%7Bf%28x_%7Bn%7D%29%28x_%7Bn%7D%20-%20x_%7Bn-1%7D%29%7D%7Bf%28x_%7Bn%7D%29%20-%20f%28x_%7Bn-1%7D%29%7D%2C%20%5Ctext%7Bwhere%20%7D%20x_%7Bn%7D%20%5Ctext%7Band%20%7D%20x_%7Bn-1%7D%20%5Ctext%7Bare%20initial%20guesses%20for%20the%20root%7D)

  * Approximating a Root of ![f(x)](http://latex.codecogs.com/gif.latex?f%28x%29) using Bisection Method and Newton's Method _(InClassAssignment)_:

    **_Newton's Method_**  
    ![equation](http://latex.codecogs.com/gif.latex?%5Ctext%7BStarting%20with%20initial%20guess%20%7D%20x_%7B0%7D%3A%20x_%7Bn&plus;1%7D%20%3D%20x_%7Bn%7D%20-%20%5Cfrac%7Bf%28x_%7Bn%7D%29%7D%7Bf%27%28x_%7Bn%7D%29%7D)

     **_Bisection Method_**  
     [Check out this link to learn more about the Bisection Method](http://www.sosmath.com/calculus/limcon/limcon07/limcon07.html)


* **CSCI 3352 - Advanced Data Structures**
  * Comparison of QuickSort and HeapSort _(Program1-NonGUI)_
  This program compares the exection times of the QuickSort and HeapSort algorithms.

    **QuickSort:**
The quick sort algorithm is relatively simple. It is broken up into two parts: a recursion method called QUICKSORT and a method that partitions the input array into smaller sub-arrays, called PARTITION.

    PARTITION will always select the last element A[r] in the subarray A[p . . . r] as the **_pivot_** or the element around which to partition.  
    [Check out this link to learn more about QuickSort](https://www.geeksforgeeks.org/quick-sort/)

    **HeapSort:**
    The heap sort algorithm is pretty straightforward. (Once you know what a heap is, of course!)

    _What is a binary heap?_ Well, in layman's terms, it is simply an array where there is a special relationship between the indices of the elements. If the parent node of the heap is stored at index `i` then the left child can be calculated as being in position `2 * i + 1` and the right child is at position `2 * i + 2`. The value at index `i` (the parent) is greater than the values in its two child nodes. This defines what is known as a **max binary heap.** If the parent is smaller than the two children nodes, this is known as a **min binary heap.**
    [Check out this link to learn more about HeapSort](https://www.geeksforgeeks.org/heap-sort/)

```java
QUICKSORT(A,p,r)
if p < r
	q = PARTITION(A,p,r)
	QUICKSORT(A,p,q - 1)
	QUICKSORT(A,q + 1,r)
where the initial call is QUICKSORT(A, 1, n). n is length of array

PARTITION(A,p,r)
	x = A[r]
	i = p - 1
	for j = p to r - 1
		if A[j] <= x
			i += 1
			exchange A[i] with A[j]
	exchange A[i + 1] with A[r]
	return i + 1
```

```
HEAPSORT
1. Build a max heap from the input data.

2. After building, the largest item is stored at the root of the heap (at index 0). Replace
   it with the last item of the heap (at index n-1, where n is length of array).
   
3. Reduce the size of the heap by 1.

4. Repeat steps 1-3 while the size of the heap is greater than 1.

5. Celebrate! Your array is sorted!
```

