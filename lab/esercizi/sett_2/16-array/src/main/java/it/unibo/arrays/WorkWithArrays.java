package it.unibo.arrays;

import java.util.Arrays;

class WorkWithArrays {

    static int countOccurrencies(final int[] array, final int element) {
        int occurrencies = 0;
        for (final int currentElement : array) {
            if (currentElement == element) {
                occurrencies++;
            }
        }
        return occurrencies;
    }

    static int[] evenElements(final int[] array) {
        final int sizeOfArray = array.length;
        int size = (int)sizeOfArray / 2;
        if(sizeOfArray % 2 == 1){
            size++;
        }
        int[] even = new int[size];
        for(int i = 0; i < sizeOfArray; i+=2) {
            even[i/2] = array[i];
        }
        return even;
    }

    static int[] oddElements(final int[] array) {
        final int sizeOfArray = array.length;
        int size = (int)sizeOfArray / 2;
        int[] odd = new int[size];
        for(int i = 1; i < sizeOfArray; i+=2) {
            odd[(i-1)/2] = array[i];
        }
        return odd;
    }

    static int mostRecurringElement(final int[] array) {
        int maxOcc = 0;
        int currOcc = 0;
        int recElem = array[0];
        for (int element : array){
            currOcc = countOccurrencies(array, element);
            if(currOcc > maxOcc) {
                maxOcc = currOcc;
                recElem = element;
            }
        }
        return recElem;
    }

    static int[] sortArray(final int[] array, final boolean isDescending) {
        final int[] sortedArray = selectionSort(array);
        if(isDescending) {
            reverse(sortedArray);
        }
        return sortedArray;
    }

    static double computeVariance(final int[] array) {
        int size = array.length;
        double variance = 0;
        double avg = calcAverage(array);
        for(int i = 0; i < size; i++) {
            variance += Math.pow((array[i]-avg), 2);
        }
        variance = variance/size;
        return variance;
    }

    static int[] revertUpTo(final int[] array, final int element) {
        final int SIZE = array.length;
        int EL_POS = findElementPosition(array, element);
        int [] reverted = new int[SIZE];
        int j = EL_POS;
        for(; j >= 0; j--) {
            reverted[EL_POS-j] = array[j];
        }
        
        for(j = EL_POS+1; j < SIZE; j++) {
            reverted[j] = array[j];
        }
        return reverted;
    }

