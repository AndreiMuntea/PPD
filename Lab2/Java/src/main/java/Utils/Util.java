package Utils;

import Domain.ComplexNumber;
import Domain.DoubleNumber;
import Domain.Matrix;

import java.util.Random;

public class Util {

    private static Random randomizer = new Random();


    public static Matrix<DoubleNumber> getRandomDoubleMatrix(Integer noRows, Integer noColumns){
        DoubleNumber[][] matrix = new DoubleNumber[noRows][noColumns];

        for(Integer i = 0; i < noRows; ++i){
            for(Integer j = 0; j < noColumns; ++j){
                matrix[i][j] = new DoubleNumber(randomizer.nextDouble());
            }
        }
        return new Matrix<DoubleNumber>(matrix, noRows, noColumns);
    }

    public static Matrix<ComplexNumber> getRandomComplexMatrix(Integer noRows, Integer noColumns){
        ComplexNumber[][] matrix = new ComplexNumber[noRows][noColumns];

        for(Integer i = 0; i < noRows; ++i){
            for(Integer j = 0; j < noColumns; ++j){
                matrix[i][j] = new ComplexNumber(randomizer.nextDouble(), randomizer.nextDouble());
            }
        }
        return new Matrix<ComplexNumber>(matrix, noRows, noColumns);
    }

    public static Matrix<DoubleNumber> getZeroesDoubleMatrix(Integer noRows, Integer noColumns){
        DoubleNumber[][] matrix = new DoubleNumber[noRows][noColumns];

        for(Integer i = 0; i < noRows; ++i){
            for(Integer j = 0; j < noColumns; ++j){
                matrix[i][j] = new DoubleNumber();
            }
        }
        return new Matrix<DoubleNumber>(matrix, noRows, noColumns);
    }

    public static Matrix<ComplexNumber> getZeroesComplexMatrix(Integer noRows, Integer noColumns){
        ComplexNumber[][] matrix = new ComplexNumber[noRows][noColumns];

        for(Integer i = 0; i < noRows; ++i){
            for(Integer j = 0; j < noColumns; ++j){
                matrix[i][j] = new ComplexNumber();
            }
        }
        return new Matrix<ComplexNumber>(matrix, noRows, noColumns);
    }
}
