
/**
 By starting at the top of the triangle below and moving to adjacent numbers on the row below,
 the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom of the triangle below:
75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 */
import java.util.ArrayList;
import java.io.*;
import java.lang.Character;
import java.util.Scanner;
public class Euler18_MaximumPathSumOne
{
    public static void main(String args[]) throws Exception {
        //store the triangle in a 2x2 array
        ArrayList<ArrayList<Integer>> triangle = readTriangle("/users/roy/desktop/triangle.txt");
        System.out.println("The maximum path sum is " + maxPath(triangle));
    }
    
    public static ArrayList<ArrayList<Integer>> readTriangle(String filePath) throws Exception {
        //read the numbers into an array list of strings
        ArrayList<String> numStr = new ArrayList<String>();
        File numFile = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(numFile));
        String filestr;
        while((filestr = br.readLine()) != null)
            numStr.add(filestr);
        br.close();
        
        //convert the integers embedded in the string to integer stored in a 2D array list 
        int rows = numStr.size();
        ArrayList<ArrayList<Integer>> numbers = new ArrayList<>(rows); //rows array list
        for(int i=0; i<rows; i++) {
            numbers.add(new ArrayList());  //each element of numbers is an array list
            //get each integer from each row of numStr
            Scanner nums = new Scanner(numStr.get(i));
            while(nums.hasNextInt())
                numbers.get(i).add(nums.nextInt());
            nums.close();
        }
        
        return numbers;
    }
    
    public static long maxPath(ArrayList<ArrayList<Integer>> triangle) {
        //For the first solution, use brute force to go through all 2^14 paths to find the largest sum
        //Store the column positions for each row in an array and systematically update the column 
        //positions for each path, then sum the values at the (row, col) pairs in the triangle

        long maxSum=0;  //initialize the maxSum to the top of the triangle
        long sumPath=0;
        int numRows=triangle.size();  //i.e., the number of rows
        int[] colIdx = new int[numRows];  //an array of column positions for the paths
        for(int i=0; i<numRows; i++)  //initialize the column position array
            colIdx[i] = 1;
        
        //GENERAL ALGORITHM:
        //set each path by adjusting the column values (much like bits in a numPaths-bits number), then find the sum of the path and compare it
        //to the current maximum sum value, then adjust maxSum if it is exceeded
        
        //To generate all possible paths through the triangle, create an array list that holds values that will specify the column index for each row of the triangle to sum
        //Each path is the result of incrementing the column index for each row to create a new path
        //Store values in an array that will determine which column to increment for each row, much like setting bits to turn columns on and off
        //The values are actually numbers to be subtracted from numRows to specify which column position gets incremented to create each new path
        //i.e. column to increment = numRows - nSubtracts[index]
        ArrayList<Integer> nSubtracts = new ArrayList<Integer>(512);  //each next column corresponding to the next row in the triangle doubles the number of paths up to 2^9
        //initialize the column subtraction values to 1-2-1, i.e. numRows-1, numRows-2, numRows-1 corresponding to the "ones"-"tens"-"ones" places in the list of columns,
        //starting with the second-to-the-last column because the last column is ALWAYS the same as the one to its left
        nSubtracts.add(1);  //index 0
        nSubtracts.add(2);  //index 1
        nSubtracts.add(1);  //index 2
        int leadColIdx=3;  //initialize the leading column index to the "n-3" position, the first significant column increment beyond the 1-2-1 pattern
        while(leadColIdx <= numRows-1) {  //keep looping until leadColIdx is less than or equal numRows-1, which corresponds to the second row in the triangle
            for(int c=0; c<nSubtracts.size(); c++) {  //loop through each of the paths defined by the elements of the nSubtracts array
                //compute the sum of the values in current path (begins initialized to all column ones)
                colIdx[numRows-1]=colIdx[numRows-2];  //the rightmost column index is always equal the one to its left
                for(int r=0; r<numRows-1; r++)  //sum through the second to last row
                    sumPath += triangle.get(r).get(colIdx[r]-1);  //the -1 makes a column position an array index
                //don't need to do a separate sum for the case where the column in the last row is swapped;
                //just check which value is larger and add that one
                if(triangle.get(numRows-1).get(colIdx[numRows-1]-1) > triangle.get(numRows-1).get(colIdx[numRows-1]))
                    sumPath += triangle.get(numRows-1).get(colIdx[numRows-1]-1);
                else
                    sumPath += triangle.get(numRows-1).get(colIdx[numRows-1]);
                if(sumPath > maxSum)
                    maxSum = sumPath;
                sumPath = 0;  //reset the path sum variable each time
                //increment the column index "numRows - nSubtracts[c]"
                colIdx[numRows-nSubtracts.get(c)-1]++;  //the "-1" makes a column position into an array index
                if(nSubtracts.get(c)>2)  //then reset the column index values to the right of the one that was just incremented
                    for(int k=nSubtracts.get(c)-1; k>=0; k--)
                        colIdx[numRows-k-1]=colIdx[numRows-nSubtracts.get(c)-1];
            }
            //adjust the column values to the next path according to the established pattern
            //increment the column reference index for the highest order position to the left; e.g. 1 --> 2
            colIdx[numRows-leadColIdx-1]++;  //the "-1" makes a column reference into an array index
            //RESET the column values to the right of the lead column to be equal the lead column
            for(int c=leadColIdx-1; c>=0; c--)
                colIdx[numRows-c-1]=colIdx[numRows-leadColIdx-1];
            leadColIdx++;  //increment the lead column index; e.g. 3 --> 4

            if(leadColIdx >= 5 && leadColIdx <=numRows-1) {  //then use the pattern "prev sequence-prev ColIdx-prev sequence" for the column subtractor indexes
                nSubtracts.add(leadColIdx-2);  //add the previous value of the leading column subtractor; e.g. 1-2-1 --> 1-2-1-3
                int arrLen=nSubtracts.size();
                for(int j=arrLen; j<2*arrLen-1; j++)
                    nSubtracts.add(nSubtracts.get(j-arrLen));  //copy the existing sequence of column subtractors to the end; e.g. 1-2-1-3 --> 1-2-1-3-1-2-1
            }
        }  //end while
        
        return maxSum;
        
        /* This is the greedy algorithm that doesn't work for this problem
        int numRows=triangle.size(), colMax=0, col2=0;
        System.out.println("row " + (0+1) + ": " + triangle.get(0).get(colMax) + ", " + maxSum);
        for(int r=1; r<numRows; r++) {
            //find the maximum of the numbers in the current row that are adjacent to the
            //maximum value in the previous row
            //always test the colMax index vs colMax+1 (unles colMax at end of prev row, then colMax-1)
            col2 = colMax + 1;
            if(r>1 && (colMax == triangle.get(r-1).size() - 1))  //colMax is at the end of previous row
                col2 = colMax - 1;
            if(triangle.get(r).get(col2) > triangle.get(r).get(colMax)) {
                colMax = col2;
            }
            maxSum += triangle.get(r).get(colMax);
            System.out.println("row " + (r+1) + ": " + triangle.get(r).get(colMax) + ", " + maxSum);
        }*/
    }
}
