/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer.objects;

import project.raytracer.Intersection;
import project.raytracer.Material;
import project.raytracer.Ray;
import project.raytracer.Vector3D;
import project.raytracer.tools.Barycentric;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jafet Rodríguez
 */
public class Polygon extends Object3D {

    public List<Triangle> triangles;

    public List<Triangle> getTriangles() {
        return triangles;
    }

    /**
     * Constructor to create a polygon
     * @param position
     * @param triangles
     * @param color
     * @param material
     */
    public Polygon(Vector3D position, Triangle[] triangles, Color color, Material material){
        super(position, color,material);
        setTriangles(triangles);
    }

    /**
     * sets all the triangles for the polygon
     * @param triangles
     */
    public void setTriangles(Triangle[] triangles) {
        Vector3D position = getPosition();
        Set<Vector3D> uniqueVertices = new HashSet<Vector3D>();
        for(Triangle triangle : triangles){
            uniqueVertices.addAll(Arrays.asList(triangle.getVertices()));
        }

        for(Vector3D vertex : uniqueVertices){
            vertex.setX(vertex.getX() + position.getX());
            vertex.setY(vertex.getY() + position.getY());
            vertex.setZ(vertex.getZ() + position.getZ());
        }

        this.triangles = Arrays.asList(triangles);
    }

    /**
     * calculates if a ray hits any of the triangles and returns the intersections with the closest one
     * @param ray
     * @return
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        double distance = -1;
        Vector3D normal = Vector3D.ZERO();
        Vector3D position = Vector3D.ZERO();

        for(Triangle triangle : getTriangles()){
            Intersection intersection = triangle.getIntersection(ray);
            double intersectionDistance = intersection.getDistance();

            if(intersection != null && intersectionDistance > 0  && (intersectionDistance < distance ||distance < 0) && intersectionDistance>1e-12){
                distance = intersectionDistance;
                position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));

                normal = Vector3D.ZERO();
                double[] uVw = Barycentric.CalculateBarycentricCoordinates(position, triangle);
                Vector3D[] normals = triangle.getNormals();
                for(int i = 0; i < uVw.length; i++) {
                    normal = Vector3D.add(normal, Vector3D.scalarMultiplication(normals[i], uVw[i]));
                }
            }
        }

        if(distance == -1){
            return null;
        }

        return new Intersection(position, distance, normal, this);
    }




}
