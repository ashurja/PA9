/**
 * Sorting.java contains Insert and Merge sorting algorithms that 
 * sort an input array in the ascending order. 
 *
 * The insert class conatins only one method that repeatedly takes 
 * the next element and inserts it in the correct ordered position. 
 *
 * Merge Sort contains two classes, Mergesort and Merge that split the 
 * array until only one element is in the subarray and merges them together 
 * in the correct order and Merge is the helper method that merges them. 
 * 
 * SOURCES[Zybooks for InsertionSort and Class slides for MergeSort]
 *
 * Name: Jamshed Ashurov
 * PID: A15475198
 * Email: jashurov@ucsd.edu
 * Login: cs12sp20aqk
 */

import java.util.Arrays; 

/**
 * The Sorting class creates Insert and Merge functions using 
 * helper methods and the ideas we learned in CSE12 class and 
 * ZyBooks. 
 */
public class Sorting <E extends Comparable<E>>  {
    
    private static final int GENERAL_RATIO_TWO = 2; //the general merge divider
    private static final int RATIO_FOUR = 4; //the ratio for this mergeSort  

    /**
     * InsertionSort algorithm that partitions the array into 
     * the sorted and unsorted parts. 
     * Repeatedly takes an element and puts in the correct spot in the 
     * sorted part. 
     * Throws an exception for invalid input. 
     *
     * @param array - the given array to be sorted
     * @return null
     */
    public void insertionSort(E[] array) {
        if (array == null) {
            throw new NullPointerException(); 
        }
        else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    throw new NullPointerException();
                }
            }
        }
        //The code above checks if the array or an element in array is null
        for (int i = 0; i < array.length; i++) {
            int j = i; // the first iteration prints the unsorted array 
            while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {
                //Swaps the unordered elements into the correct order
                E temp = array[j]; 
                array[j] = array[j - 1]; 
                array[j - 1] = temp; 
                j--; 
            }
            System.out.println(Arrays.toString(array)); 
        }
    }
    /**
     * MergeSort method that divides the input into 1:3 ratio
     * if the array's length is larger or equal to 4, and half if less. 
     * Uses the "merge" method to merge back the elements in the right order.
     * Throws an exception fro invalid input. 
     *
     * @param array - the input array
     * @return null;  
     */
    public void mergeSort(E[] array) {
        if (array == null) { 
            throw new NullPointerException(); 
        }
        else {
           for (int i = 0; i < array.length; i++) {
               if (array[i] == null) {
                   throw new NullPointerException(); 
               }
           }
        }
        //The code above check if the array or an element in array is null
        int len = array.length; //length of array
        int ratio = 0; //the ratio or mid of the array
        E[] left_arr = null; 
        E[] right_arr = null; 
        if (len <= 1) {
            return; //No need to be sorted or merged if only 1 or 0 length
        }
        else if (len < RATIO_FOUR) {
            ratio = len / GENERAL_RATIO_TWO; //sets the partition into two halves
            left_arr = Arrays.copyOfRange(array, 0, ratio);   
            right_arr = Arrays.copyOfRange(array, ratio, len); 
        }
        else {
            ratio = len / RATIO_FOUR; //sets the partition 1:3 ratio
            left_arr = Arrays.copyOfRange(array, 0, ratio);
            right_arr = Arrays.copyOfRange(array, ratio, len);
        }
        
        mergeSort(left_arr); 
        mergeSort(right_arr); 
        merge(array, left_arr, right_arr, left_arr.length, right_arr.length);
        System.out.println(Arrays.toString(array));         
    }

    /**
     * The helper method that merges the left and the array into an ordered 
     * array.
     *
     * @param array - the merged ordered array
     * @param leftArray - left ordered array
     * @param rightArray - right ordered array
     * @param left - length of the left array
     * @param right - length of the right array
     * @return null
     */
    public void merge(E[] array, E[] leftArray, E[] rightArray, 
                        int left, int right) {
        int l_point = 0; //pointer of the left array
        int r_point = 0; //pointer of the right array
        int main_point = 0; //pointer of the main array

        while (l_point < left && r_point < right) {
            //The if section below is called if right element > left element
            if (leftArray[l_point].compareTo(rightArray[r_point]) < 0) {
                array[main_point] = leftArray[l_point]; 
                l_point++; 
            }
            //The else section below is called if left element > right elem
            else {
                array[main_point] = rightArray[r_point]; 
                r_point++; 
            }
            main_point++; //Main pointer increments unconditionally 
        }
        //The code below is used when the right pointer = right array length
        while (l_point < left) {
            array[main_point] = leftArray[l_point]; 
            l_point++; 
            main_point++; 
        }
        //The code below is used when the left pointer = left array length
        while (r_point < right) {
            array[main_point] = rightArray[r_point]; 
            r_point++; 
            main_point++; 
        }
    }
}
