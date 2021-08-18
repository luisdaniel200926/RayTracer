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
 * @author Jafet Rodríguez
 */
public class Camera extends Object3D {
    // 0 is fovh
    // 1 is fovv
    private float[] fieldOfView = new float[2];
    private float defaultZ = 15f;
    // 0 is width
    // 1 is height
    private int[] resolution;
    private float[] nearFarPlanes = new float[2];

    /**
     * Constructor to create a camera which will be the one looking at the scene that will be rendered.
     * @param position
     * @param fieldOfViewHorizontal
     * @param fieldOfViewVertical
     * @param widthResolution
     * @param heightResolution
     * @param nearPlane
     * @param farPlane
     */
    public Camera(Vector3D position, float fieldOfViewHorizontal, float fieldOfViewVertical, int widthResolution, int heightResolution, float nearPlane, float farPlane, Material material) {
        super(position, Color.black, material);
        setFieldOfViewHorizontal(fieldOfViewHorizontal);
        setFieldOfViewVertical(fieldOfViewVertical);
        setResolution(new int[]{widthResolution, heightResolution});
        setNearFarPlanes(new float[]{nearPlane, farPlane});
    }

    /**
     * returns the field of view of the camara
     * @return
     */

    public float[] getFieldOfView() {
        return fieldOfView;
    }

    /**
     * sets the field of view of the camera
     * @param fieldOfView
     */
    public void setFieldOfView(float[] fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    /**
     * gets the field of view horizontal of the camera
     * @return
     */
    public float getFieldOfViewHorizontal() {
        return fieldOfView[0];
    }

    /**
     * sets the field of view horizontal
     * @param fov
     */
    public void setFieldOfViewHorizontal(float fov) {
        fieldOfView[0] = fov;
    }

    /**
     * gets the field of view vertically
     * @return
     */
    public float getFieldOfViewVertical() {
        return fieldOfView[1];
    }

    /**
     * sets the field of view vertically
     * @param fov
     */
    public void setFieldOfViewVertical(float fov) {
        fieldOfView[1] = fov;
    }

    /**
     * gets the defaultZ
     * @return
     */
    public float getDefaultZ() {
        return defaultZ;
    }

    /**
     * sets the default Z
     * @param defaultZ
     */
    public void setDefaultZ(float defaultZ) {
        this.defaultZ = defaultZ;
    }

    /**
     * returns the resolution in which te image will be maked
     * @return
     */
    public int[] getResolution() {
        return resolution;
    }

    /**
     *sets the resolution for the image
     * @param resolution
     */
    public void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    /**
     * gets the resolution width
     * @return
     */
    public int getResolutionWidth() {
        return getResolution()[0];
    }

    /**
     * gets the resolution height
     * @return
     */
    public int getResolutionHeight() {
        return getResolution()[1];
    }

    /**
     * Calculates each position the camera will be needing to fire each ray
     * it is like creating the grid panel for the camera
     * @return
     */
    public Vector3D[][] calculatePositionsToRay() {
        float angleMaxX = 90 - (getFieldOfViewHorizontal() / 2f);
        float radiusMaxX = getDefaultZ() / (float) Math.cos(Math.toRadians(angleMaxX));

        float maxX = (float) Math.sin(Math.toRadians(angleMaxX)) * radiusMaxX;
        float minX = -maxX;

        float angleMaxY = 90 - (getFieldOfViewVertical() / 2f);
        float radiusMaxY = getDefaultZ() / (float) Math.cos(Math.toRadians(angleMaxY));

        float maxY = (float) Math.sin(Math.toRadians(angleMaxY)) * radiusMaxY;
        float minY = -maxY;

        Vector3D[][] positions = new Vector3D[getResolutionWidth()][getResolutionHeight()];
        float posZ = getDefaultZ();
        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                float posX = minX + (((maxX - minX) / (float) getResolutionWidth()) * x);
                //float posY = minY + (((maxY - minY) / (float) getResolutionHeight()) * y);
                float posY = maxY - (((maxY - minY) / (float) getResolutionHeight()) * y);
                positions[x][y] = new Vector3D(posX, posY, posZ);
            }
        }

        return positions;
    }

    /**
     * returns the near and far planes
     * @return
     */
    public float[] getNearFarPlanes() {
        return nearFarPlanes;
    }

    /**
     * sets the near and far plane
     * @param nearFarPlanes
     */
    public void setNearFarPlanes(float[] nearFarPlanes) {
        this.nearFarPlanes = nearFarPlanes;
    }

    /**
     * method not used by the camera
     * @param ray
     * @return
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}
