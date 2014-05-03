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

      def retrieveRowElementByIndex(buffer: ArrayBuffer[Int], i: Int) = {
        if (i < 0 || i >= buffer.length)
          0
        else
          buffer(i)
      }

      def calculateRowElement(index: Int, previousRow: ArrayBuffer[Int]): Int = {
        retrieveRowElementByIndex(previousRow, index - 1) +
          retrieveRowElementByIndex(previousRow, index)
      }

      if (index == 0) {
        ArrayBuffer(1)
      }
      else {
        val currentRow: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
        val previousRow = getPascalRow(index - 1)

        for (i <- 0 to previousRow.length) {
          currentRow += calculateRowElement(i, previousRow)
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

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    1
  }
}
