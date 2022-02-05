namespace Euler60_PrimePairSets
{
    public class ClassRunner
    {
        public static void Main(string[] args)
        {
            //string[] argsToTest = new[] { "--s", "3", "--n", "1000000", "--t", "5", "--i", "3000" };
            string[] argsToTest = new[] { "--help" };
            PrimePairSets.Run(argsToTest);
        }
    }
}
