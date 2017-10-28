import Domain.Matrix;
import Domain.MultiplicationCallback;
import Domain.SumCallback;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void SumCallbackSmallDataSet() throws Exception {
        System.out.println("Beginning SumCallbackSmallDataSet Test");

        Matrix a = new Matrix(3, 3, new Double[][]{ {1.0, 2.0, 3.0}, {3.0, 4.0, 5.0}, {5.0, 6.0, 7.0}});
        Matrix b = new Matrix(3, 3, new Double[][]{ {1.0, 2.0, 3.0}, {3.0, 4.0, 5.0}, {5.0, 6.0, 7.0}});
        Matrix c =  new Matrix(3, 3, new Double[][]{ {2.0, 4.0, 6.0}, {6.0, 8.0, 10.0}, {10.0, 12.0, 14.0}});
        Matrix resSingleThreaded = new Matrix(3,3);
        Matrix resMultiThreaded = new Matrix(3, 3);

        Main.doWorkSingleThreaded(a, b,resSingleThreaded, new SumCallback());
        Main.doWorkMultiThreaded(a,b, resMultiThreaded, new SumCallback(), 2);

        assertEquals(c, resMultiThreaded);
        assertEquals(c, resSingleThreaded);

        System.out.println("Finished test\n");
    }

    @Test
    public void SumCallbackSmallRandomDataSet() throws Exception {
        System.out.println("Beginning SumCallbackSmallRandomDataSet Test");

        Matrix a = new Matrix(7, 7, new Random());
        Matrix b = new Matrix(7, 7, new Random());
        Matrix resSingleThreaded = new Matrix(7,7);
        Matrix resMultiThreaded = new Matrix(7, 7);

        Main.doWorkSingleThreaded(a, b,resSingleThreaded, new SumCallback());
        Main.doWorkMultiThreaded(a,b, resMultiThreaded, new SumCallback(), 5);

        assertEquals(resSingleThreaded, resMultiThreaded);

        System.out.println("Finished test\n");
    }

    @Test
    public void SumCallbackBigDataSet() throws Exception {
        System.out.println("Beginning SumCallbackBigDataSet Test");

        Matrix a = new Matrix(1000, 1000, new Random());
        Matrix b = new Matrix(1000, 1000, new Random());
        Matrix resSingleThreaded = new Matrix(1000, 1000);
        Matrix resMultiThreaded = new Matrix(1000, 1000);

        Main.doWorkSingleThreaded(a, b,resSingleThreaded, new SumCallback());
        Main.doWorkMultiThreaded(a,b, resMultiThreaded, new SumCallback(), 7);

        assertEquals(resSingleThreaded, resMultiThreaded);

        System.out.println("Finished test\n");
    }

    @Test(expected = RuntimeException.class)
    public void SumCallbackInconsistentData1() throws Exception {
        Matrix a = new Matrix(7, 7, new Random());
        Matrix b = new Matrix(5, 7, new Random());
        Matrix c = new Matrix(7,7);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }

    @Test(expected = RuntimeException.class)
    public void SumCallbackInconsistentData2() throws Exception {
        Matrix a = new Matrix(7, 7, new Random());
        Matrix b = new Matrix(7, 2, new Random());
        Matrix c = new Matrix(7,7);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }

    @Test(expected = RuntimeException.class)
    public void SumCallbackInconsistentData3() throws Exception {
        Matrix a = new Matrix(7, 7, new Random());
        Matrix b = new Matrix(7, 7, new Random());
        Matrix c = new Matrix(5,7);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }

    @Test(expected = RuntimeException.class)
    public void SumCallbackInconsistentData4() throws Exception {
        Matrix a = new Matrix(7, 7, new Random());
        Matrix b = new Matrix(7, 7, new Random());
        Matrix c = new Matrix(7,6);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }

    @Test
    public void MultiplyCallbackSmallDataSetSquareMatrix() throws Exception {
        System.out.println("Beginning MultiplyCallbackSmallDataSetSquareMatrix Test");

        Matrix a = new Matrix(3, 3, new Double[][]{ {1.0, 2.0, 3.0}, {3.0, 4.0, 5.0}, {5.0, 6.0, 7.0}});
        Matrix b = new Matrix(3, 3, new Double[][]{ {1.0, 2.0, 3.0}, {3.0, 4.0, 5.0}, {5.0, 6.0, 7.0}});
        Matrix c =  new Matrix(3, 3, new Double[][]{ {22.0, 28.0, 34.0}, {40.0, 52.0, 64.0}, {58.0, 76.0, 94.0}});
        Matrix resSingleThreaded = new Matrix(3,3);
        Matrix resMultiThreaded = new Matrix(3, 3);

        Main.doWorkSingleThreaded(a, b,resSingleThreaded, new MultiplicationCallback());
        Main.doWorkMultiThreaded(a,b, resMultiThreaded, new MultiplicationCallback(), 2);

        assertEquals(c, resMultiThreaded);
        assertEquals(c, resSingleThreaded);

        System.out.println("Finished test\n");
    }

    @Test
    public void MultiplyCallbackSmallDataSet() throws Exception {
        System.out.println("Beginning MultiplyCallbackSmallDataSet Test");

        Matrix a = new Matrix(5, 7, new Double[][]{
                {-0.939,  0.377,  0.087, -0.778,  0.981, -0.384,  0.541},
                {0.445, -0.157,  0.693,  0.257, -0.862,  0.451, -0.701},
                {0.420, -0.428, -0.060,  0.495,  0.637, -0.042,  0.812},
                {-0.455,  0.523,  0.519,  0.346,  0.558, -0.830,  0.667},
                {0.814, -0.257, -0.975, -0.720, -0.783,  0.676,  0.363}
        });

        Matrix b = new Matrix(7, 2, new Double[][]{
                { 0.985,  0.640},
                {0.071,  0.163},
                {-0.990, -0.735},
                {0.136, -0.569},
                {-0.473,  0.684},
                {-0.081, -0.802},
                {0.492, -0.834}
        });

        Matrix c = new Matrix(5, 2, new Double[][]{
                {-1.2568229999999998,  0.36700600000000005 },
                {-0.19763699999999995, -0.7630549999999999 },
                {0.6116370000000001, -0.24633500000000003 },
                {-0.746336, -0.2932359999999999},
                {2.145072,  0.22490799999999966 }
        });

        Matrix resSingleThreaded = new Matrix(5,2);
        Matrix resMultiThreaded = new Matrix(5, 2);

        Main.doWorkSingleThreaded(a, b,resSingleThreaded, new MultiplicationCallback());
        Main.doWorkMultiThreaded(a,b, resMultiThreaded, new MultiplicationCallback(), 3);

        assertEquals(c, resMultiThreaded);
        assertEquals(c, resSingleThreaded);

        System.out.println("Finished test\n");
    }


    @Test
    public void MultiplyCallbackBigDataSet() throws Exception {
        System.out.println("Beginning MultiplyCallbackBigDataSet Test");

        Matrix a = new Matrix(782, 852, new Random());
        Matrix b = new Matrix(852, 981, new Random());
        Matrix resSingleThreaded = new Matrix(782, 981);
        Matrix resMultiThreaded = new Matrix(782, 981);

        Main.doWorkSingleThreaded(a, b,resSingleThreaded, new MultiplicationCallback());
        Main.doWorkMultiThreaded(a,b, resMultiThreaded, new MultiplicationCallback(), 11);

        assertEquals(resSingleThreaded, resMultiThreaded);

        System.out.println("Finished test\n");
    }

    @Test(expected = RuntimeException.class)
    public void MultiplyCallbackInconsistentData1() throws Exception {
        Matrix a = new Matrix(7, 7, new Random());
        Matrix b = new Matrix(6, 7, new Random());
        Matrix c = new Matrix(7,7);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }

    @Test(expected = RuntimeException.class)
    public void MultiplyCallbackInconsistentData2() throws Exception {
        Matrix a = new Matrix(14, 7, new Random());
        Matrix b = new Matrix(7, 14, new Random());
        Matrix c = new Matrix(9,14);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }

    @Test(expected = RuntimeException.class)
    public void MultiplyCallbackInconsistentData3() throws Exception {
        Matrix a = new Matrix(14, 7, new Random());
        Matrix b = new Matrix(7, 14, new Random());
        Matrix c = new Matrix(14,9);

        Main.doWorkSingleThreaded(a,b,c, new SumCallback());
    }
}