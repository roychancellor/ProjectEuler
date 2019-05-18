import java.io.FileNotFoundException;
import java.io.IOException;

public class NamesDemo {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Names test = new Names();
		test.readNamesCSV("/users/roy/desktop/p022_names.txt");
		test.getNamesFromCSV(test.nameCSV);
		QuickSort.qSort(test.names);
		test.printSum(test.sumScores());
	}
}
