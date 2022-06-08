using Euler66_DiophantineEquation;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace Euler66_DiophantineEquationTests
{
    [TestClass]
    public class ProgramTests
    {
        [TestMethod]
        public void GenerateSolution_TestCasesFromPESite_ShouldPassIf_GeneratesCorrectSolution()
        {
            var D_expectedXSolutions = new Dictionary<int, long> { { 2, 3L }, { 3, 2L }, { 5, 9L }, { 6, 5L }, { 7, 8L } };

            foreach (var D in D_expectedXSolutions.Keys)
            {
                var xSolution = Program.GenerateMinimalSolution(D, 1000);

                Assert.AreEqual(D_expectedXSolutions[D], xSolution);
            }
        }

        [TestMethod]
        public void FindD_TestCasesFromPESite_ShouldPassIf_GeneratesCorrectD()
        {
            var expectedD = 5;
            var maxD = 7;

            var result = Program.FindD(maxD);

            Assert.AreEqual(expectedD, result);
        }
    }
}
