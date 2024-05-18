import static org.junit.jupiter.api.Assertions.*;

class MyFormTest {
    @org.junit.jupiter.api.Test
    void TestIsPrime1() {
        assertTrue(MyForm.IsPrime(997));
    }
    @org.junit.jupiter.api.Test
    void TestIsPrime2() {
        assertFalse(MyForm.IsPrime(13445));
    }
    @org.junit.jupiter.api.Test
    void TestGenerateNum1() {
        assertEquals(9699690, GenerateNum(1, 20));
    }
    @org.junit.jupiter.api.Test
    void TestGenerateNum2() {
        assertEquals(1062347, GenerateNum(10, 28));
    }
    @org.junit.jupiter.api.Test
    void TestIsSquare1() {
        assertTrue(MyForm.IsSquare(169));
    }
    @org.junit.jupiter.api.Test
    void TestIsSquare2() {
        assertFalse(MyForm.IsSquare(168));
    }
    @org.junit.jupiter.api.Test
    void TestFermaFactorization() {
        long[] arr = {491, 509};
        assertArrayEquals(arr, MyForm.FermaFactorization(249919));
    }

    public long GenerateNum(int lower, int higher) {
        boolean[] prime = new boolean[higher+1];
        // ініціалзація масиву одиницями
        for(int i = 0; i <= higher; i++) {
            prime[i] = true;
        }
        for(int p = 2; p * p <= higher; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= higher; i += p) {
                    prime[i] = false; // викреслення індексів
                }
            }
        }
        long num = 1;
        for(int p = lower; p <= higher; p++) {
            if(prime[p]) {
                num *= p;
            }
        }
        return num;
    }
}