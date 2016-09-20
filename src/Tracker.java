/**
 * Tracks gameplay statistics.
 */
public class Tracker {

    private int nQuestions; /**< The total number of questions asked. */
    private int nCorrect; /**< The number of questions answered correctly. */
    private int nIncorrect; /**< The number of questions answered incorrectly. */

    /**
     * Tracker constructor.
     */
    public Tracker() {
        nQuestions = nCorrect = nIncorrect = 0;
    }

    /**
     * Getter for nQuestions.
     * @return The total number of asked questions.
     */
    public int getnQuestions() {
        return nQuestions;
    }

    /**
     * Setter for nQuestions.
     * @param nQuestions The number of asked questions.
     */
    public void setnQuestions(int nQuestions) {
        this.nQuestions = nQuestions;
    }

    /**
     * Getter for nCorrect.
     * @return The number of questions answered correctly.
     */
    public int getnCorrect() {
        return nCorrect;
    }

    /**
     * Getter for nIncorrect.
     * @return The number of questions answered incorrectly.
     */
    public int getnIncorrect() {
        return nIncorrect;
    }

    /**
     * Call to track when an answer is correct.
     */
    public void correct() {
        nCorrect++;
    }

    /**
     * Call to track when an answer is incorrect.
     */
    public void incorrect() {
        nIncorrect++;
    }

    /**
     * Calculates the percentage of correct questions.
     * @return The percentage of correct questions.
     */
    public double percentCorrect() {
        try {
            return ((double)nCorrect / nQuestions) * 100;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /**
     * Calculates the percentage of incorrect questions.
     * @return The percentage of incorrect questions.
     */
    public double percentIncorrect() {
        try {
            return ((double)nIncorrect / nQuestions) * 100;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /**
     * Calculates the number of questions remaining.
     * @return The number of questions remaining.
     */
    public int questionsRemaining() {
        return nQuestions - (questionsAnswered());
    }

    /**
     * Calculates the number of questions answered.
     * @return The number of questions answered.
     */
    public int questionsAnswered() {
        return nCorrect + nIncorrect;
    }

    /**
     * Calculates the percentage of completion.
     * @return The percentage of completion.
     */
    public double percentComplete() {
        try {
            return ((double)questionsAnswered() / nQuestions) * 100;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
