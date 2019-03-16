/*
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
a^2 + b^2 = c^2.  For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
*/

public class Euler09_PythagoreanTriple
{
    public static void main(String args[]) {
        long a, b, c;
        boolean trip1000Found=false;
        
        //Since a<b<c by definition and c<a+b by the triangle inequality, start with the smallest
        //value of b (2), smallest value of a (1), and smallest value of c (1) and use nested
        //loops to compute all valid combinations of a, b, c while testing for a triple first, then
        //testing their sum until 1000 is achieved.  Once found, compute the product abc.
        
        b=2;  //the smallest possible integer for a triangle
        while(!trip1000Found) {  //'b' loop
            a=1;  //start at the smallest integer value of a for each value of b
            while(a<b && !trip1000Found) {  //'a' loop
                c=0;  //smallest integer value of c for each (a, b) pair
                while(c<(a+b) && !trip1000Found) {  //'c' loop (c must satisfy triangle inequality)
                    c++;
                    if((a*a+b*b)==(c*c)) { //true = triple found
                        System.out.println("(" + a + ", " + b + ", " + c + ") and a+b+c = " + (a + b + c));
                        if((a+b+c)==1000) {
                            trip1000Found = true;
                            System.out.println("\nThe triple has product " + (a*b*c));
                            break;  //probably not needed once a+b+c=1000 is found
                        }
                    }
                }
                a++;
            }
            b++;
        }
    }
}
