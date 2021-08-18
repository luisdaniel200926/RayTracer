package project.raytracer;

/**
 * Class to create materials to get different results with the objects
 * depending on how the material is conformed
 */
public class Material {

    private float shininess;
    //private float specular;
    //private float diffuse;
    //private float ambient;
    private float reflectivity;
    private float refractivity;
    private float indexRefractivity;

    /**
     * Constructor for a material
     * @param shininess
     * @param reflectivity
     * @param refractivity
     * @param indexRefractivity
     */
    Material( float shininess, float reflectivity, float refractivity, float indexRefractivity){
        //setAmbient(ambient);
        //setDiffuse(diffuse);
        setShininess(shininess);
        //setSpecular(specular);
        setReflectivity(reflectivity);
        setRefractivity(refractivity);
        setIndexRefractivity(indexRefractivity);
    }

    /**
     * returns the shininess
     * @return
     */
    public float getShininess() {
        return shininess;
    }

    /**
     * sets the shininess
     * @param shininess
     */
    public void setShininess(float shininess) {
        this.shininess = shininess;
    }

    /**
     * returns reflectivity
     * @return
     */
    public float getReflectivity() {
        return reflectivity;
    }

    /**
     * sets reflectivity of the material
     * @param reflectivity
     */
    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    /**
     * returns the refractivity
     * @return
     */
    public float getRefractivity() {
        return refractivity;
    }

    /**
     * sets the refractivity
     * @param refractivity
     */
    public void setRefractivity(float refractivity) {
        this.refractivity = refractivity;
    }

    /**
     * return the index in which the refractivity will be calculated upon
     * @return
     */
    public float getIndexRefractivity() {
        return indexRefractivity;
    }

    /**
     * sets the refractivity
     * @param indexRefractivity
     */
    public void setIndexRefractivity(float indexRefractivity) {
        this.indexRefractivity = indexRefractivity;
    }

}
