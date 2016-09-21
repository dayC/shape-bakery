/**
 * Enumerator which represents a shape.
 */
public enum Shape {
    TRIANGLE ("triangle", "images/triangle.png"),       ///<A triangle shape.
    SQUARE ("square", "images/square.png"),             ///<A square shape.
    CIRCLE ("circle", "images/circle.png"),             ///<A circle shape.
    RHOMBUS ("rhombus", "images/rhombus.png"),          ///<A rhombus shape.
    TRAPEZOID ("trapezoid", "images/trapezoid.png"),    ///<A trapezoid shape.
    PENTAGON ("pentagon", "images/pentagon.png");       ///<A pentagon shape.

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
     * Gets the name of the shape in human-readable format.
     * @return The name of the shape in human-readable format.
     */
    public String getReadable() {
        return readable;
    }

    /**
     * Gets the image of the shape
     * @return The image of the shape.
     */
    public String getImage() {
        return image;
    }
}
