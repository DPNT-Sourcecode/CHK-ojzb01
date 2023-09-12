package solutions.CHK

data class Product(val price: Int, val offers: List<Offer> = listOf())
data class Offer(val requiredCount: Int, val price: Int, val freeItem: Char? = null, val freeItemCount: Int = 1)

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
        'K' to Product(80, listOf(Offer(2, 150))),
        'L' to Product(90),
        'M' to Product(15),
        'N' to Product(40, listOf(Offer(3, 120, 'M'))),
        'O' to Product(10),
        'P' to Product(50, listOf(Offer(5, 200))),
        'Q' to Product(30, listOf(Offer(3, 80))),
        'R' to Product(50, listOf(Offer(3, 150, 'Q'))),
        'S' to Product(30),
        'T' to Product(20),
        'U' to Product(40, listOf(Offer(4,120))),
        'V' to Product(50, listOf(Offer(3, 130), Offer(2, 90))),
        'W' to Product(20),
        'X' to Product(90),
        'Y' to Product(10),
        'Z' to Product(50)
    )

    fun checkout(skus: String): Int {

        val itemCounts = mutableMapOf<Char, Int>()

        skus.forEach {
            if(!productMap.containsKey(it)) return -1
            itemCounts[it] = itemCounts.getOrDefault(it, 0) + 1
        }

        var total = 0

        // process all offers that give other products free
        productMap.forEach { (productChar, product) ->
            var count = itemCounts.getOrDefault(productChar, 0)
            product.offers.forEach { offer ->
                if(offer.freeItem != null) {
                    while (count >= offer.requiredCount) {
                        val remainingFreeItemCount = itemCounts.getOrDefault(offer.freeItem, 0) - offer.freeItemCount
                        if (remainingFreeItemCount < 0) break

                        itemCounts[offer.freeItem] = remainingFreeItemCount
                        count-= offer.requiredCount
                        total+=offer.price
                    }
                }
            }
        }

        itemCounts.forEach {
            val productChar = it.key
            var count = it.value
            val product = productMap[productChar]
            val offers = product?.offers

            offers?.forEach { offer ->
                if(offer.freeItem != null) {
                    while (count >= offer.requiredCount) {
                        val remainingFreeItemCount = itemCounts.getOrDefault(offer.freeItem, 0) - offer.freeItemCount

                        // If we don't have enough free items, rollback and break
                        if (remainingFreeItemCount < 0) {
                            break
                        }

                        itemCounts[offer.freeItem] = remainingFreeItemCount
                        total += offer.price

                        count -= offer.requiredCount
                    }
                }
            }

            total += count * product!!.price
        }

        itemCounts.forEach {
            val productChar = it.key
            var count = it.value
            val product = productMap[productChar]
            val offers = product?.offers

            offers?.forEach { offer ->
                while (count >= offer.requiredCount && offer.freeItem == null) {
                    total += offer.price
                    count -= offer.requiredCount
                }
            }

            total += count * product!!.price
        }

        return total
    }
}

