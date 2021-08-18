/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer;

import project.raytracer.objects.Object3D;

/**
 *
 * @author Jafet Rodríguez
 */
public class Intersection {

    private double distance;
    private Vector3D normal;
    private Vector3D position;
    private Object3D object;

    /**
     * Constructor for a intersection
     * @param position
     * @param distance
     * @param normal
     * @param object
     */
    public Intersection(Vector3D position, double distance, Vector3D normal, Object3D object) {
        setPosition(position);
        setDistance(distance);
        setNormal(normal);
        setObject(object);
    }

    /**
     * returns the distance
     * @return
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the distance
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * returns the normal
     * @return
     */
    public Vector3D getNormal() {
        return normal;
    }

    /**
     * sets the normal
     * @param normal
     */
    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    /**
     * returns the position where it intersects
     * @return
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * sets the position of the intersection
     * @param position
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * return the object it intersected with
     * @return
     */
    public Object3D getObject() {
        return object;
    }

    /**
     * sets object it intersected with
     * @param object
     */
    public void setObject(Object3D object) {
        this.object = object;
    }
}
