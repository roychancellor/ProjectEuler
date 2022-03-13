using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler62_CubicPermutations
{
    public class Runner
    {
        private static Dictionary<int, List<int>> cubeDigitLists = new Dictionary<int, List<int>>();
        private const int N_START = 100;
        private const int N_END = 500;
        private const int NUM_PERMUT_GOAL = 3;
        
        public static void Run(string[] args)
        {
            int nStart = N_START;
            int nEnd = N_END;
            int permutTarget = NUM_PERMUT_GOAL;
            if (args.Length >= 6)
            {
                if (args[0].ToLower().StartsWith("--s"))
                {
                    if (!int.TryParse(args[1], out int start))
                    {
                        Console.WriteLine($"Error: The argument {args[0]} {args[1]} must be parsable as an integer greater than zero. Ending.");
                        return;
                    }
                    nStart = start;
                }
                if (args[2].ToLower().StartsWith("--e"))
                {
                    if (!int.TryParse(args[3], out int end))
                    {
                        Console.WriteLine($"Error: The argument {args[2]} {args[3]} must be parsable as an integer greater than zero. Ending.");
                        return;
                    }
                    nEnd = end;
                }
                if (args[4].ToLower().StartsWith("--p"))
                {
                    if (!int.TryParse(args[5], out int permut))
                    {
                        Console.WriteLine($"Error: The argument {args[4]} {args[5]} must be parsable as an integer greater than zero. Ending.");
                        return;
                    }
                    permutTarget = permut;
                }
            }

            Console.Write($"Making cube digit sets from n = {nStart} to n = {nEnd}...");
            cubeDigitLists = Utils.MakeDigitLists(nStart, nEnd);
            Console.WriteLine("DONE");

            Console.WriteLine($"Checking permutations...");
            for (int n = nStart; n <= nEnd - 5; n++)
            {
                int numPermut = 1;
                Console.WriteLine($"Checking {n}");
                for (int i = n + 1; i <= nEnd; i++)
                {
                    if (Utils.ShouldCheckForPermutation(cubeDigitLists[n], cubeDigitLists[i]))
                    {
                        if (Utils.IsPermutation(cubeDigitLists[n], cubeDigitLists[i]))
                        {
                            numPermut++;
                            Console.WriteLine($"Found permutation of {n}^3 with {i}^3. That's {numPermut}.");
                            if (numPermut == permutTarget)
                            {
                                Console.WriteLine($"FOUND IT!!! The smallest cube with {permutTarget} permutations of its digits that are cubes is {Utils.Cube(n)}");
                                return;
                            }
                        } 
                    }
                }
            }

            return;
        }
    }
}
