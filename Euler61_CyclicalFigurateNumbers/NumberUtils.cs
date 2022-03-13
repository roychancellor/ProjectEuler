using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Euler61_CyclicalFigurateNumbers
{
    public static class NumberUtils
    {
        public static List<int> Triangular { get; set; }
        public static List<int> Square { get; set; }
        public static List<int> Pentagonal { get; set; }
        public static List<int> Hexagonal { get; set; }
        public static List<int> Heptagonal { get; set; }
        public static List<int> Octagonal { get; set; }
        public static Dictionary<int, List<int>> NumberLists { get; set; }
        public static Dictionary<int, int> SideStartIndex { get; set; } = new Dictionary<int, int> { { 3, 45 }, { 4, 32 }, { 5, 26 }, { 6, 23 }, { 7, 21 }, { 8, 19 } };
        public static List<int> AllFigurateNumbers { get; set; }
        public static Dictionary<int, Figurate> FigurateData { get; set; }

        public static void PopulateNumberLists(int ofSize)
        {
            Triangular = new List<int>();
            Square = new List<int>();
            Pentagonal = new List<int>();
            Hexagonal = new List<int>();
            Heptagonal = new List<int>();
            Octagonal = new List<int>();
            AllFigurateNumbers = new List<int>();
            FigurateData = new Dictionary<int, Figurate>();

            var triangularFigurate = new Figurate();
            var squareFigurate = new Figurate();
            var pentagonalFigurate = new Figurate();
            var hexagonalFigurate = new Figurate();
            var heptagonalFigurate = new Figurate();
            var octagonalFigurate = new Figurate();
            FigurateData.Add(3, triangularFigurate);
            FigurateData.Add(4, squareFigurate);
            FigurateData.Add(5, pentagonalFigurate);
            FigurateData.Add(6, hexagonalFigurate);
            FigurateData.Add(7, heptagonalFigurate);
            FigurateData.Add(8, octagonalFigurate);

            for (int i = 0; i < ofSize; i++)
            {
                var toAdd = MakeFigurateValue(3, i + SideStartIndex[3]);
                if (toAdd < 10000 && !LastTwo(toAdd).StartsWith("0"))
                {
                    AllFigurateNumbers.Add(toAdd);
                    Triangular.Add(toAdd);
                    FigurateData[3].Values.Add(toAdd);
                    FigurateData[3].ValueToDigitSets(toAdd); 
                }

                toAdd = MakeFigurateValue(4, i + SideStartIndex[4]);
                if (toAdd < 10000 && !LastTwo(toAdd).StartsWith("0"))
                {
                    AllFigurateNumbers.Add(toAdd);
                    Square.Add(toAdd);
                    FigurateData[4].Values.Add(toAdd);
                    FigurateData[4].ValueToDigitSets(toAdd); 
                }

                toAdd = MakeFigurateValue(5, i + SideStartIndex[5]);
                if (toAdd < 10000 && !LastTwo(toAdd).StartsWith("0"))
                {
                    AllFigurateNumbers.Add(toAdd);
                    Pentagonal.Add(toAdd);
                    FigurateData[5].Values.Add(toAdd);
                    FigurateData[5].ValueToDigitSets(toAdd); 
                }

                toAdd = MakeFigurateValue(6, i + SideStartIndex[6]);
                if (toAdd < 10000 && !LastTwo(toAdd).StartsWith("0"))
                {
                    AllFigurateNumbers.Add(toAdd);
                    Hexagonal.Add(toAdd);
                    FigurateData[6].Values.Add(toAdd);
                    FigurateData[6].ValueToDigitSets(toAdd); 
                }

                toAdd = MakeFigurateValue(7, i + SideStartIndex[7]);
                if (toAdd < 10000 && !LastTwo(toAdd).StartsWith("0"))
                {
                    AllFigurateNumbers.Add(toAdd);
                    Heptagonal.Add(toAdd);
                    FigurateData[7].Values.Add(toAdd);
                    FigurateData[7].ValueToDigitSets(toAdd); 
                }

                toAdd = MakeFigurateValue(8, i + SideStartIndex[8]);
                if (toAdd < 10000 && !LastTwo(toAdd).StartsWith("0"))
                {
                    AllFigurateNumbers.Add(toAdd);
                    Octagonal.Add(toAdd);
                    FigurateData[8].Values.Add(toAdd);
                    FigurateData[8].ValueToDigitSets(toAdd); 
                }
            }

            NumberLists = new Dictionary<int, List<int>>
            {
                { 3, Triangular },
                { 4, Square },
                { 5, Pentagonal },
                { 6, Hexagonal },
                { 7, Heptagonal },
                { 8, Octagonal }
            };
        }

        public static int MakeFigurateValue(int sides, int n)
        {
            switch (sides)
            {
                case 3:
                    return n * (n + 1) / 2;
                case 4:
                    return n * n;
                case 5:
                    return n * (3 * n - 1) / 2;
                case 6:
                    return n * (2 * n - 1);
                case 7:
                    return n * (5 * n - 3) / 2;
                case 8:
                    return n * (3 * n - 2);
                default:
                    return -1;
            }
        }

        public static int Combine(string firstTwo, string lastTwo)
        {
            return int.Parse(firstTwo) * 100 + int.Parse(lastTwo);
        }

        public static bool Split(int toSplit, out string firstTwo, out string lastTwo)
        {
            if (toSplit < 1000)
            {
                firstTwo = string.Empty;
                lastTwo = string.Empty;
                return false;
            }
            firstTwo = FirstTwo(toSplit);
            lastTwo = LastTwo(toSplit);

            return true;
        }

        public static string FirstTwo(int value)
        {
            return value >= 1000 ? value.ToString().Substring(0, 2) : null;
        }

        public static string LastTwo(int value)
        {
            return value >= 1000 ? value.ToString().Substring(2, 2) : null;
        }

        public static bool IsCyclicFigurate(List<int> p3p8, out List<int> cyclicFigurateResult)
        {
            List<string> firstTwos = new List<string> { "", "", "", "", "", "" };
            List<string> lastTwos = new List<string> { "", "", "", "", "", "" };
            cyclicFigurateResult = new List<int>();

            for (int i0 = 0; i0 < 6; i0++)
            {
                firstTwos[0] = FirstTwo(p3p8[i0]);
                lastTwos[0] = LastTwo(p3p8[i0]);
                for (int i1 = 0; i1 < 6; i1++)
                {
                    if (i1 == i0) continue;
                    firstTwos[1] = FirstTwo(p3p8[i1]);
                    lastTwos[1] = LastTwo(p3p8[i1]);
                    if (!lastTwos[0].Equals(firstTwos[1])) continue;
                    for (int i2 = 0; i2 < 6; i2++)
                    {
                        if (i2 == i1 || i2 == i0) continue;
                        firstTwos[2] = FirstTwo(p3p8[i2]);
                        lastTwos[2] = LastTwo(p3p8[i2]);
                        if (!lastTwos[1].Equals(firstTwos[2])) continue;
                        for (int i3 = 0; i3 < 6; i3++)
                        {
                            if (i3 == i2 || i3 == i1 || i3 == i0) continue;
                            firstTwos[3] = FirstTwo(p3p8[i3]);
                            lastTwos[3] = LastTwo(p3p8[i3]);
                            if (!lastTwos[2].Equals(firstTwos[3])) continue;
                            for (int i4 = 0; i4 < 6; i4++)
                            {
                                if (i4 == i3 || i4 == i2 || i4 == i1 || i4 == i0) continue;
                                firstTwos[4] = FirstTwo(p3p8[i4]);
                                lastTwos[4] = LastTwo(p3p8[i4]);
                                if (!lastTwos[3].Equals(firstTwos[4])) continue;
                                for (int i5 = 0; i5 < 6; i5++)
                                {
                                    if (i5 == i4 || i5 == i3 || i5 == i2 || i5 == i1 || i5 == i0) continue;
                                    firstTwos[5] = FirstTwo(p3p8[i5]);
                                    lastTwos[5] = LastTwo(p3p8[i5]);
                                    if (!lastTwos[4].Equals(firstTwos[5])) continue;
                                    if (!firstTwos[0].Equals(lastTwos[5])) continue;
                                    
                                    if (IsCyclic(firstTwos, lastTwos))
                                    {
                                        for (int i = 0; i < firstTwos.Count; i++)
                                        {
                                            cyclicFigurateResult.Add(Combine(firstTwos[i], lastTwos[i]));
                                        }
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

        public static bool IsCyclicFigurateThree(List<int> p3p8)
        {
            List<string> firstTwos = new List<string> { "", "", "" };
            List<string> lastTwos = new List<string> { "", "", "" };

            for (int i0 = 0; i0 < 3; i0++)
            {
                firstTwos[0] = FirstTwo(p3p8[i0]);
                lastTwos[0] = LastTwo(p3p8[i0]);
                for (int i1 = 0; i1 < 3; i1++)
                {
                    if (i1 == i0) continue;
                    firstTwos[1] = FirstTwo(p3p8[i1]);
                    lastTwos[1] = LastTwo(p3p8[i1]);
                    if (!lastTwos[0].Equals(firstTwos[1])) continue;
                    for (int i2 = 0; i2 < 3; i2++)
                    {
                        if (i2 == i1 || i2 == i0) continue;
                        firstTwos[2] = FirstTwo(p3p8[i2]);
                        lastTwos[2] = LastTwo(p3p8[i2]);
                        if (!lastTwos[1].Equals(firstTwos[2])) continue;
                        if (IsCyclicThreeV2(firstTwos, lastTwos))
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static bool IsCyclic(List<string> firstTwos, List<string> lastTwos)
        {
            return firstTwos[0].Equals(lastTwos[5]) &&
                   lastTwos[0].Equals(firstTwos[1]) &&
                   lastTwos[1].Equals(firstTwos[2]) &&
                   lastTwos[2].Equals(firstTwos[3]) &&
                   lastTwos[3].Equals(firstTwos[4]) &&
                   lastTwos[4].Equals(firstTwos[5]);
        }

        public static bool IsCyclicThreeV2(List<string> firstTwos, List<string> lastTwos)
        {
            return firstTwos[0].Equals(lastTwos[2]) &&
                   lastTwos[0].Equals(firstTwos[1]) &&
                   lastTwos[1].Equals(firstTwos[2]);
        }

        public static bool IsCyclicThree(List<int> toCheck)
        {
            return FirstTwo(toCheck[0]).Equals(LastTwo(toCheck[2])) &&
                LastTwo(toCheck[0]).Equals(FirstTwo(toCheck[1])) &&
                LastTwo(toCheck[1]).Equals(FirstTwo(toCheck[2]));
        }

        public static string PrintList(List<int> toPrint)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var num in toPrint)
            {
                sb.Append(num + " ");
            }
            return sb.ToString();
        }

        public static bool ShouldCheckIfCyclicFigurate(HashSet<string> firstTwos, HashSet<string> lastTwos)
        {
            return firstTwos.Count == lastTwos.Count && Enumerable.Intersect(firstTwos, lastTwos).Count() == 6;
        }

        public static bool ShouldCheckIfCyclicFigurateThree(HashSet<string> firstTwos, HashSet<string> lastTwos)
        {
            var countsMatch = firstTwos.Count == lastTwos.Count;
            var intersectSize = Enumerable.Intersect(firstTwos, lastTwos).Count();
            var toReturn = countsMatch && intersectSize == 3;
            return toReturn;
        }

        public static void Log(RunLogConfig config, string toLog)
        {
            var filePath = $"{config.RunLogPath}\\{config.RunLogFileName}";

            StreamWriter file = new StreamWriter(filePath, append: true);
            using (file)
            {
                file.WriteLine(toLog);
            }
        }

        public static void PrintSets(HashSet<string> leftSideSet, HashSet<string> rightSideSet)
        {
            foreach (var toPrint in leftSideSet)
            {
                Console.Write(toPrint + " ");
            }
            Console.Write("; ");
            foreach (var toPrint in rightSideSet)
            {
                Console.Write(toPrint + " ");
            }
            Console.Write("; ");
            foreach (var toPrint in Enumerable.Intersect(leftSideSet, rightSideSet))
            {
                Console.Write(toPrint + " ");
            }
            Console.WriteLine();
        }
    }
}
