/**
 * Defines the interface for a question/answer class of the form memorize and match a sequence.
 * \todo Implement this interface for modularity, currently unused.
 */
public interface QAInterface<T> {
    /**
     * Generates a random sequence.
     * @param n Desired sequence length.
     */
    public T[] generate(int n);

    /**
     * Validates an answer sequence against the correct sequence.
     * @param answer The proposed answer sequence.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean validate(String[] answer);

    /**
     * Generates the human-readable question.
     * @return The human-readable question.
     */
    public String getQuestion();
}
