package Domain;

import java.util.Arrays;

public class Matrix<T extends Number> {

    public Matrix(T[][] data, Integer noRows, Integer noColumns) {
        this.data = data;
        this.noColumns = noColumns;
        this.noRows = noRows;
    }

    @SuppressWarnings("unchecked")
    public Matrix(Integer noRows, Integer noColumns){
        this.noColumns = noColumns;
        this.noRows = noRows;
        this.data = (T[][])new Number[noRows][noColumns];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix<?> matrix = (Matrix<?>) o;

        if (noColumns != null ? !noColumns.equals(matrix.noColumns) : matrix.noColumns != null) return false;
        if (noRows != null ? !noRows.equals(matrix.noRows) : matrix.noRows != null) return false;

        for(int i = 0; i < noRows; ++i){
            for(int j = 0; j < noColumns; ++j){
                if(!data[i][j].equals(matrix.data[i][j])){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(data);
        result = 31 * result + (noColumns != null ? noColumns.hashCode() : 0);
        result = 31 * result + (noRows != null ? noRows.hashCode() : 0);
        return result;
    }

    public T get(Integer i, Integer j){
        return data[i][j];
    }

    public void set(T other, Integer i, Integer j){
        data[i][j] = other;
    }

    public Integer getNoColumns() {
        return noColumns;
    }

    public Integer getNoRows() {
        return noRows;
    }

    private T[][] data;
    private Integer noColumns;
    private Integer noRows;
}
