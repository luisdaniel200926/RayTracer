/**
 * [1968] - [2020] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package project.raytracer.objects;

import project.raytracer.IIntersectable;
import project.raytracer.Intersection;
import project.raytracer.Ray;
import project.raytracer.Vector3D;

/**
 * Class to create a triangle
 * with it's vertex
 */
public class Triangle implements IIntersectable {
    /**
     * Epsilon is a really small value that we use in order to identify if a ray has intersected of it's near the ends of the triangle
     */
    public static final double EPSILON = 0.000000001;
    /**
     * vertices keeps the positions of the triangles vertices
     */
    private Vector3D[] vertices;
    /**
     * vertices keeps the normals of each of the triangles vertices
     */
    private Vector3D[] normals;

    /**
     * Constructor for a triangle, just need all vertex positions
     * @param vertex1
     * @param vertex2
     * @param vertex3
     */
    public Triangle(Vector3D vertex1, Vector3D vertex2, Vector3D vertex3) {
        setVertices(vertex1, vertex2, vertex3);
        setNormals(null);
    }

    /**
     * Constructor for a triangle, needs all vertex positions and normals of each vertex
     * @param vertex1
     * @param vertex2
     * @param vertex3
     * @param normal1
     * @param normal2
     * @param normal3
     */
    public Triangle(Vector3D vertex1, Vector3D vertex2, Vector3D vertex3, Vector3D normal1, Vector3D normal2, Vector3D normal3) {
        this(vertex1, vertex2, vertex3);
        setNormals(normal1, normal2, normal3);
    }

    /**
     * Constructor by receiving vertex and normals in an array format
     * checks it has all data needed.
     * @param vertices
     * @param normal
     */
    public Triangle(Vector3D[] vertices, Vector3D[] normal) {
        if (vertices.length == 3) {
            setVertices(vertices[0], vertices[1], vertices[2]);
        } else {
            setVertices(Vector3D.ZERO(), Vector3D.ZERO(), Vector3D.ZERO());
        }
        setNormals(normal);
    }

    /**
     * returns all vertex of the triangle
     * @return
     */
    public Vector3D[] getVertices() {
        return vertices;
    }

    /**
     * Sets the triangle vertices positions to the ones given.
     * @param vertex1
     * @param vertex2
     * @param vertex3
     */
    public void setVertices(Vector3D vertex1, Vector3D vertex2, Vector3D vertex3) {
        Vector3D[] vertices = new Vector3D[]{vertex1, vertex2, vertex3};
        setVertices(vertices);
    }

    /**
     * Sets the triangle vertices positions in array form to the ones given.
     * @param vertices
     */
    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }

    /**
     * returns the normals of the triangle if it has ones assigned.
     * @return
     */
    public Vector3D[] getNormals() {
        if(normals == null){
            Vector3D normal = getNormal();
            setNormals(new Vector3D[]{normal, normal, normal});
        }
        return normals;
    }

    //Modified

    /**
     * if it has none normals assigned it calculates them
     * and returns the normal of the triangle itself.
     * @return normal of the triangle
     */
    public Vector3D getNormal() {
        Vector3D normal = Vector3D.ZERO();

        Vector3D[] normals = this.normals;
        if (normals == null) {
            Vector3D[] vertices = getVertices();
            Vector3D v = Vector3D.substract(vertices[1], vertices[0]);
            Vector3D w = Vector3D.substract(vertices[2], vertices[0]);

            normal = Vector3D.scalarMultiplication(Vector3D.normalize(Vector3D.crossProduct(v, w)), -1.0);
        } else {
            for(int i = 0; i < normals.length; i++){
                normal.setX(normal.getX() + normals[i].getX());
                normal.setY(normal.getY() + normals[i].getY());
                normal.setZ(normal.getZ() + normals[i].getZ());
            }
            normal.setX(normal.getX() / normals.length);
            normal.setY(normal.getY() / normals.length);
            normal.setZ(normal.getZ() / normals.length);
        }

        return normal;
    }

    /**
     * sets normals to the ones given in array form.
     * @param normals
     */
    public void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }

    /**
     * sets normals to the ones given
     * @param normal1
     * @param normal2
     * @param normal3
     */
    public void setNormals(Vector3D normal1, Vector3D normal2, Vector3D normal3) {
        Vector3D[] normals = new Vector3D[]{normal1, normal2, normal3};
        setNormals(normals);
    }

    /**
     ** Checks if the triangle is hit by the ray
     *      *
     *      * if it hits it returns the intersection object created
     *      * @param ray
     *      * @return
     * @see <a href="https://cadxfem.org/inf/Fast%20MinimumStorage%20RayTriangle%20Intersection.pdf">Moller-Trumbore intersection algorithm</a>
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        Intersection intersection = new Intersection(null, -1, null, null);

        Vector3D[] vertices = getVertices();
        Vector3D v2v0 = Vector3D.substract(vertices[2], vertices[0]);
        Vector3D v1v0 = Vector3D.substract(vertices[1], vertices[0]);
        Vector3D vectorP = Vector3D.crossProduct(ray.getDirection(), v1v0);
        double determinant = Vector3D.dotProduct(v2v0, vectorP);
        double invertedDeterminant = 1.0 / determinant;
        Vector3D vectorT = Vector3D.substract(ray.getOrigin(), vertices[0]);
        double u = Vector3D.dotProduct(vectorT, vectorP) * invertedDeterminant;
        if (u < 0 || u > 1) {
            return intersection;
        }

        Vector3D vectorQ = Vector3D.crossProduct(vectorT, v2v0);
        double v = Vector3D.dotProduct(ray.getDirection(), vectorQ) * invertedDeterminant;
        if (v < 0 || (u + v) > (1.0 + EPSILON)) {
            return intersection;
        }

        double t = Vector3D.dotProduct(vectorQ, v1v0) * invertedDeterminant;
        intersection.setDistance(t);

        return intersection;
    }
}
