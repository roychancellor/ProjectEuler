using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler66_DiophantineEquation
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

        private static int ComputeK(int b, int c, int maxK)
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

        public static RootFraction GetPeriod(int N)
        {
            int maxK;
            RootFraction rf = new RootFraction(N) { Coefficients = new List<int>(), };
            rf.A = (int)Math.Sqrt(rf.N);
            rf.B = rf.N - rf.A * rf.A;
            rf.C = rf.A;
            maxK = rf.A;
            rf.K = ComputeK(rf.B, rf.C, maxK);
            var toAdd = (rf.C + rf.K) / rf.B;
            rf.Coefficients.Add(toAdd);
            if (toAdd == 2 * rf.A)
            {
                return rf;
            }

            RootFraction starter = new RootFraction
            {
                A = rf.A,
                B = rf.B,
                C = rf.C,
                K = rf.K
            };

            bool foundPeriod = false;
            int iterations = 0;
            while (!foundPeriod && iterations < 1000)
            {
                rf.C = rf.K;
                rf.B = (rf.N - rf.C * rf.C) / rf.B;
                rf.K = ComputeK(rf.B, rf.C, maxK);
                rf.Coefficients.Add((rf.C + rf.K) / rf.B);
                foundPeriod = ReturnedToStarter(starter, rf);

                if (foundPeriod)
                {
                    return rf;
                }

                iterations++;
            }
            return null;
        }

        private static bool ReturnedToStarter(RootFraction starter, RootFraction toTest)
        {
            var lastIndex = toTest.Coefficients.Count - 1;
            return toTest.Coefficients[lastIndex] == 2 * starter.A;
        }
    }
}
