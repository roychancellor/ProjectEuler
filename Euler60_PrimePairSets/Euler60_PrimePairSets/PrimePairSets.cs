<<<<<<< HEAD
﻿using com.royware.RoyUtils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Euler60_PrimePairSets
{
    public class PrimePairSets
    {
        private const int START_PRIME_DEFAULT = 3;
        private const int NUM_REF_PRIMES_DEFAULT = 10000;
        private const string REF_PRIMES_FILE_NAME = "refprimes.txt";
        private const string REF_PRIMES_FILE_PATH = @"C:\dev.net\ProjectEuler\Euler60_PrimePairSets\Euler60_PrimePairSets\Euler60_PrimePairSets\";

        private const string HELP_TEXT = "Euler60_PrimePairSets.exe\n--s <starting prime as integer>\n--n <number of reference primes as an integer>\n--t <target number of prime pairs to find>\n--i <number of iterations>";

        private static Dictionary<string, string> ARGUMENT_MAP = new Dictionary<string, string> 
        { 
            { "--s", "startPrime" },
            { "--n", "numberOfReferencePrimes" },
            { "--t", "targetPrimePairs" },
            { "--i", "numberOfIterations" },
            { "--help", HELP_TEXT } 
        };

        //////////////////////////////////////////////////////////////////////////////////////////
        // Run from a command line with Euler60_PrimePairSets.exe --s 3 --n 1000000 --t 5 --i 3000
        //////////////////////////////////////////////////////////////////////////////////////////

        public static void Run(string[] args)
        {
            Dictionary<string, int> parsedArgs = RoyUtils.ParseArgs(args.ToList(), ARGUMENT_MAP, HELP_TEXT);

            if (parsedArgs.ContainsKey("--help"))
            {
                return;
            }
            
            int startPrime = parsedArgs.ContainsKey(nameof(startPrime)) ? parsedArgs[nameof(startPrime)] : START_PRIME_DEFAULT;
            int numberOfReferencePrimes = parsedArgs.ContainsKey(nameof(numberOfReferencePrimes)) ? parsedArgs[nameof(numberOfReferencePrimes)] : NUM_REF_PRIMES_DEFAULT;
            int targetPrimePairs = parsedArgs.ContainsKey(nameof(targetPrimePairs)) ? parsedArgs[nameof(targetPrimePairs)] : 4;
            int numberOfIterations = parsedArgs.ContainsKey(nameof(numberOfIterations)) ? parsedArgs[nameof(numberOfIterations)] : 100;

            if (startPrime == 1 || startPrime == 2 || !RoyUtils.IsPrime(startPrime))
            {
                Console.WriteLine($"The number {startPrime} is invalid. It must be >= 3 and prime. EXITING...");
                return;
            }

            Console.WriteLine("***********************************");
            Console.WriteLine($"Running with the following inputs:");
            Console.WriteLine($"Starting prime: {startPrime}\nNumber of reference primes: {numberOfReferencePrimes}\nTarget Prime Pairs: {targetPrimePairs}\nIterations: {numberOfIterations}");
            Console.WriteLine("***********************************");

            Console.WriteLine("\nMaking list of reference primes...");
            List<long> primesReference = MakeReferencePrimes(REF_PRIMES_FILE_NAME);

            FindTheAnswer(primesReference, numberOfIterations);
        }

        private static void FindTheAnswer(List<long> primesReference, int numPrimes)
        {
            Console.WriteLine("Starting the heavy lifting of finding all the 5-tuples.....");
            Dictionary<string, List<long>> tuplesToTest = MakeQuintuples(primesReference, numPrimes);
            Console.WriteLine("DONE!!!!\n");

            List<long> sums = new List<long>();
            foreach (var strTuple in tuplesToTest.Keys)
            {
                var sum = Enumerable.Sum(tuplesToTest[strTuple]);
                Console.WriteLine($"Tuple: {strTuple}, Sum: {sum}");
                sums.Add(sum);
            }
            long minSum = sums.Min();
            Console.WriteLine($"The minimum sum is: {minSum}");
        }

        private static Dictionary<string, List<long>> MakeQuintuples(List<long> primes, int numPrimes)
        {
            Dictionary<string, List<long>> tuples = new Dictionary<string, List<long>>();

            long minSum = long.MaxValue;
            for (int a = 0; a < numPrimes; a++)
            {
                Console.WriteLine($"a: {a}");
                var p1 = primes[a];
                if (p1 > minSum) break;
                
                for (int b = primes.IndexOf(7); b < numPrimes; b++)
                {
                    var p2 = primes[b];
                    if (p2 <= p1) continue;
                    if (p1 + p2 > minSum) break;
                    if (!TestPair(p1, p2)) continue;

                    for (int c = primes.IndexOf(11); c < numPrimes; c++)
                    {
                        var p3 = primes[c];
                        if (p3 <= p2) continue;
                        if (p1 + p2 + p3 > minSum) break;
                        if (!TestPair(p1, p3) || !TestPair(p2, p3)) continue;

                        for (int d = primes.IndexOf(13); d < numPrimes; d++)
                        {
                            var p4 = primes[d];
                            if (p4 <= p3) continue;
                            if (p1 + p2 + p3 + p4 > minSum) break;
                            if (!TestPair(p1, p4) || !TestPair(p2, p4) || !TestPair(p3, p4)) continue;

                            for (int e = primes.IndexOf(17); e < numPrimes; e++)
                            {
                                var p5 = primes[e];
                                if (p5 <= p4) continue;
                                var testSum = p1 + p2 + p3 + p4 + p5;
                                if (testSum > minSum) break;
                                if (!TestPair(p1, p5) || !TestPair(p2, p5) || !TestPair(p3, p5) || !TestPair(p4, p5)) continue;

                                // By the time we get here, the five primes meet all the criteria
                                minSum = testSum;
                                string tupleFormatted = FormatQuintuple(p1, p2, p3, p4, p5);
                                Console.WriteLine($"Found one!!! {tupleFormatted}");
                                tuples.Add(tupleFormatted, new List<long> { p1, p2, p3, p4, p5 });
                            }
                        }
                    }
                }
            }
            return tuples;
        }

        private static bool TestPair(long p1, long p2)
        {
            return RoyUtils.IsPrime(long.Parse($"{p1}{p2}")) && RoyUtils.IsPrime(long.Parse($"{p2}{p1}"));
        }

        private static string FormatQuintuple(long i, long j, long k, long c, long d)
        {
            return $"({i}, {j}, {k}, {c}, {d})";
        }

        private static List<long> MakeReferencePrimes(int startPrime, int numberOfReferencePrimes)
        {
            int primeCandidate = startPrime;
            List<long> toReturn = new List<long>();
            //string[] primes = new string[numberOfReferencePrimes];
            int numPrimes = 0;
            while(numPrimes < numberOfReferencePrimes)
            {
                if (RoyUtils.IsPrime(primeCandidate))
                {
                    toReturn.Add(primeCandidate);
                    //primes[numPrimes] = primeCandidate.ToString();
                    numPrimes++;
                }
                primeCandidate += 2;
            }
            //string csv = string.Join(',', primes);
            return toReturn;
        }

        private static List<long> MakeReferencePrimes(string primesFile, int startPrime = START_PRIME_DEFAULT, int numberOfReferencePrimes = NUM_REF_PRIMES_DEFAULT)
        {
            List<long> toReturn = new List<long>();
            string filePath = $"{REF_PRIMES_FILE_PATH}{primesFile}";
            try
            {
                Console.Write($"Reading {primesFile} to get the reference primes...");
                var primesString = File.ReadAllText(filePath);
                var primesToParse = primesString.Split(',');
                foreach (var primeStr in primesToParse)
                {
                    bool parsed = int.TryParse(primeStr, out int prime);
                    if (!parsed)
                    {
                        throw new Exception();
                    }
                    toReturn.Add(prime);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"\nException reading from reference primes file {filePath}. Building list manually.\n{ex}");
                return MakeReferencePrimes(START_PRIME_DEFAULT, NUM_REF_PRIMES_DEFAULT);
            }

            Console.WriteLine("DONE");
            return toReturn;
        }
    }
}
=======
﻿using com.royware.RoyUtils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Euler60_PrimePairSets
{
    public class PrimePairSets
    {
        private const int START_PRIME_DEFAULT = 3;
        private const int NUM_REF_PRIMES_DEFAULT = 10000;
        private const string REF_PRIMES_FILE_NAME = "refprimes.txt";
        private const string REF_PRIMES_FILE_PATH = @"C:\dev.net\ProjectEuler\Euler60_PrimePairSets\Euler60_PrimePairSets\Euler60_PrimePairSets\";

        private const string HELP_TEXT = "Euler60_PrimePairSets.exe\n--s <starting prime as integer>\n--n <number of reference primes as an integer>\n--t <target number of prime pairs to find>\n--i <number of iterations>";

        private static Dictionary<string, string> ARGUMENT_MAP = new Dictionary<string, string> 
        { 
            { "--s", "startPrime" },
            { "--n", "numberOfReferencePrimes" },
            { "--t", "targetPrimePairs" },
            { "--i", "numberOfIterations" },
            { "--help", HELP_TEXT } 
        };

        //////////////////////////////////////////////////////////////////////////////////////////
        // Run from a command line with Euler60_PrimePairSets.exe --s 3 --n 1000000 --t 5 --i 3000
        //////////////////////////////////////////////////////////////////////////////////////////

        public static void Run(string[] args)
        {
            Dictionary<string, int> parsedArgs = RoyUtils.ParseArgs(args.ToList(), ARGUMENT_MAP, HELP_TEXT);

            if (parsedArgs.ContainsKey("--help"))
            {
                return;
            }
            
            int startPrime = parsedArgs.ContainsKey(nameof(startPrime)) ? parsedArgs[nameof(startPrime)] : START_PRIME_DEFAULT;
            int numberOfReferencePrimes = parsedArgs.ContainsKey(nameof(numberOfReferencePrimes)) ? parsedArgs[nameof(numberOfReferencePrimes)] : NUM_REF_PRIMES_DEFAULT;
            int targetPrimePairs = parsedArgs.ContainsKey(nameof(targetPrimePairs)) ? parsedArgs[nameof(targetPrimePairs)] : 4;
            int numberOfIterations = parsedArgs.ContainsKey(nameof(numberOfIterations)) ? parsedArgs[nameof(numberOfIterations)] : 100;

            if (startPrime == 1 || startPrime == 2 || !RoyUtils.IsPrime(startPrime))
            {
                Console.WriteLine($"The number {startPrime} is invalid. It must be >= 3 and prime. EXITING...");
                return;
            }

            Console.WriteLine("***********************************");
            Console.WriteLine($"Running with the following inputs:");
            Console.WriteLine($"Starting prime: {startPrime}\nNumber of reference primes: {numberOfReferencePrimes}\nTarget Prime Pairs: {targetPrimePairs}\nIterations: {numberOfIterations}");
            Console.WriteLine("***********************************");

            Console.WriteLine("\nMaking list of reference primes...");
            List<long> primesReference = MakeReferencePrimes(REF_PRIMES_FILE_NAME);

            FindTheAnswer(primesReference, numberOfIterations);
        }

        private static void FindTheAnswer(List<long> primesReference, int numPrimes)
        {
            Console.WriteLine("Starting the heavy lifting of finding all the 5-tuples.....");
            Dictionary<string, List<long>> tuplesToTest = MakeQuintuples(primesReference, numPrimes);
            Console.WriteLine("DONE!!!!\n");

            List<long> sums = new List<long>();
            foreach (var strTuple in tuplesToTest.Keys)
            {
                var sum = Enumerable.Sum(tuplesToTest[strTuple]);
                Console.WriteLine($"Tuple: {strTuple}, Sum: {sum}");
                sums.Add(sum);
            }
            long minSum = sums.Min();
            Console.WriteLine($"The minimum sum is: {minSum}");
        }

        private static Dictionary<string, List<long>> MakeQuintuples(List<long> primes, int numPrimes)
        {
            Dictionary<string, List<long>> tuples = new Dictionary<string, List<long>>();

            long minSum = long.MaxValue;
            for (int a = 0; a < numPrimes; a++)
            {
                Console.WriteLine($"a: {a}");
                var p1 = primes[a];
                if (p1 > minSum) break;
                
                for (int b = primes.IndexOf(7); b < numPrimes; b++)
                {
                    var p2 = primes[b];
                    if (p2 <= p1) continue;
                    if (p1 + p2 > minSum) break;
                    if (!TestPair(p1, p2)) continue;

                    for (int c = primes.IndexOf(11); c < numPrimes; c++)
                    {
                        var p3 = primes[c];
                        if (p3 <= p2) continue;
                        if (p1 + p2 + p3 > minSum) break;
                        if (!TestPair(p1, p3) || !TestPair(p2, p3)) continue;

                        for (int d = primes.IndexOf(13); d < numPrimes; d++)
                        {
                            var p4 = primes[d];
                            if (p4 <= p3) continue;
                            if (p1 + p2 + p3 + p4 > minSum) break;
                            if (!TestPair(p1, p4) || !TestPair(p2, p4) || !TestPair(p3, p4)) continue;

                            for (int e = primes.IndexOf(17); e < numPrimes; e++)
                            {
                                var p5 = primes[e];
                                if (p5 <= p4) continue;
                                var testSum = p1 + p2 + p3 + p4 + p5;
                                if (testSum > minSum) break;
                                if (!TestPair(p1, p5) || !TestPair(p2, p5) || !TestPair(p3, p5) || !TestPair(p4, p5)) continue;

                                // By the time we get here, the five primes meet all the criteria
                                minSum = testSum;
                                string tupleFormatted = FormatQuintuple(p1, p2, p3, p4, p5);
                                Console.WriteLine($"Found one!!! {tupleFormatted}");
                                tuples.Add(tupleFormatted, new List<long> { p1, p2, p3, p4, p5 });
                            }
                        }
                    }
                }
            }
            return tuples;
        }

        private static bool TestPair(long p1, long p2)
        {
            return RoyUtils.IsPrime(long.Parse($"{p1}{p2}")) && RoyUtils.IsPrime(long.Parse($"{p2}{p1}"));
        }

        private static string FormatQuintuple(long i, long j, long k, long c, long d)
        {
            return $"({i}, {j}, {k}, {c}, {d})";
        }

        private static List<long> MakeReferencePrimes(int startPrime, int numberOfReferencePrimes)
        {
            int primeCandidate = startPrime;
            List<long> toReturn = new List<long>();
            //string[] primes = new string[numberOfReferencePrimes];
            int numPrimes = 0;
            while(numPrimes < numberOfReferencePrimes)
            {
                if (RoyUtils.IsPrime(primeCandidate))
                {
                    toReturn.Add(primeCandidate);
                    //primes[numPrimes] = primeCandidate.ToString();
                    numPrimes++;
                }
                primeCandidate += 2;
            }
            //string csv = string.Join(',', primes);
            return toReturn;
        }

        private static List<long> MakeReferencePrimes(string primesFile, int startPrime = START_PRIME_DEFAULT, int numberOfReferencePrimes = NUM_REF_PRIMES_DEFAULT)
        {
            List<long> toReturn = new List<long>();
            string filePath = $"{REF_PRIMES_FILE_PATH}{primesFile}";
            try
            {
                Console.Write($"Reading {primesFile} to get the reference primes...");
                var primesString = File.ReadAllText(filePath);
                var primesToParse = primesString.Split(',');
                foreach (var primeStr in primesToParse)
                {
                    bool parsed = int.TryParse(primeStr, out int prime);
                    if (!parsed)
                    {
                        throw new Exception();
                    }
                    toReturn.Add(prime);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"\nException reading from reference primes file {filePath}. Building list manually.\n{ex}");
                return MakeReferencePrimes(START_PRIME_DEFAULT, NUM_REF_PRIMES_DEFAULT);
            }

            Console.WriteLine("DONE");
            return toReturn;
        }
    }
}
>>>>>>> d9c22b24cd5f796081e701d17bd92728397709b4
