package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        if (skus.any { it !in "ABCD" }) return -1

        val itemCounts = mapOf<Char, Int>(
            'A' to 0,
            'B' to 0,
            'C' to 0,
            'D' to 0
        )

        val aTotal = (itemCounts['A']!! / 3) * 130 + (itemCounts['A']!! % 3) * 50
    }
}