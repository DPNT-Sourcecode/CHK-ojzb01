package solutions.CHK

data class Product(val price: Int, val offers: List<Offer> = listOf())
data class Offer(
    val requiredCount: Int,
    val price: Int,
    val freeItem: Char? = null,
    val freeItemCount: Int = 1,
    val groupItems: List<Char>? = null
)

object CheckoutSolution {
    private var productMap = mutableMapOf<Char, Product>(
        'A' to Product(50, listOf(Offer(5,200), Offer(3, 130))),
        'B' to Product(30, listOf(Offer(2, 45))),
        'C' to Product(20),
        'D' to Product(15),
        'E' to Product(40, listOf(Offer(2, 80, 'B'))),
        'F' to Product(10, listOf(Offer(3, 20))),
        'G' to Product(20),
        'H' to Product(10, listOf(Offer(10, 80), Offer(5, 45))),
        'I' to Product(35),
        'J' to Product(60),
        'K' to Product(70, listOf(Offer(2, 150))),
        'L' to Product(90),
        'M' to Product(15),
        'N' to Product(40, listOf(Offer(3, 120, 'M'))),
        'O' to Product(10),
        'P' to Product(50, listOf(Offer(5, 200))),
        'Q' to Product(30, listOf(Offer(3, 80))),
        'R' to Product(50, listOf(Offer(3, 150, 'Q'))),
        'S' to Product(20, listOf(Offer(3, 45, groupItems = listOf('S', 'T', 'X', 'Y', 'Z')))),
        'T' to Product(20, listOf(Offer(3, 45, groupItems = listOf('S', 'T', 'X', 'Y', 'Z')))),
        'U' to Product(40, listOf(Offer(4,120))),
        'V' to Product(50, listOf(Offer(3, 130), Offer(2, 90))),
        'W' to Product(20),
        'X' to Product(17, listOf(Offer(3, 45, groupItems = listOf('S', 'T', 'X', 'Y', 'Z')))),
        'Y' to Product(20, listOf(Offer(3, 45, groupItems = listOf('S', 'T', 'X', 'Y', 'Z')))),
        'Z' to Product(21, listOf(Offer(3, 45, groupItems = listOf('S', 'T', 'X', 'Y', 'Z')))),
    )

    fun checkout(skus: String): Int {
        val itemCounts = skus.groupingBy { it }.eachCount().toMutableMap()

        if (itemCounts.keys.any { it !in productMap }) return -1

        var total = 0

        total += processFreeItems(itemCounts)
        total += processGroupDiscounts(itemCounts)
        total += processRegularDiscountsAndRemainingItems(itemCounts)

        return total

    }

    private fun processRegularDiscountsAndRemainingItems(itemCounts: MutableMap<Char, Int>): Int {
        var total = 0
        itemCounts.forEach { p ->
            val productChar = p.key
            var count = p.value
            val product = productMap[productChar]!!

            product.offers
                .filter { it.groupItems == null && it.freeItem == null }
                .sortedByDescending { o -> o.requiredCount }
                .forEach { offer ->
                    while(count >= offer.requiredCount) {
                        total += offer.price
                        count -= offer.requiredCount
                    }
                }

            total += count * product.price
        }
        return total
    }

    private fun processGroupDiscounts(itemCounts: MutableMap<Char, Int>): Int {
        var total = 0
        productMap.values.forEach { product ->
            val groupOffers = product.offers.filter { it.groupItems != null }
            groupOffers.forEach { offer ->
                val eligibleGroupItems = offer.groupItems!!.associateWith {
                    itemCounts.getOrDefault(it, 0)
                }.toMutableMap()
                while (eligibleGroupItems.values.sum() >= offer.requiredCount) {
                    total += offer.price

                    var itemsNeededForDiscount = offer.requiredCount
                    for ((item, availabeCount) in eligibleGroupItems.entries.sortedByDescending {
                        productMap[it.key]!!.price
                    }) {
                        val itemsToUseForDiscount = minOf(availabeCount, itemsNeededForDiscount)
                        eligibleGroupItems[item] = availabeCount - itemsToUseForDiscount
                        itemCounts[item] = itemCounts.getOrDefault(item, 0) - itemsToUseForDiscount
                        itemsNeededForDiscount -= itemsToUseForDiscount
                    }
                }
            }
        }
        return total
    }

    private fun processFreeItems(itemCounts: MutableMap<Char, Int>): Int {
        var total = 0
        productMap.forEach { (productChar, product) ->
            var count = itemCounts.getOrDefault(productChar, 0)

            product.offers.filter { it.freeItem != null }.sortedByDescending { it.requiredCount }.forEach { offer ->
                while (count >= offer.requiredCount && (itemCounts[offer.freeItem] ?: 0) >= offer.freeItemCount) {
                    total += offer.price
                    count -= offer.requiredCount
                    itemCounts[offer.freeItem!!] = maxOf(0, itemCounts[offer.freeItem]!! - offer.freeItemCount)
                }
            }
            itemCounts[productChar] = count
        }
        return total
    }
}




