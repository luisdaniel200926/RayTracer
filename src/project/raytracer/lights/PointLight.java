/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer.lights;

import project.raytracer.Intersection;
import project.raytracer.Material;
import project.raytracer.Vector3D;

import java.awt.*;

/**
 * @author Jafet Rodríguez
 */
public class PointLight extends Light {
    /**
     * Constructor to create a point light
     * @param position
     * @param color
     * @param intensity
     * @param material
     */
    public PointLight(Vector3D position, Color color, double intensity, Material material) {
        super(position, color, intensity,material);
    }

    /**
     * Calculates the NDotL and returns it
     * @param intersection
     * @return
     */
    @Override
    public float getNDotL(Intersection intersection) {
        return (float) Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.normalize(Vector3D.substract(getPosition(), intersection.getPosition()))), 0.0);
    }

    /**
     * returns the intesity considering the distance between the light and the position
     * @param distance
     * @return
     */
    @Override
    public double getIntensity(double distance) {
        return (this.getIntensity()/Math.pow(distance,2));
    }

}
