package main;

import java.math.BigInteger;

public class Task implements Runnable {

    private int taskNumber;
    private Application app;
    private int lowerBorder;
    private int upperBorder;
    // used for performance
    private int[] firstPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103,
            107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211,
            223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
            337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449,
            457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541};


    public Task(int taskNumber, Application app, int lowerBorder, int upperBorder) {
        this.taskNumber = taskNumber;
        this.app = app;
        this.lowerBorder = lowerBorder;
        this.upperBorder = upperBorder;
        System.out.println("Task0" + taskNumber + ": [" + lowerBorder + ";" + (upperBorder - 1) + "]");
    }

    /**
     * Iterates getNumber() over the whole interval
     */
    private void calculateFortunateNumbers() {
        for (int i = lowerBorder; i < upperBorder; i++) {
            int number = getNumber(i);
            String type = (isPrime(number)) ? "prime" : "non-prime";
            if (type.equals("non-prime")) {
                System.out.println("Fortune's conjecture falsified!!!11!");
            }
            if (app != null) app.addSolution(type, i, number);
            System.out.println("Task0" + taskNumber + " found #" + i + ":\t " + number + " (" + type + ")");
        }
    }


    /**
     * finds the fortunate number for a given integer
     *
     * @param number int: n-th number
     * @return int: n-th fortunate number
     */
    public int getNumber(int number) {
        BigInteger product = BigInteger.ONE;
        if (number > firstPrimes.length) {
            int nextPrime = firstPrimes[firstPrimes.length - 1];
            for (int i = firstPrimes.length; i <= number; i++) {
                nextPrime = findNextPrime(nextPrime);
                product = product.multiply(BigInteger.valueOf(nextPrime));
            }
        }
        for (int i = 0; i < number && i < firstPrimes.length; i++) {
            product = product.multiply(BigInteger.valueOf(firstPrimes[i]));
        }

        return findFortuneNumberForProduct(product);
    }

    /**
     * Finds the next prime in line for a given integer
     *
     * @param from int: value after which the next prime is searched for
     * @return int: next prime in line
     */
    public int findNextPrime(int from) {
        for (int i = from + 1; i < Integer.MAX_VALUE; i++) {
            if (isPrime(i)) return i;
        }
        return 0;
    }

    /**
     * Finds the appropriate fortunate number for a given product
     *
     * @param product BigInteger: product for which the fortunate number is searched for
     * @return int: the fortunate number
     */
    public int findFortuneNumberForProduct(BigInteger product) {
        int diff = 1;
        for (BigInteger i = product.add(BigInteger.ONE); ; i = i.add(BigInteger.ONE)) {
            if (diff == 1) {
                diff++;
                continue;
            }
            if (isPrime(i)) return diff;
            diff++;
        }
    }

    /**
     * Checks whether the given integer is a prime number
     *
     * @param number int: number to check
     * @return boolean: whether prime or not
     */
    public boolean isPrime(int number) {
        if (number == 2) return true;
        if (number == 3) return true;
        if (number % 2 == 0) return false;
        if (number % 3 == 0) return false;

        int i = 5;
        int w = 2;
        while (i * i <= number) {
            if (number % i == 0) return false;
            i += w;
            w = 6 - w;
        }
        return true;
    }

    /**
     * Checks whether the given BigNumber is a prime number
     *
     * @param number BigInteger: number to check
     * @return boolean: whether prime or not
     */
    public boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 1) return isPrime(number.intValue());
        if (number.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0) return false;
        if (number.mod(BigInteger.valueOf(3)).compareTo(BigInteger.ZERO) == 0) return false;

        BigInteger i = BigInteger.valueOf(5);
        BigInteger w = BigInteger.valueOf(2);
        while (i.multiply(i).compareTo(number) < 1) {
            if (number.mod(i).compareTo(BigInteger.ZERO) == 0) return false;
            i = i.add(w);
            w = BigInteger.valueOf(6).subtract(w);
        }
        return true;
    }

    @Override
    public void run() {
        calculateFortunateNumbers();
    }
}
