package com.sparkge.math;

public class Vector2f {
    /**
     * @description the x and y components of the vector (assume origin)
     */
    private float x, y;

    /**
     * @description Init vector with provided components
     * @param x the x component of the vector
     * @param y the y component of the vector
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @description Init the vector with 0 x and y component values
     */
    public Vector2f() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + ">";
    }

    /**
     * @description determines if two vectors are equal
     * @param v the vector to compare
     * @return whether or not the vectors are equal
     */
    public boolean equals(Vector2f v) {
        return (this.x == v.getX() && this.y == v.getY());
    }

    /**
     * @description returns the magnitude of the vector
     * @return the magnitude of the vector
     */
    public float getMagnitude() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * @description returns the normalized unit vector u = v / |v|
     * @return the unit vector
     */
    public Vector2f getNormalizedVector() {
        float magnitude = this.getMagnitude();
        return new Vector2f(
            this.x / magnitude,
            this.y / magnitude
        );
    }

    /**
     * @description Vector addition
     * @param v the vector to be added to this vector
     * @return a new vector resulting from the sum
     */
    public Vector2f add(Vector2f v) {
        return new Vector2f(
            this.x + v.getX(),
            this.y + v.getY()
        );
    }

    /**
     * @description In-place vector addition
     * @param v the vector to be added
     * @return the calling vector
     */
    public Vector2f addEq(Vector2f v) {
        this.x += v.getX();
        this.y += v.getY();
        return this;
    }

    /**
     * @description Vector subtraction
     * @param v the vector to be subtracted
     * @return a new vector resulting from the subtraction
     */
    public Vector2f sub(Vector2f v) {
        return new Vector2f(
            this.x - v.getY(),
            this.y -v.getY()
        );
    }

    /**
     * @description In-place vector subtraction
     * @param v the vector to be subtracted
     * @return the calling vector
     */
    public Vector2f subEq(Vector2f v) {
        this.x -= v.getX();
        this.y -= v.getY();
        return this;
    }

    /**
     * @description component-wise multiplication for vectors
     * @param v the component to be multiplied
     * @return a new vector resulting from the multiplication
     */
    public Vector2f mul(Vector2f v) {
        return new Vector2f(
            this.x * v.getX(),
            this.y * v.getY()
        );
    }

    /**
     * @description In-place component-wise multiplication
     * @param v the vector to be multiplied
     * @return the calling vector
     */
    public Vector2f mulEq(Vector2f v) {
        this.x *= v.getX();
        this.y *= v.getY();
        return this;
    }

    /**
     * @description Component-wise division
     * @param v the vector to divide by
     * @return a new vector resulting from the division
     */
    public Vector2f div(Vector2f v) {
        return new Vector2f(
            this.x / v.getX(),
            this.y / v.getY()
        );
    }

    /**
     * @description In-place component-wise division
     * @param v the vector to divide by
     * @return the calling vector
     */
    public Vector2f divEq(Vector2f v) {
        this.x /= v.getX();
        this.y /= v.getY();
        return this;
    }

    /**
     * @description computes the dot product for 2 vectors
     * @param v the vector to be dotted
     * @return the dot product scalar value
     */
    public float dot(Vector2f v) {
        return this.x * v.getX() + this.y * v.getY();
    }

    /**
     * @description scales the vector
     * @param value the value to scale by
     * @return a new vector resulting from teh scaling
     */
    public Vector2f scale(float value) {
        return new Vector2f(
            this.x * value,
            this.y * value
        );
    }

    /**
     * @description In-place vector scaling
     * @param value the value to scale by
     * @return the calling vector
     */
    public Vector2f scaleEq(float value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    /**
     * @description  Applies a translation to the vector
     * @param xDif the distance to move the x component
     * @param yDif the distance to move the y component
     * @return a new vector resulting from the translation
     */
    public Vector2f translate(float xDif, float yDif) {
        return new Vector2f(
            this.x + xDif,
            this.y + yDif
        );
    }

    /**
     * @description In place vector translation
     * @param xDif the distance to move the x component
     * @param yDif the distance to move the y component
     * @return the calling vector
     */
    public Vector2f translateEq(float xDif, float yDif) {
        this.x += xDif;
        this.y += yDif;
        return this;
    }

    /**
     * @description rotates the vector around provided angle
     * @param angle angle in degrees
     * @return new vector rotated around the angle
     */
    public Vector2f rotate(float angle) {
        double radians = Math.toRadians(angle);
        double cosine = Math.cos(radians);
        double sine = Math.sin(radians);
        return new Vector2f(
            (float)(this.x * cosine - this.y * sine),
            (float)(this.x * sine + this.y * cosine)
        );
    }

    /**
     * @description In place rotation of a vector
     * @param angle angle in degrees
     * @return the calling vector
     */
    public Vector2f rotateEq(float angle) {
        double radians = Math.toRadians(angle);
        double cosine = Math.cos(radians);
        double sine = Math.sin(radians);
        this.x = (float)(this.x * cosine - this.y *sine);
        this.y = (float)(this.x * sine - this.y * cosine);
        return this;
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public Vector2f setX(float x) {
        this.x = x;
        return this;
    }
    public Vector2f setY(float y) {
        this.y = y;
        return this;
    }
}
