package Domain;

import Threads.IThreadCallback;

public class MultiplicationCallback implements IThreadCallback<MatrixThreadContext> {
    @Override
    public void RunCallback(MatrixThreadContext context) {
        if(!context.getFirstMatrix().getNoColumns().equals(context.getSecondMatrix().getNoLines())){
            throw  new RuntimeException("Number of columns from first matrix should be equal with number of lines from second matrix!");
        }

        if(!context.getFirstMatrix().getNoLines().equals(context.getResult().getNoLines())){
            throw  new RuntimeException("Resulted matrix should have number of lines equals with number of lines from first matrix!");
        }

        if(!context.getSecondMatrix().getNoColumns().equals(context.getResult().getNoColumns())){
            throw  new RuntimeException("Resulted matrix should have number of columns equals with number of columns from second matrix!");

        }

        Matrix.Multiply(
                context.getFirstMatrix(),
                context.getSecondMatrix(),
                context.getResult(),
                context.getStartLine(),
                context.getEndLine()
        );
    }
}
