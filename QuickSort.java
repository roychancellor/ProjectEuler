//Implements quick sort on an array list of strings
import java.util.ArrayList;
public class QuickSort {
	static void qSort(ArrayList<String> names) {  //receives names as an object, so names will be modified back at the calling location
		//call the quick sort routine which will recursively call itself
		qs(names, 0, names.size()-1);
	}
	
	//implements quick sort on an array list of strings
	private static void qs(ArrayList<String> names, int left, int right) {
		int i, j;
		String x, y;
		
		i = left;
		j = right;
		x = names.get((left+right)/2);
		
		do {
			while((names.get(i).compareToIgnoreCase(x) < 0) && (i < right))
				i++;
			while((names.get(j).compareToIgnoreCase(x) > 0) && (j > left))
				j--;
			if(i <= j) {
				y = names.get(i);
				names.set(i, names.get(j));
				names.set(j, y);
				i++;
				j--;
			}
		} while(i <= j);
		if(left < j) qs(names, left, j);
		if(i < right) qs(names, i, right);
	}
}
