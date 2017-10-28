import Domain.ComplexNumber;
import Domain.DoubleNumber;
import Domain.Matrix;
import Threads.MatrixCustomOperator1;
import Utils.Util;
import Threads.MatrixMultiplication;
import Threads.MatrixSum;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void TestAddDoubleSmallDataSet() throws Exception {
        System.out.println("TestAddDoubleSmallDataSet");

        DoubleNumber[][] dataA = new DoubleNumber[][]{
                {new DoubleNumber(1.0),new DoubleNumber(2.0),new DoubleNumber(3.0)},
                {new DoubleNumber(7.0),new DoubleNumber(8.0),new DoubleNumber(9.0)},
                {new DoubleNumber(4.0),new DoubleNumber(5.0),new DoubleNumber(6.0)}};

        DoubleNumber[][] dataB = new DoubleNumber[][]{
                {new DoubleNumber(1.0),new DoubleNumber(2.0),new DoubleNumber(3.0)},
                {new DoubleNumber(7.0),new DoubleNumber(8.0),new DoubleNumber(9.0)},
                {new DoubleNumber(4.0),new DoubleNumber(5.0),new DoubleNumber(6.0)}};

        DoubleNumber[][] res = new DoubleNumber[][]{
                {new DoubleNumber(2.0),new DoubleNumber(4.0),new DoubleNumber(6.0)},
                {new DoubleNumber(14.0),new DoubleNumber(16.0),new DoubleNumber(18.0)},
                {new DoubleNumber(8.0),new DoubleNumber(10.0),new DoubleNumber(12.0)}};

        Matrix<DoubleNumber> a = new Matrix<DoubleNumber>(dataA, 3, 3);
        Matrix<DoubleNumber> b = new Matrix<DoubleNumber>(dataB,3 ,3);

        Matrix<DoubleNumber> r0 = new Matrix<DoubleNumber>(res, 3,3);
        Matrix<DoubleNumber> r1 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r2 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r4 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r8 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r64 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixSum<DoubleNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixSum<DoubleNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixSum<DoubleNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixSum<DoubleNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixSum<DoubleNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r0,r1);
        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

    @Test
    public void TestAddComplexSmallDataSet() throws Exception {
        System.out.println("TestAddComplexSmallDataSet");

        ComplexNumber[][] dataA = new ComplexNumber[][]{
                {new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)},
                {new ComplexNumber(1.0, 2.0),new ComplexNumber(7.0, 8.0),new ComplexNumber(7.0, 8.0)},
                {new ComplexNumber(5.0, 6.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)}};

        ComplexNumber[][] dataB = new ComplexNumber[][]{
                {new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)},
                {new ComplexNumber(1.0, 2.0),new ComplexNumber(7.0, 8.0),new ComplexNumber(7.0, 8.0)},
                {new ComplexNumber(5.0, 6.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)}};

        ComplexNumber[][] res = new ComplexNumber[][]{
                {new ComplexNumber(6.0, 8.0),new ComplexNumber(6.0, 8.0),new ComplexNumber(6.0, 8.0)},
                {new ComplexNumber(2.0, 4.0),new ComplexNumber(14.0, 16.0),new ComplexNumber(14.0, 16.0)},
                {new ComplexNumber(10.0, 12.0),new ComplexNumber(6.0, 8.0),new ComplexNumber(6.0, 8.0)}};

        Matrix<ComplexNumber> a = new Matrix<ComplexNumber>(dataA, 3, 3);
        Matrix<ComplexNumber> b = new Matrix<ComplexNumber>(dataB,3 ,3);

        Matrix<ComplexNumber> r0 = new Matrix<ComplexNumber>(res, 3,3);
        Matrix<ComplexNumber> r1 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r2 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r4 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r8 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r64 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixSum<ComplexNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixSum<ComplexNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixSum<ComplexNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixSum<ComplexNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixSum<ComplexNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r0,r1);
        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

    @Test
    public void TestMultiplyComplexSmallDataSet() throws Exception {
        System.out.println("TestAddComplexSmallDataSet");

        ComplexNumber[][] dataA = new ComplexNumber[][]{
                {new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)},
                {new ComplexNumber(1.0, 2.0),new ComplexNumber(7.0, 8.0),new ComplexNumber(7.0, 8.0)},
                {new ComplexNumber(5.0, 6.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)}};

        ComplexNumber[][] dataB = new ComplexNumber[][]{
                {new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)},
                {new ComplexNumber(1.0, 2.0),new ComplexNumber(7.0, 8.0),new ComplexNumber(7.0, 8.0)},
                {new ComplexNumber(5.0, 6.0),new ComplexNumber(3.0, 4.0),new ComplexNumber(3.0, 4.0)}};

        ComplexNumber[][] res = new ComplexNumber[][]{
                {new ComplexNumber(-21.0, 72.0),new ComplexNumber(-25.0, 100.0),new ComplexNumber(-25.0, 100.0)},
                {new ComplexNumber(-27.0, 114.0),new ComplexNumber(-31.0, 174.0),new ComplexNumber(-31.0, 174.0)},
                {new ComplexNumber(-23.0, 86.0),new ComplexNumber(-27.0, 114.0),new ComplexNumber(-27.0, 114.0)}};

        Matrix<ComplexNumber> a = new Matrix<ComplexNumber>(dataA, 3, 3);
        Matrix<ComplexNumber> b = new Matrix<ComplexNumber>(dataB,3 ,3);

        Matrix<ComplexNumber> r0 = new Matrix<ComplexNumber>(res, 3,3);
        Matrix<ComplexNumber> r1 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r2 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r4 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r8 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r64 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixMultiplication<ComplexNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixMultiplication<ComplexNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixMultiplication<ComplexNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixMultiplication<ComplexNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixMultiplication<ComplexNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r0,r1);
        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

    @Test
    public void TestMultiplyDoubleSmallDataSet() throws Exception {
        System.out.println("TestMultiplyDoubleSmallDataSet");

        DoubleNumber[][] dataA = new DoubleNumber[][]{
                {new DoubleNumber(1.0),new DoubleNumber(2.0),new DoubleNumber(3.0)},
                {new DoubleNumber(7.0),new DoubleNumber(8.0),new DoubleNumber(9.0)},
                {new DoubleNumber(4.0),new DoubleNumber(5.0),new DoubleNumber(6.0)}};

        DoubleNumber[][] dataB = new DoubleNumber[][]{
                {new DoubleNumber(1.0),new DoubleNumber(2.0),new DoubleNumber(3.0)},
                {new DoubleNumber(7.0),new DoubleNumber(8.0),new DoubleNumber(9.0)},
                {new DoubleNumber(4.0),new DoubleNumber(5.0),new DoubleNumber(6.0)}};

        DoubleNumber[][] res = new DoubleNumber[][]{
                {new DoubleNumber(27.0),new DoubleNumber(33.0),new DoubleNumber(39.0)},
                {new DoubleNumber(99.0),new DoubleNumber(123.0),new DoubleNumber(147.0)},
                {new DoubleNumber(63.0),new DoubleNumber(78.0),new DoubleNumber(93.0)}};

        Matrix<DoubleNumber> a = new Matrix<DoubleNumber>(dataA, 3, 3);
        Matrix<DoubleNumber> b = new Matrix<DoubleNumber>(dataB,3 ,3);

        Matrix<DoubleNumber> r0 = new Matrix<DoubleNumber>(res, 3,3);
        Matrix<DoubleNumber> r1 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r2 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r4 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r8 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r64 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixMultiplication<DoubleNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixMultiplication<DoubleNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixMultiplication<DoubleNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixMultiplication<DoubleNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixMultiplication<DoubleNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r0,r1);
        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }


    @Test
    public void TestAddDoubleBigDataSet() throws Exception {
        System.out.println("TestAddDoubleBigDataSet");

        Matrix<DoubleNumber> a = Util.getRandomDoubleMatrix(1500, 1500);
        Matrix<DoubleNumber> b = Util.getRandomDoubleMatrix(1500, 1500);

        Matrix<DoubleNumber> r1 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r2 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r4 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r8 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<DoubleNumber> r64 = Util.getZeroesDoubleMatrix(a.getNoRows(), a.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixSum<DoubleNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixSum<DoubleNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixSum<DoubleNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixSum<DoubleNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixSum<DoubleNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }


    @Test
    public void TestAddComplexBigDataSet() throws Exception {
        System.out.println("TestAddComplexBigDataSet");

        Matrix<ComplexNumber> a = Util.getRandomComplexMatrix(1500, 1500);
        Matrix<ComplexNumber> b = Util.getRandomComplexMatrix(1500, 1500);

        Matrix<ComplexNumber> r1 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r2 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r4 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r8 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());
        Matrix<ComplexNumber> r64 = Util.getZeroesComplexMatrix(a.getNoRows(), a.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixSum<ComplexNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixSum<ComplexNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixSum<ComplexNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixSum<ComplexNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixSum<ComplexNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

    @Test
    public void TestMultiplyDoubleBigDataSet() throws Exception {
        System.out.println("TestMultiplyDoubleBigDataSet");

        Matrix<DoubleNumber> a = Util.getRandomDoubleMatrix(1000, 1000);
        Matrix<DoubleNumber> b = Util.getRandomDoubleMatrix(1000, 1000);

        Matrix<DoubleNumber> r1 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r2 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r4 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r8 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r64 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixMultiplication<DoubleNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixMultiplication<DoubleNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixMultiplication<DoubleNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixMultiplication<DoubleNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixMultiplication<DoubleNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

    @Test
    public void TestMultiplyComplexBigDataSet() throws Exception {
        System.out.println("TestMultiplyComplexBigDataSet");

        Matrix<ComplexNumber> a = Util.getRandomComplexMatrix(1000, 1000);
        Matrix<ComplexNumber> b = Util.getRandomComplexMatrix(1000, 1000);

        Matrix<ComplexNumber> r1 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r2 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r4 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r8 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r64 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixMultiplication<>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixMultiplication<ComplexNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixMultiplication<ComplexNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixMultiplication<ComplexNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixMultiplication<ComplexNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }


    @Test
    public void TestCustomOperator1ComplexBigDataSet() throws Exception {
        System.out.println("TestCustomOperator1ComplexBigDataSet");

        Matrix<ComplexNumber> a = Util.getRandomComplexMatrix(1000, 1000);
        Matrix<ComplexNumber> b = Util.getRandomComplexMatrix(1000, 1000);

        Matrix<ComplexNumber> r1 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r2 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r4 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r8 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<ComplexNumber> r64 = Util.getZeroesComplexMatrix(a.getNoRows(), b.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixCustomOperator1<ComplexNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixCustomOperator1<ComplexNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixCustomOperator1<ComplexNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixCustomOperator1<ComplexNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixCustomOperator1<ComplexNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

    @Test
    public void TestCustomOperator1DoubleBigDataSet() throws Exception {
        System.out.println("TestCustomOperator1DoubleBigDataSet");

        Matrix<DoubleNumber> a = Util.getRandomDoubleMatrix(1000, 1000);
        Matrix<DoubleNumber> b = Util.getRandomDoubleMatrix(1000, 1000);

        Matrix<DoubleNumber> r1 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r2 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r4 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r8 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());
        Matrix<DoubleNumber> r64 = Util.getZeroesDoubleMatrix(a.getNoRows(), b.getNoColumns());

        Double t1 = Main.doWork(a,b,r1,new MatrixCustomOperator1<DoubleNumber>(), 1);
        System.out.println("1 thread(s): " + t1);

        Double t2 = Main.doWork(a,b,r2,new MatrixCustomOperator1<DoubleNumber>(), 2);
        System.out.println("2 thread(s): " + t2);

        Double t4 = Main.doWork(a,b,r4,new MatrixCustomOperator1<DoubleNumber>(), 4);
        System.out.println("4 thread(s): " + t4);

        Double t8 = Main.doWork(a,b,r8,new MatrixCustomOperator1<DoubleNumber>(), 8);
        System.out.println("8 thread(s): " + t8);

        Double t64 = Main.doWork(a,b,r64,new MatrixCustomOperator1<DoubleNumber>(), 64);
        System.out.println("64 thread(s): " + t64);

        assertEquals(r1,r2);
        assertEquals(r1,r4);
        assertEquals(r1,r8);
        assertEquals(r1,r64);

        System.out.println("Finished test\n");
    }

}