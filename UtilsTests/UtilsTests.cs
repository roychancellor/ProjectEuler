using Euler63_PowerfulDigitCounts;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace UtilsTests
{
    [TestClass]
    public class UtilsTests
    {
        [TestMethod]
        public void NumberOfDigits_ShouldPassIf_ReturnsCorrectNumberOfDigits()
        {
            var toTest = new List<int> { 1, 12, 123, 1234, 12345, 123456, 1234567, 12345678, 123456789 };
            var expectedResults = new List<int> { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            int i = 0;
            foreach (var num in toTest)
            {
                var testResult = Utils.NumberOfDigits(num);

                Assert.AreEqual(expectedResults[i], testResult);

                i++;
            }
        }
    }
}
