import java.math.BigInteger;

/**
 * @author Sebastian Jaroszek
 * created 21-12-2018
 *
 * 3 algorytmy na ciąg fibonacciego, każdy z inną złożonością obliczeniową
 * najbardziej wydajny algorytm został stworzony z użyciem macierzy i potęgowania
 */

public class Fibo {

    private static BigInteger[][] multiplySquaredByUnicolumn(BigInteger[][] matrixA, BigInteger[][] matrixB) {
        BigInteger[][] result = new BigInteger[2][1];

        result[0][0] = (matrixA[0][0].multiply(matrixB[0][0])).add(matrixA[0][1].multiply(matrixB[1][0]));
        result[1][0] = (matrixA[1][0].multiply(matrixB[0][0])).add(matrixA[1][1].multiply(matrixB[1][0]));

        return result;
    }

    private static BigInteger[][] multiplySquaredMatrices(BigInteger[][] matrixA, BigInteger[][] matrixB) {
        BigInteger[][] result = new BigInteger[2][2];

        result[0][0] = (matrixA[0][0].multiply(matrixB[0][0])).add(matrixA[0][1].multiply(matrixB[1][0]));
        result[0][1] = (matrixA[0][0].multiply(matrixB[0][1])).add(matrixA[0][1].multiply(matrixB[1][1]));
        result[1][0] = (matrixA[1][0].multiply(matrixB[0][0])).add(matrixA[1][1].multiply(matrixB[1][0]));
        result[1][1] = (matrixA[1][0].multiply(matrixB[0][1])).add(matrixA[1][1].multiply(matrixB[1][1]));

        return result;
    }

    private static BigInteger[][] squareMatrix(BigInteger[][] x, int n) {
        n = n - 2;

        BigInteger[][] result = new BigInteger[2][2];
        result[0][0] = BigInteger.ZERO;
        result[0][1] = BigInteger.ONE;
        result[1][0] = BigInteger.ONE;
        result[1][1] = BigInteger.ONE;

        while (n > 0) {
            if (n % 2 == 0) {
                n = n / 2;
                x = multiplySquaredMatrices(x, x);
            } else {
                n = n - 1;
                result = multiplySquaredMatrices(result, x);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int n = 1000000; // liczy bez większego problemu nawet dla 9 999 999

        BigInteger[][] matrixA = new BigInteger[2][2];
        matrixA[0][0] = BigInteger.ZERO;
        matrixA[0][1] = BigInteger.ONE;
        matrixA[1][0] = BigInteger.ONE;
        matrixA[1][1] = BigInteger.ONE;

        BigInteger[][] squaredMatrix = squareMatrix(matrixA, n);

        BigInteger[][] matrixB = new BigInteger[2][1];
        matrixB[0][0] = BigInteger.ZERO;
        matrixB[1][0] = BigInteger.ONE;

        long timeBefore = System.currentTimeMillis();
        BigInteger[][] results = multiplySquaredByUnicolumn(squaredMatrix, matrixB);

        System.out.println("MACIERZE wynik: " + results[1][0]);

        long timeAfter = System.currentTimeMillis();

        System.out.println("MACIERZE czas: " + (timeAfter - timeBefore));


        timeBefore = System.currentTimeMillis();
        System.out.println("PĘTLA wynik: " + fiboOnLoop(n));
        timeAfter = System.currentTimeMillis();

        System.out.println("PĘTLA czas: " + (timeAfter - timeBefore));


        timeBefore = System.currentTimeMillis();
        System.out.println("REKURENCJA wynik: " + recurrentFibo(45)); //45
        timeAfter = System.currentTimeMillis();

        System.out.println("REKURENCJA czas: " + (timeAfter - timeBefore));

    }

    private static BigInteger fiboOnLoop(int n) {
        BigInteger lastResult = BigInteger.ZERO;
        BigInteger result = BigInteger.ONE;
        BigInteger helper;
        while (n > 1) {
            helper = result;
            result = lastResult.add(result);
            lastResult = helper;
            n--;
        }

        return result;
    }

    private static BigInteger recurrentFibo(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 2 || n == 1) {
            return BigInteger.ONE;
        } else {
            return recurrentFibo(n - 2).add(recurrentFibo(n - 1));
        }
    }

}
