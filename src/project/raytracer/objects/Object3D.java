/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer.objects;

import project.raytracer.IIntersectable;
import project.raytracer.Material;
import project.raytracer.Vector3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */
public abstract class Object3D implements IIntersectable {

    private Vector3D position;
    private Color color;
    private Material material;

    /**
     * returns the material
     * @return
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * sets the material
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * returns the position
     * @return
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * sets the position
     * @param position
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * returns the color of the object
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the color of the object
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Constructor to create a new object'ñ
     * '¡890
     * @param position
     * @param color
     * @param material
     */
    public Object3D(Vector3D position, Color color, Material material) {
        setPosition(position);
        setColor(color);
        setMaterial(material);
    }

}
