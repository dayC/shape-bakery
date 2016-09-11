/**
 * Enumerator which represents a shape.
 */
public enum Shape {
    TRIANGLE ("triangle"),
    SQUARE ("square"),
    CIRCLE ("circle"),
    RHOMBUS ("rhombus"),
    TRAPEZOID ("trapezoid"),
    PENTAGON ("pentagon");

    private final String readable; /**< The name of the shape in human-readable format. */

    /**
     * Shape constructor.
     * @param readable The name of the shape in human-readable format.
     */
    Shape(String readable) {
        this.readable = readable;
    }

    /**
     * @return The name of the shape in human-readable format.
     */
    public String getReadable() {
        return readable;
    }
}
