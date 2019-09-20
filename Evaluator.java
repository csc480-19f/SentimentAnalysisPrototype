package analysis;

import com.stanford_nlp.SentimentAnalyzer.Analyze;

import java.io.File;
// import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Evaluator {

    public static void main(String[] args) {
        File input = new File("input.txt");
        File output = new File("analyzerOutput.txt");
        try{
     //       output.createNewFile();
    //        FileWriter write = new FileWriter(output);
            Scanner scone = new Scanner(input);
            Analyze analyze = new Analyze();
            System.out.println("Files built.");
            int len = 0;
            System.out.println("Counting lines...");
            while(scone.hasNext()){
                scone.nextLine();
                len++;
            }
        //    System.out.println("Building sentiment...");
       //     String[] texts = new String[len];
       //     scone.close();
       //     scone = new Scanner(input);
        //    len = 0;
       //     System.out.println("Arranging lines...");
       //     while(scone.hasNext()){
        //        texts[len] = scone.nextLine();
        //        len++;
        //    }
        //    System.out.println("Sentimizing...");
       //     long begin = System.currentTimeMillis();
        //    double[] scores;
        //    for(int q = 0; q < 19; q++){
         //       scores = analyze.sentiment(texts);
        //    }
        //    scores = analyze.sentiment(texts);
        //    long end = System.currentTimeMillis();
        //    System.out.println("Writing to file...");
       //     for(int q = 0; q < scores.length; q++){
       //         write.write(scores[q]+"\n");
        //    }
            System.out.println("Finished processing.");
        //    write.flush();
        //    write.close();
            scone.close();
            System.out.println("Preparing files...");
            Scanner scine = new Scanner(System.in);
            System.out.println("Human file name?");
            String humanName = scine.nextLine();
            File human = new File(humanName);
            scone = new Scanner(human);
            Scanner scune = new Scanner(output);
            int scorePercent = 0;
            int pythonScores = 0;
            Scanner pScan = new Scanner(input);
            String[] emails = new String[50];
            int eIndex = 0;
            while(pScan.hasNext()){
                emails[eIndex] = pScan.nextLine();
                eIndex++;
            }
            ArrayList<ArrayList<ArrayList<Double>>> zults = AnalyzeThis.sentimize(emails);
            double[] compS = new double[50];
            for(int q = 0; q < 50; q++){
                double compTotal = 0.0;
                for(int w = 0; w < zults.get(q).size(); w++){
                    compTotal += zults.get(q).get(w).get(3);
                }
                compS[q] = (compTotal / (zults.get(q).size()+0.0));
            }
            System.out.println("Comparing results...");
            for(int q = 0; q < 50; q++){
                int huzu = Integer.parseInt(scone.nextLine());
                double dhuzu = (0.0+huzu);
                if(dhuzu==Double.parseDouble(scune.nextLine())){
                    scorePercent += 2;
                }
                if(compS[q] >= -1 && compS[q] < -.6){
                    if(huzu==0){
                        pythonScores += 2;
                    }
                }
                if(compS[q] >= -.6 && compS[q] < -.2){
                    if(huzu==1){
                        pythonScores += 2;
                    }
                }
                if(compS[q] >= -.2 && compS[q] < .2){
                    if(huzu==2){
                        pythonScores += 2;
                    }
                }
                if(compS[q] >= .2 && compS[q] < .6){
                    if(huzu==3){
                        pythonScores += 2;
                    }
                }
                if(compS[q] >= .6 && compS[q] <= 1){
                    if(huzu==4){
                        pythonScores += 2;
                    }
                }
            }
            scone.close();
            scune.close();
            System.out.println("Percent equal java: "+scorePercent+"%");
            System.out.println("Percent equal pyth: "+pythonScores+"%");
            System.out.println("Human 1?");
            scone = new Scanner(System.in);
            humanName = scone.nextLine();
            File human1 = new File(humanName);
            scune = new Scanner(human1);
            System.out.println("Human 2?");
            scone = new Scanner(System.in);
            humanName = scone.nextLine();
            File human2 = new File(humanName);
            scine = new Scanner(human2);
            int agreesOn = 0;
            for(int q = 0; q < len; q++){
                if(Integer.parseInt(scune.nextLine())==Integer.parseInt(scine.nextLine())){
                    agreesOn += 2;
                }
            }
            System.out.println("Agrees on: "+agreesOn+"%");
        //    System.out.println("Time elapsed during sentiment (1000 lines): "+((end-begin)/1000));

            String[] emailList = new String[1000];
            Scanner emailListS = new Scanner(new File("1000-emails-fixed.txt"));
            for(int q = 0; q < 1000; q++){
                emailList[q] = emailListS.nextLine();
            }
            System.out.println("Timing: Python...");
            long thisBegin = System.currentTimeMillis();
            AnalyzeThis.sentimize(emailList);
            long thisEnd = System.currentTimeMillis();
            System.out.println("Timing: Java... ");
            long analBegin = System.currentTimeMillis();
            analyze.sentiment(emailList);
            long analEnd = System.currentTimeMillis();
            System.out.println();
            System.out.println("Java: "+((analEnd-analBegin)/1000)+", Python: "+((thisEnd-thisBegin)/1000));


        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Oh no!");
        }

    }

}
