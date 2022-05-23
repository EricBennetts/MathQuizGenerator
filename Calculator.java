
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static float mixedNumToFloat(String x) {
        int underline = x.indexOf('_'), verticalBar = x.indexOf('|');
        String front = x.substring(0, underline);
        float x1 = Integer.parseInt(front);
        String mid = x.substring(underline + 1, verticalBar);
        float x2 = Integer.parseInt(mid);
        String back = x.substring(verticalBar + 1);
        float x3 = Integer.parseInt(back);
        return ((x1 * x3) + x2) / x3;
    }

    // Converts the string containing mixed numbers to double
    public static String originalToFloat(String x) {
        boolean isFloat;
        isFloat = x.contains(".");
        if (isFloat)
            return x;
        if (x.contains("_"))
            return String.valueOf(mixedNumToFloat(x));
        return x;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner quizInput = new Scanner(new File("Quiz.txt"));
        Scanner reqInput = new Scanner(new File("Requirements.txt"));
        int quizNum = Integer.parseInt(reqInput.nextLine().substring(36));
        int i;
        // quizHolder is used to hold the original quiz questions
        String[] quizHolder = new String[quizNum];
        String[] infix = new String[quizNum];
        for (int k = 0; k < quizNum; k++)
            infix[k] = "";
        for (i = 0; i < quizNum; i++) {
            String lines = quizInput.nextLine();
            quizHolder[i] = lines;
            Scanner lineInput = new Scanner(lines);
            String items = lineInput.next();
            items = lineInput.next();
            // items is now the first item
            for (int j = 0;!(items.equals("=")); items = lineInput.next(), j++) {
                // The last bit of an odd integer is 1
                if ((j & 1) == 0)
                    infix[i] += originalToFloat(items) + " ";
                else
                    infix[i] += items + " ";
            }
            infix[i] += "=";
        }
        String[] postfix = new String[quizNum];
        for (i = 0; i < quizNum; i++)
            postfix[i] = Convertor.infixToPostfix(infix[i]);
        String[] finalAns = new String[quizNum];
        for (i = 0; i < quizNum; i++)
            finalAns[i] = Evaluator.evaluatePostfix(quizHolder[i], postfix[i]);

        PrintWriter writer = new PrintWriter("Answer.txt");
        for (i = 0; i < quizNum; i++)
            writer.printf(finalAns[i] + "\n");
        quizInput.close();
        writer.close();
    }
}

class Convertor {
    private static int Precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        if (ch == '*' || ch == '/')
            return 2;
        return -1;
    }

    static String infixToPostfix(String expr) {
        StringBuilder post = new StringBuilder();
        Scanner line = new Scanner(expr);
        String items;
        int i;
        Stack<Character> oprtStack = new Stack<>();
        for (items = line.next(), i = 0; !(items.equals("=")); items = line.next(), i++) {
            // If the item is an operand, just concatenate it to the result string
            if (Precedence(items.charAt(0)) == -1) {
                post.append(items).append(" ");
                continue;
            }
            // Push the first operator onto stack
            if (i == 1)
                oprtStack.push(items.charAt(0));
            else {
                // If ICP > ISP, push
                if (Precedence(items.charAt(0)) > Precedence(oprtStack.peek()))
                    oprtStack.push(items.charAt(0));
                else {
                    while (!oprtStack.empty() && Precedence(oprtStack.peek()) >= Precedence(items.charAt(0)))
                        post.append(oprtStack.pop()).append(" ");
                    oprtStack.push(items.charAt(0));
                }
            }
        }
        while (!oprtStack.empty())
            post.append(oprtStack.pop()).append(" ");
        post.append("=");
        // The equal sign is used to indicate the end of the string
        return post.toString();
    }
}

class Evaluator {
    private static boolean isNumber(String x) {
        return x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/");
    }
    static String evaluatePostfix(String in, String x) {
        Scanner line = new Scanner(x);
        Stack<Float> oprdStack = new Stack<>();
        String items;
        for (items = line.next(); !items.equals("="); items = line.next()) {
            // If token is an operand, push onto stack
            if (!isNumber(items))
                oprdStack.push(Float.parseFloat(items));
            else {
                float o2 = oprdStack.pop();
                float o1 = oprdStack.pop();
                float res = 0;
                if (items.equals("+"))
                    res = Float.parseFloat(new DecimalFormat("#.0000").format(o1 + o2));
                if (items.equals("-"))
                    res = Float.parseFloat(new DecimalFormat("#.0000").format(o1 - o2));
                if (items.equals("*"))
                    res = Float.parseFloat(new DecimalFormat("#.0000").format(o1 * o2));
                if (items.equals("/"))
                    res = Float.parseFloat(new DecimalFormat("#.0000").format(o1 / o2));
                oprdStack.push(res);
            }
        }
        return in + " " + oprdStack.pop();
    }
}