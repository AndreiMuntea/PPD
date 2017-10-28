package FileHelper;

import Domain.Matrix;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    public static void writeMatrix(Matrix a, String filename) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new java.io.FileWriter(filename));
        fileWriter.write(a.toString());
        fileWriter.close();
    }
}
