/**
 * @author Phoenix Boisnier
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds methods that execute python code.
 */
public class AnalyzeThis {

    /**
     * This returns the results from the sentiment analysis of the list of emails.
     * @param emails The list of emails to be analyzed.
     * @param threadID The number appended to the end of the file.txt. Used for concurrency.
     * @return A double[][][] array of emails' sentences' doubles for neg, neu, pos, and compound for the list of emails.
     */
    public static double[][][] sentimize(String[] emails, long threadID){
        ArrayList<ArrayList<ArrayList<Double>>> retVal = new ArrayList<>();

        //This section creates the file and enciphers it.

        File pyIn = new File("pythonInput"+threadID+".txt");
        try{
            pyIn.createNewFile();
            FileWriter write = new FileWriter(pyIn);
            for (String email : emails) {
                //First we apply a caesar shift of 10 to our email.
                String[] param1 = {email, "10"};
                String ciph1 = Cipherer.encipher(0, param1);
                //Then we apply a vignere cipher with keyword systemic to the caesar shifted text.
                String[] param2 = {ciph1, "systemic"};
                String ciph2 = Cipherer.encipher(2, param2);
                write.write(ciph2 + "\n");
                write.flush();
            }
            write.close();
        }
        catch(IOException e) {
            System.out.println("Oh no!");
        }

        //This section feeds the python code the enciphered .txt file and demands the sentiment results.

        String filePath = pyIn.getPath();

        try {
            //TODO Replace the command string with the final pathname for the sentiment analyzer, SentimentPerEmail.py.
            Process p = Runtime.getRuntime().exec("python C:/Users/pbois/PycharmProjects/Sentiment/SentimentPerEmail.py "
                    + filePath + threadID);
            //TODO Replace the string for the file's path with the permanent pathname.
            File input = new File("C:/Users/pbois/PycharmProjects/Sentiment/output"+threadID+".txt");
            Scanner scone = new Scanner(input);
            while(scone.hasNext()){
                Scanner cone = new Scanner(scone.nextLine());
                ArrayList<ArrayList<Double>> scoresPerSentence = new ArrayList<>();
                while(cone.hasNext()){
                    ArrayList<Double> scores = new ArrayList<>();
                    scores.add(Double.parseDouble(cone.next()));
                    scores.add(Double.parseDouble(cone.next()));
                    scores.add(Double.parseDouble(cone.next()));
                    scores.add(Double.parseDouble(cone.next()));
                    scoresPerSentence.add(scores);
                }
                retVal.add(scoresPerSentence);
            }
            p.destroy();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oh no.");
        }

        //Get your fresh, hot results here.

        return AnalyzeThis.processResults(retVal);
    }

    /**
     * Used to turn the return value of sentimize into an array.
     * @param results These are the array list results to be processed.
     * @return The double[][][] array.
     */
    private static double[][][] processResults(ArrayList<ArrayList<ArrayList<Double>>> results){
        double[][][] retVal = new double[results.size()][][];
        for(int q = 0; q < results.size(); q++){
            retVal[q] = new double[results.get(q).size()][4];
            for(int w = 0; w < results.get(q).size(); w++){
                for(int e = 0; e < 4; e++){
                    retVal[q][w][e] = results.get(q).get(w).get(e);
                }
            }
        }
        return retVal;
    }

}