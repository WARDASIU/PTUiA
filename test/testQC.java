package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class testQC {
    QuickSort quickSort = new QuickSort();
    quickcompare qc = new quickcompare() {
        @Override
        public int compare(int a, int b) {
            if (a > b){
                return 1;
            }else{
                return 0;
            }
        }
    };

    @Test
    public void checkQuickSort() {
        int[] exp = {1,2,3,4,5};
        int[] arr = {5,1,4,2,3};

        Assertions.assertEquals(exp,quickSort.quicksort(arr,0,1));
    }
    @Test
    public void checkQuickCompare() {
        int a = 5;
        int b = 6;
        int exp = 0;
        Assertions.assertEquals(exp,qc.compare(a,b));
    }
}
