using Euler64_OddPeriodSquareRoots;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace Euler64_OddPeriodSquareRootsTests
{
    [TestClass]
    public class RunnerTests
    {
        [TestMethod]
        public void GetPeriod_TestCasesFromProjecteulerPage_ShouldPassIf_AllPeriodsMatch()
        {
            Dictionary<int, int> rootList = new Dictionary<int, int> 
            {
                { 29, 5 },
                { 11, 2 },
                { 7, 4 },
                { 6, 2 },
                { 22, 6 },
                { 2, 1 },
                { 3, 2 },
                { 5, 1 },
                { 8, 2 },
                { 10, 1 },
                { 12, 2 },
                { 13, 5 },
                { 14, 4 },
                { 15, 2 },
                { 17, 1 },
                { 18, 2 },
                { 19, 6 },
                { 20, 2 },
                { 21, 6 },
                { 23, 4 },
                { 24, 2 },
                { 26, 1 },
                { 27, 2 },
                { 28, 4 },
                { 30, 2 },
                { 31, 8 },
                { 32, 4 },
                { 33, 4 },
                { 34, 4 },
                { 35, 2 },
                { 37, 1 },
                { 38, 2 },
                { 39, 2 },
                { 40, 2 },
                { 41, 3 },
                { 42, 2 },
                { 43, 10 },
                { 44, 8 },
                { 45, 6 },
                { 46, 12 },
                { 47, 4 },
                { 48, 2 },
            };

            foreach (var N in rootList.Keys)
            {
                var testPeriod = Runner.GetPeriod(N);
                Assert.AreEqual(rootList[N], testPeriod); 
            }
        }
    }
}
