/**
 * This class shows an example of a typical usage of RunsPython.sentimize(String[] emails)
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
    }

}
