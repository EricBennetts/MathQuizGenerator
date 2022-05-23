
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Marker {
    static String getMyAnsInQuiz(String x) {
        Scanner line = new Scanner(x);
        String items = line.next();
        while (!items.equals("="))
            items = line.next();
        // If there is no answer, mark it as "NO"
        try {
            items = line.next();
        } catch (NoSuchElementException no) {
            return "NO";
        }
        // Only check two decimal places
        return String.format("%.2f", Float.parseFloat(items));
    }
    static String getRealAns(String x) {
        Scanner line = new Scanner(x);
        String items = line.next();
        while (!items.equals("="))
            items = line.next();
        try {
            items = line.next();
        } catch (NoSuchElementException no) {
            return "NOANS";
        }
        return String.format("%.2f", Float.parseFloat(items));
    }
    static int finalLine(String myAns, String RealAns) {
        if (myAns.equals(RealAns))
            return 0;
        if (myAns.equals("NO"))
            return 1;
        if (RealAns.equals("NOANS"))
            return 2;
        // If not equal, return -1
        return -1;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner ansInput = new Scanner(new File("Answer.txt"));
        Scanner reqInput = new Scanner(new File("Requirements.txt"));
        Scanner myAnsInput = new Scanner(new File("MyAnswer.txt"));
        int quizNum = Integer.parseInt(reqInput.nextLine().substring(36));
        int i;
        String[] result = new String[quizNum];
        for (i = 0; i < quizNum; i++) {
            result[i] = myAnsInput.nextLine();
            int id = finalLine(getMyAnsInQuiz(result[i]), getRealAns(ansInput.nextLine()));
            if (id == 0)
                result[i] += " " + "CORRECT";
            else if (id == 1)
                result[i] += " " + "NOT ANSWERED!";
            else if (id == 2)
                result[i] += " " + "ANSWER NOT GIVEN!";
            else
                result[i] += " " + "WRONG";
        }
        PrintWriter toFinalPaper = new PrintWriter("FinalPaper.txt");
        for (i = 0; i < quizNum; i++)
            toFinalPaper.printf(result[i] + "\n");
        ansInput.close(); reqInput.close(); myAnsInput.close(); toFinalPaper.close();
    }
}
