import scala.util.control.Breaks

val openSymbol = '('
val closeSymbol = ')'

def balance(chars: List[Char]): Boolean = {
  false
}
def getClosedScope(chars: List[Char]): List[Char] = {
  var openIndex = -1
  var closeIndex = -1

  //wtf, how to break loop????
  for (i <- 0 to chars.length - 1) {
    if (chars(i) == openSymbol && openIndex == -1) {
      openIndex = i
    }
  }

  if (openIndex == -1) {
    println("no opening symbol")
    return null
  }


  for (i <- chars.length - 1 to openIndex by -1) {
    if (chars(i) == closeSymbol && closeIndex == -1) {
      closeIndex = i
    }
  }

  if (openIndex == -1 && closeIndex == -1) {
    //no braced, we are fine
  }
  else if (openIndex == -1 && closeIndex != -1 ||
    openIndex != -1 && closeIndex == -1) {
    //brace opened but not closed or other way
  }
  else {
    //we are good - found ranges
  }

  chars.slice(openIndex, closeIndex)
}
//"(just an) example".toList
//getClosedScope("123(..)456".toList)
val sliced = getClosedScope("((123456))12".toList)
println(sliced)

//balance("())(".toList)
//balance("123".toList)
//balance("()()()()()".toList)
//balance(" (5 - (1+2) * 2 - 2) ".toList)
//balance("()()()()()".toList)