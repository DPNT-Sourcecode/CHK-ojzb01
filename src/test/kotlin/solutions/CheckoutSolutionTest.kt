package solutions

import org.junit.jupiter.api.Test
import solutions.CHK.CheckoutSolution
import kotlin.test.assertEquals

class CheckoutSolutionTest {

    @Test
    fun testCheckout() {
        assertEquals(0, CheckoutSolution.checkout(""))
        assertEquals(50, CheckoutSolution.checkout("A"))
        assertEquals(80, CheckoutSolution.checkout("AB"))
        assertEquals(95, CheckoutSolution.checkout("ABD"))
        assertEquals(130, CheckoutSolution.checkout("AAA"))
        assertEquals(160, CheckoutSolution.checkout("AAAB"))
        assertEquals(200, CheckoutSolution.checkout("AAAAA"))
        assertEquals(-1, CheckoutSolution.checkout("F"))
        assertEquals(-1, CheckoutSolution.checkout("AF"))
        assertEquals(250, CheckoutSolution.checkout("AAAAAA"))
        assertEquals(300, CheckoutSolution.checkout("AAAAAAA"))
        assertEquals(330, CheckoutSolution.checkout("AAAAAAAA"))
        assertEquals(45, CheckoutSolution.checkout("BB"))
        assertEquals(70, CheckoutSolution.checkout("BE"))
        assertEquals(80, CheckoutSolution.checkout("BEE"))


    }
}
