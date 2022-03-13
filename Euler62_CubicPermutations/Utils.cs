using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler62_CubicPermutations
{
    public class Utils
    {
        public static double Cube(int n)
        {
            return n * 1.0 * n * n;
        }

        public static Dictionary<int, double> MakeCubes(int nStart, int nEnd)
        {
            if (nEnd < nStart)
            {
                var temp = nEnd;
                nEnd = nStart;
                nStart = temp;
            }

            Dictionary<int, double> toReturn = new Dictionary<int, double>();
            for (int i = nStart; i <= nEnd; i++)
            {
                toReturn.Add(i, Cube(i));
            }
            return toReturn;
        }

        public static HashSet<int> NumberToDigitSet(long number)
        {
            HashSet<int> toReturn = new HashSet<int>();
            foreach (var digit in number.ToString())
            {
                toReturn.Add(digit);
            }
            return toReturn;
        }

        public static List<int> NumberToDigitList(double number)
        {
            List<int> toReturn = new List<int>();
            foreach (var digit in number.ToString())
            {
                toReturn.Add(digit);
            }
            return toReturn;
        }

        public static Dictionary<int, List<int>> MakeDigitLists(int nStart, int nEnd)
        {
            Dictionary<int, List<int>> toReturn = new Dictionary<int, List<int>>();
            for (int i = nStart; i <= nEnd; i++)
            {
                toReturn.Add(i, NumberToDigitList(Cube(i)));
            }
            return toReturn;
        }

        public static bool ShouldCheckForPermutation(List<int> cubeOne, List<int> cubeTwo)
        {
            return cubeOne.Count == cubeTwo.Count;
        }

        public static bool IsPermutation(HashSet<int> cubeOne, HashSet<int> cubeTwo)
        {
            return cubeOne.Count == cubeTwo.Count && Enumerable.Intersect(cubeOne, cubeTwo).Count() == cubeOne.Count;
        }

        public static bool IsPermutation(List<int> cubeOneDigits, List<int> cubeTwoDigits)
        {
            cubeOneDigits.Sort();
            cubeTwoDigits.Sort();

            for (int i = 0; i < cubeOneDigits.Count; i++)
            {
                if (cubeOneDigits[i] != cubeTwoDigits[i])
                {
                    return false;
                }
            }
            return true;
        }
    }
}
