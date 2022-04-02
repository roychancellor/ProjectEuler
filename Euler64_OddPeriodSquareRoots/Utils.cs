using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler64_OddPeriodSquareRoots
{
    public class Utils
    {
        private static HashSet<int> _perfectSquares = new HashSet<int>();

        public static void MakePerfectSquares(int quantityToMake)
        {
            for (int i = 1; i <= quantityToMake; i++)
            {
                _perfectSquares.Add(i * i);
            }
        }
        
        public static bool IsPerfectSquare(int N)
        {
            return _perfectSquares.Contains(N);
        }

        public static bool ReturnedToStarter(RootFraction starter, RootFraction toTest)
        {
            var lastIndex = toTest.Coefficients.Count - 1;
            return toTest.Coefficients[lastIndex] == 2 * starter.A;
            //return starter.B == toTest.B && starter.C == toTest.C && starter.K == toTest.K;
        }

        // Recursive method to return gcd of a and b
        public static int GCD(int a, int b)
        {
            if (a == 0)
            {
                return b;
            }
            return GCD(b % a, a);
        }

        // method to return LCM of two numbers
        public static int LCM(int a, int b)
        {
            return (a / GCD(a, b)) * b;
        }
    }
}
