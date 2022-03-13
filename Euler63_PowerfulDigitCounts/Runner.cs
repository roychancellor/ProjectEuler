using System;

namespace Euler63_PowerfulDigitCounts
{
    public class Runner
    {
        public static void Run(string[] args)
        {
            int countOfPowers = 0;
            for (int n = 1; n < 22; n++)
            {
                Console.WriteLine($"n = {n}");
                for (int x = 1; x < 10; x++)
                {
                    var power = Math.Pow(x, n);
                    var digits = Utils.NumberOfDigits(power);
                    if (digits == n)
                    {
                        var toAdd = 10 - x;
                        Console.WriteLine($"{x}^{n} = {power} which has {digits} digits meets the condition. Adding {toAdd} to the count.");
                        countOfPowers += toAdd;
                        break;
                    }
                }
            }

            Console.WriteLine($"FINISHED!!! The number of n-digit positive integers that are also an nth power is {countOfPowers}");
        }
    }
}