    static int[] duplicateElements(final int[] array, final int times) {
        final int[] returnValue = new int[array.length * times];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < times; j++) {
                returnValue[i * times + j] = array[i];
            }
        }
        return returnValue;
    }

    /** Testing methods **/

    /* Utility method for testing countOccurr method */
    static boolean testCountOccurrencies() {
        return countOccurrencies(new int[] { 1, 2, 3, 4 }, 1) == 1
            && countOccurrencies(new int[] { 0, 2, 3, 4 }, 1) == 0
            && countOccurrencies(new int[] { 7, 4, 1, 9, 3, 1, 5 }, 2) == 0
            && countOccurrencies(new int[] { 1, 2, 1, 3, 4, 1 }, 1) == 3
            && countOccurrencies(new int[] { 2, 3, 5, 5, 6, 7, 5}, 5) == 3;
    }

    /* Utility method for testing evenElems method */
    static boolean testEvenElements() {
        return Arrays.equals(evenElements(new int[] { 1, 2, 3, 4 }), new int[] { 1, 3 })
            && Arrays.equals(evenElements(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }), new int[] { 1, 3, 5, 7, 9 })
            && Arrays.equals(evenElements(new int[] { 4, 6, 7, 9, 1, 5, 23, 11, 73 }), new int[] { 4, 7, 1, 23, 73 })
            && Arrays.equals(
                evenElements(new int[] { 7, 5, 1, 24, 12, 46, 23, 11, 54, 81 }),
                new int[] { 7, 1, 12, 23, 54 }
        );
    }

    /* Utility method for testing oddElems method */
    static boolean testOddElements() {
        return Arrays.equals(oddElements(new int[] { 1, 2, 3, 4 }), new int[] { 2, 4 })
            && Arrays.equals(oddElements(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }), new int[] { 2, 4, 6, 8 })
            && Arrays.equals(oddElements(new int[] { 4, 6, 7, 9, 1, 5, 23, 11, 73 }), new int[] { 6, 9, 5, 11 })
            && Arrays.equals(
                oddElements(new int[] { 7, 5, 1, 24, 12, 46, 23, 11, 54, 81 }),
                new int[] { 5, 24, 46, 11, 81 }
            );
    }

    /* Utility method for testing getMostRecurringElem method */
    static boolean testMostRecurringElement() {
        return mostRecurringElement(new int[] { 1, 2, 1, 3, 4 }) == 1
            && mostRecurringElement(new int[] { 7, 1, 5, 7, 7, 9 }) == 7
            && mostRecurringElement(new int[] { 1, 2, 3, 1, 2, 3, 3 }) == 3
            && mostRecurringElement(new int[] { 5, 11, 2, 11, 7, 11 }) == 11;
    }

    /* Utility method for testing sortArray method */
    static boolean testSortArray() {
        return Arrays.equals(sortArray(new int[] { 3, 2, 1 }, false), new int[] { 1, 2, 3 })
            && Arrays.equals(sortArray(new int[] { 1, 2, 3 }, true), new int[] { 3, 2, 1 })
            && Arrays.equals(
                sortArray(new int[] { 7, 4, 1, 5, 9, 3, 5, 6 }, false),
                new int[] { 1, 3, 4, 5, 5, 6, 7, 9 }
            );
    }

    /* Utility method for testing computeVariance method */
    static boolean testComputeVariance() {
        return computeVariance(new int[] { 1, 2, 3, 4 }) == 1.25
            && computeVariance(new int[] { 1, 1, 1, 1 }) == 0
            && computeVariance(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }) == 8.25;
    }

    /* Utility method for testing revertUpTo method */
    static boolean testRevertUpTo() {
        return
            Arrays.equals(
                revertUpTo(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 5),
                new int[] { 5, 4, 3, 2, 1, 6, 7, 8, 9, 10 }
            )
            && Arrays.equals(
                revertUpTo(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 3),
                new int[] { 3, 2, 1, 4, 5, 6, 7, 8, 9, 10 }
            )
            && Arrays.equals(
                revertUpTo(new int[] { 1, 2, 3 }, 3),
                new int[] { 3, 2, 1 }
            );
    }

    /* Utility method for testing dupElems method */
    static boolean testDuplicateElements() {
        return Arrays.equals(duplicateElements(new int[] { 1, 2, 3 }, 2), new int[] { 1, 1, 2, 2, 3, 3 })
            && Arrays.equals(duplicateElements(new int[] { 1, 2 }, 5), new int[] { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2 });
    }

    static double calcAverage(int [] array){
        double avg = 0.0;
        for (int i = 0; i < array.length; i++) {
            avg += (double)array[i];
        }
        avg = (double)(avg/array.length);
        return avg;
    }

    private static void reverse(final int[] array){
        int size = array.length;
        int temp;
        for (int i = 0; i < size / 2; i++) { 
            temp = array[i]; 
            array[i] = array[size - i - 1]; 
            array[size - i - 1] = temp; 
        } 
    }

    static int findElementPosition(final int[] array, final int element){
        int pos = -1;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == element) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private static int[] selectionSort(final int[] array){
        final int[] sortedArray = array.clone();
        for(int i = 0; i < sortedArray.length - 1; i++) {
            int min = i;
            for(int j = i + 1; j < sortedArray.length; j++) {
                if(sortedArray[j] < sortedArray[min]) {
                    min = j;
                }
            }
            int temp = sortedArray[i];
            sortedArray[i] = sortedArray[min];
            sortedArray[min] = temp;
        }
        return sortedArray;
    }
    public static void main(final String[] args) {
        System.out.println("testCountOccurr: " + testCountOccurrencies());
        System.out.println("testEvenElems: " + testEvenElements());
        System.out.println("testOddElems: " + testOddElements());
        System.out.println("testGetMostRecurringElem: " + testMostRecurringElement());
        System.out.println("testSortArray: " + testSortArray());
        System.out.println("testComputeVariance: " + testComputeVariance());
        System.out.println("testRevertUpTo: " + testRevertUpTo());
        System.out.println("testDupElems: " + testDuplicateElements());
    }
}
