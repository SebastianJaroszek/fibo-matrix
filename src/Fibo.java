import java.math.BigInteger;

/**
 * @author Sebastian Jaroszek
 * created 21-12-2018
 *
 * 3 algorytmy na ciąg fibonacciego, każdy z inną złożonością obliczeniową
 * najbardziej wydajny algorytm został stworzony z użyciem macierzy i potęgowania
 */

public class Fibo {

    private static BigInteger potegowanie(int podstawa, int n) {
        BigInteger x = BigInteger.valueOf(podstawa);
        //BigInteger n = BigInteger.valueOf(wykladnik);

        BigInteger wynik = BigInteger.ONE;

        while (n > 0) {
            if (n % 2 == 0) {
                n = n / 2;
                x = x.multiply(x);
            } else {
                n = n - 1;
                wynik = wynik.multiply(x);
            }
        }

        return wynik;
    }

    private static BigInteger[][] pomnozMacierzPrzezJednokolumnowa(BigInteger[][] A, BigInteger[][] B) {
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
//        System.out.println(potegowanie(10, 9));

        /*BigInteger[][] A = new BigInteger[2][2];
        A[0][0] = BigInteger.ZERO;
        A[1][0] = BigInteger.ONE;
        A[0][1] = BigInteger.ONE;
        A[1][1] = BigInteger.ONE;

        BigInteger[][] B = new BigInteger[2][1];
        B[0][0] = BigInteger.ZERO;
        B[1][0] = BigInteger.ONE;

        BigInteger[][] pomnozoneMacierze = pomnozMacierzPrzezJednokolumnowa(A, B);
        System.out.println(pomnozoneMacierze[0][0] + ", " + pomnozoneMacierze[1][0]);*/

        /*BigInteger[][] A = new BigInteger[2][2];
        A[0][0] = BigInteger.ZERO;
        A[0][1] = BigInteger.ONE;
        A[1][0] = BigInteger.ONE;
        A[1][1] = BigInteger.ONE;


        BigInteger[][] B = new BigInteger[2][2];
        B[0][0] = BigInteger.ZERO;
        B[0][1] = BigInteger.ONE;
        B[1][0] = BigInteger.ONE;
        B[1][1] = BigInteger.ONE;

        BigInteger[][] pomnozoneMacierze = pomnozMacierzeKwadratowe(A, B);

        System.out.println(pomnozoneMacierze[0][0] + ", " + pomnozoneMacierze[0][1]);
        System.out.println(pomnozoneMacierze[1][0] + ", " + pomnozoneMacierze[1][1]);*/

        int n = 1000000; // prawie 10 milionów

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
        BigInteger[][] wyniki = pomnozMacierzPrzezJednokolumnowa(spotegowanaMacierz, B);

        //System.out.println(wyniki[0][0] + " <--- to jest główny wynik");
        System.out.println(wyniki[1][0]);

//        System.out.println(zwyczajnyCiagFibo(9999999)); // prawie 10 milionów
        long po = System.currentTimeMillis();

        System.out.println("czas: " + (po - przed)); //3048 4549 7322


        przed = System.currentTimeMillis();
        System.out.println(zwyczajnyCiagFibo(1000000)); //dla n=43 to około 11 sekund
        po = System.currentTimeMillis();

        System.out.println("czas: " + (po - przed)); //3048 4549 7322


        przed = System.currentTimeMillis();
        System.out.println(rekurencyjnyCiagFibo(43)); //dla n=43 to około 11 sekund
        po = System.currentTimeMillis();

        System.out.println("czas: " + (po - przed)); //3048 4549 7322


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
