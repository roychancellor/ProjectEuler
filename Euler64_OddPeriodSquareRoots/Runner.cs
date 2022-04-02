using System;
using System.Collections.Generic;

namespace Euler64_OddPeriodSquareRoots
{
    public class Runner
    {
        public static void Run(string[] args)
        {
            int countOfOddPeriods = 0;

            Utils.MakePerfectSquares(101);

            for (int N = 2; N <= 10000; N++)
            {
                if (Utils.IsPerfectSquare(N))
                {
                    continue;
                }

                int period = GetPeriod(N);

                if (period < 0)
                {
                    throw new Exception($"After 1000 iterations, {N} is not periodic.");
                }

                if (period % 2 == 1)
                {
                    countOfOddPeriods++;
                } 
            }

            Console.WriteLine($"FINISHED! The number of odd period square roots for N <= 10000 is {countOfOddPeriods}.");
        }

        public static int GetPeriod(int N)
        {
            int maxK;
            RootFraction rf = new RootFraction(N)
            {
                Coefficients = new List<int>(),
            };
            rf.A = (int)Math.Sqrt(rf.N);
            rf.B = rf.N - rf.A * rf.A;
            rf.C = rf.A;
            maxK = rf.A;
            rf.K = RootFraction.ComputeK(rf.B, rf.C, maxK);
            var toAdd = (rf.C + rf.K) / rf.B;
            rf.Coefficients.Add(toAdd);
            if (toAdd == 2 * rf.A)
            {
                //PrintResult(rf, 1);
                return 1;
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
                rf.K = RootFraction.ComputeK(rf.B, rf.C, maxK);
                rf.Coefficients.Add((rf.C + rf.K) / rf.B);
                foundPeriod = Utils.ReturnedToStarter(starter, rf);

                if (foundPeriod)
                {
                    var period = rf.Coefficients.Count;
                    if (period % 2 == 1)
                    { 
                        //PrintResult(rf, period); 
                    }
                    return period;
                }

                iterations++;
            }
            return -1;
        }

        private static void PrintResult(RootFraction rf, int period)
        {
            if (period % 2 == 0)
            {
                return;
            }
            Console.Write($"N: {rf.N}, period = {period}, coeffs: ");
            foreach (var coeff in rf.Coefficients)
            {
                Console.Write($"{coeff} ");
            }
            Console.WriteLine();
        }
    }
}
