<<<<<<< HEAD
using System;
using System.Collections.Generic;
using System.Linq;

namespace Euler59_XORDecryption
{
    public class BruteForceDecryption
    {
        public static void DecryptAndComputeSum()
        {
            List<int> asciiNumbers = new List<int>();

            List<int> cipherNumbers = Utilities.GetCipherNumbers();

            int iterationsCompleted = 0;
            bool foundKey = false;
            string thePlainText = string.Empty;

            for (int i = Utilities.START_ASCII; i <= Utilities.END_ASCII && !foundKey; i++)
            {
                for (int j = Utilities.START_ASCII; j <= Utilities.END_ASCII && !foundKey; j++)
                {
                    for (int k = Utilities.START_ASCII; k <= Utilities.END_ASCII && !foundKey; k++)
                    {
                        iterationsCompleted++;
                        if (iterationsCompleted % 1000 == 0)
                        {
                            Console.WriteLine($"Iterations completed: {iterationsCompleted}");
                        }
                        
                        foundKey = Utilities.MakePlainTextFromCipherText(new List<int>{i, j, k}, cipherNumbers, out string plainText);
                        
                        if (foundKey)
                        {
                            Console.WriteLine($"Key: {(char)i}{(char)j}{(char)k} => {plainText}");
                            thePlainText = plainText;
                        }
                    }
                }
            }

            // Compute the sum of the ASCII values of the plain text
            int sum = Utilities.ComputeSumOfPlainTextAscii(thePlainText);
            Console.WriteLine($"\nThe sum is: {sum}");
        }
    }
=======
using System;
using System.Collections.Generic;
using System.Linq;

namespace Euler59_XORDecryption
{
    public class BruteForceDecryption
    {
        public static void DecryptAndComputeSum()
        {
            List<int> asciiNumbers = new List<int>();

            List<int> cipherNumbers = Utilities.GetCipherNumbers();

            int iterationsCompleted = 0;
            bool foundKey = false;
            string thePlainText = string.Empty;

            for (int i = Utilities.START_ASCII; i <= Utilities.END_ASCII && !foundKey; i++)
            {
                for (int j = Utilities.START_ASCII; j <= Utilities.END_ASCII && !foundKey; j++)
                {
                    for (int k = Utilities.START_ASCII; k <= Utilities.END_ASCII && !foundKey; k++)
                    {
                        iterationsCompleted++;
                        if (iterationsCompleted % 1000 == 0)
                        {
                            Console.WriteLine($"Iterations completed: {iterationsCompleted}");
                        }
                        
                        foundKey = Utilities.MakePlainTextFromCipherText(new List<int>{i, j, k}, cipherNumbers, out string plainText);
                        
                        if (foundKey)
                        {
                            Console.WriteLine($"Key: {(char)i}{(char)j}{(char)k} => {plainText}");
                            thePlainText = plainText;
                        }
                    }
                }
            }

            // Compute the sum of the ASCII values of the plain text
            int sum = Utilities.ComputeSumOfPlainTextAscii(thePlainText);
            Console.WriteLine($"\nThe sum is: {sum}");
        }
    }
>>>>>>> d9c22b24cd5f796081e701d17bd92728397709b4
}