/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer.lights;

import project.raytracer.Intersection;
import project.raytracer.Material;
import project.raytracer.Ray;
import project.raytracer.Vector3D;
import project.raytracer.objects.Object3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */
public abstract class Light extends Object3D {
    private double intensity;

    /**
     * Constructor for a light
     * @param position
     * @param color
     * @param intensity
     * @param material
     */
    public Light(Vector3D position, Color color, double intensity, Material material){
        super(position, color ,material);
        setIntensity(intensity);
    }

    /**
     * gets the intensity
     * @return
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * sets the intensity
     * @param intensity
     */
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * abstract method to get NDotL
     * @param intersection
     * @return
     */
    public abstract float getNDotL(Intersection intersection);

    /**
     * light doesn't need this method
     * @param ray
     * @return
     */
    public Intersection getIntersection(Ray ray){
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }

    /**
     * just to get the light if it has to change intensity
     * @param distance
     * @return
     */
    public abstract double getIntensity(double distance);
}
