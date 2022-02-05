<<<<<<< HEAD
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Euler59_XORDecryption
{
    public class Utilities
    {
        public static int START_ASCII = 97;
        public static int END_ASCII = 122;
        public static int KEY_LENGTH = 3;
        public static string CIPHER_NUMBER_SOURCE = "p059_cipher.txt";
        public static string[] commonWords = new[] { "the", "that", "also", "and" };

        public static string[] GetNumbersAsTextFromFile(string fromFilePath) 
        {
            try
            {
                var toReturn = File.ReadAllText(fromFilePath).Split(',');
                return toReturn;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Reading file {fromFilePath} threw an exception\n{ex}");
                return null;
            }
	    }
        
        public static string MakeStringFromListOfASCIINumbers(List<int> asciiNumbers)
        {
            StringBuilder sb = new StringBuilder();

            foreach (var ascii in asciiNumbers)
            {
                sb.Append((char)ascii);
            }

            return sb.ToString();
        }

        public static List<int> GetCipherNumbers()
        {
            List<int> cipherNumbers = new List<int>();

            var cipherTextAsAsciiNumbers = Utilities.GetNumbersAsTextFromFile(CIPHER_NUMBER_SOURCE);

            foreach (string str in cipherTextAsAsciiNumbers)
            {
                cipherNumbers.Add(int.Parse(str));
            }

            return cipherNumbers;
        }
        
        public static int FindMostCommonNumber(Dictionary<int, int> numberCounts) 
        {
            int mostCommon = -1;
            int maxCount = -1;
            
            foreach (int num in numberCounts.Keys)
            {
                if (numberCounts[num] > maxCount) 
                {
                    mostCommon = num;
                    maxCount = numberCounts[num];
                }
            }
            Console.WriteLine("\nThe most common number in the current list is: " +  mostCommon + " which occurs " + maxCount + " times");
            
            return mostCommon;
        }
        
        public static Dictionary<int, int> MakeCountsByNumber(List<int> numbers) 
        {
            Dictionary<int, int> counts = new Dictionary<int, int>();
            
            foreach (int num in numbers) {
                if (counts.ContainsKey(num)) 
                {
                    counts[num] += 1;
                } 
                else 
                {
                    counts.Add(num, 1);
                }
            }
            return counts;
        }
    
        public static bool MakePlainTextFromCipherText(List<int> keys, List<int> cipherNumbers, out string plainText)
        {
            string stringToTest = string.Empty;
            List<int> asciiNumbers = new List<int>();
            bool foundKey = false;
            plainText = string.Empty;
            
            for (int c = 0; c < cipherNumbers.Count; c += 3)
            {
                asciiNumbers.Add(cipherNumbers[c] ^ keys[0]);
                asciiNumbers.Add(cipherNumbers[c + 1] ^ keys[1]);
                asciiNumbers.Add(cipherNumbers[c + 2] ^ keys[2]);
                stringToTest = Utilities.MakeStringFromListOfASCIINumbers(asciiNumbers);
                if (!foundKey && Utilities.commonWords.Any(word => stringToTest.Contains($" {word} ")))
                {
                    foundKey = true;
                }
            }

            if (foundKey)
            {
                plainText = stringToTest;
            }

            return foundKey;
        }

        public static int ComputeSumOfPlainTextAscii(string plainText)
        {
            int sum = 0;
            foreach (char c in plainText)
            {
                sum += c;
            }
            return sum;
        }
    }
=======
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Euler59_XORDecryption
{
    public class Utilities
    {
        public static int START_ASCII = 97;
        public static int END_ASCII = 122;
        public static int KEY_LENGTH = 3;
        public static string CIPHER_NUMBER_SOURCE = "p059_cipher.txt";
        public static string[] commonWords = new[] { "the", "that", "also", "and" };

        public static string[] GetNumbersAsTextFromFile(string fromFilePath) 
        {
            try
            {
                var toReturn = File.ReadAllText(fromFilePath).Split(',');
                return toReturn;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Reading file {fromFilePath} threw an exception\n{ex}");
                return null;
            }
	    }
        
        public static string MakeStringFromListOfASCIINumbers(List<int> asciiNumbers)
        {
            StringBuilder sb = new StringBuilder();

            foreach (var ascii in asciiNumbers)
            {
                sb.Append((char)ascii);
            }

            return sb.ToString();
        }

        public static List<int> GetCipherNumbers()
        {
            List<int> cipherNumbers = new List<int>();

            var cipherTextAsAsciiNumbers = Utilities.GetNumbersAsTextFromFile(CIPHER_NUMBER_SOURCE);

            foreach (string str in cipherTextAsAsciiNumbers)
            {
                cipherNumbers.Add(int.Parse(str));
            }

            return cipherNumbers;
        }
        
        public static int FindMostCommonNumber(Dictionary<int, int> numberCounts) 
        {
            int mostCommon = -1;
            int maxCount = -1;
            
            foreach (int num in numberCounts.Keys)
            {
                if (numberCounts[num] > maxCount) 
                {
                    mostCommon = num;
                    maxCount = numberCounts[num];
                }
            }
            Console.WriteLine("\nThe most common number in the current list is: " +  mostCommon + " which occurs " + maxCount + " times");
            
            return mostCommon;
        }
        
        public static Dictionary<int, int> MakeCountsByNumber(List<int> numbers) 
        {
            Dictionary<int, int> counts = new Dictionary<int, int>();
            
            foreach (int num in numbers) {
                if (counts.ContainsKey(num)) 
                {
                    counts[num] += 1;
                } 
                else 
                {
                    counts.Add(num, 1);
                }
            }
            return counts;
        }
    
        public static bool MakePlainTextFromCipherText(List<int> keys, List<int> cipherNumbers, out string plainText)
        {
            string stringToTest = string.Empty;
            List<int> asciiNumbers = new List<int>();
            bool foundKey = false;
            plainText = string.Empty;
            
            for (int c = 0; c < cipherNumbers.Count; c += 3)
            {
                asciiNumbers.Add(cipherNumbers[c] ^ keys[0]);
                asciiNumbers.Add(cipherNumbers[c + 1] ^ keys[1]);
                asciiNumbers.Add(cipherNumbers[c + 2] ^ keys[2]);
                stringToTest = Utilities.MakeStringFromListOfASCIINumbers(asciiNumbers);
                if (!foundKey && Utilities.commonWords.Any(word => stringToTest.Contains($" {word} ")))
                {
                    foundKey = true;
                }
            }

            if (foundKey)
            {
                plainText = stringToTest;
            }

            return foundKey;
        }

        public static int ComputeSumOfPlainTextAscii(string plainText)
        {
            int sum = 0;
            foreach (char c in plainText)
            {
                sum += c;
            }
            return sum;
        }
    }
>>>>>>> d9c22b24cd5f796081e701d17bd92728397709b4
}