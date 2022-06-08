using com.royware.RoyUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace Euler66_DiophantineEquation
{
    public class Program
    {
        public static readonly int MAX_ITERATIONS = 1000000;
        public static readonly double EPSILON = 1.0E-9;
        public static readonly BigInteger MAX_Y = new BigInteger(1.0E10);
        public static readonly BigInteger MAX_Y_DIV_100 = MAX_Y / 100;
        public static Dictionary<int, RootFraction> COEFF_LOOKUP;

        public static void Main(string[] args)
        {
            int MAX_D = 1000;
            var DLargestX = FindD(MAX_D);

            Console.WriteLine($"The value of D for which the minimal solution has the largest x value is {DLargestX}");
        }

        public static int FindD(int MAX_D)
        {
            BigInteger xMax = -1;
            int DLargestX = -1;

            COEFF_LOOKUP = GeneratePeriodicCoefficients(MAX_D);

            for (int d = 2; d <= MAX_D; d++)
            {
                if (RoyUtils.IsPerfectSquare(d))
                {
                    continue;
                }

                var x_solution = GenerateMinimalSolution(d, out BigInteger ySol);
                if (x_solution <= 0)
                {
                    Console.WriteLine($"*** Did not find a solution for d = {d}.");
                    continue;
                }
                var isSolution = x_solution * x_solution - d * ySol * ySol == 1;
                Console.WriteLine($"d = {d}, minimal solution x = {x_solution}, y = {ySol}, check is a solution: {isSolution}");
                if (x_solution > xMax)
                {
                    xMax = x_solution;
                    DLargestX = d;
                    Console.WriteLine($"Updating x to {xMax} and dLargestX to {d}");
                }
            }
            return DLargestX;
        }

        // This relies on the algorithm developed in Euler 64 that found the periodic coefficients
        // of the continued fraction representation of a non-square number.
        public static Dictionary<int, RootFraction> GeneratePeriodicCoefficients(int N)
        {
            Dictionary<int, RootFraction> toReturn = new Dictionary<int, RootFraction>();
            for (int i = 2; i <= N; i++)
            {
                if (RoyUtils.IsPerfectSquare(i))
                {
                    continue;
                }
                var rf = RootFraction.GetPeriod(i);
                if (rf != null)
                {
                    toReturn.Add(i, rf);
                }
            }
            return toReturn;
        }

        // This doesn't work because anything involving double will lack the precision necessary
        // to determine if (x, y) is a solution.
        public static BigInteger GenerateMinimalSolution(int D, int maxIterations)
        {
            BigInteger y = 1;
            double yd = 1.0;
            for (int i = 0; i < maxIterations; i++)
            {
                double logToCheck = Math.Log10(D) + 2 * Math.Log10(yd);
                double powD = Math.Pow(10, logToCheck);
                bool isInteger = powD.ToString("F9").Contains("0000000");
                if (isInteger)
                {
                    return (BigInteger)Math.Sqrt(1.0 + powD);
                }
                //BigInteger toCheck = 1 + D * y * y;
                //if (_perfectSquares.Contains(toCheck))
                //{
                //    return (BigInteger)Math.Sqrt((double)toCheck);
                //}
                y++;
            }     
            return -1L;
        }

        // This is the method that solves the problem
        public static BigInteger GenerateMinimalSolution(int D, out BigInteger ySol)
        {
            // This methodology is based on https://ir.canterbury.ac.nz/bitstream/handle/10092/10158/unger_2009_report.pdf?sequence=1
            // Specifically, Theorem 2.4 on page 7, Theorem 4.2 pn page 18, and the example on page 20.
            // It also uses the methods developed in Project Euler #64 to determine the periodic coefficients of a non-square number
            // which the RootFraction class contains.

            // Get the periodic coefficients of the continued fraction expansion of SQRT(D)
            RootFraction rf;
            if (COEFF_LOOKUP.ContainsKey(D))
            {
                rf = COEFF_LOOKUP[D];
            }
            else
            {
                ySol = BigInteger.Zero;
                return BigInteger.Zero;
            }
            // Determine if the period is even or odd
            // Theorem 4.2 says the convergent number, N, that contains the solution is: If period is even: N = period - 1; if period is odd: N = 2 * period - 1
            var period = rf.Coefficients.Count;
            int N = period % 2 == 0 ? period - 1 : 2 * period - 1;
            
            // Find the numerator and denominator of the Nth convergent of the continued fraction representation of SQRT(D)
            // The numerator is the x solution and the denominator is the y solution of x^2 - Dy^2 = 1
            var xSol = ComputeConvergent(N, rf, out ySol);
            return xSol;
        }

        private static BigInteger ComputeConvergent(int N, RootFraction rf, out BigInteger ySol)
        {
            // Algorithm for finding the numerator and denominator of the Nth convergent of the continued fraction representation
            // of the SQRT(D) taken from https://ir.canterbury.ac.nz/bitstream/handle/10092/10158/unger_2009_report.pdf?sequence=1
            // in Theorem 2.4.

            var a0 = rf.A;
            var coeffs = rf.Coefficients;
            var period = rf.Coefficients.Count;
            BigInteger p_nm2 = BigInteger.Zero;
            BigInteger p_nm1 = BigInteger.One;
            BigInteger p_n = a0 * p_nm1 + p_nm2;
            BigInteger q_nm2 = BigInteger.One;
            BigInteger q_nm1 = BigInteger.Zero;
            BigInteger q_n = a0 * q_nm1 + q_nm2;

            if (N == 0)
            {
                ySol = q_n;
                return p_n;
            }

            var c = 0;
            for (int n = 1; n <= N; n++)
            {
                p_nm2 = p_nm1;
                p_nm1 = p_n;
                p_n = coeffs[c] * p_nm1 + p_nm2;
                q_nm2 = q_nm1;
                q_nm1 = q_n;
                q_n = coeffs[c] * q_nm1 + q_nm2;
                c++;
                if (c == period)
                {
                    // Reset to the start of the period
                    c = 0;
                }
            }

            ySol = q_n;
            return p_n;
        }

        // All of the below were related to vain attempts to solve the problem using double.
        // The double allows for large enough numbers, but lacks the precision necessary
        // to verify (x, y) is a solution.
        public static BigInteger Sqrt(BigInteger n)
        {
            if (n == 0) return 0;
            if (n > 0)
            {
                int bitLength = Convert.ToInt32(Math.Ceiling(BigInteger.Log(n, 2)));
                BigInteger root = BigInteger.One << (bitLength / 2);

                while (!IsSqrt(n, root))
                {
                    root += n / root;
                    root /= 2;
                }

                return root;
            }

            throw new ArithmeticException("NaN");
        }

        private static bool IsSqrt(BigInteger n, BigInteger root)
        {
            BigInteger lowerBound = root * root;
            BigInteger upperBound = lowerBound + root + root + 1;

            return n >= lowerBound && n < upperBound;
        }

        public static bool IsSolution(double x, double y, int D)
        {
            var xBI = new BigInteger(x);
            var yBI = new BigInteger(y);
            var toReturn = xBI * xBI - D * yBI * yBI;
            return toReturn == 1; /*x * x - D * y * y - 1 < EPSILON;*/
        }

        public static bool IsInteger(double x)
        {
            var toCheck = x.ToString("F12");
            bool toReturn = toCheck.Contains(".000000000");
            return toReturn; //x - (double)((BigInteger)x) > EPSILON;
        }

        public static double F(double xPlusk, double xMinusk)
        {
            return Math.Log(xPlusk) + Math.Log(xMinusk);
        }

        public static double FPrime(double x, double xPlusk, double xMinusk)
        {
            return 2.0 * x / (xPlusk * xMinusk);
        }

        public static double Newton(double x, double xPlusk, double xMinusk)
        {
            double h = F(xPlusk, xMinusk) / FPrime(x, xPlusk, xMinusk);
            int iter = 0;
            while (iter < MAX_ITERATIONS)
            {
                if (Math.Abs(h) < EPSILON)
                {
                    break;
                }
                h = F(xPlusk, xMinusk) / FPrime(x, xPlusk, xMinusk);

                // x(i+1) = x(i) - f(x) / f'(x)
                x -= h;

                iter++;
            }
            if (iter >= MAX_ITERATIONS)
            {
                return double.MinValue;
            }

            return x;
        }
    }
}
