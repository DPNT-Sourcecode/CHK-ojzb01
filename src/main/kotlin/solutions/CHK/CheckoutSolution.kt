package solutions.CHK

data class Product(val price: Int, val offers: List<Offer> = listOf())
data class Offer(val requiredCount: Int, val price: Int)

object CheckoutSolution {
    private var productMap = mutableMapOf<Char, Product>(
        'A' to Product(50, listOf(Offer(5,200), Offer(3, 130))),
        'B' to Product(30, listOf(Offer(2, 45))),
        'C' to Product(20),
        'D' to Product(15),
        'E' to Product(40),
        'F' to Product(10),
        'G' to Product(20),
        'H' to Product(10),
        'I' to Product(35),
        'J' to Product(60),
        'K' to Product(80),
        'L' to Product(90),
        'M' to Product(15),
        'N' to Product(40),
        'O' to Product(10),
        'P' to Product(50),
        'Q' to Product(30),
        'R' to Product(50),
        'S' to Product(30),
        'T' to Product(20),
        'U' to Product(40),
        'V' to Product(50),
        'W' to Product(20),
        'X' to Product(90),
        'Y' to Product(10),
        'Z' to Product(50),


        )

    fun checkout(skus: String): Int {
        if (skus.any { it !in "ABCDEF" }) return -1

        val itemCounts = mutableMapOf<Char, Int>(
            'A' to 0,
            'B' to 0,
            'C' to 0,
            'D' to 0,
            'E' to 0,
            'F' to 0
        )

        skus.forEach {
            itemCounts[it] = itemCounts[it]!! + 1
        }

        val aTotal = calcATotal(itemCounts['A']!!)
        val bTotal = calcBTotal(itemCounts['B']!!, itemCounts['E']!!)
        val cTotal = itemCounts['C']!! * 20
        val dTotal = itemCounts['D']!! * 15
        val eTotal = itemCounts['E']!! * 40
        val fTotal = calcFTotal(itemCounts['F']!!)

        return aTotal + bTotal + cTotal + dTotal + eTotal + fTotal
    }

    private fun calcFTotal(count: Int): Int {
        val offer = count / 3
        val remaining = count % 3

        return offer * 20 + remaining * 10
    }

    fun calcATotal(count: Int): Int {
        val offer1Count = count / 5
        val remainingAfterOffer1 = count % 5
        val offer2Count = remainingAfterOffer1 / 3
        val remainingAfterOffer2 = remainingAfterOffer1 % 3

        return offer1Count * 200 + offer2Count * 130 + remainingAfterOffer2 * 50
    }

    fun calcBTotal(bCount: Int, eCount: Int): Int {
        val bFreeCount = eCount / 2
        val bChargeable = bCount - bFreeCount
        if (bChargeable <= 0) return 0

        val offer = bChargeable / 2
        val remaining = bChargeable % 2

        return offer * 45 + remaining * 30
    }
}




