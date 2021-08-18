/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer.objects;

import project.raytracer.Intersection;
import project.raytracer.Material;
import project.raytracer.Ray;
import project.raytracer.Vector3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */
public class Sphere extends Object3D {

    private float radius;

    /**
     * returns the radius
     * @return
     */
    public float getRadius() {
        return radius;
    }

    /**
     * set the radius
     * @param radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * constructor to create a new sphere
     * @param position
     * @param radius
     * @param color
     * @param material
     */
    public Sphere(Vector3D position, float radius, Color color, Material material) {
        super(position, color, material);
        setRadius(radius);
    }

    /**
     * calculates if the given ray intersect with the sphere
     * @param ray
     * @return
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        double distance = -1;
        Vector3D normal = Vector3D.ZERO();
        Vector3D position = Vector3D.ZERO();

        Vector3D directionSphereRay = Vector3D.substract(ray.getOrigin(), getPosition());
        double firstP = Vector3D.dotProduct(ray.getDirection(), directionSphereRay);
        double secondP = Math.pow(Vector3D.magnitude(directionSphereRay), 2);
        double intersection = Math.pow(firstP, 2) - secondP + Math.pow(getRadius(), 2);

        if(intersection >= 0){
            double sqrtIntersection = Math.sqrt(intersection);
            double part1 = -firstP + sqrtIntersection;
            double part2 = -firstP - sqrtIntersection;

            distance = Math.min(part1, part2);
            position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
            normal = Vector3D.normalize(Vector3D.substract(position, getPosition()));
        } else {
            return null;
        }

        return new Intersection(position, distance, normal, this);
    }
}
