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
    val combinations = calcCombinations(money, coins, Vector[Int](), Vector[Vector[Int]]())

    println(combinations)

    combinations.length
  }

  def calcCombinations(amountToFill: Int, coinsSet: List[Int],
                       currCombination: Vector[Int], allCombinations: Vector[Vector[Int]]): Vector[Vector[Int]] = {

    println(s"$amountToFill [$coinsSet]")

    if (amountToFill == 0 || coinsSet.isEmpty)
      allCombinations


    else if (amountToFill - coinsSet.head == 0) {
      // adding current combination with head to success
      val c = allCombinations :+ (currCombination :+ coinsSet.head)

      println(s"FOUND combination ${currCombination :+ coinsSet.head}")

      // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
      calcCombinations(amountToFill, coinsSet.tail, currCombination, c)
    }


    else if (amountToFill - coinsSet.head < 0) {
      // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
      calcCombinations(amountToFill, coinsSet.tail, currCombination, allCombinations)
    }


    else if (amountToFill - coinsSet.head > 0) {
      // launching calculation to fill in (amount - head) with other coins
      val c1 = calcCombinations(amountToFill - coinsSet.head, coinsSet.tail, currCombination :+ coinsSet.head, allCombinations)

      // launching calculation to fill in (amount - head) with same coins (because head can be fitted once more)
      val c2 = calcCombinations(amountToFill - coinsSet.head, coinsSet, currCombination :+ coinsSet.head, c1)

      // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
      calcCombinations(amountToFill, coinsSet.tail, currCombination, c2)
    }




    allCombinations
  }
}