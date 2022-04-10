using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace ProjectEuler65_ConvergentsOfe
{
    public class Logic
    {
        public static BigInteger GenerateNumeratorForConvergent(int N, Convergent convergent)
        {
            List<int> coefficients = GenerateCoefficientsForConvergent(N, convergent);
            if (coefficients == null)
            {
                return -1;
            }

            int index = coefficients.Count - 1;
            BigInteger denom = coefficients[index];
            BigInteger numer = 1;
            BigInteger numerNew = coefficients[0];
            for (int i = index - 1; i >= 0; i--)
            {
                numerNew = denom * coefficients[i] + numer;
                BigInteger d_old = denom;
                denom = numerNew;
                numer = d_old;
            }

            return numerNew;
        }

        public static List<int> GenerateCoefficientsForConvergent(int N, Convergent convergent)
        {
            if (convergent == null || convergent.LeadingCoeff == 0 || convergent.PeriodicCoeffs == null || convergent.PeriodicCoeffs.Count == 0)
            {
                return null;
            }

            List<int> toReturn = new List<int> { convergent.LeadingCoeff };
            int period = convergent.PeriodicCoeffs.Count;
            int positionInPeriod = 0;
            int periodCount = 1;
            for (int i = 1; i < N; i++)
            {
                var coeff = convergent.PeriodicCoeffs[positionInPeriod];
                positionInPeriod++;
                if (coeff <= 0)
                {
                    coeff = convergent.CoefficientGenerators[positionInPeriod](periodCount);
                }
                if (positionInPeriod == period)
                {
                    positionInPeriod = 0;
                    periodCount++;
                }
                
                toReturn.Add(coeff);
            }

            return toReturn;
        }

        public static int ComputeCoeff2k(int k)
        {
            return 2 * k;
        }
    }
}
