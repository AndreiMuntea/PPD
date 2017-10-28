package Threads;

import Domain.Matrix;
import Domain.Number;

public class MatrixMultiplication<T extends Number> implements IRunCallback<T> {

    @SuppressWarnings("unchecked")
    @Override
    public void runCallback(Matrix<T> firstMatrix, Matrix<T> secondMatrix, Matrix<T> result, Integer start, Integer end) {
        for(Integer i = start; i < end; ++i) {
            for(Integer j = 0; j < secondMatrix.getNoColumns(); ++j){
                T res = result.get(i,j);
                for(Integer k = 0; k < secondMatrix.getNoRows(); ++k){
                    res = (T) res.add(firstMatrix.get(i,k).multiply(secondMatrix.get(k,j)));
                }
                result.set(res,i,j);
            }
        }
    }
}
