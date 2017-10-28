package FileHelper;

import Domain.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static Matrix ReadMatrixFromFile(String fileName) throws CorruptedFileException, FileNotFoundException {

        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        Integer noLines = scan.nextInt();
        Integer noColumns = scan.nextInt();

        if(noLines <= 0){
            throw  new CorruptedFileException("Number of lines should be greater than 0!");
        }

        if(noColumns <= 0){
            throw  new CorruptedFileException("Number of columns should be greater than 0!");
        }

        Double data[][] = new Double[noLines][noColumns];

        for(Integer i = 0; i < noLines; ++i){
            for(Integer j = 0; j < noColumns; ++j){
                data[i][j] = scan.nextDouble();
            }
        }

        return new Matrix(noLines, noColumns, data);
    }

}
