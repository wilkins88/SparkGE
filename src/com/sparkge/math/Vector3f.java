package com.sparkge.math;

/**
 * @description Class for working with 3 dimensional vectors
 * @author Thomas Wilkins | twilkins@radialspark.com
 * @history
 *  2018-06-02 | Thomas Wilkins | Created
 */

public class Vector3f {
    /**
     * @description the x, y, and z components of the vector (assume origin)
     */
    private float x, y, z;

    /**
     * @description Init vector with provided components
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param z the component of the vector
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @description Init the vector with 0 x, y, and z component values
     */
    public Vector3f() {
        this(0, 0, 0);
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z +">";
    }

    /**
     * @description determines if two vectors are equal
     * @param v the vector to compare
     * @return whether or not the vectors are equal
     */
    public boolean equals(Vector3f v) {
        return (this.x == v.getX() && this.y == v.getY() && this.z == v.getZ());
    }

    /**
     * @description gets the magnitude squared -- useful for size comparisons
     * @return the squared magnitude of the vector
     */
    public float getMagnitudeSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    /**
     * @description returns the magnitude of the vector
     * @return the magnitude of the vector
     */
    public float getMagnitude() {
        return (float)Math.sqrt(this.getMagnitudeSquared());
    }

    /**
     * @description returns the normalized unit vector u = v / |v|
     * @return the unit vector
     */
    public Vector3f getNormalizedVector() {
        float magnitude = this.getMagnitude();
        return new Vector3f(
                this.x / magnitude,
                this.y / magnitude,
                this.z / magnitude
        );
    }

    /**
     * @description Vector addition
     * @param v the vector to be added to this vector
     * @return a new vector resulting from the sum
     */
    public Vector3f add(Vector3f v) {
        return new Vector3f(
                this.x + v.getX(),
                this.y + v.getY(),
                this.z + v.getZ()
        );
    }

    /**
     * @description In-place vector addition
     * @param v the vector to be added
     * @return the calling vector
     */
    public Vector3f addEq(Vector3f v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
        return this;
    }

    /**
     * @description Vector subtraction
     * @param v the vector to be subtracted
     * @return a new vector resulting from the subtraction
     */
    public Vector3f sub(Vector3f v) {
        return new Vector3f(
                this.x - v.getY(),
                this.y - v.getY(),
                this.z - v.getZ()
        );
    }

    /**
     * @description In-place vector subtraction
     * @param v the vector to be subtracted
     * @return the calling vector
     */
    public Vector3f subEq(Vector3f v) {
        this.x -= v.getX();
        this.y -= v.getY();
        this.z -= v.getZ();
        return this;
    }

    /**
     * @description component-wise multiplication for vectors
     * @param v the component to be multiplied
     * @return a new vector resulting from the multiplication
     */
    public Vector3f mul(Vector3f v) {
        return new Vector3f(
                this.x * v.getX(),
                this.y * v.getY(),
                this.z * v.getZ()
        );
    }

    /**
     * @description In-place component-wise multiplication
     * @param v the vector to be multiplied
     * @return the calling vector
     */
    public Vector3f mulEq(Vector3f v) {
        this.x *= v.getX();
        this.y *= v.getY();
        this.z *= v.getZ();
        return this;
    }

    /**
     * @description Component-wise division
     * @param v the vector to divide by
     * @return a new vector resulting from the division
     */
    public Vector3f div(Vector3f v) {
        return new Vector3f(
                this.x / v.getX(),
                this.y / v.getY(),
                this.z / v.getZ()
        );
    }

    /**
     * @description In-place component-wise division
     * @param v the vector to divide by
     * @return the calling vector
     */
    public Vector3f divEq(Vector3f v) {
        this.x /= v.getX();
        this.y /= v.getY();
        this.z /= v.getZ();
        return this;
    }

    /**
     * @description computes the dot product for 2 vectors
     * @param v the vector to be dotted
     * @return the dot product scalar value
     */
    public float dot(Vector3f v) {
        return this.x * v.getX() + this.y * v.getY() + this.z * v.getZ();
    }

    /**
     * @description calculates the cross product of 2 vectors
     * @param v the vector to be crossed with
     * @return a new vector resulting from the cross
     */
    public Vector3f cross(Vector3f v) {
        return new Vector3f(
          this.y * v.getZ() - this.z * v.getY(),
          this.z * v.getX() - this.x * v.getZ(),
          this.x * v.getY() - this.y * v.getX()
        );
    }

    /**
     * @description in place cross product of 2 vectors
     * @param v the vector to be crossed with
     * @return the calling vector
     */
    public Vector3f crossEq(Vector3f v) {
        float newX = this.y * v.getZ() - this.z * v.getY();
        float newY = this.z * v.getX() - this.x * v.getZ();
        float newZ = this.x * v.getY() - this.y * v.getX();
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        return this;
    }

    /**
     * @description scales the vector
     * @param value the value to scale by
     * @return a new vector resulting from teh scaling
     */
    public Vector3f scale(float value) {
        return new Vector3f(
                this.x * value,
                this.y * value,
                this.z * value
        );
    }

    /**
     * @description In-place vector scaling
     * @param value the value to scale by
     * @return the calling vector
     */
    public Vector3f scaleEq(float value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;
        return this;
    }

    /**
     * @description  Applies a translation to the vector
     * @param xDif the distance to move the x component
     * @param yDif the distance to move the y component
     * @param zDif the distance to move the z component
     * @return a new vector resulting from the translation
     */
    public Vector3f translate(float xDif, float yDif, float zDif) {
        return new Vector3f(
                this.x + xDif,
                this.y + yDif,
                this.z + zDif
        );
    }

    /**
     * @description In place vector translation
     * @param xDif the distance to move the x component
     * @param yDif the distance to move the y component
     * @param zDif the distance to move the z component
     * @return the calling vector
     */
    public Vector3f translateEq(float xDif, float yDif, float zDif) {
        this.x += xDif;
        this.y += yDif;
        this.z += zDif;
        return this;
    }

    /**
     * @description rotates the vector around provided angle
     * @param v vector (axis) to rotate around
     * @param angle the angle to rotate around the axis
     * @return new rotated vector
     */
    public Vector3f rotate(Vector3f v, float angle) {
        Quaternion q = new Quaternion(v.getX(), v.getY(), v.getZ(), angle);
        Quaternion result = q.mul(new Quaternion(this.x, this.y, this.z, 0)).mul(q.getConjugate());
        return new Vector3f(result.getX(), result.getY(), result.getZ());
    }

    /**
     * @description In place rotation of a vector
     * @param angle angle in degrees
     * @return the calling vector
     */
    public Vector3f rotateEq(Vector3f v, float angle) {
        Quaternion q = new Quaternion(v.getX(), v.getY(), v.getZ(), angle);
        Quaternion result = q.mul(new Quaternion(this.x, this.y, this.z, 0)).mul(q.getConjugate());
        this.x = result.getX();
        this.y = result.getY();
        this.z = result.getZ();
        return this;
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public float getZ() {
        return this.z;
    }
    public Vector3f setX(float x) {
        this.x = x;
        return this;
    }
    public Vector3f setY(float y) {
        this.y = y;
        return this;
    }
    public Vector3f setZ(float z) {
        this.z = z;
        return this;
    }
}
