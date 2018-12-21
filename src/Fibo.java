import java.math.BigInteger;

/**
 * @author Sebastian Jaroszek
 * created 21-12-2018
 *
 * 3 algorytmy na ciąg fibonacciego, każdy z inną złożonością obliczeniową
 * najbardziej wydajny algorytm został stworzony z użyciem macierzy i potęgowania
 */

public class Fibo {

    private static BigInteger[][] pomnozKwadratowaPrzezJednokolumnowa(BigInteger[][] A, BigInteger[][] B) {
        BigInteger[][] wynik = new BigInteger[2][1];

        wynik[0][0] = (A[0][0].multiply(B[0][0])).add(A[0][1].multiply(B[1][0]));
        wynik[1][0] = (A[1][0].multiply(B[0][0])).add(A[1][1].multiply(B[1][0]));

        return wynik;
    }

    private static BigInteger[][] pomnozMacierzeKwadratowe(BigInteger[][] A, BigInteger[][] B) {
        BigInteger[][] wynik = new BigInteger[2][2];

        wynik[0][0] = (A[0][0].multiply(B[0][0])).add(A[0][1].multiply(B[1][0]));
        wynik[0][1] = (A[0][0].multiply(B[0][1])).add(A[0][1].multiply(B[1][1]));
        wynik[1][0] = (A[1][0].multiply(B[0][0])).add(A[1][1].multiply(B[1][0]));
        wynik[1][1] = (A[1][0].multiply(B[0][1])).add(A[1][1].multiply(B[1][1]));

        return wynik;
    }

    private static BigInteger[][] potegowanieMacierzy(BigInteger[][] x, int n) {
        n = n - 2;

        BigInteger[][] wynik = new BigInteger[2][2];
        wynik[0][0] = BigInteger.ZERO;
        wynik[0][1] = BigInteger.ONE;
        wynik[1][0] = BigInteger.ONE;
        wynik[1][1] = BigInteger.ONE;

        while (n > 0) {
            if (n % 2 == 0) {
                n = n / 2;
                x = pomnozMacierzeKwadratowe(x, x);
            } else {
                n = n - 1;
                wynik = pomnozMacierzeKwadratowe(wynik, x);
            }
        }

        return wynik;
    }

    public static void main(String[] args) {

        int n = 1000000; // liczy bez większego problemu nawet dla 9 999 999

        BigInteger[][] A = new BigInteger[2][2];
        A[0][0] = BigInteger.ZERO;
        A[0][1] = BigInteger.ONE;
        A[1][0] = BigInteger.ONE;
        A[1][1] = BigInteger.ONE;

        BigInteger[][] spotegowanaMacierz = potegowanieMacierzy(A, n);

        BigInteger[][] B = new BigInteger[2][1];
        B[0][0] = BigInteger.ZERO;
        B[1][0] = BigInteger.ONE;

        long przed = System.currentTimeMillis();
        BigInteger[][] wyniki = pomnozKwadratowaPrzezJednokolumnowa(spotegowanaMacierz, B);

        System.out.println("MACIERZE wynik: " + wyniki[1][0]);

        long po = System.currentTimeMillis();

        System.out.println("MACIERZE czas: " + (po - przed));


        przed = System.currentTimeMillis();
        System.out.println("PĘTLA wynik: " + zwyczajnyCiagFibo(n));
        po = System.currentTimeMillis();

        System.out.println("PĘTLA czas: " + (po - przed));


        przed = System.currentTimeMillis();
        System.out.println("REKURENCJA wynik: " + rekurencyjnyCiagFibo(45));
        po = System.currentTimeMillis();

        System.out.println("REKURENCJA czas: " + (po - przed));

    }

    private static BigInteger zwyczajnyCiagFibo(int n) {
        BigInteger poprzedniWynik = BigInteger.ZERO;
        BigInteger wynik = BigInteger.ONE;
        BigInteger helper;
        while (n > 1) {
            helper = wynik;
            wynik = poprzedniWynik.add(wynik);
            poprzedniWynik = helper;
            n--;
        }

        return wynik;
    }

    private static BigInteger rekurencyjnyCiagFibo(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 2 || n == 1) {
            return BigInteger.ONE;
        } else {
            return rekurencyjnyCiagFibo(n - 2).add(rekurencyjnyCiagFibo(n - 1));
        }
    }

}
