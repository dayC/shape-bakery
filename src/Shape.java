/**
 * Enumerator which represents a shape.
 */
public enum Shape {
    TRIANGLE ("triangle", "images/triangle.png"),
    SQUARE ("square", "images/square.png"),
    CIRCLE ("circle", "images/circle.png"),
    RHOMBUS ("rhombus", "images/rhombus.jpg"),
    TRAPEZOID ("trapezoid", "images/trapezoid.jpg"),
    PENTAGON ("pentagon", "images/pentagon.png");

    private final String readable; /**< The name of the shape in human-readable format. */
    private final String image; /**< The name of the shape in human-readable format. */

    /**
     * Shape constructor.
     * @param readable The name of the shape in human-readable format.
     */
    Shape(String readable, String image) {
        this.readable = readable;
        this.image = image;
    }

    /**
     * @return The name of the shape in human-readable format.
     */
    public String getReadable() {
        return readable;
    }
    public String getImage() {
        return image;
    }
}
