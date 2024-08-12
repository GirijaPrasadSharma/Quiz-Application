import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {

    // Data structure to store quiz questions, options, and correct answers
    static String[] questions = {
            "What is the capital of France?",
            "What is the largest planet in our solar system?",
            "Who wrote 'Romeo and Juliet'?"
    };

    static String[][] options = {
            {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
            {"1. Earth", "2. Jupiter", "3. Mars", "4. Saturn"},
            {"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. J.K. Rowling"}
    };

    static int[] correctAnswers = {3, 2, 2};  // Indices of correct options

    static int score = 0;  // To keep track of the user's score
    static boolean timeUp = false;

    // Store user's answers for the summary
    static int[] userAnswers = new int[questions.length];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            // Initialize userAnswer with a default value indicating no answer
            userAnswers[i] = -1;

            // Start timer for the question
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up!");
                    timeUp = true;
                }
            };

            timer.schedule(task, 10000); // 10 seconds for each question

            if (!timeUp) {
                System.out.print("Your answer (1-4): ");
                if (scanner.hasNextInt()) {
                    userAnswers[i] = scanner.nextInt();
                }
            }

            // Cancel the timer as soon as user answers
            timer.cancel();

            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            } else if (!timeUp && userAnswers[i] != -1) {
                System.out.println("Wrong answer!");
            }

            timeUp = false;  // Reset the timer flag for the next question
        }

        // Display final score
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.length);

        // Display summary of correct/incorrect answers
        System.out.println("\nSummary:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            if (userAnswers[i] != -1) {
                System.out.println("Your answer: " + options[i][userAnswers[i] - 1]);
            } else {
                System.out.println("Your answer: No answer");
            }
            System.out.println("Correct answer: " + options[i][correctAnswers[i] - 1]);
            if (userAnswers[i] == correctAnswers[i]) {
                System.out.println("Result: Correct\n");
            } else {
                System.out.println("Result: Incorrect\n");
            }
        }
    }
}
