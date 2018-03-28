public class SortFuncs {
	/*
	 * This is a modified implementation of insertion sort enabled
	 * to sort chars instead of integers
	 * @param in array to be sorted
	 */
	public static void charSort(char[] in) {
		for(int i = 1,j; i<in.length; i++) {
			char temp = in[i];
			for(j = i; (j>0)&&(temp<in[j-1]); j--)
				in[j] = in[j-1];
			in[j] = temp;
		}
	}
	/*
	 * This is an adapted verison of the insertionsort from the lectures
	 * to be able to sort through String objects
	 * @param in object that contains the String to be sorted
	 */
	public static void insertionSort(CustomList in) {
		for(int i=1, j; i<in.size(); i++){
			String temp = in.getData(i);
			for(j=i; (j>0)&&((temp.compareTo(in.getData(j-1)))<0); j--){
				String tmp = in.getData(j-1);
				in.set(tmp,j);
			}
			in.set(temp,j);
		}
	}
	/*
	 * This is an adapted version of quicksort from the lectures
	 * to be able to sort through String objects
	 * @param lo first element
	 * @param hi last element
	 * @param in array to be sorted
	 */
	public static void quickSort(int lo, int hi, CustomList[] in) {
		int first = lo, last = hi;
		CustomList temp;
		// set a pivot element
		String pivot = in[(lo+hi)/2].getHead().data;
		// divide arrays
		while(first<=last){
			// identify a number greater than pivot value from left subarray
			while((in[first].getHead().data).compareTo(pivot) < 0){
				first++;
			}
			// identify a number less than pivot value from right subarray
			while((in[last].getHead().data).compareTo(pivot) > 0){
				last--;
			}
			// swap
			if(first<=last){
				temp = in[first];
				in[first] = in[last];
				in[last] = temp;
				first++;
				last--;
			}
		}
		// recursive method call
		if(lo < last)
			quickSort(lo, last, in);
		if(first < hi)
			quickSort(first, hi, in);
	}
}
