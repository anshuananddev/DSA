import util.Solution;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String RESET = "\u001B[0m";
        String RED_TEXT = "\u001B[31m";
        String GREEN_TEXT = "\u001B[32m";
        String YELLOW_TEXT = "\u001B[33m";
        String BLACK_BG = "\u001B[40m";
        String WHITE_BG = "\u001B[47m";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Type \n1 if class name on Leetcode is 'Solution' \n2 otherwise");

            String leetcodePackagePrefix = "leetcode.";
            int optionSelected = input.nextInt();
            input.nextLine();
            String className = "";
            while (optionSelected != 1 && optionSelected != 2) {
                System.out.println(RED_TEXT + "-- Please enter either 1 or 2 --" + RESET);
                optionSelected = input.nextInt();
                input.nextLine();
            }
            if (optionSelected == 1) {
                System.out.println("Enter question number");
                String questionNumber = input.nextLine();
                while (!questionNumber.matches("^\\d{0,4}$") || Integer.parseInt(questionNumber) < 1) {
                    System.out.println(RED_TEXT + "-- Enter correct question number --" + RESET);
                    questionNumber = input.nextLine();
                }
                className = leetcodePackagePrefix + "S" + questionNumber;

            } else {
                System.out.println("Enter exact class name");
                String questionName = input.nextLine();
                className = leetcodePackagePrefix + questionName;
            }
            System.out.printf(YELLOW_TEXT + "Instantiating %s class\n" + RESET, className);
            try {
                Class<?> c = Class.forName(className);
                Solution solution = (Solution) c.getDeclaredConstructor().newInstance();
                solution.run();
            } catch (ClassNotFoundException e) {
                System.out.println(RED_TEXT + BLACK_BG + "Solution not present in repo / Question Name is wrong" + RESET);
            }

            System.out.println(GREEN_TEXT + BLACK_BG + "Press Enter to continue OR Type 'exit' to exit" + RESET);

        } while (!input.nextLine().equals("exit"));

    }
}