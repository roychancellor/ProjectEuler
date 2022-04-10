using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace ProjectEuler65_ConvergentsOfe.Tests
{
    [TestClass]
    public class LogicTests
    {
        [TestMethod]
        public void GenerateCoefficientsForConvergent_Sqrt2_ShouldPassIf_GeneratesCorrectCoefficients()
        {
            var convergentToTest = TestTransactions.CONVERGENT_SQRT_2;
            int numberToMake = 5 * convergentToTest.PeriodicCoeffs.Count + 1;

            var coeffs = Logic.GenerateCoefficientsForConvergent(numberToMake, convergentToTest);

            Assert.AreEqual(convergentToTest.LeadingCoeff, coeffs[0]);
            int p = 0;
            int period = convergentToTest.PeriodicCoeffs.Count;
            for (int i = 1; i < numberToMake; i++)
            {
                Assert.AreEqual(convergentToTest.PeriodicCoeffs[p], coeffs[i]);
                p++;
                if (p == period)
                {
                    p = 0;
                }
            }
        }

        [TestMethod]
        public void GenerateCoefficientsForConvergent_Sqrt23_ShouldPassIf_GeneratesCorrectCoefficients()
        {
            var convergentToTest = TestTransactions.CONVERGENT_SQRT_23;
            int numberToMake = 5 * convergentToTest.PeriodicCoeffs.Count + 1;

            var coeffs = Logic.GenerateCoefficientsForConvergent(numberToMake, convergentToTest);

            Assert.AreEqual(convergentToTest.LeadingCoeff, coeffs[0]);
            int p = 0;
            int period = convergentToTest.PeriodicCoeffs.Count;
            for (int i = 1; i < numberToMake; i++)
            {
                Assert.AreEqual(convergentToTest.PeriodicCoeffs[p], coeffs[i]);
                p++;
                if (p == period)
                {
                    p = 0;
                }
            }
        }

        [TestMethod]
        public void GenerateCoefficientsForConvergent_Sqrt94_ShouldPassIf_GeneratesCorrectCoefficients()
        {
            var convergentToTest = TestTransactions.CONVERGENT_SQRT_94;
            int numberToMake = 5 * convergentToTest.PeriodicCoeffs.Count + 1;

            var coeffs = Logic.GenerateCoefficientsForConvergent(numberToMake, convergentToTest);

            Assert.AreEqual(convergentToTest.LeadingCoeff, coeffs[0]);
            int p = 0;
            int period = convergentToTest.PeriodicCoeffs.Count;
            for (int i = 1; i < numberToMake; i++)
            {
                Assert.AreEqual(convergentToTest.PeriodicCoeffs[p], coeffs[i]);
                p++;
                if (p == period)
                {
                    p = 0;
                }
            }
        }

        [TestMethod]
        public void GenerateCoefficientsForConvergent_e_ShouldPassIf_GeneratesCorrectCoefficients()
        {
            var convergentToTest = TestTransactions.CONVERGENT_e;
            int numberToMake = 5 * convergentToTest.PeriodicCoeffs.Count + 1;

            var coeffs = Logic.GenerateCoefficientsForConvergent(numberToMake, convergentToTest);

            Assert.AreEqual(convergentToTest.LeadingCoeff, coeffs[0]);
            int p = 0;
            int period = convergentToTest.PeriodicCoeffs.Count;
            int periodCount = 1;
            for (int i = 1; i < numberToMake; i++)
            {
                if (convergentToTest.PeriodicCoeffs[p] <= 0)
                {
                    Assert.AreEqual(Logic.ComputeCoeff2k(periodCount), coeffs[i]);
                }
                else
                {
                    Assert.AreEqual(convergentToTest.PeriodicCoeffs[p], coeffs[i]);
                }
                p++;
                if (p == period)
                {
                    p = 0;
                    periodCount++;
                }
            }
        }

        [TestMethod]
        public void GenerateNumeratorForConvergent_Sqrt2_ShouldPassIf_GeneratesCorrectNumerator()
        {
            var convergentToTest = new Convergent
            {
                LeadingCoeff = 1,
                PeriodicCoeffs = new List<int> { 2 },
            };
            int numberToMake = 5 * convergentToTest.PeriodicCoeffs.Count + 1;
            var expectedNumerators = new Dictionary<int, int> { { 1, 1 }, { 2, 3 }, { 3, 7 }, { 4, 17 }, { 5, 41 }, { 6, 99 }, { 7, 239 }, { 8, 577 }, { 9, 1393 }, { 10, 3363 } };

            foreach (var N in expectedNumerators.Keys)
            {
                var numerator = Logic.GenerateNumeratorForConvergent(N, convergentToTest);
                Assert.AreEqual(expectedNumerators[N], numerator);
            }
        }

        [TestMethod]
        public void GenerateNumeratorForConvergent_e_ShouldPassIf_GeneratesCorrectNumerator()
        {
            var convergentToTest = TestTransactions.CONVERGENT_e;
            var expectedNumerators = new Dictionary<int, int> { { 1, 2 }, { 2, 3 }, { 3, 8 }, { 4, 11 }, { 5, 19 }, { 6, 87 }, { 7, 106 }, { 8, 193 }, { 9, 1264 }, { 10, 1457 } };

            foreach (var N in expectedNumerators.Keys)
            {
                var numerator = Logic.GenerateNumeratorForConvergent(N, convergentToTest);
                Assert.AreEqual(expectedNumerators[N], numerator);
            }
        }
    }

    class TestTransactions
    {
        public static Convergent CONVERGENT_e = new Convergent
        {
            LeadingCoeff = 2,
            PeriodicCoeffs = new List<int> { 1, 0, 1 },
            CoefficientGenerators = new Dictionary<int, Func<int, int>> { { 2, Logic.ComputeCoeff2k } },
        };
        public static Convergent CONVERGENT_SQRT_2 = new Convergent
        {
            LeadingCoeff = 1,
            PeriodicCoeffs = new List<int> { 2 },
        };
        public static Convergent CONVERGENT_SQRT_23 = new Convergent
        {
            LeadingCoeff = 4,
            PeriodicCoeffs = new List<int> { 1, 3, 1, 8 },
        };
        public static Convergent CONVERGENT_SQRT_94 = new Convergent
        {
            LeadingCoeff = 9,
            PeriodicCoeffs = new List<int> { 1, 2, 3, 1, 1, 5, 1, 8, 1, 5, 1, 1, 3, 2, 1, 18 },
        };
    }
}
