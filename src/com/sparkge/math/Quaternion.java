package com.sparkge.math;

/**
 * @description Class for working with Quaternions
 * @author Thomas Wilkins | twilkins@radialspark.com
 * @history
 *  2018-06-02 | Thomas Wilkins | Created
 */


public class Quaternion {
    private float x, y, z, w;
    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Quaternion(Vector3f axis, float angle) {
        float sinHalfAngle = (float)Math.sin(angle / 2);
        float cosHalfAngle = (float)Math.cos(angle / 2);
        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
    }
    public Quaternion() {
        this(0, 0, 0, 0);
    }

    /**
     * @description  returns the squared magnitude of the quaternion -- useful for length comparisons
     * @return the squared magnitude of the the quaternion
     */
    public float getMagnitudeSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    /**
     * @description returns the magnitude of the magnitude
     * @return quaternion magnitude
     */
    public float getMagnitude() {
        return (float)Math.sqrt((this.getMagnitudeSquared()));
    }

    /**
     * @descriptions creates a conjugate for the quaternion
     * @return the conjugate of the quaternion
     */
    public Quaternion getConjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    /**
     * @description calculates the dot product of the quaternion
     * @param q the quaternion to dot with
     * @return the dot product
     */
    public float dot(Quaternion q) {
        return this.x * q.getX() + this.y * q.getY() + this.z * q.getZ() + this.w * q.getW();
    }
    /**
     * @description component-wise Quaternion addition
     * @param q the quaternion to be added
     * @return a new quaternion resulting from the addition
     */
    public Quaternion add(Quaternion q) {
        return new Quaternion(
                this.x + q.getX(),
                this.y + q.getY(),
                this.z + q.getZ(),
                this.w + q.getW()
        );
    }

    /**
     * @description In place component-wise Quaternion addition
     * @param q the quaternion to be added
     * @return the calling quaternion
     */
    public Quaternion addEq(Quaternion q) {
        this.x += q.getX();
        this.y += q.getY();
        this.z += q.getZ();
        this.w += q.getW();
        return this;
    }

    /**
     * @description Component-wise Quaternion subtraction
     * @param q the quaternion to be subtracted
     * @return a new quaternion resulting from the subtraction
     */
    public Quaternion sub(Quaternion q) {
        return new Quaternion(
            this.x - q.getX(),
            this.y - q.getY(),
            this.z - q.getZ(),
            this.w - q.getW()
        );
    }

    /**
     * @description Component-wise Quaternion subtraction
     * @param q the quaternion to be subtracted
     * @return a new quaternion resulting from the subtraction
     */
    public Quaternion subEq(Quaternion q) {
      this.z -= q.getX();
      this.y -= q.getY();
      this.z -= q.getZ();
      this.w -= q.getW();
      return this;
    }

    /**
     * @description quaternion multiplication
     * @param q the quaternion to multiply with
     * @return a new quaternion resulting from the multiplication
     */
    public Quaternion mul(Quaternion q) {
        return new Quaternion(
            this.w * q.getX() + this.x * q.getW() + this.y * q.getZ() - this.z * q.getY(),
            this.w * q.getY() - this.x * q.getZ() + this.y * q.getW() + this.z * q.getX(),
            this.w * q.getZ() + this.x * q.getY() - this.y * q.getX() + this.z * q.getW(),
            this.w * q.getW() - this.x * q.getX() - this.y * q.getY() - this.z * q.getZ()
        );
    }

    /**
     * @description in place quaternion multiplication
     * @param q the quaternion to multiply against
     * @return the calling quaternion
     */
    public Quaternion mulEq(Quaternion q) {
        float newX = this.w * q.getX() + this.x * q.getW() + this.y * q.getZ() - this.z * q.getY();
        float newY = this.w * q.getY() - this.x * q.getZ() + this.y * q.getW() + this.z * q.getX();
        float newZ = this.w * q.getZ() + this.x * q.getY() - this.y * q.getX() + this.z * q.getW();
        float newW = this.w * q.getW() - this.x * q.getX() - this.y * q.getY() - this.z * q.getZ();
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
        return this;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}
