using Euler61_CyclicalFigurateNumbers;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace Euler61_Tests
{
    [TestClass]
    public class NumberUtilsTests
    {
        private static List<int> expectedTriangular = new List<int> { 1035, 1081, 1128, 1176, 1225 };
        private static List<int> expectedSquare = new List<int> { 1024, 1089, 1156, 1225, 1296 };
        private static List<int> expectedPentagonal = new List<int> { 1001, 1080, 1162, 1247, 1335 };
        private static List<int> expectedHexagonal = new List<int> { 1035, 1128, 1225, 1326, 1431 };
        private static List<int> expectedHeptagonal = new List<int> { 1071, 1177, 1288, 1404, 1525 };
        private static List<int> expectedOctagonal = new List<int> { 1045, 1160, 1281, 1408, 1541 };
        private static Dictionary<int, List<int>> expectedNumberLists = new Dictionary<int, List<int>>
            {
                { 3, expectedTriangular },
                { 4, expectedSquare },
                { 5, expectedPentagonal },
                { 6, expectedHexagonal },
                { 7, expectedHeptagonal },
                { 8, expectedOctagonal },
            };

        [TestMethod]
        public void MakeFigurate_Triangular_ShouldPassIf_ProducesExpectedNumbers()
        {
            for (int j = 0; j < 5; j++)
            {
                var testResult = NumberUtils.MakeFigurateValue(3, j + NumberUtils.SideStartIndex[3]);
                Assert.AreEqual(expectedTriangular[j], testResult);
            }
        }

        [TestMethod]
        public void MakeFigurate_Square_ShouldPassIf_ProducesExpectedNumbers()
        {
            for (int j = 0; j < 5; j++)
            {
                var testResult = NumberUtils.MakeFigurateValue(4, j + NumberUtils.SideStartIndex[4]);
                Assert.AreEqual(expectedSquare[j], testResult);
            }
        }

        [TestMethod]
        public void MakeFigurate_Pentagonal_ShouldPassIf_ProducesExpectedNumbers()
        {
            for (int j = 0; j < 5; j++)
            {
                var testResult = NumberUtils.MakeFigurateValue(5, j + NumberUtils.SideStartIndex[5]);
                Assert.AreEqual(expectedPentagonal[j], testResult);
            }
        }

        [TestMethod]
        public void MakeFigurate_Hexagonal_ShouldPassIf_ProducesExpectedNumbers()
        {
            for (int j = 0; j < 5; j++)
            {
                var testResult = NumberUtils.MakeFigurateValue(6, j + NumberUtils.SideStartIndex[6]);
                Assert.AreEqual(expectedHexagonal[j], testResult);
            }
        }

        [TestMethod]
        public void MakeFigurate_Heptagonal_ShouldPassIf_ProducesExpectedNumbers()
        {
            for (int j = 0; j < 5; j++)
            {
                var testResult = NumberUtils.MakeFigurateValue(7, j + NumberUtils.SideStartIndex[7]);
                Assert.AreEqual(expectedHeptagonal[j], testResult);
            }
        }

        [TestMethod]
        public void MakeFigurate_Octagonal_ShouldPassIf_ProducesExpectedNumbers()
        {
            for (int j = 0; j < 5; j++)
            {
                var testResult = NumberUtils.MakeFigurateValue(8, j + NumberUtils.SideStartIndex[8]);
                Assert.AreEqual(expectedOctagonal[j], testResult);
            }
        }

        [TestMethod]
        public void PopulateNumberLists_ShouldPassIf_DictionaryPopulatedCorrectly()
        {
            NumberUtils.PopulateNumberLists(5);
            
            for (int side = 3; side <= 8; side++)
            {
                for (int i = 0; i < 5; i++)
                {
                    var testResult = NumberUtils.NumberLists[side][i];
                    Assert.AreEqual(expectedNumberLists[side][i], testResult);
                }
            }
        }

        [TestMethod]
        public void Combine_ShouldPassIf_TwoHalvesProduceExpectedValue()
        {
            var firstTwo = "10";
            var lastTwo = "01";
            var expectedResult = 1001;

            var testResult = NumberUtils.Combine(firstTwo, lastTwo);

            Assert.AreEqual(expectedResult, testResult);
        }

        [TestMethod]
        public void Split_ShouldPassIf_ProducesExpectedFirstAndSecondHalf()
        {
            var testValue = 1001;
            var expectedFirstTwo = "10";
            var expectedLastTwo = "01";

            var testResult = NumberUtils.Split(testValue, out string firstTwoResult, out string lastTwoResult);

            Assert.IsTrue(testResult);
            Assert.AreEqual(expectedFirstTwo, firstTwoResult);
            Assert.AreEqual(expectedLastTwo, lastTwoResult);
        }
    }
}
