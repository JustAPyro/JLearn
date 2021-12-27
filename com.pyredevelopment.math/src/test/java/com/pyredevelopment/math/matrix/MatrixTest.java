package com.pyredevelopment.math.matrix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    double[][] testMatrix; // matrix used for testing
    double[][] expectedMatrix; // Matrix to hold the expected outcome

    @Test
    @DisplayName("Testing 2x2 Matrix Determinant")
    void test2x2determinant() {
        testMatrix = new double[][]{
                {7, 5},
                {2, 1}};
        assertEquals(-3, Matrix.determinant(testMatrix));
    }

    @Test
    @DisplayName("Testing 3x3 Matrix Determinant")
    void test3x3determinant() {
        testMatrix = new double[][]{
                {6, 3, 4},
                {6, 1, 8},
                {3, 0, 9}};
        assertEquals(-48, Matrix.determinant(testMatrix));
    }

    @Test
    @DisplayName("Testing 4x4 Matrix Determinant")
    void test4x4determinant() {
        testMatrix = new double[][]{
                {3, 8, 8, 4},
                {3, 7, 6, 1},
                {5, 7, 6, 9},
                {7, 9, 2, 9}};
        assertEquals(-288, Matrix.determinant(testMatrix));
    }

    @Test
    @DisplayName("Testing 10x10 Matrix Determinant")
    void test10x10determinant() {
        testMatrix = new double[][]{
                {8,8,8,9,3,7,3,8,8,4},
                {7,2,5,7,4,3,6,9,3,9},
                {9,0,0,2,3,4,0,1,8,8},
                {4,4,6,8,3,1,8,0,0,1},
                {7,8,0,5,2,7,1,9,4,0},
                {4,1,5,7,5,9,9,0,3,2},
                {4,4,2,7,8,1,5,6,1,4},
                {4,5,5,0,8,9,5,3,0,9},
                {5,7,1,7,5,4,2,0,0,1},
                {1,7,2,1,5,2,1,4,7,8}};
        assertEquals(-1075387108, Matrix.determinant(testMatrix));
    }

    @Test
    @DisplayName("Testing 3x3 Matrix Of Minors")
    void test3x3matrixofminors() {
        testMatrix = new double[][] {
                {4,2,3},
                {6,4,0},
                {9,5,0}};
        expectedMatrix = new double[][] {
                {0, 0, -6},
                {-15, -27, 2},
                {-12, -18, 4}};

        assertArrayEquals(expectedMatrix, Matrix.matrixOfMinors(testMatrix));

    }

    @Test
    @DisplayName("Testing 4x4 Matrix Of Minors")
    void test4x4matrixofminors() {
        testMatrix = new double[][] {
                {3,1,3,3},
                {2,4,1,7},
                {7,5,0,7},
                {1,3,3,2}};
        expectedMatrix = new double[][] {
                {32, 98, 62,-38},
                {57,-21,  8, 72},
                {47,-33,-30, 28},
                {13,105,-40,-62}};

        assertArrayEquals(expectedMatrix, Matrix.matrixOfMinors(testMatrix));

    }

    @Test
    @DisplayName("Testing 5x5 Matrix Of Minors")
    void test5x5matrixofminors() {
        testMatrix = new double[][] {
                {3,8,8,4,1},
                {1,7,0,5,6},
                {8,2,1,8,3},
                {5,7,0,3,2},
                {8,3,7,0,1}};
        expectedMatrix = new double[][] {
                {-1200,-744,1338,-1596,-1998},
                {761,-284,-427,-703,-3951},
                {542,1150,-52,-2128,-522},
                {-1423,2548,2357,-817,2529},
                {1294,686,970,2128,2358}};

        assertArrayEquals(expectedMatrix, Matrix.matrixOfMinors(testMatrix));

    }

}