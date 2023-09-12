package solutions

import org.junit.jupiter.api.Test
import solutions.CHK.CheckoutSolution
import kotlin.test.assertEquals

class CheckoutSolutionTest {

//    - {"method":"checkout","params":["BEBEEE"],"id":"CHK_R4_063"}, expected: 160, got: 205
//    - {"method":"checkout","params":["ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"],"id":"CHK_R4_139"}, expected: 1880, got: 1895
//    - {"method":"checkout","params":["LGCKAQXFOSKZGIWHNRNDITVBUUEOZXPYAVFDEPTBMQLYJRSMJCWH"],"id":"CHK_R4_140"}, expected: 1880, got: 1895




    @Test
    fun testCheckout() {
//        assertEquals(0, CheckoutSolution.checkout(""))
        assertEquals(160, CheckoutSolution.checkout("BEBEEE"))
        assertEquals(80, CheckoutSolution.checkout("AB"))
        assertEquals(95, CheckoutSolution.checkout("ABD"))
        assertEquals(130, CheckoutSolution.checkout("AAA"))
        assertEquals(160, CheckoutSolution.checkout("AAAB"))
        assertEquals(200, CheckoutSolution.checkout("AAAAA"))
//        assertEquals(-1, CheckoutSolution.checkout("G"))
//        assertEquals(-1, CheckoutSolution.checkout("AG"))
        assertEquals(250, CheckoutSolution.checkout("AAAAAA"))
        assertEquals(300, CheckoutSolution.checkout("AAAAAAA"))
        assertEquals(330, CheckoutSolution.checkout("AAAAAAAA"))
        assertEquals(45, CheckoutSolution.checkout("BB"))
        assertEquals(70, CheckoutSolution.checkout("BE"))
        assertEquals(10, CheckoutSolution.checkout("F"))
        assertEquals(20, CheckoutSolution.checkout("FF"))
        assertEquals(20, CheckoutSolution.checkout("FFF"))
        assertEquals(30, CheckoutSolution.checkout("FFFF"))


    }
}


