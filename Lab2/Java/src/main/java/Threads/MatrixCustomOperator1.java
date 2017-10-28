package Threads;

import Domain.Matrix;
import Domain.Number;

public class MatrixCustomOperator1<T extends Number> implements IRunCallback<T> {
    @SuppressWarnings("unchecked")
    @Override
    public void runCallback(Matrix<T> firstMatrix, Matrix<T> secondMatrix, Matrix<T> result, Integer start, Integer end) {
        for(Integer i = start; i < end; ++i) {
            for(Integer j = 0; j < secondMatrix.getNoColumns(); ++j){
                T res = result.get(i,j);
                T zero = (T)res.castAsValue(0.0);
                T one  = (T)res.castAsValue(1.0);

                if (firstMatrix.get(i,j).equals(zero) && secondMatrix.get(i,j).equals(zero))
                {
                    result.set(zero,i,j);
                }
                else if (firstMatrix.get(i,j).equals(zero))
                {
                    T temp = (T)one.division(secondMatrix.get(i,j));
                    result.set((temp.equals(zero) ? zero : (T)one.division(temp)),i,j);
                }
                else if (secondMatrix.get(i,j).equals(zero))
                {
                    T temp = (T)one.division(firstMatrix.get(i,j));
                    result.set((temp.equals(zero) ? zero : (T)one.division(temp)),i,j);
                }
                else
                {
                    T temp1 = (T)one.division(firstMatrix.get(i,j));
                    T temp2 = (T)one.division(secondMatrix.get(i,j));
                    T rs = (T)temp1.add(temp2);
                    result.set((rs.equals(zero) ? zero : (T)one.division(rs)), i,j);
                }
            }
        }
    }
}
