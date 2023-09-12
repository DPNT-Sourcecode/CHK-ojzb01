package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        if (skus.any { it !in "ABCD" }) return -1

        val itemCounts = mutableMapOf<Char, Int>(
            'A' to 0,
            'B' to 0,
            'C' to 0,
            'D' to 0,
            'E' to 0
        )

        skus.forEach {
            itemCounts[it] = itemCounts[it]!! + 1
        }

        val aTotal = (itemCounts['A']!! / 3) * 130 + (itemCounts['A']!! % 3) * 50
        val bTotal = (itemCounts['B']!! / 2) * 45 + (itemCounts['B']!! % 2) * 30
        val cTotal = itemCounts['C']!! * 20
        val dTotal = itemCounts['D']!! * 15

        return aTotal + bTotal + cTotal + dTotal
    }

    fun calcATotal(count: Int): Int {
        val offer1Count = count / 5
        val remainingAfterOffer1 = count % 5
        val offer2 = remainingAfterOffer1 / 3
        val remainingAfterOffer2 =remainingAfterOffer1 % 3
    }
}