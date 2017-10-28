package Domain;

import Threads.IThreadCallback;

public class SumCallback implements IThreadCallback<MatrixThreadContext> {

    @Override
    public void RunCallback(MatrixThreadContext context) {
        if(!context.getFirstMatrix().getNoLines().equals(context.getSecondMatrix().getNoLines())){
            throw  new RuntimeException("Number of lines should be equals!");
        }

        if(!context.getFirstMatrix().getNoLines().equals(context.getResult().getNoLines())){
            throw  new RuntimeException("Number of lines should be equals!");
        }

        if(!context.getFirstMatrix().getNoColumns().equals(context.getSecondMatrix().getNoColumns())){
            throw  new RuntimeException("Number of columns should be equals!");
        }

        if(!context.getFirstMatrix().getNoColumns().equals(context.getResult().getNoColumns())){
            throw  new RuntimeException("Number of columns should be equals!");
        }

        Matrix.Add(
                context.getFirstMatrix(),
                context.getSecondMatrix(),
                context.getResult(),
                context.getStartLine(),
                context.getEndLine()
        );
    }
}
