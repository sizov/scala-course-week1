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
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    getPascalRow(r)(c)
  }

  /**
   *
   * @param index
   * @return
   */
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

  /**
   *
   * @param index
   * @param previousRow
   * @return
   */
  def calculateRowElement(index: Int, previousRow: Array[Int]): Int = {
    retrieveRowElementByIndex(previousRow, index - 1) + retrieveRowElementByIndex(previousRow, index)
  }

  /**
   *
   * @param buffer
   * @param i
   * @return
   */
  def retrieveRowElementByIndex(buffer: Array[Int], i: Int) = {
    if (i < 0 || i >= buffer.length)
      0
    else
      buffer(i)
  }


  /**
   * Exercise 2
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
    amountUsingSingleCoins(money, coins)
  }

  def amountUsingSingleCoins(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty)
      0
    else if (canBeDoneWithSoloCoin(money, coins.head))
      1 + amountUsingSingleCoins(money, coins.tail)
    else
      amountUsingSingleCoins(money, coins.tail)
  }

  def canBeDoneWithSoloCoin(money: Int, coin: Int): Boolean = {
    closestToTarget(money, 0, coin) == money
  }

  def closestToTarget(target: Int, current: Int, increm: Int): Int = {
    if (current >= target)
      current
    else
      closestToTarget(target, current + increm, increm)
  }

}
