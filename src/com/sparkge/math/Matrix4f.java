/**
 * @description Matrix4f class for working with 4x4 matrices
 * @author Connor Zint | czint@radialspark.com
 * @history
 *  2018-05-26 | czint | Created
 */

package com.sparkge.math;

public class Matrix4f {

    private float[][] m;

    /**
     * @description init with float matrix
     * @param m float representation of the matrix
     */
    public Matrix4f(float[][] m) {
        this.setMatrix(m);
    }

    /**
     * @description init with default matrix containing all 0 values
     */
    public Matrix4f() {
        this.m = new float[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + m[0][0] + "," + m[0][1] + "," + m[0][2] + "," + m[0][3] + "]\n");
        sb.append("[" + m[1][0] + "," + m[1][1] + "," + m[1][2] + "," + m[1][3] + "]\n");
        sb.append("[" + m[2][0] + "," + m[2][1] + "," + m[2][2] + "," + m[2][3] + "]\n");
        sb.append("[" + m[3][0] + "," + m[3][1] + "," + m[3][2] + "," + m[3][3] + "]\n");
        return sb.toString();
    }

    /**
     * @description determine if another matrix instance is equal to this one
     * @param m2 matrix to compare
     * @return returns false if not equal
     */
    public boolean equals(Matrix4f m2) {
        float[][] otherM = m2.getM();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.m[i][j] != otherM[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @description set matrix
     * @param m float matrix
     */
    private void setMatrix(float[][] m) {
        if (m.length != 4 || m[0].length != 4) {
            throw new InvalidMatrixException("Matrix dimensions must be 4x4");
        } else {
            this.m = m;
        }
    }

    /**
     * @description get the sum of another matrix to this instance
     * @param m2 matrix to add
     * @return sum as a new instance
     */
    public Matrix4f add(Matrix4f m2) {
        float[][] m3 = new float[][]{};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m3[i][j] = this.m[i][j] + m2.get(i, j);
            }
        }
        return new Matrix4f(m3);
    }

    /**
     * @description add another matrix to this instance
     * @param m2 matrix to add
     * @return this
     */
    public Matrix4f addEq(Matrix4f m2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.m[i][j] += m2.get(i, j);
            }
        }
        return this;
    }

    /**
     * @description get the difference between this instance and another matrix
     * @param m2 matrix to subtract
     * @return difference as a new instance
     */
    public Matrix4f sub(Matrix4f m2) {
        float[][] m3 = new float[][]{};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m3[i][j] = this.m[i][j] - m2.get(i, j);
            }
        }
        return new Matrix4f(m3);
    }

    /**
     * @description subtract from this instance's matrix
     * @param m2 matrix to subtract
     * @return this
     */
    public Matrix4f subEq(Matrix4f m2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.m[i][j] -= m2.get(i, j);
            }
        }
        return this;
    }

    /**
     * @description get the product of this instance's matrix and another matrix as a new instance
     * @param m2 matrix to multiply by
     * @return product as a new instance
     */
    public Matrix4f mul(Matrix4f m2) {
        float[][] m3 = new float[][]{};
        float[][] otherM = m2.getM();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m3[i][j] = this.m[i][0] * otherM[0][j] +
                        this.m[i][1] * otherM[1][j] +
                        this.m[i][2] * otherM[2][j] +
                        this.m[i][3] * otherM[3][j];
            }
        }
        return new Matrix4f(m3);
    }

    /**
     * @description get the product of this instance's matrix and another matrix
     * @param m2 matrix to multiply by
     * @return this
     */
    public Matrix4f mulEq(Matrix4f m2) {
        float[][] otherM = m2.getM();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.m[i][j] = this.m[i][0] * otherM[0][j] +
                        this.m[i][1] * otherM[1][j] +
                        this.m[i][2] * otherM[2][j] +
                        this.m[i][3] * otherM[3][j];
            }
        }
        return this;
    }

    /**
     * @description get the scaled value of this instance as a new instance of matrix
     * @param val value to scale by
     * @return scaled matrix as a new instance
     */
    public Matrix4f scale(float val) {
        float[][] m3 = new float[][]{};
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                m3[i][j] = this.m[i][j] * val;
            }
        }
        return new Matrix4f(m3);
    }

    /**
     * @description get the scaled value of this instance
     * @param val value to scale by
     * @return this
     */
    public Matrix4f scaleEq(float val) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                this.m[i][j] *= val;
            }
        }
        return this;
    }

    public float get(int row, int col) {
        return m[row][col];
    }

    public float[][] getM() {
        return this.m;
    }

    public float[][] set(int row, int col, float val) {
        this.m[row][col] = val;
        return this.m;
    }

    public float[][] setM(float[][] m) {
        this.setMatrix(m);
        return m;
    }
}
