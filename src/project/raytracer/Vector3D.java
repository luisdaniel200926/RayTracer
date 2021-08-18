/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer;

/**
 *
 * @author Jafet Rodríguez
 */
public class Vector3D {

    private static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    private double x, y, z;

    /**
     * returns x value
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * sets x value
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * returns y value
     * @return
     */
    public double getY() {
        return y;
    }
    /**
     * sets y value
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * returns z value
     * @return
     */
    public double getZ() {
        return z;
    }
    /**
     * sets z value
     * @param z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Constructor to create a new Vector3D
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Method to get the dot product of two vectors
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static double dotProduct(Vector3D vectorA, Vector3D vectorB){
        return (vectorA.getX() * vectorB.getX()) + (vectorA.getY() * vectorB.getY()) + (vectorA.getZ() * vectorB.getZ());
    }

    /**
     * Method to get the distance between two vectors
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static double distanceBetween(Vector3D vectorA, Vector3D vectorB){
        return (Math.pow((Math.pow(vectorB.getX() - vectorA.getX(), 2) + Math.pow(vectorB.getY() - vectorA.getY(), 2) + Math.pow(vectorB.getZ() - vectorA.getZ(), 2)), 0.5) );
    }

    /**
     * method to calculate the cross product of two vectors
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static Vector3D crossProduct(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D((vectorA.getY() * vectorB.getZ()) - (vectorA.getZ() * vectorB.getY()),
                (vectorA.getZ() * vectorB.getX()) - (vectorA.getX() * vectorB.getZ()),
                (vectorA.getX() * vectorB.getY()) - (vectorA.getY() * vectorB.getX()));
    }

    /**
     * gets the magnitude of the given vector
     * @param vectorA
     * @return
     */
    public static double magnitude(Vector3D vectorA){
        return Math.sqrt(dotProduct(vectorA, vectorA));
    }

    /**
     * adds the two vectors given
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static Vector3D add(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY(), vectorA.getZ() + vectorB.getZ());
    }

    /**
     * substracts the second vector to the first one
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static Vector3D substract(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY(), vectorA.getZ() - vectorB.getZ());
    }

    /**
     * normalizes the given vector
     * @param vectorA
     * @return
     */
    public static Vector3D normalize(Vector3D vectorA){
        double mag = Vector3D.magnitude(vectorA);
        return new Vector3D(vectorA.getX() / mag, vectorA.getY() / mag, vectorA.getZ() / mag);
    }

    /**
     * multiply the vector times some float value and returns the vector calculated
     * @param vectorA
     * @param scalar
     * @return
     */
    public static Vector3D scalarMultiplication(Vector3D vectorA, double scalar){
        return new Vector3D(vectorA.getX() * scalar, vectorA.getY() * scalar, vectorA.getZ() * scalar);
    }

    /**
     *prints the vector in a readable manner
     * @return
     */
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
    }

    /**
     * clones a vector
     * @return
     */
    public Vector3D clone(){
        return new Vector3D(getX(), getY(), getZ());
    }

    /**
     * returns a Vector(0,0,0)
     * @return
     */
    public static Vector3D ZERO(){
        return ZERO.clone();
    }
}
