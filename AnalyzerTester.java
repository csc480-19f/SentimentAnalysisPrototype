/**
 * This class shows an example of a typical usage of RunsPython.sentimize(String[] emails).
 * Also, it demonstrates how to use AnalyzeThis.sentimize(String[] emails).
 */
public class AnalyzerTester {

    public static void main(String[] args){
        String test = "This is the test string. It has stuff. Feel free to add more. It would make me so very happy.";
        String[] emails = {test};
        double[] zults = RunsPython.sentimize(emails);
        for(double d : zults){
            //To note; the resulting array from RunsPython.sentimize(emails) is in the order of:
            //neg, neu, pos, compound
            System.out.println("Zults: "+d);
        }
        double[][][] zults3 = AnalyzeThis.sentimize(emails, 0);
        for(double[][] q : zults3){
            for(double[] w : q){
                for(double d : w){
                    System.out.print(d+" ");
                }
                System.out.print(": ");
            }
            System.out.println();
        }
    }

}
