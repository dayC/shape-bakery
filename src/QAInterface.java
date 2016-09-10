/**
 * QAInterface defines the interface for a question/answer class of the form select one answer from a list of
 * possibilities.
 */
public interface QAInterface<T> {
    /**
     * Returns an array containing the possible answers.
     * @return An array containing the possible answers.
     */
    public T[] getOptions();

    /**
     * Generates a random question sequence.
     * @param n Desired sequence length.
     */
    public void generate(int n);

    /**
     * Validates an answer against the correct one.
     * @param idx The index of the user's answer.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean validate(int idx);

    /**
     * Generates the human-readable question.
     * @return The human-readable question.
     */
    public String getQuestion();
}
