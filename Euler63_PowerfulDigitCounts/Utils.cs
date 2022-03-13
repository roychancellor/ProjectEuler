using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Euler63_PowerfulDigitCounts
{
    public static class Utils
    {
        public static int NumberOfDigits(double ofValue)
        {
            return (int)Math.Log10(ofValue) + 1;
        }
    }
}
