/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer;

/**
 *The ray is generated with a original position and a direction which it will "expand" upon
 * it can be used for various functionalities but it is used mostly to verify intersections
 */
public class Ray {
    /**
     * origin has the position in which the ray will start generating
     */
    private Vector3D origin;
    /**
     * direction has the value in which the ray will be directed when it`s casted
     */
    private Vector3D direction;
    /**
     * Constructor of the ray only requires the origin position and the direction
     * @param origin
     * @param direction
     */
    public Ray(Vector3D origin, Vector3D direction) {
        setOrigin(origin);
        setDirection(direction);
    }
    /**
     * returns the position which the ray is created upon
     * @return
     */
    public Vector3D getOrigin() {
        return origin;
    }
    /**
     * Set the original position for the ray to be casted
     * @param origin
     */
    public void setOrigin(Vector3D origin) {
        this.origin = origin;
    }
    /**
     * Get the direction the ray will follow
     * @return
     */
    public Vector3D getDirection() {
        return Vector3D.normalize(direction);
    }
    /**
     * sets the direction the ray will be directed to.
     * @param direction
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
