package test;

import main.Task;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class TestFortunateNumberTask {

    private Task task;

    @Before
    public void setup() {
        task = new Task(1, null, 0, 1);
    }

    @Test
    public void testGetNumber() {
        assertEquals(3, task.getNumber(1));
        assertEquals(5, task.getNumber(2));
        assertEquals(7, task.getNumber(3));
        assertEquals(13, task.getNumber(4));
        assertEquals(23, task.getNumber(5));
        assertEquals(17, task.getNumber(6));
        assertEquals(19, task.getNumber(7));
        assertEquals(23, task.getNumber(8));
        assertEquals(37, task.getNumber(9));
    }

    @Test
    public void testFindNextPrime() {
        assertEquals(3, task.findNextPrime(2));
        assertEquals(53, task.findNextPrime(47));
        assertEquals(73, task.findNextPrime(71));
        assertEquals(131, task.findNextPrime(127));
        assertEquals(191, task.findNextPrime(181));
        assertEquals(353, task.findNextPrime(349));
        assertEquals(419, task.findNextPrime(409));
        assertEquals(457, task.findNextPrime(449));
        assertEquals(521, task.findNextPrime(509));
    }

    @Test
    public void testFindFortunateNumberForProduct() {
        assertEquals(3, task.findFortuneNumberForProduct(BigInteger.valueOf(2)));
        assertEquals(5, task.findFortuneNumberForProduct(BigInteger.valueOf(6)));
        assertEquals(7, task.findFortuneNumberForProduct(BigInteger.valueOf(30)));
        assertEquals(13, task.findFortuneNumberForProduct(BigInteger.valueOf(210)));
        assertEquals(23, task.findFortuneNumberForProduct(BigInteger.valueOf(2310)));
        assertEquals(17, task.findFortuneNumberForProduct(BigInteger.valueOf(30030)));
        assertEquals(19, task.findFortuneNumberForProduct(BigInteger.valueOf(510510)));
        assertEquals(23, task.findFortuneNumberForProduct(BigInteger.valueOf(9699690)));
        assertEquals(37, task.findFortuneNumberForProduct(BigInteger.valueOf(223092870)));
    }

    @Test
    public void testIsPrimeInt() {
        assertEquals(true, task.isPrime(2));
        assertEquals(true, task.isPrime(47));
        assertEquals(true, task.isPrime(71));
        assertEquals(true, task.isPrime(127));
        assertEquals(true, task.isPrime(181));
        assertEquals(true, task.isPrime(349));
        assertEquals(true, task.isPrime(409));
        assertEquals(true, task.isPrime(449));
        assertEquals(true, task.isPrime(509));
    }

    @Test
    public void testIsNotPrimeInt() {
        assertEquals(false, task.isPrime(4));
        assertEquals(false, task.isPrime(48));
        assertEquals(false, task.isPrime(72));
        assertEquals(false, task.isPrime(128));
        assertEquals(false, task.isPrime(182));
        assertEquals(false, task.isPrime(350));
        assertEquals(false, task.isPrime(410));
        assertEquals(false, task.isPrime(450));
        assertEquals(false, task.isPrime(510));
    }

    @Test
    public void testIsPrimeBigInteger() {
        assertEquals(true, task.isPrime(BigInteger.valueOf(2)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(47)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(71)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(127)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(181)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(349)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(409)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(449)));
        assertEquals(true, task.isPrime(BigInteger.valueOf(509)));
    }

    @Test
    public void testIsNotPrimeBigInteger() {
        assertEquals(false, task.isPrime(BigInteger.valueOf(4)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(48)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(72)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(128)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(182)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(350)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(410)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(450)));
        assertEquals(false, task.isPrime(BigInteger.valueOf(510)));
    }

}