public class SortFuncs {
	/*
	 * Sorts the contents of the list in ascending order
	 */
	public static void sort() {
		for(int i=1, j; i<size(); i++){
			String temp = getData(i);
			for(j=i; (j>0)&&((temp.compareTo(getData(j-1)))<0); j--){
				String tmp = getData(j-1);
				set(tmp,j);
			}
			set(temp,j);
		}
	}
}
