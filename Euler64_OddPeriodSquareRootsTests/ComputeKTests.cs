using Euler64_OddPeriodSquareRoots;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace Euler64_OddPeriodSquareRootsTests
{
    [TestClass]
    public class ComputeKTests
    {
        [TestMethod]
        public void ComputeK_ShouldPassIf_ComputesCorrectK()
        {
            List<TestCase> TestCases = new List<TestCase>
            {
                new TestCase { C = 4, B = 6, ExpectedK = 2, MaxK = 4 },
                new TestCase { C = 2, B = 3, ExpectedK = 4, MaxK = 4 },
                new TestCase { C = 4, B = 2, ExpectedK = 4, MaxK = 4 },
                new TestCase { C = 4, B = 3, ExpectedK = 2, MaxK = 4 },
                new TestCase { C = 2, B = 6, ExpectedK = 4, MaxK = 4 },
                new TestCase { C = 4, B = 1, ExpectedK = 4, MaxK = 4 },
            };

            foreach (var tc in TestCases)
            {
                var testK = RootFraction.ComputeK(tc.B, tc.C, tc.MaxK);

                Assert.AreEqual(tc.ExpectedK, testK);
            }
        }
    }

    class TestCase
    {
        public int C { get; set; }
        public int B { get; set; }
        public int MaxK { get; set; }
        public int ExpectedK { get; set; }
    }
}
