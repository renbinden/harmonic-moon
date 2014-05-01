package io.github.lucariatias.harmonicmoon.particle;


public class Vector3D {

    public double x;
    public double y;
    public double z;

    public Vector3D(double numX, double numY, double numZ) {
        this.x = numX;
        this.y = numY;
        this.z = numZ;
    }

    public void setNum(double numX, double numY, double numZ) {
        this.x = numX;
        this.y = numY;
        this.z = numZ;
    }

    public void add(Vector3D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public void sub(Vector3D other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
    }

    public void mult(Vector3D other) {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
    }

    public void div(Vector3D other) {
        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;
    }

    public void add(double num) {
        this.x += num;
        this.y += num;
        this.z += num;
    }

    public void sub(double num) {
        this.x -= num;
        this.y -= num;
        this.z -= num;
    }

    public void mult(double num) {
        this.x *= num;
        this.y *= num;
        this.z *= num;
    }

    public void div(double num) {
        this.x /= num;
        this.y /= num;
        this.z /= num;
    }

    public double dist(Vector3D other) {
        double xd = other.x - this.x;
        double yd = other.y - this.y;

        return (xd * xd) + (yd * yd);
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public void normalize() {
        double m = mag();
        if (m != 0) {
            div(m);
        }
    }

    public static Vector3D mult(Vector3D vec, double num) {
        return new Vector3D(vec.x * num, vec.y * num, vec.z * num);
    }

    public static Vector3D sub(Vector3D vec, Vector3D vect) {
        return new Vector3D(vec.x - vect.x, vec.y - vect.y, vec.z - vect.z);
    }

    public static Vector3D add(Vector3D vec, double num) {
        return new Vector3D(vec.x + num, vec.y + num, vec.z + num);
    }

}
