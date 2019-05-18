/* Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order.
 * Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
 * So, COLIN would obtain a score of 938 × 53 = 49714.
 * What is the total of all the name scores in the file?
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
public class Names {
	//letVal will contain a lookup for characters and their values for creating a name score
	HashMap<Character, Integer> letToVal = new HashMap<Character, Integer>();
	ArrayList<String> names = new ArrayList<String>();  //names from the provided file stored here
	String nameCSV="";
	
	//constructor creates a character-to-value map, though could just convert characters a-z to values 1-26 using a simple linear transform (A=65 and Z=90)
	Names() {
		for(int i=(int)'A'; i<=(int)'Z'; i++)  //'A' is 65 and 'Z' is 90 in ASCII
			letToVal.put((char)i,i-((int)'A'-1));
		letToVal.put('\"',0);  //make a " equal to 0 so quotes do not impact the name score (otherwise need to remove the quotes)
	}
	
	//method that reads a file in CSV format with quotes around each string separated by commas AS A SINGLE STRING
	void readNamesCSV(String fileName) throws FileNotFoundException, IOException {
		//open a file reader
		try {
			//open the file and create a new buffered reader (or is it better to use a Scanner???)
			File namesFile = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(namesFile));
			//read names until null
			String nameStr;
			while((nameStr = br.readLine()) != null)  //names are in one continuous string in the given text file p022_names.txt
				if(nameStr != null) nameCSV = nameStr;
			//close the file scanner
			br.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Something bad happened with your file....");
		}
	}
	
	//parse out the names from a single string formatted as "name1","name2","name3",...
	//method will leave the enclosing quotations (i.e. parses at the commas only)
	void getNamesFromCSV(String nameStr) {
		//parse out the name string at the commas (which leaves the names quoted)
		try (Scanner rowScanner = new Scanner(nameStr)) {
	        rowScanner.useDelimiter(","); //("\",\"");
	        //rowScanner.useDelimiter("\"([^\"]*)\"");
	        while (rowScanner.hasNext()) {
	            names.add(rowScanner.next());
	        }
			rowScanner.close();
	    }
	}
	
	//method to create the product of a name score and position of name in sorted list
	long sumScores() {
		long sum=0;
		for(int i=0; i<names.size(); i++)
			sum += nameScore(names.get(i)) * (i+1);  //sums the products of the name scores and positions in the sorted list
		return sum;
	}
	
	//method to create a name score from a single name (name score = SUM of numeric values of each letter in the name)
	//the name can be any case, but each character will be converted to upper case first before computing the score
	long nameScore(String name) {
		long letterSum=0;
		//iterate through the string character-by-character (using charAt) and lookup its numeric value using a hash table created in the constructor
		for(int i=0; i<name.length(); i++)
			letterSum += letToVal.get(Character.toUpperCase(name.charAt(i)));
		return letterSum;
	}
	
	//method to print the sum of name scores
	void printSum(long sumOfScores) {
		System.out.println("The sum of the name scores is " + sumOfScores);
	}
	
	void printNames() {
		for(int i=0; i<names.size(); i++)
		System.out.println(names.get(i));
	}
}
