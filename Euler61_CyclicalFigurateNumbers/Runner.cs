using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler61_CyclicalFigurateNumbers
{
    public class Runner
    {
        private static int sizeOfLists;
        private const int SIZE_OF_LISTS_DEFAULT = 100;
        private const string RUN_LOG_PATH = @"C:\dev.net\ProjectEuler\Euler61_CyclicalFigurateNumbers\RunLogs";
        private const string RUN_LOG_FILE_NAME = "Euler61_RunLog_{0}.log";
        private static Stopwatch _sw = new Stopwatch();
        
        public static void Run(string[] args)
        {
            sizeOfLists = SIZE_OF_LISTS_DEFAULT;
            if (args.Length >= 2 && args[0].Contains("--n"))
            {
                if (!int.TryParse(args[1], out sizeOfLists))
                {
                    Console.WriteLine($"The argument {args[0]} {args[1]} is invalid. It must be --n <valid integer>. Exiting...");
                    return;
                }
            }
            
            // Make the lists of figurate numbers for reference. Each list starts with the first figurate number >= 1000 for each type.
            NumberUtils.PopulateNumberLists(sizeOfLists);

            //FindCyclicFigurateThree();
            _sw.Start();
            FindCyclicFigurateSix();
            _sw.Stop();
            Console.WriteLine($"The elapsed time is: {_sw.Elapsed.TotalSeconds} seconds ({_sw.Elapsed.TotalMinutes} min or {_sw.Elapsed.TotalHours} hr)");
        }

        public static void FindCyclicFigurateSix()
        {
            var fd = NumberUtils.FigurateData;
            int val;
            var firstTwos = new HashSet<string>();
            var lastTwos = new HashSet<string>();
            var toCheck = new List<int>() { 0, 0, 0, 0, 0, 0 };
            RunLogConfig runLogConfig = new RunLogConfig
            {
                RunLogPath = RUN_LOG_PATH,
                RunLogFileName = string.Format(RUN_LOG_FILE_NAME, DateTime.UtcNow.Ticks)
            };

            for (int tri = fd[3].Values.Count - 1; tri >= 0; tri--)
            {
                val = fd[3].Values[tri];
                toCheck[0] = val;
                for (int sq = fd[4].Values.Count - 1; sq >= 0; sq--)
                {
                    val = fd[4].Values[sq];
                    toCheck[1] = val;
                    var permut = $"Permut: t {tri} s {sq}";
                    Console.WriteLine($"{permut}; elapsed: {_sw.Elapsed.TotalSeconds} sec");
                    NumberUtils.Log(runLogConfig, permut);
                    for (int pent = fd[5].Values.Count - 1; pent >= 0; pent--)
                    {
                        val = fd[5].Values[pent];
                        toCheck[2] = val;
                        for (int hex = fd[6].Values.Count - 1; hex >= 0; hex--)
                        {
                            val = fd[6].Values[hex];
                            toCheck[3] = val;
                            for (int hept = fd[7].Values.Count - 1; hept >= 0; hept--)
                            {
                                val = fd[7].Values[hept];

                                toCheck[4] = val;
                                for (int oct = fd[8].Values.Count - 1; oct >= 0; oct--)
                                {
                                    val = fd[8].Values[oct];
                                    toCheck[5] = val;

                                    firstTwos.Clear();
                                    lastTwos.Clear();
                                    foreach (var toAdd in toCheck)
                                    {
                                        firstTwos.Add(NumberUtils.FirstTwo(toAdd));
                                        lastTwos.Add(NumberUtils.LastTwo(toAdd));
                                    }

                                    var shouldCheck = NumberUtils.ShouldCheckIfCyclicFigurate(firstTwos, lastTwos);
                                    if (!shouldCheck) continue;
                                    var isCyclicFigurate = NumberUtils.IsCyclicFigurate(toCheck, out List<int> resultList);

                                    if (isCyclicFigurate)
                                    {
                                        Console.WriteLine($"FOUND IT!!!");
                                        Console.WriteLine($"The numbers (in order) are: {NumberUtils.PrintList(resultList)}");
                                        Console.Write($"The corresponding polygonal sides are: ");
                                        for (int i = 0; i < 6; i++)
                                        {
                                            Console.Write($"P{toCheck.IndexOf(resultList[i]) + 3} ");
                                        }
                                        Console.WriteLine($"\nTheir sum is: {Enumerable.Sum(resultList)}");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public static void FindCyclicFigurateThree()
        {
            var fd = NumberUtils.FigurateData;
            int val;
            var firstTwos = new HashSet<string>();
            var lastTwos = new HashSet<string>();
            var toCheck = new List<int>() { 0, 0, 0 };
            RunLogConfig runLogConfig = new RunLogConfig
            {
                RunLogPath = RUN_LOG_PATH,
                RunLogFileName = string.Format(RUN_LOG_FILE_NAME, DateTime.UtcNow.Ticks)
            };

            for (int tri = fd[3].Values.Count - 1; tri >= 0; tri--)
            {
                val = fd[3].Values[tri];
                toCheck[0] = val;
                for (int sq = fd[4].Values.Count - 1; sq >= 0; sq--)
                {
                    val = fd[4].Values[sq];
                    toCheck[1] = val;
                    var permut = $"Permut: t {tri} s {sq}";
                    Console.WriteLine(permut);
                    NumberUtils.Log(runLogConfig, permut);
                    for (int pent = fd[5].Values.Count - 1; pent >= 0; pent--)
                    {
                        val = fd[5].Values[pent];
                        toCheck[2] = val;
                        
                        firstTwos.Clear();
                        lastTwos.Clear();
                        foreach (var toAdd in toCheck)
                        {
                            firstTwos.Add(NumberUtils.FirstTwo(toAdd));
                            lastTwos.Add(NumberUtils.LastTwo(toAdd));
                        }

                        var shouldCheck = NumberUtils.ShouldCheckIfCyclicFigurateThree(firstTwos, lastTwos);
                        if (!shouldCheck) continue;
                        var isCyclicFigurate = NumberUtils.IsCyclicFigurateThree(toCheck);

                        if (isCyclicFigurate)
                        {
                            Console.WriteLine($"FOUND IT!!!");
                            NumberUtils.PrintSets(firstTwos, lastTwos);
                            Console.WriteLine($"\nThe numbers are: {NumberUtils.PrintList(toCheck)}");
                            Console.WriteLine($"Their sum is: {Enumerable.Sum(toCheck)}");
                            return;
                        }
                    }
                }
            }
        }
    }
}
