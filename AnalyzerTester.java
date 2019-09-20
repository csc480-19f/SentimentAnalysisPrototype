import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
        double[][][] zults3 = AnalyzeThis.sentimize(emails);
        for(double[][] q : zults3){
            for(double[] w : q){
                for(double d : w){
                    System.out.print(d+" ");
                }
                System.out.print(": ");
            }
            System.out.println();
        }
        System.out.println(AnalyzeThis.averageNeg(zults3));
        System.out.println(AnalyzeThis.averageNeu(zults3));
        System.out.println(AnalyzeThis.averagePos(zults3));
        System.out.println(AnalyzeThis.averageCompound(zults3));
        String[] emailList = new String[1000];
        Scanner scone;
        try{
            scone =new Scanner(new File("emails.txt"));
          for(int q = 0; q < 1000; q++){
              emailList[q] = scone.nextLine();
          }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        long begin = System.currentTimeMillis();
        AnalyzeThis.sentimize(emailList);
        long end = System.currentTimeMillis();
        System.out.println("1000 emails time: "+((end-begin)/1000));
    }

}
