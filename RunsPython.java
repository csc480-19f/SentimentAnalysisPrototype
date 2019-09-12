import java.io.*;
import java.util.Scanner;

public class RunsPython {

    public static void main(String args[]) {

        // This part just lets us do more w/o editing data.
        System.out.println("Emails?");
        String eTot = new Scanner(System.in).nextLine();
        int emails = Integer.parseInt(eTot);
        System.out.println("Chars?");
        String cTot = new Scanner(System.in).nextLine();
        int c = Integer.parseInt(cTot);

        //TODO This would not exist in a final version.
        String emailText =  "\"Hello, " +
                "Now that the semester is under way, we wanted to take the opportunity to remind you of two unique opportunities for you and your students to meet and learn from some of the most trailblazing women in the video game industry—a ticketed discussion on October 3 and a student symposium on October 4. " +
                "This is a great opportunity for students engaged in writing, game design, music, creative and visual arts, or women’s studies to hear about what drives these prominent women to create. It will provide inspiration for the next generation of game makers and executives, score composers, writers, and gaming scholars. The speakers include Jen Taylor, the voice of Microsoft’s Cortana; Kiki Wolfkill, executive producer at 343 Studios and the Halo franchise; Robin Hunicke, CEO and Co-founder of independent San Francisco game studio Funomena; and Jacinda Chew, Studio Art Director at Insomniac Games, Telltale Studios. " +
                "Please see below for more information about these events. If you are interested in purchasing tickets for an entire class at a discounted group rate, please contact Lee Smith at 585-263-2700. " +
                "Women in Games: Create! Speaker Event and Reception" +
                "Thursday, October 3, 2019" +
                "6 to 9 p.m. " +
                "Celebrate the contributions of women to the electronic games industry, past and present, during the second annual Women in Games celebration on October 3. Enjoy an evening of discussions with female innovators and leaders from the video game industry. Tickets are $15 and may be purchased online or by calling Guest Information Services at 585-263-2700. Guests must present tickets at the museum admissions desk the day of the event. " +
                "Women in Games: Create! Student Symposium (Space Limited) " +
                "Friday, October 4, 2019 " +
                "9 a.m. to 4:30 p.m. " +
                "Age 17 and older " +
                "" +
                "Enjoy a unique opportunity to explore important topics with trailblazing women and today’s leaders in the electronic gaming industry. In small group settings, participants will discuss the gaming industry, the creative process, and how to chart a path to success. Registration is $40 and includes breakfast and lunch, a behind-the-scenes tour of The Strong’s collections, and the opportunity to visit the MAGIC game design center at Rochester Institute of Technology (RIT). " +
                "" +
                "We hope you will consider including these events in your curriculum and sharing these opportunities with your students! We look forward to seeing you and your students at The Strong this fall! " +
                "" +
                "Sincerely, " +
                "" +
                "Lisa M. Feinstein, " +
                "Co-Chair, Women in Games Initiative " +
                "Vice President for Institutional Advancement " +
                "The Strong " +
                "" +
                "Cathy DeBellis " +
                "(she/her/hers) " +
                "Director of Public Programs " +
                "The Strong " +
                "One Manhattan Square " +
                "Rochester, NY 14607 U.S.A. " +
                "585-410-6397 " +
                "CDeBellis@museumofplay.org " +
                "museumofplay.org " +
                "Twitter: @museumofplay " +
                "Facebook: @thestrongmuseum " +
                "The Strong is home to the International Center for the History of Electronic Games, National Toy Hall of Fame, World Video Game Hall of Fame, Brian Sutton-Smith Library and Archives of Play, Woodbury School, and the American Journal of Play.\"";

        //TODO The resulting file here would be given to us in a method call or something in the final version.
        File pyIn = new File("pythonInput.txt");
        try{
            pyIn.createNewFile();
            FileWriter write = new FileWriter(pyIn);
            for(int q = 0; q < emails; q++){
                write.write(emailText+"\n");
                write.flush();
            }
            write.close();
        }
        catch(IOException e) {
            System.out.println("Oh no!");
        }

        //TODO This is where we begin to time, as this is what we care about timing.
        Long begin = System.currentTimeMillis();

        String filePath = pyIn.getPath();

        try {
            //TODO Replace the command string with the final pathname for the sentiment analyzer.
            Process p = Runtime.getRuntime().exec("python C:/Users/pbois/PycharmProjects/Sentiment/SentimentPrototype.py " + filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()), 8);
            //TODO This will either be the average of all the emails it just read, OR
            //TODO This could be a 'true' sent in saying it's time to read a file.
            System.out.println("Python output: "+in.readLine());
            p.destroy();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oh no.");
        }

        //TODO Delete from here to next TODO
        Long end = System.currentTimeMillis();
        /*
        Total / email.length() * 3150 = projected average time
         */
        double total = ((end-begin)/1000.0);
        System.out.println("Time for "+emails+" emails size "+ emailText.length()+":\n\t"+total+"s");
        //TODO Stop deleting now.
    }

}
