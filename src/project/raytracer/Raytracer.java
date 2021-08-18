/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer;

import project.raytracer.lights.DirectionalLight;
import project.raytracer.lights.Light;
import project.raytracer.lights.PointLight;
import project.raytracer.objects.*;
import project.raytracer.tools.OBJReader;
import project.raytracer.*;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static project.raytracer.Vector3D.dotProduct;
import static project.raytracer.Vector3D.magnitude;

/**
 * @author Jafet Rodríguez
 */
public class Raytracer {
    //Variables to measure the progress of the rendering
    static int done=0;
    static double donePorcentage=0;
    static int totalPixels=0;
    //Number of logical processors you wish to use
    static int nThreads=12;

    public static void main(String[] args) {
        Date intialdate=(new Date());
        System.out.println(intialdate);
        Scene scene01 = new Scene();
        Scene scene02 = new Scene();
        Scene scene03 = new Scene();
        Scene scene04 = new Scene();
        Scene scene05 = new Scene();
        Material defaultMaterial = new Material(50f,0f,0,1);
        Material nullmaterial = null;

        Material reflectiveMaterial = new Material(50f,.7f,0,1);
        Material refractiveMaterial = new Material(50f,0f,0.75f,1.5f);
        Material reflectiverefractiveMaterial = new Material(50f,.30f,.30f,1);
        scene01.setCoefficients(.1f,1,5);
        scene02.setCoefficients(.1f,1,5);
        scene03.setCoefficients(.1f,1,5);
        scene04.setCoefficients(.1f,1,5);
        scene05.setCoefficients(.1,1,5);

        scene01.setCamera(new Camera(new Vector3D(0, 0, -8), 160, 160, 800, 800, 8.2f, 75f,nullmaterial));
        scene02.setCamera(new Camera(new Vector3D(0, 0, -8), 160, 160, 2400, 2400, 2f, 50f,nullmaterial));
        scene03.setCamera(new Camera(new Vector3D(0, 0, -10), 160, 160, 800, 800, 2f, 50f,nullmaterial));
        scene04.setCamera(new Camera(new Vector3D(0, 0, -2), 160, 160, 1200, 1200, 2f, 50f,nullmaterial));
        scene05.setCamera(new Camera(new Vector3D(.5, .5, -2), 160, 160, 2400, 2400, 2f, 50f,nullmaterial));

        //Pacman Scene
        scene02.addLight(new PointLight(new Vector3D(-2f, 0f, 1f), Color.WHITE, 50,nullmaterial));
        scene02.addLight(new PointLight(new Vector3D(-1.7f, 1f, -.5f), new Color(42, 42, 36, 255), 10,nullmaterial));
        scene02.addLight(new PointLight(new Vector3D(-1.7f, 3f, 0f), Color.white, 30,nullmaterial));
        scene02.addLight(new PointLight(new Vector3D(-2f, -.3f, -3f), Color.white, 20,nullmaterial));
        Material PacMaterial = new Material(20f,.1f,0,1);
        scene02.addObject(OBJReader.GetPolygon("PacMan (3).obj", new Vector3D(-2f, 1f, 2f), new Color(244, 253, 24),PacMaterial));
        scene02.addObject(new Sphere(new Vector3D(-1f, 1f, 1.5f), 0.5f, Color.white,defaultMaterial));
        scene02.addObject(new Sphere(new Vector3D(0f, .5f, 1f), 0.5f, Color.white,reflectiveMaterial));
        scene02.addObject(new Sphere(new Vector3D(1f, 0f, .5f), 0.5f, Color.white,defaultMaterial));
        scene02.addObject(new Sphere(new Vector3D(2f, -.5f, 0f), 0.5f, Color.white,reflectiveMaterial));
        scene02.addObject(OBJReader.GetPolygon("LargePlatform.obj", new Vector3D(0,-1.5f,3f),new Color(28, 37, 242),reflectiveMaterial));
        scene02.addObject(new Sphere(new Vector3D(-1.2f, -.2f, -4f), 0.3f, Color.white,refractiveMaterial));
        scene02.addObject(OBJReader.GetPolygon("Cube.obj", new Vector3D(-2f, -1f, -1f), Color.MAGENTA, defaultMaterial));

        //scene02.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(0f, -.75f, 0f), Color.white, defaultMaterial));
        //scene01.addLight(new DirectionalLight(Vector3D.ZERO(), new Vector3D(0.0, 0.0, 1.0), Color.WHITE, 0.8));
        //scene01.addLight(new DirectionalLight(Vector3D.ZERO(), new Vector3D(0.0, -0.1, 0.1), Color.WHITE, 0.2));
        //scene01.addLight(new DirectionalLight(Vector3D.ZERO(), new Vector3D(0, -0.1, 0.0), Color.WHITE, 1));
        //scene01.addLight(new PointLight(new Vector3D(-3f, 2.5, 3f), Color.white, 50));
        //scene01.addLight(new PointLight(new Vector3D(3f, 4, 8f), new Color(239, 91, 253), 50,nullmaterial));
        scene01.addLight(new PointLight(new Vector3D(1f, 1, -6f), Color.white, 50,nullmaterial));
        scene01.addLight(new PointLight(new Vector3D(0f, 2f, 3f), Color.WHITE, 100,nullmaterial));
        scene01.addLight(new PointLight(new Vector3D(0f, -1f, 3f), Color.WHITE, 100,nullmaterial));
        Material ballMaterial= new Material(50,.5f,0,1);
        scene01.addObject(new Sphere(new Vector3D(3.85f, 1f, 4.5f), 0.5f, Color.PINK,ballMaterial));
        Material ballMaterial2= new Material(75,0.5f,0.3f,1.5f);
        //scene01.addObject(new Sphere(new Vector3D(0f, 1f, 2f), 0.5f, Color.WHITE,ballMaterial2));
        scene01.addObject(new Sphere(new Vector3D(3.85f, 1f, 3.5f), 0.5f, Color.CYAN,ballMaterial));
        scene01.addObject(OBJReader.GetPolygon("Ring.obj", new Vector3D(0, -0.15f, 3), Color.white,defaultMaterial));
        Material potMaterial= new Material(40,1,0,1);
        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(0f, .20f, 3f), Color.white,potMaterial));
        //scene01.addObject(OBJReader.GetPolygon("Bunny.obj", new Vector3D(-1.5f, 1f, 2f), Color.BLUE,potMaterial));
        scene01.addObject(OBJReader.GetPolygon("LargePlatform.obj", new Vector3D(0,-4f,10f),Color.MAGENTA,defaultMaterial));


        //scene03.addObject(new Sphere( new Vector3D(0f, 0f, -5f),1f, Color.WHITE, reflectiveMaterialGlass));
        //scene03.addObject(OBJReader.GetPolygon("Cube.obj", new Vector3D(0f, -.5f, -3f), Color.green, refractiveMaterial));
        scene03.addObject(new Sphere( new Vector3D(-1.3f, 0f, -5f),1f, Color.WHITE, refractiveMaterial));

        scene03.addObject(OBJReader.GetPolygon("Cube.obj", new Vector3D(2f, 0f, 4f), Color.MAGENTA, reflectiveMaterial));
        scene03.addObject(OBJReader.GetPolygon("CubeQuad.obj", new Vector3D(0f, 0.5f, 5f), Color.RED, defaultMaterial));
        scene03.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(-.6f, -.75f, -1.5f), Color.white, defaultMaterial));
        scene03.addObject(OBJReader.GetPolygon("LargePlatform.obj", new Vector3D(0,-1.5f,3f),new Color(242, 120, 122),reflectiveMaterial));
        scene03.addObject(new Sphere(new Vector3D(-2.5f, 3f, 2f) ,.7f, Color.pink, reflectiveMaterial));
        scene03.addLight(new PointLight(new Vector3D(0,5,-8),Color.white,10,nullmaterial));
        scene03.addLight(new PointLight(new Vector3D(2f, 3f, 0f), new Color(172, 242, 115), 20f, nullmaterial));
        Material material_Teapot = new Material(35f,.3f,0,1);
        Material refractiveMaterial2 = new Material(50,0,.25f,1);
        scene04.addObject(OBJReader.GetPolygon("LargePlatform.obj", new Vector3D(0,-1.5f,12f),new Color(242, 120, 122),defaultMaterial));
        scene04.addLight(new PointLight(new Vector3D(0f, 1f, 10f), Color.white, 75f, nullmaterial));
        scene04.addLight(new PointLight(new Vector3D(3f, 2f, 6f), new Color(255, 253, 122), 50f, nullmaterial));
        scene04.addLight(new DirectionalLight(Vector3D.ZERO(), new Vector3D(0, -0.1, 0.0), Color.WHITE, 0.2,nullmaterial));
        scene04.addObject(new Sphere(new Vector3D(-2.5f, 2f, 20f) ,2f, new Color(221, 91, 253), reflectiveMaterial));
        scene04.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(2.5f, -1.5f, 10f), new Color(40, 99, 91, 226), refractiveMaterial2));
        scene04.addObject(new Sphere(new Vector3D(1.5f, -.6f, 6f) ,.4f, new Color(178, 246, 253), refractiveMaterial));
        scene04.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(-.3f, -.7f, 14f), new Color(255, 211, 174),material_Teapot));


        for(int i = 0 ; i<3;i++){
                for(int j = 0 ; j<3;j++) {
                scene05.addObject(new Sphere(new Vector3D(i*1f, j*1f, 16f) ,.5f, new Color(249 -i*60, 247-i*j*18, 253-j*60), reflectiveMaterial));
            }
        }
        scene05.addObject(OBJReader.GetPolygon("Cube.obj", new Vector3D(.9f, .35f, 14f), new Color(255, 255, 255, 226), defaultMaterial));
        Material material_floor = new Material(5f,.5f,0,1);
        scene05.addObject(OBJReader.GetPolygon("LargePlatform.obj", new Vector3D(0,-.8f,12f),new Color(185, 242, 129),material_floor));

        scene05.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(3.8f, -.7f, 12f), new Color(255, 40, 50),material_Teapot));
        scene05.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(-1.5f, -.7f, 12f), new Color(55, 48, 255),material_Teapot));
        scene05.addLight(new PointLight(new Vector3D(2.5,1.5,15),Color.white,50,nullmaterial));
        scene05.addLight(new PointLight(new Vector3D(2.5,2,12),Color.white,50,nullmaterial));
        scene05.addLight(new PointLight(new Vector3D(0,.4,8),Color.white,50,nullmaterial));

        BufferedImage image = raytrace(scene05);
        File outputImage = new File("./FinalRenders/image02.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException ioe) {
            System.out.println("Something failed");
        }
        System.out.println(intialdate);
        System.out.println(new Date());
    }

    /**
     * In charge of starting to create the raytrace of the given scene
     * @param scene
     * @return
     */
    public static BufferedImage raytrace(Scene scene) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        Camera mainCamera = scene.getCamera();
        ArrayList<Light> lights = scene.getLights();
        float[] nearFarPlanes = mainCamera.getNearFarPlanes();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        ArrayList<Object3D> objects = scene.getObjects();
        Vector3D[][] positionsToRaytrace = mainCamera.calculatePositionsToRay();
        totalPixels=positionsToRaytrace.length*positionsToRaytrace[0].length;

        for (int i = 0; i < positionsToRaytrace.length; i++) {
            for (int j = 0; j < positionsToRaytrace[i].length; j++) {

                Runnable runnable = RayProcess(image,i,j,objects,positionsToRaytrace,mainCamera,nearFarPlanes,lights,scene);
                executorService.execute(runnable);

            }
        }
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(1, TimeUnit.HOURS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!executorService.isTerminated()){
                System.err.println("Cancel non-finished");
            }
        }
        executorService.shutdownNow();

        return image;
    }

    /**
     * Paralelized function that is in charge of generate a color for each pixel that its given
     * considering all the lights , objects and how they interact with each other depending on their material
     * @param image
     * @param i
     * @param j
     * @param objects
     * @param positionsToRaytrace
     * @param mainCamera
     * @param nearFarPlanes
     * @param lights
     * @param scene
     * @return
     */
    public static Runnable RayProcess(BufferedImage image,int i,int j,ArrayList<Object3D> objects,Vector3D[][] positionsToRaytrace,Camera mainCamera,float[]nearFarPlanes,ArrayList<Light> lights,Scene scene){

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
                double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
                double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();

                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                float cameraZ = (float) mainCamera.getPosition().getZ();
                Intersection closestIntersection = raycast(ray, objects, (Object3D) null, new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});

                Color pixelColor = Color.BLACK;
                if (closestIntersection != null) {
                    Intersection intersectionWithObjectsToLight=null;
                    Object3D object=closestIntersection.getObject();
                    boolean inLight=false;

                        for (Light light : lights) {
                            Ray rayShadow = new Ray(closestIntersection.getPosition(),Vector3D.substract(light.getPosition(),closestIntersection.getPosition()));
                            intersectionWithObjectsToLight=raycast(rayShadow,scene.getObjects(),object,new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                            if(intersectionWithObjectsToLight==null ){
                                inLight=true;
                            }
                        }
                    if(!inLight){
                        //add ambient
                        pixelColor=Color.BLACK;
                        //Color clDarker = Blend(object.getColor(), Color.black, (float) scene.getAmbient_coefficient());
                        //pixelColor=clDarker;
                    }else {
                        pixelColor = Color.BLACK;
                        for (Light light : lights) {

                            Color calculatedColor=calculateColor(light,closestIntersection,object,scene,ray.getDirection());
                            pixelColor=addColor(pixelColor,calculatedColor);
                            float rest = 0;
                            boolean restDefined = false;

                            if(object.getMaterial().getRefractivity()>0.0 && object.getMaterial().getReflectivity()>0.0){
                                rest = 1 - object.getMaterial().getRefractivity() - object.getMaterial().getReflectivity();
                                restDefined=true;
                            }
                            if(object.getMaterial().getRefractivity()>0.0){

                                int counter=0;
                                Ray refractorRay = createRayRefractor(ray.getDirection(),closestIntersection.getNormal(),closestIntersection.getPosition(),null,object);
                                Intersection newIntersection = refractorMethod(refractorRay,objects,object,new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]},counter,closestIntersection);
                                if(newIntersection!=null){
                                    Color refractColor=calculateColor(light,newIntersection,object,scene,ray.getDirection());
                                    float percentageR= object.getMaterial().getRefractivity();
                                     if(!restDefined){
                                         rest = 1 - percentageR;
                                     }
                                    pixelColor=addColor(pixelColor,refractColor,rest,percentageR);
                                }
                            }
                            if(object.getMaterial().getReflectivity()>0.0) {

                                Ray reflectRay = createRayReflection(ray.getDirection(),closestIntersection.getNormal(),closestIntersection.getPosition());
                                int counter=0;
                                Intersection newIntersection = reflectMethod(reflectRay,objects,object,new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]},counter,closestIntersection);
                                if(newIntersection!=null){
                                    Color reflectColor=calculateColor(light,newIntersection,object,scene,ray.getDirection());
                                    float percentageR= object.getMaterial().getReflectivity();
                                    if(!restDefined){
                                        rest = 1 - percentageR;
                                    }
                                    pixelColor=addColor(pixelColor,reflectColor,rest,percentageR);
                                }

                            }
                        }

                    }

                }
                setRGB(image,i, j, pixelColor);
                percentageDone();
            }
        };

        return runnable;

    }

    /**
     * returns the given value if it does respect the limits and if doesn't it
     * returns the limit he is closest to.
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Simply adds the values of the given colors and returns the color calculated.
     * @param original
     * @param otherColor
     * @return
     */
    public static Color addColor(Color original, Color otherColor){
        float red = clamp((original.getRed() / 255.0f) + (otherColor.getRed() / 255.0f), 0, 1);
        float green = clamp((original.getGreen() / 255.0f) + (otherColor.getGreen() / 255.0f), 0, 1);
        float blue = clamp((original.getBlue() / 255.0f) + (otherColor.getBlue() / 255.0f), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     * Add the colors but in a percentage way so it can be more inclined to one of the colors.
     * @param original
     * @param otherColor
     * @param percentege1
     * @param percentege2
     * @return
     */
    public static Color addColor(Color original, Color otherColor,float percentege1,float percentege2){
        float red = clamp(((original.getRed() / 255.0f)*percentege1) + ((otherColor.getRed() / 255.0f)*percentege2), 0, 1);
        float green = clamp(((original.getGreen() / 255.0f)*percentege1) + ((otherColor.getGreen() / 255.0f)*percentege2), 0, 1);
        float blue = clamp(((original.getBlue() / 255.0f)*percentege1) + ((otherColor.getBlue() / 255.0f)*percentege2), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     * In charge of casting the ray and asking every object if it was hit and it returns
     * the nearest intersection it has.
     * @param ray
     * @param objects
     * @param caster
     * @param clippingPlanes
     * @return
     */
    public static Intersection raycast(Ray ray, ArrayList<Object3D> objects, Object3D caster, float[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            if (caster == null || !currentObj.equals(caster)) {
                Intersection intersection = currentObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersection.getPosition().getZ() >= clippingPlanes[0] &&
                                    intersection.getPosition().getZ() <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }

            }else if(caster != null && currentObj.equals(caster)){
                Ray rayPlus = new Ray(Vector3D.add(ray.getOrigin(),Vector3D.scalarMultiplication(ray.getDirection(),0.001f)),ray.getDirection());
                Intersection intersection = currentObj.getIntersection(rayPlus);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    if (distance >= 0 && (closestIntersection == null || distance < closestIntersection.getDistance())) {
                        closestIntersection = intersection;
                    }
                }

            }

        }

        return closestIntersection;
    }

    /**
     * Calculates the the reflective ray for a given direction of the intersection and normal of where it intersected.
     * @param direction
     * @param normal
     * @param position
     * @return
     */
    public static Ray createRayReflection(Vector3D direction,Vector3D normal,Vector3D position){
        Vector3D reflectDir = Vector3D.substract(direction,Vector3D.scalarMultiplication(Vector3D.normalize(normal),2*Vector3D.dotProduct(direction,Vector3D.normalize(normal))));
        //Vector3D reflectRayPosition = Vector3D.add(closestIntersection.getPosition(),Vector3D.scalarMultiplication(reflectDir,0.01f));
        Vector3D reflectRayPosition = position;
        return new Ray(reflectRayPosition,reflectDir);
    }

    /**
     * Calculates the the refractive ray for a given direction of the intersection and normal of where it intersected.
     * and also considers the refraction index that the objects have in their material
     * @param direction
     * @param normal
     * @param position
     * @param object
     * @param object2
     * @return
     */
    public static Ray createRayRefractor(Vector3D direction,Vector3D normal,Vector3D position,Object3D object, Object3D object2){
        direction = Vector3D.normalize(direction);

        double indexRefraction1 = 1;
        if(object!=null){
            indexRefraction1=object.getMaterial().getIndexRefractivity();
        }

        double indexRefraction2 = object2.getMaterial().getIndexRefractivity();
        double indexRefraction = indexRefraction1 / indexRefraction2;
        Vector3D directionRayRefraction = Vector3D.add(Vector3D.scalarMultiplication(direction, indexRefraction), Vector3D.scalarMultiplication(normal, indexRefraction * indexRefraction1 - indexRefraction2));

        Ray refractionRay = new Ray(position, directionRayRefraction);
        return refractionRay;
    }

    /**
     * Recursive method that is in charge of finding the reflective intersection that should be used to have a proper reflection
     * it has a limit of 5 times recursion.
     * @param reflectRay
     * @param objects
     * @param object
     * @param planes
     * @param counter
     * @param intersection
     * @return
     */
    public static Intersection reflectMethod(Ray reflectRay,ArrayList<Object3D> objects,Object3D object,float[] planes,int counter,Intersection intersection){

        Intersection newIntersection = raycast(reflectRay,objects,object,planes);

        if(newIntersection==null){
            return intersection;
        }else if(newIntersection.getObject().getMaterial().getReflectivity()<=0){
            return newIntersection;
        }else{
            Ray ray=createRayReflection(reflectRay.getDirection(),newIntersection.getNormal(),newIntersection.getPosition());
            counter++;
            if(counter>=5){return newIntersection;}
            return reflectMethod(ray,objects,newIntersection.getObject(),planes,counter,newIntersection);
        }

    }

    /**
     * it calculates the intersection which upon the refracted object will actually be calculated
     * @param refractRay
     * @param objects
     * @param object
     * @param planes
     * @param counter
     * @param intersection
     * @return
     */
    public static Intersection refractorMethod(Ray refractRay,ArrayList<Object3D> objects,Object3D object,float[] planes,int counter,Intersection intersection){

        Intersection newIntersection = raycast(refractRay,objects,object,planes);

        if(newIntersection==null){
            return intersection;
        }else if(newIntersection.getObject().getMaterial().getRefractivity()<=0){
            return newIntersection;
        }else{
            Ray ray=createRayRefractor(refractRay.getDirection(),newIntersection.getNormal(),newIntersection.getPosition(),object,newIntersection.getObject());
            counter++;
            if(counter>=6){return newIntersection;}
            return refractorMethod(ray,objects,newIntersection.getObject(),planes,counter,newIntersection);
        }
    }

    /**
     * paints the pixel of the image of the given color on the position it has
     * @param image
     * @param x
     * @param y
     * @param pixelColor
     */
    public static synchronized void setRGB(BufferedImage image,int x, int y, Color pixelColor){
        image.setRGB(x, y, pixelColor.getRGB());
    }

    /**
     * prints the progress of the creation of the image
     */
    public static synchronized void percentageDone(){
        done++;
        if(donePorcentage!=done*100/totalPixels){
            donePorcentage = done * 100 / totalPixels;
            System.out.println("Rendering: " + donePorcentage);
        }
    }


    /**
     * Calculates the color for the given intersection considering the light given
     * It calculates considering the blinn phong algorithm, the light intensity and intensity
     * all upon the intersection that is given.
     * @param light
     * @param closestIntersection
     * @param object
     * @param scene
     * @param direction
     * @return
     */
    public static Color calculateColor(Light light,Intersection closestIntersection, Object3D object, Scene scene, Vector3D direction){
        Color pixelColor = Color.black;
        Vector3D halfwayVector = Vector3D.scalarMultiplication(Vector3D.add(Vector3D.substract(light.getPosition(), closestIntersection.getPosition()), direction),1/Vector3D.magnitude(Vector3D.add(Vector3D.substract(light.getPosition(), closestIntersection.getPosition()), direction)));
        double specular = Math.pow(Vector3D.dotProduct(closestIntersection.getNormal(),halfwayVector),object.getMaterial().getShininess());

        float nDotL = light.getNDotL(closestIntersection);
        float intensity = (float) light.getIntensity() * nDotL;
        if (!(light instanceof DirectionalLight)) {
            intensity = (float) light.getIntensity(closestIntersection.getDistance()) * nDotL;
        }
        Color lightColor = light.getColor();
        Color objColor = closestIntersection.getObject().getColor();
        float[] lightColors = new float[]{lightColor.getRed() / 255.0f, lightColor.getGreen() / 255.0f, lightColor.getBlue() / 255.0f};
        float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
        float[] objBlinnColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
            objColors[colorIndex] *= scene.getDiffuse_coefficient()*intensity * lightColors[colorIndex];
        }
        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
            objBlinnColors[colorIndex] *= scene.getSpecular_coefficient()*specular * lightColors[colorIndex] *intensity;
        }

        Color diffuse = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
        Color specularC = new Color(clamp(objBlinnColors[0], 0, 1), clamp(objBlinnColors[1], 0, 1), clamp(objBlinnColors[2], 0, 1));

        pixelColor = addColor(pixelColor, diffuse);
        pixelColor = addColor(pixelColor,specularC);
        return pixelColor;
    }

}
