package recfun

import scala.collection.mutable.ArrayBuffer

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
    val combinations = new ArrayBuffer[Vector[Int]]()


    def iter(amountToFill: Int, currentlyUseCombination: Vector[Int], coinsSet: List[Int]) : Boolean = {
      println(s"$amountToFill [$coinsSet]")

      if (amountToFill == 0)
        false

      else if(coinsSet.isEmpty)
        false

      else {

        val dif = amountToFill - coinsSet.head

        if (dif > 0) {
          iter(dif, currentlyUseCombination :+ coinsSet.head, coinsSet.tail)

          iter(dif, currentlyUseCombination :+ coinsSet.head, coinsSet)
        }

        else if (dif == 0) {
//          println(s"===> ${currentlyUseCombination :+ coinsSet.head}")
          combinations += currentlyUseCombination :+ coinsSet.head

          println(s"found a good combination ${currentlyUseCombination :+ coinsSet.head} with head coin ${coinsSet.head},\n" +
            s"now will search to express $amountToFill with smaller set ${coinsSet.tail}")

          iter(amountToFill, currentlyUseCombination, coinsSet.tail)

          true
        }

        //currently added coin too big to fill amount
        else {
          iter(amountToFill, currentlyUseCombination, coinsSet.tail)
        }

      }


    }

    iter(money, Vector[Int](), coins)

    println(combinations)

    combinations.length
  }

  def countSingleCoinInAmount(targetAmount: Int, currentAmount: Int, coin: Int): Int = {
    if (currentAmount == targetAmount)
      targetAmount / coin
    else if (currentAmount < targetAmount)
      countSingleCoinInAmount(targetAmount, currentAmount + coin, coin)
    else
      0
  }


}
