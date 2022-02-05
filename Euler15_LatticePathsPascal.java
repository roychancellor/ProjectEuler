/*
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the
 * right and down, there are exactly 6 routes to the bottom right corner.
How many such routes are there through a 20×20 grid?

Turns out the number of paths is the sum of the diagonal elements of Pascal's triangle
starting with row n-2 and working down and to the right.
For example, for a 2x2:  1 + 2 + 3 = 6; 3x3:  1 + 3 + 6 + 10 = 20, and so on.
In general:  paths = 2*(1 + sum of n-1 diagonal elements starting from row n)
which gives paths = 2*[1+P(n,2)+P(n+1,3)+P(n+2,4)+...+P(2n-2,n)]
 */

import java.util.ArrayList;
public class Euler15_LatticePathsPascal
{
    public static void main(String args[]) {
        int numRows=20;
        long sumPaths=1;  //always is 1 + ...
        long [][] coeffs = new long[2*numRows-1][2*numRows];  //array for holding Pascal coeffs
        coeffs = pascalsTriangle(numRows);  //load the Pascal's triangle coefficients
        int r=numRows, c=2;
        while(r<=2*numRows-2) {
            sumPaths += coeffs[r][c-1];
            r++;
            c++;
        }
        System.out.print("\n" + numRows + "x" + numRows + " grid paths = " + (2*sumPaths));
    }
    
    public static long[][] pascalsTriangle(int n) {
        int numRows=2*n-1;
        long [][] coeffs = new long[numRows][numRows+1];  //array for holding Pascal coeffs

        //compute the Pascal's triangle coefficients up to row n (Pascal's begins with row 0)
        coeffs[0][0] = 1;
        coeffs[1][0] = 1;
        coeffs[1][1] = 1;
        for(int r=2; r<numRows; r++) {
            coeffs[r][0] = 1;  //put a 1 in the first column always
            for(int c=1; c<r; c++)  //don't really need all the columns...could reduce
                coeffs[r][c] = coeffs[r-1][c-1] + coeffs[r-1][c];
            coeffs[r][r] = 1;  //put a 1 in the last column always
        }
        return coeffs;
    }
}
