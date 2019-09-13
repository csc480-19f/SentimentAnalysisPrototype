/**
 * @author Phoenix Boisnier
 */

import java.io.*;
import java.util.Scanner;

/**
 * This class holds methods that execute python code.
 */
public class RunsPython {

    /**
     * This returns the results from the sentiment analysis of the list of emails.
     * @param emails The list of emails to be analyzed.
     * @return A double array containing averaged doubles for neg, neu, pos, and compound for the list of emails.
     */
    public static double[] sentimize(String[] emails){
        double[] retVal = new double[4];

        //This section creates the file and enciphers it.

        File pyIn = new File("pythonInput.txt");
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
            //TODO Replace the command string with the final pathname for the sentiment analyzer, SentimentPrototype.py.
            Process p = Runtime.getRuntime().exec("python C:/Users/pbois/PycharmProjects/Sentiment/SentimentPrototype.py " + filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()), 8);
            String vals = in.readLine();
            Scanner scone = new Scanner(vals);
            int retValInd = 0;
            while(scone.hasNext()){
                retVal[retValInd] = Double.parseDouble(scone.next());
                retValInd++;
            }
            p.destroy();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oh no.");
        }

        //Get your fresh, hot results here.

        return retVal;
    }

}
