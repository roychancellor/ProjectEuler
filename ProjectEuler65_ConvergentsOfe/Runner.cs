using com.royware.RoyUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace ProjectEuler65_ConvergentsOfe
{
    public class Runner
    {
        public static Convergent CONVERGENT_e = new Convergent
        {
            LeadingCoeff = 2,
            PeriodicCoeffs = new List<int> { 1, 0, 1 },
            CoefficientGenerators = new Dictionary<int, Func<int, int>> { { 2, Logic.ComputeCoeff2k } },
        };

        public static void Run(string[] args)
        {
            var N = 100;
            BigInteger numer = Logic.GenerateNumeratorForConvergent(N, CONVERGENT_e);
            var sumOfNumeratorDigits = RoyUtils.SumOfDigitsIn(numer);
            Console.WriteLine($"For convergent {N} of e, the numerator is {numer} and the sum of its digits is {sumOfNumeratorDigits}");
        }
    }
}
