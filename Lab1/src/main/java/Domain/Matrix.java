package Domain;

import java.util.Arrays;
import java.util.Random;

public class Matrix {

    private static final Double MIN_RANGE = -1.0;
    private static final Double MAX_RANGE = 1.0;

    public Matrix(Integer noLines, Integer noColumns, Double[][] data) {
        this.noLines = noLines;
        this.noColumns = noColumns;
        this.data = data;
    }

    public Matrix(Integer noLines, Integer noColumns, Random random) {
        this.noLines = noLines;
        this.noColumns = noColumns;
        this.data = new Double[noLines][noColumns];

        for(Integer i = 0; i < noLines; ++i) {
            for (Integer j = 0; j < noColumns; ++j){
                data[i][j] = MIN_RANGE + (MAX_RANGE - MIN_RANGE) * random.nextDouble();
            }
        }
    }

    public Matrix(Integer noLines, Integer noColumns){
        this.noLines = noLines;
        this.noColumns = noColumns;
        this.data = new Double[noLines][noColumns];

        for(Integer i = 0; i < noLines; ++i) {
            for (Integer j = 0; j < noColumns; ++j){
                data[i][j] = 0.0;
            }
        }
    }

    public Integer getNoLines() {
        return noLines;
    }

    public Integer getNoColumns() {
        return noColumns;
    }

    public Double[][] getData() {
        return data;
    }

    public static void Multiply(Matrix firstMatrix, Matrix secondMatrix, Matrix result, Integer startLine, Integer endLine) {
        for(Integer i = startLine; i < endLine; ++i) {
            for(Integer j = 0; j < secondMatrix.noColumns; ++j){
                Double accumulator = 0.0;
                for(Integer k = 0; k < secondMatrix.noLines; ++k){
                    accumulator += firstMatrix.data[i][k] * secondMatrix.data[k][j];
                }
                result.data[i][j] = accumulator;
            }
        }
    }

    public static void Add(Matrix firstMatrix, Matrix secondMatrix, Matrix result, Integer startLine, Integer endLine){
        for(Integer i = startLine; i < endLine; ++i){
            for(Integer j = 0; j < firstMatrix.noColumns; ++j){
                result.data[i][j] = firstMatrix.data[i][j] + secondMatrix.data[i][j];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(noLines + " " + noColumns + "\n");

        for(Integer i = 0; i < noLines; ++i){
            for(Integer j = 0; j < noColumns; ++j){
                result.append(data[i][j]).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        if (noLines != null ? !noLines.equals(matrix.noLines) : matrix.noLines != null) return false;
        if (noColumns != null ? !noColumns.equals(matrix.noColumns) : matrix.noColumns != null) return false;
        return Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = noLines != null ? noLines.hashCode() : 0;
        result = 31 * result + (noColumns != null ? noColumns.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }

    private Integer noLines;
    private Integer noColumns;
    private Double[][] data;
}
