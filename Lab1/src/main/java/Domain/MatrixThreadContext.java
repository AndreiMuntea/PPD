package Domain;

public class MatrixThreadContext {

    public MatrixThreadContext(Matrix firstMatrix, Matrix secondMatrix, Matrix result, Integer startLine, Integer endLine) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.result = result;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public Matrix getResult(){
        return result;
    }

    public Matrix getFirstMatrix() {
        return firstMatrix;
    }

    public Matrix getSecondMatrix() {
        return secondMatrix;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public Integer getEndLine() {
        return endLine;
    }

    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private Matrix result;
    private Integer startLine;
    private Integer endLine;
}
