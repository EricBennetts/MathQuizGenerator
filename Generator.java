
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Generator {
    public static int generateInt(int x) {
        if (x == 1)
            return 1 + (int)(Math.random() * 101);
        else if (x == 0)
            return 1 + (int)(Math.random() * 501);
        return 0;
    }

    public static float generateFloat(int x) {
        if (x == 1)
            return (float)Math.random() * 101;
        else
            return (float)Math.random() * 501;
    }

    public static int generateDenominator() {
        // Denominator is ranges in [2, 9]
        return 2 + (int)(Math.random() * 8);
    }

    public static int determineType() {
        return (int)(Math.random() * 2);
    }

    public static int determineIntOrFloat() {
        return (int)(Math.random() * 2);
    }

    public static char determineOperator() {
        int i = (int)(Math.random() * 4);
        if (i == 0)
            return '+';
        else if (i == 1)
            return '-';
        else if (i == 2)
            return '*';
        else
            return '/';
    }

    public static String generateMixedNumber(int x) {
        int numerator = generateDenominator(), denominator = generateDenominator();
        while (denominator <= numerator) {
            denominator = generateDenominator();
            numerator = generateDenominator();
        }
        return generateInt(x) + "_" + numerator + "|" + denominator;
    }

    public static String[] generateAllTypes(int num, String s2) {
        String[] questions = new String[num];
        int i, j;
        for (i = 0; i < num; i++)
            questions[i] = "";
        int within100 = Integer.parseInt(String.valueOf(s2.charAt(62)));
        // 62 is where the 0 or 1 lies for determining if the operands lies within [0,100]
        for (i = 0; i < num; i++) {
            // The first question will only contain integer arithmetic
            if (i == 0) {
                int o1 = generateInt(within100), o2 = generateInt(within100);
                questions[i] = String.valueOf(o1);
                questions[i] += " " + determineOperator() + " " + o2;
            }
            // The second question will only contain fraction arithmetic
            else if (i == 1) {
                questions[i] = generateMixedNumber(within100);
                questions[i] += " " + determineOperator() + " " +generateMixedNumber(within100);
            }
            // The third question will include floating point arithmetic
            else if (i == 2) {
                String[] eachItem = new String[i + 1];
                eachItem[0] = String.valueOf(generateInt(within100));
                eachItem[1] = String.valueOf(generateFloat(within100));
                eachItem[2] = generateMixedNumber(within100);
                // Change the order
                Collections.shuffle(Arrays.asList(eachItem));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
            else {
                String[] eachItem = new String[4];
                for (int k = 0; k < eachItem.length; k++) {
                    int a = determineType();
                    if (a == 0)
                        eachItem[k] = String.valueOf(generateInt(within100));
                    else if (a == 1)
                        eachItem[k] = String.valueOf(generateFloat(within100));
                    else if (a == 2)
                        eachItem[k] = generateMixedNumber(within100);
                }
                Collections.shuffle(Arrays.asList(eachItem));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
        }
        return questions;
    }

    //This method is to generate questions containing only integers
    public static String[] generateOnlyInt(int num, String s2) {
        String[] questions = new String[num];
        int i, j;
        for (i = 0; i < num; i++)
            questions[i] = "";
        int within100 = Integer.parseInt(String.valueOf(s2.charAt(62)));
        // 62 is where the 0 or 1 lies for determining if the operands lies within [0,100]
        for (i = 0; i < num; i++) {
            // The first question will only contain 2 integer items
            if (i == 0) {
                int o1 = generateInt(within100), o2 = generateInt(within100);
                questions[i] = String.valueOf(o1);
                questions[i] += " " + determineOperator() + " " + o2;
            }
            // The second question will contain 3 integer items
            else if (i == 1) {
                String[] eachItem = new String[3];
                eachItem[0] = String.valueOf(generateInt(within100));
                eachItem[1] = String.valueOf(generateInt(within100));
                eachItem[2] = String.valueOf(generateInt(within100));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
            // The third question will contain 4 integer items
            else if (i == 2) {
                String[] eachItem = new String[4];
                eachItem[0] = String.valueOf(generateInt(within100));
                eachItem[1] = String.valueOf(generateInt(within100));
                eachItem[2] = String.valueOf(generateInt(within100));
                eachItem[3] = String.valueOf(generateInt(within100));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
            else {
                String[] eachItem = new String[5];
                for (int k = 0; k < eachItem.length; k++)
                    eachItem[k] = String.valueOf(generateInt(within100));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
        }
        return questions;
    }

    //This method is to generate questions containing integers and floats
    public static String[] generateIntAndFloat(int num, String s2) {
        String[] questions = new String[num];
        int i, j;
        for (i = 0; i < num; i++)
            questions[i] = "";
        int within100 = Integer.parseInt(String.valueOf(s2.charAt(62)));
        // 62 is where the 0 or 1 lies for determining if the operands lies within [0,100]
        for (i = 0; i < num; i++) {
            // The first question will contain an integer and a float item
            if (i == 0) {
                int o1 = generateInt(within100);
                float o2 = generateFloat(within100);
                questions[i] = String.valueOf(o1);
                questions[i] += " " + determineOperator() + " " + o2;
            }
            // The second question will contain 1 integer item and 2 float items
            else if (i == 1) {
                String[] eachItem = new String[3];
                eachItem[0] = String.valueOf(generateInt(within100));
                eachItem[1] = String.valueOf(generateFloat(within100));
                eachItem[2] = String.valueOf(generateFloat(within100));
                Collections.shuffle(Arrays.asList(eachItem));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
            // The third question will contain random items of integers and floats
            else if (i == 2) {
                String[] eachItem = new String[4];
                for (j = 0; j < eachItem.length; j++) {
                    int a = determineType();
                    if (a == 0)
                        eachItem[j] = String.valueOf(generateFloat(within100));
                    else
                        eachItem[j] = String.valueOf(generateInt(within100));
                }
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
            else {
                String[] eachItem = new String[5];
                for (int k = 0; k < eachItem.length; k++) {
                    int a = determineIntOrFloat();
                    if (a == 0)
                        eachItem[k] = String.valueOf(generateFloat(within100));
                    else
                        eachItem[k] = String.valueOf(generateInt(within100));
                }
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
        }
        return questions;
    }

    //This method is to generate questions containing integers and mixed numbers
    public static String[] generateIntAndFixedNum(int num, String s2) {
        String[] questions = new String[num];
        int i, j;
        for (i = 0; i < num; i++)
            questions[i] = "";
        int within100 = Integer.parseInt(String.valueOf(s2.charAt(62)));
        // 62 is where the 0 or 1 lies for determining if the operands lies within [0,100]
        for (i = 0; i < num; i++) {
            // The first question will contain an integer and a mixed number
            if (i == 0) {
                questions[i] = generateMixedNumber(within100);
                questions[i] += " " + determineOperator() + " " + generateInt(within100);
            }
            // The second question will contain 1 integer and 2 mixed numbers
            else if (i == 1) {
                String[] eachItem = new String[3];
                eachItem[0] = String.valueOf(generateInt(within100));
                eachItem[1] = generateMixedNumber(within100);
                eachItem[2] = generateMixedNumber(within100);
                Collections.shuffle(Arrays.asList(eachItem));
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
            // The third question will contain random items of integers and mixed numbers
            else if (i == 2) {
                String[] eachItem = new String[4];
                for (j = 0; j < eachItem.length; j++) {
                    int a = determineType();
                    if (a == 0)
                        eachItem[j] = String.valueOf(generateInt(within100));
                    else
                        eachItem[j] = generateMixedNumber(within100);
                }
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            } else {
                String[] eachItem = new String[5];
                for (int k = 0; k < eachItem.length; k++) {
                    int a = determineIntOrFloat();
                    if (a == 0)
                        eachItem[k] = String.valueOf(generateInt(within100));
                    else
                        eachItem[k] = generateMixedNumber(within100);
                }
                for (j = 0; j < eachItem.length; j++) {
                    if (j != eachItem.length - 1)
                        questions[i] += eachItem[j] + " " + determineOperator() + " ";
                    else
                        questions[i] += eachItem[j];
                }
            }
        }
        return questions;
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputFile = new Scanner(new File("Requirements.txt"));
        String qNum = inputFile.nextLine();
        String req2 = inputFile.nextLine();
        String req3 = inputFile.nextLine();
        String req4 = inputFile.nextLine();
        // Get the number of questions
        String numStr = qNum.substring(36);
        //char n = qNum.charAt(36);
        int num = Integer.parseInt(numStr);
        String[] questions = new String[num];
        int floatAri = Integer.parseInt(String.valueOf(req3.charAt(43)));
        int mixedNumAri = Integer.parseInt(String.valueOf(req4.charAt(41)));

        if (floatAri == 1 && mixedNumAri == 1)
            questions = generateAllTypes(num, req2);
        else if (floatAri == 0 && mixedNumAri == 0)
            questions = generateOnlyInt(num, req2);
        else if (floatAri == 1 && mixedNumAri == 0)
            questions = generateIntAndFloat(num, req2);
        else if (floatAri == 0 && mixedNumAri == 1)
            questions = generateIntAndFixedNum(num, req2);
        else {
            System.out.println("Invalid requirement!!");
            System.exit(-1);
        }

        PrintWriter writer = new PrintWriter("Quiz.txt");
        for (int i = 0; i < num; i++)
            writer.printf("[" + (i + 1) + "]" + " " + questions[i] + " =" + "\n");
        writer.close();
        inputFile.close();
    }
}
