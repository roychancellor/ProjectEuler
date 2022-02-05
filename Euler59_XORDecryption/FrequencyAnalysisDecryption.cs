<<<<<<< HEAD
using System;
using System.Collections.Generic;

namespace Euler59_XORDecryption
{
    public class FrequencyAnalysisDecryption
    {
        public static void DecryptAndComputeSum()
        {
            List<int> cipherNumbers = Utilities.GetCipherNumbers();
            
            Dictionary<int, int> counts = Utilities.MakeCountsByNumber(cipherNumbers);
            
            // Find the most common number in the cipher text
            int mostCommonNumber = Utilities.FindMostCommonNumber(counts);
            
            // Find the second most common number
            counts.Remove(mostCommonNumber);
            int secondMostCommonNumber = Utilities.FindMostCommonNumber(counts);
            
            // Find the third most common number
            counts.Remove(secondMostCommonNumber);
            int thirdMostCommonNumber = Utilities.FindMostCommonNumber(counts);

            // Assume the most common character in the plain text is ' ' (space) (ASCII 32)
            // Assume the second most common character is 'e' (ASCII 101) based on the English language letter frequency
            // Assume the third most common character is 'a' (ASCII 97)
            // XOR each of the three most common numbers in the cipher text with the three most common plain text characters
            // to get the most probable keys. Exclude any potential keys that are not a-z.
            int[] mostCommonCipherValues = new[] { mostCommonNumber, secondMostCommonNumber, thirdMostCommonNumber };
            int[] mostCommonPlainTextValues = new[] { (int)' ', (int)'e', (int)'a' };
            List<int> possibleKeys = new List<int>();
            foreach (var cipherValue in mostCommonCipherValues)
            {
                foreach (var plainTextValue in mostCommonPlainTextValues)
                {
                    var possibleKey = cipherValue ^ plainTextValue;
                    // The problem statement said the key would be a lower case character
                    if ((char)possibleKey < 'a' || (char)possibleKey > 'z')
                    {
                        continue;
                    }
                    possibleKeys.Add(possibleKey);
                    break;
                }
            }

            Console.WriteLine($"\nThere are {possibleKeys.Count} possible key characters that would make {possibleKeys.Count}! permutations");

            // Then, apply each of the six permutations of the three possible key values to the cipher text
            // to see which one (if any) makes English.
            if (possibleKeys.Count != Utilities.KEY_LENGTH)
            {
                Console.WriteLine("There are not enough keys to continue. Ending program.");
                return;
            }
            Dictionary<int, List<int>> keyPermut = new Dictionary<int, List<int>>();
            keyPermut.Add(1, new List<int>{ possibleKeys[0], possibleKeys[1], possibleKeys[2] });
            keyPermut.Add(2, new List<int>{ possibleKeys[0], possibleKeys[2], possibleKeys[1] });
            keyPermut.Add(3, new List<int>{ possibleKeys[1], possibleKeys[0], possibleKeys[2] });
            keyPermut.Add(4, new List<int>{ possibleKeys[1], possibleKeys[2], possibleKeys[0] });
            keyPermut.Add(5, new List<int>{ possibleKeys[2], possibleKeys[0], possibleKeys[1] });
            keyPermut.Add(6, new List<int>{ possibleKeys[2], possibleKeys[1], possibleKeys[0] });
            
            string thePlainText = string.Empty;
            bool keyFound = false;
            int index = 0;
            for (int i = 1; i <= 6; i++)
            {
                keyFound = Utilities.MakePlainTextFromCipherText(keyPermut[i], cipherNumbers, out string plainText);
                if (keyFound)
                {
                    thePlainText = plainText;
                    index = i;
                    break;
                }
            }

            Console.WriteLine($"\nThe key was found: {keyFound}");
            if (keyFound)
            {
                Console.WriteLine($"\nThe key: {(char)keyPermut[index][0]}{(char)keyPermut[index][1]}{(char)keyPermut[index][2]}");

                Console.WriteLine($"\nThe plain text:\n{thePlainText}");

                int sum = Utilities.ComputeSumOfPlainTextAscii(thePlainText);
                Console.WriteLine($"\nThe sum of the plain text ASCII is: {sum}");
            }
        }
    }
=======
using System;
using System.Collections.Generic;

namespace Euler59_XORDecryption
{
    public class FrequencyAnalysisDecryption
    {
        public static void DecryptAndComputeSum()
        {
            List<int> cipherNumbers = Utilities.GetCipherNumbers();
            
            Dictionary<int, int> counts = Utilities.MakeCountsByNumber(cipherNumbers);
            
            // Find the most common number in the cipher text
            int mostCommonNumber = Utilities.FindMostCommonNumber(counts);
            
            // Find the second most common number
            counts.Remove(mostCommonNumber);
            int secondMostCommonNumber = Utilities.FindMostCommonNumber(counts);
            
            // Find the third most common number
            counts.Remove(secondMostCommonNumber);
            int thirdMostCommonNumber = Utilities.FindMostCommonNumber(counts);

            // Assume the most common character in the plain text is ' ' (space) (ASCII 32)
            // Assume the second most common character is 'e' (ASCII 101) based on the English language letter frequency
            // Assume the third most common character is 'a' (ASCII 97)
            // XOR each of the three most common numbers in the cipher text with the three most common plain text characters
            // to get the most probable keys. Exclude any potential keys that are not a-z.
            int[] mostCommonCipherValues = new[] { mostCommonNumber, secondMostCommonNumber, thirdMostCommonNumber };
            int[] mostCommonPlainTextValues = new[] { (int)' ', (int)'e', (int)'a' };
            List<int> possibleKeys = new List<int>();
            foreach (var cipherValue in mostCommonCipherValues)
            {
                foreach (var plainTextValue in mostCommonPlainTextValues)
                {
                    var possibleKey = cipherValue ^ plainTextValue;
                    // The problem statement said the key would be a lower case character
                    if ((char)possibleKey < 'a' || (char)possibleKey > 'z')
                    {
                        continue;
                    }
                    possibleKeys.Add(possibleKey);
                    break;
                }
            }

            Console.WriteLine($"\nThere are {possibleKeys.Count} possible key characters that would make {possibleKeys.Count}! permutations");

            // Then, apply each of the six permutations of the three possible key values to the cipher text
            // to see which one (if any) makes English.
            if (possibleKeys.Count != Utilities.KEY_LENGTH)
            {
                Console.WriteLine("There are not enough keys to continue. Ending program.");
                return;
            }
            Dictionary<int, List<int>> keyPermut = new Dictionary<int, List<int>>();
            keyPermut.Add(1, new List<int>{ possibleKeys[0], possibleKeys[1], possibleKeys[2] });
            keyPermut.Add(2, new List<int>{ possibleKeys[0], possibleKeys[2], possibleKeys[1] });
            keyPermut.Add(3, new List<int>{ possibleKeys[1], possibleKeys[0], possibleKeys[2] });
            keyPermut.Add(4, new List<int>{ possibleKeys[1], possibleKeys[2], possibleKeys[0] });
            keyPermut.Add(5, new List<int>{ possibleKeys[2], possibleKeys[0], possibleKeys[1] });
            keyPermut.Add(6, new List<int>{ possibleKeys[2], possibleKeys[1], possibleKeys[0] });
            
            string thePlainText = string.Empty;
            bool keyFound = false;
            int index = 0;
            for (int i = 1; i <= 6; i++)
            {
                keyFound = Utilities.MakePlainTextFromCipherText(keyPermut[i], cipherNumbers, out string plainText);
                if (keyFound)
                {
                    thePlainText = plainText;
                    index = i;
                    break;
                }
            }

            Console.WriteLine($"\nThe key was found: {keyFound}");
            if (keyFound)
            {
                Console.WriteLine($"\nThe key: {(char)keyPermut[index][0]}{(char)keyPermut[index][1]}{(char)keyPermut[index][2]}");

                Console.WriteLine($"\nThe plain text:\n{thePlainText}");

                int sum = Utilities.ComputeSumOfPlainTextAscii(thePlainText);
                Console.WriteLine($"\nThe sum of the plain text ASCII is: {sum}");
            }
        }
    }
>>>>>>> d9c22b24cd5f796081e701d17bd92728397709b4
}