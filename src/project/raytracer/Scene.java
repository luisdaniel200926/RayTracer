/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer;

import project.raytracer.lights.Light;
import project.raytracer.objects.Camera;
import project.raytracer.objects.Object3D;

import java.util.ArrayList;

/**
 *
 * @author Jafet Rodríguez
 */
public class Scene {

    private Camera camera;
    private double ambient_coefficient;
    private double diffuse_coefficient;
    private double specular_coefficient;
    private ArrayList<Object3D> objects;
    private ArrayList<Light> lights;

    /**
     * Constructor to create a scene
     */
    public Scene(){
        setObjects(new ArrayList<Object3D>());
        setLights(new ArrayList<Light>());
        setAmbient_coefficient(1f);
        setDiffuse_coefficient(1f);
        setSpecular_coefficient(1f);
    }

    /**
     * returns the camera for the scene
     * @return
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * sets the camera for the scene
     * @param camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * method to add new object to the scene
     * @param object
     */
    public void addObject(Object3D object){
        getObjects().add(object);
    }

    /**
     * returns all the object on the scene
     * @return
     */
    public ArrayList<Object3D> getObjects() {
        return objects;
    }

    /**
     * set all the objects of the scene
     * @param objects
     */
    public void setObjects(ArrayList<Object3D> objects) {
        this.objects = objects;
    }

    /**
     * returns all the lights on the scene
     * @return
     */
    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     * sets all the lights of the scene
     * @param lights
     */
    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    /**
     * method to add a new light to the scene
     * @param light
     */
    public void addLight(Light light){
        getLights().add(light);
    }

    /**
     * set the ambient coefficient
     * @param ambient_coefficient
     */
    public void setAmbient_coefficient(double ambient_coefficient){this.ambient_coefficient=ambient_coefficient;}
    /**
     * get the ambient coefficient
     * @return
     */
    public double getAmbient_coefficient(){return ambient_coefficient;}
    /**
     * set the diffuse coefficient
     * @param diffuse_coefficient
     */
    public void setDiffuse_coefficient(double diffuse_coefficient){this.diffuse_coefficient=diffuse_coefficient;}

    /**
     * returns the diffuse coefficient
     * @return
     */
    public double getDiffuse_coefficient(){return diffuse_coefficient;}

    /**
     * sets the specular coefficient
     * @param specular_coefficient
     */
    public void setSpecular_coefficient(double specular_coefficient){this.specular_coefficient=specular_coefficient;}

    /**
     * gets the specular coefficient
     * @return
     */
    public double getSpecular_coefficient(){return specular_coefficient;}

    /**
     * method to simply set all the coefficients needed for a scene
     * @param ambient_coefficient
     * @param diffuse_coefficient
     * @param specular_coefficient
     */
    public void setCoefficients(double ambient_coefficient, double diffuse_coefficient, double specular_coefficient){setAmbient_coefficient(ambient_coefficient);setDiffuse_coefficient(diffuse_coefficient);setSpecular_coefficient(specular_coefficient);}

}
