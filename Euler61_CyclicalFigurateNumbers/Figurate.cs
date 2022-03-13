using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler61_CyclicalFigurateNumbers
{
    public class Figurate
    {
        public List<int> Values { get; set; }
        public HashSet<string> FirstTwoDigits { get; set; }
        public HashSet<string> LastTwoDigits { get; set; }

        public Figurate()
        {
            Values = new List<int>();
            FirstTwoDigits = new HashSet<string>();
            LastTwoDigits = new HashSet<string>();
        }

        public bool ValueToDigitSets(int value)
        {
            if (value < 1000)
            {
                return false;
            }

            var result = NumberUtils.Split(value, out string firstTwo, out string lastTwo);
            FirstTwoDigits.Add(firstTwo);
            if (int.Parse(lastTwo) < 10)
            {
                return result;
            }
            LastTwoDigits.Add(lastTwo);

            return result;
        }
    }
}
