using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectEuler65_ConvergentsOfe
{
    public class Convergent
    {
        public int LeadingCoeff { get; set; }
        
        // For any computed coefficients, put any non-positive number (zero or negative) in its place in the list of periodic coefficients.
        // For example, if the pattern is 1, 2k, 1, then make this list 1, 0, 1.
        public List<int> PeriodicCoeffs { get; set; }
        
        // For coefficients that can be generated based on their position in the period,
        // the dictionary key is the position in the period and the value is a function
        // that computes the coefficient based on a passed-in index.
        // For example, suppose the periodic coefficients are 1, 2k, 1, where k = 1, 2, 3, ...
        // then the Dictionary entry would be (1, ComputeCoeffDelegate) where ComputeCoeffDelegate
        // is a Func<int, int>
        public Dictionary<int, Func<int, int>> CoefficientGenerators { get; set; }
    }
}
