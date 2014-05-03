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
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def getPascalRow(index: Int): scala.collection.mutable.ArrayBuffer[Int] = {
      if (index == 0) {
        ArrayBuffer(1)
      }
      else {
        val currentRow: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
        val previousRow = getPascalRow(index - 1)
        val previousRowLength = previousRow.length

        for (i <- 0 to previousRowLength) {
          val topLeftIndex = i - 1
          val topRightIndex = i

          var topLeftValue: Int = 0
          var topRightValue: Int = 0

          if (topLeftIndex >= 0) {
            topLeftValue = previousRow(topLeftIndex)
          }

          if (topRightIndex < previousRowLength) {
            topRightValue = previousRow(topRightIndex)
          }

          currentRow += topLeftValue + topRightValue
        }

        currentRow
      }
    }

    getPascalRow(r)(c)
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

  //  def balance(chars: List[Char]): Boolean = {
  //    val openSymbol = '('
  //    val closeSymbol = ')'
  //
  //    var openedCount = 0
  //    var closedCount = 0
  //
  //    for (i <- 0 to chars.length - 1) {
  //      if (chars(i) == openSymbol)
  //        openedCount = openedCount + 1
  //      else if (chars(i) == closeSymbol)
  //        closedCount = closedCount + 1
  //      if (openedCount < closedCount)
  //        return false
  //    }
  //
  //    true
  //  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    1
  }
}
