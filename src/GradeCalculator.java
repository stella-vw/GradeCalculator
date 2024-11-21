import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeCalculator{

    static Scanner in = new Scanner(System.in);

    static double[][] getGrades(int numGrades) {
       double[][] numsArray = new double[2][numGrades];
       double sum = 0;
       while (true) {
           for (int i = 0; i < numGrades; i++) {
               try {
                   System.out.println("input your grade as a percentage first, then input the weight of that grade as a decimal, hit enter after each pair");
                   numsArray[0][i] = in.nextDouble(); // store first number inputted into first row of array which will be the grades row
                   numsArray[1][i] = in.nextDouble(); // store second number inputted into second row of array which will be the weights row
                   sum += numsArray[1][i];
                   System.out.println(sum);
               } catch (InputMismatchException e) {
                   System.out.println("You entered an invalid input. Try again");
               }
           }
           if (1 - sum > 0) {
               return numsArray;
           }
           System.out.println("the weights you entered sum to greater than one, try again!");
           sum = 0;
       }
    }

    static double FinalGradeNeeded(double[] grades, double[] weights) {
       System.out.println("Enter the final grade you want in your class");
       double targetGrade = in.nextDouble();
       in.nextLine();
       double sum = 0;
       double weightSum = 0;
       for (int i = 0; i < grades.length; i++) {
           sum += grades[i] * weights[i];
           weightSum += weights[i];
       }
       return (targetGrade - sum) / (1-weightSum);
   }

   // static String getMode()
    static String getMode() {
        while (true) {
            System.out.println("Which mode would you like to use: final grade needed or current grade");
            String mode = in.nextLine();
            //in.nextLine();
            if (mode.equalsIgnoreCase("final grade needed") || mode.equalsIgnoreCase("current grade")) {
               return mode;
            }
            System.out.println("you entered an invalid input, try again!");
        }
    }

    static int getNumInput() {
        while (true) {
            System.out.println("OK, now enter the number of the grades you are going to input");
            int numInput = in.nextInt();
            if (numInput > 0) {
                return numInput;
            }
            System.out.println("You entered an invalid input, try again!");
        }
    }

    static double currentGrade(double[] grades, double[] weights) {
        double weightedGrades = 0;
        double totalWeight = 0;
        for (int i = 0; i < grades.length; i++) {
            weightedGrades += grades[i] * weights[i];
            totalWeight += weights[i];
        }
        return weightedGrades/totalWeight;
    }

   public static void main(String[] args) {
       System.out.println("Welcome to Grade Calculator!");

       while (true) {
           String mode = getMode();
           int numGrades = getNumInput();
           double[][] userData = getGrades(numGrades);

           for (int i = 0; i < userData[0].length; i++) {
               System.out.println("Grade " + (i+1) + ": " + userData[0][i] + " " + userData[1][i]);
           }

           if (mode.equalsIgnoreCase("final grade needed")) {
               double finalGrade = FinalGradeNeeded(userData[0], userData[1]);
               System.out.printf("Your final grade needed is %.2f\n", finalGrade);
           }
           else if (mode.equalsIgnoreCase("current grade")) {
               double currentGrade = currentGrade(userData[0], userData[1]);
               System.out.printf("Your current grade is %.2f\n", currentGrade);
           }

           System.out.println("Do you want to use this again? Y/N \n(if nothing happens press enter again)");
           in.nextLine();
           String choice = in.nextLine();
           if (!choice.equalsIgnoreCase("Y")) {
               break;
           }
       }
   }
}