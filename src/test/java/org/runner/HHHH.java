package org.runner;

import java.util.Arrays;

public class HHHH {

    public static void main(String[] args) {
        // First array with 3 elements
        int[] array1 = {10, 20, 30};

        // Second array with 5 elements (initialized with 0s)
        int[] array2 = new int[5];

        // Copy elements of array1 into array2
        System.arraycopy(array1, 0, array2, 0, array1.length);

        // Print the second array after copying
        System.out.println("Array2 after copying: " + Arrays.toString(array2));
    }
}
