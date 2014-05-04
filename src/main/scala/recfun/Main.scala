package recfun

object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }


  /**
   * ======================================================
   * Exercise 1
   * ======================================================
   */
  def pascal(c: Int, r: Int): Int = {
    getPascalRow(r)(c)
  }

  def getPascalRow(index: Int): Array[Int] = {
    if (index == 0)
      Array(1)
    else {
      val currentRow: Array[Int] = Array.ofDim[Int](index + 1)
      val previousRow = getPascalRow(index - 1)
      for (i <- 0 to previousRow.length) {
        currentRow(i) = calculateRowElement(i, previousRow)
      }

      currentRow
    }
  }

  def calculateRowElement(index: Int, previousRow: Array[Int]): Int = {
    retrieveRowElementByIndex(previousRow, index - 1) + retrieveRowElementByIndex(previousRow, index)
  }

  def retrieveRowElementByIndex(buffer: Array[Int], i: Int) = {
    if (i < 0 || i >= buffer.length)
      0
    else
      buffer(i)
  }


  /**
   * ======================================================
   * Exercise 2
   * ======================================================
   */
  def balance(chars: List[Char]): Boolean = {
    val openSymbol = '('
    val closeSymbol = ')'

    def balanceIter(chars: List[Char], openedCount: Int, closedCount: Int): Boolean = {
      if (chars.isEmpty)
        true
      else if (chars.head == openSymbol)
        balanceIter(chars.tail, openedCount + 1, closedCount)
      else if (chars.head == closeSymbol) {
        if (closedCount == openedCount)
          false
        else
          balanceIter(chars.tail, openedCount, closedCount + 1)
      }
      else
        balanceIter(chars.tail, openedCount, closedCount)
    }

    balanceIter(chars, 0, 0)
  }

  /**
   * ======================================================
   * Exercise 3
   * ======================================================
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    fillAmountIter(money, coins)

  }


  //amount = 2, coinsSet = [1,2]
  def fillAmountIter(amount: Int, coinsSet: List[Int]): Int = {

    println(s"filling $amount with $coinsSet")

    if (coinsSet.isEmpty)
      0

    else if (amount == 0)
      0

    else if (amount < coinsSet.head) {
//      println(s"Can\'t fill $amount with head ${coinsSet.head}. Will try with ${coinsSet.tail}")
      fillAmountIter(amount, coinsSet.tail)
    }

    else if (amount > coinsSet.head) {
//      println(s"Can fill $amount with head (${coinsSet.head}), more remains. Will try filling smaller amount (${amount - coinsSet.head})")
      //      1 + fillAmountIter(amount - coinsSet.head, coinsSet)
      fillAmountIter(amount - coinsSet.head, coinsSet)
    }

    else {
//      println(s"Can EXACTLY fill $amount with head (${coinsSet.head}), NO more amount to fill remains")

      //FIXME: need to reset amount now
      1 + fillAmountIter(amount, coinsSet.tail)
    }


    //      lessAmountToFillSameCoins + sameAmountToFillLessCoins


    //    def func() {
    //      if (x)
    //        0
    //      else if (y)
    //        func(A) + func(B)
    //      else
    //        func(c)
    //    }
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //    if (coinsSet.isEmpty)
    //      0
    //    else if (canBeDoneWithSoloCoin(amountToFill, coinsSet.head))
    //      1 + amountUsingSingleCoins(amountToFill, coinsSet.tail)
    //    else
    //    val
    //    amountUsingCoinsIter (amountToFill -, coinsSet.tail)
  }


  //  def amountUsingSingleCoins(money: Int, coins: List[Int]): Int = {
  //    if (coins.isEmpty)
  //      0
  //    else if (canBeDoneWithSoloCoin(money, coins.head))
  //      1 + amountUsingSingleCoins(money, coins.tail)
  //    else
  //      amountUsingSingleCoins(money, coins.tail)
  //  }
  //
  //  def canBeDoneWithSoloCoin(money: Int, coin: Int): Boolean = {
  //    closestToTarget(money, 0, coin) == money
  //  }
  //
  //  def closestToTarget(target: Int, current: Int, increm: Int): Int = {
  //    if (current >= target)
  //      current
  //    else
  //      closestToTarget(target, current + increm, increm)
  //  }

}
