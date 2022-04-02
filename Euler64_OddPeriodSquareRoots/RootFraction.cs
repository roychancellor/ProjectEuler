using System.Collections.Generic;

namespace Euler64_OddPeriodSquareRoots
{
    public class RootFraction
    {
        public int A { get; set; }
        public int B { get; set; }
        public int C { get; set; }
        public int K { get; set; }
        public int N { get; set; }
        public List<int> Coefficients { get; set; }

        public RootFraction() { }

        public RootFraction(int n)
        {
            N = n;
        }

        public static int ComputeK(int b, int c, int maxK)
        {
            if (b == 1)
            {
                // This will be the end of the search
                return c;
            }
            // find the largest value <= maxK such that (c + k) is divisible by b
            int k = 0;
            for (int i = 1; i <= maxK; i++)
            {
                if ((c + i) % b == 0)
                {
                    k = i;
                }
            }
            return k;
        }
    }
}
