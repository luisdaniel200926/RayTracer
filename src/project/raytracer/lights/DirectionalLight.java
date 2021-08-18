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
 *
 * @author Jafet Rodríguez
 */
public class DirectionalLight extends Light {
    private Vector3D direction;

    /**
     * Constructor for a Directional Light
     * @param position
     * @param direction
     * @param color
     * @param intensity
     * @param material
     */
    public DirectionalLight(Vector3D position, Vector3D direction, Color color, double intensity, Material material){
        super(position, color, intensity,material);
        setDirection(Vector3D.normalize(direction));
    }

    /**
     * returns the direction
     * @return
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * sets the direction the light will be following
     * @param direction
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    /**
     * Calculates the NDotL
     * @param intersection
     * @return
     */
    @Override
    public float getNDotL(Intersection intersection) {
        return (float)Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)), 0.0);
    }

    /**
     * returns the intensity decreased by the distance it has traveled
     * but it's not used by the directional light
     * @param distance
     * @return
     */
    @Override
    public double getIntensity(double distance) {
        return getIntensity();
    }

}
