package test;

// implementacja qucksort
interface quickcompare {
	int compare (int a, int b);

	; //non-public, qc moved out of class,
}
class QuickSort implements quickcompare{ //non-public, added implements

	public int[] quicksort ( int[] array, int start, int end) { //zmiana na return, zwraca dodatkowo zmieniana tablice
		//renamed to quicksort, qc removed from param, first param from Object[] changed to int[]
		if (start < end){
			int temp; //changed to int
			int pivot, low, high;
			
			pivot = start;
			low = start + 1;
			high = end;
			while (true){
				while ((high>= low) && (compare(array[low], array[pivot]) <= 0)){//changed imple from qc.compare to compare()
					++low;
				}
				while ((high >= low) && (compare(array[high], array[pivot]) >0)){//
					--high;//no semicolon
				}
				if (low < high){
					temp = array[low];
					array[low] = array[high];
					array[high] = temp;
				} else {
					break;
				}	
			}
			temp = array[pivot];
			array[pivot] = array[high];
			array[high] = temp;
			
//			quicksort(array, start, high); rekurencja niepotrzebna
//			quicksort(array,high+1, end);
		}
		return array;
	}


	@Override
	public int compare(int a, int b) { //added method instructions
		if (a > b){
			return 1;
		}else{
			return 0;
		}
	}
}