package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {

  import Main.countChange

//  test("recur 1") {
//    assert(countChange(1, List(1, 2)) === 1)
//  }

//  test("recur 2") {
//    assert(countChange(10, List(2, 5, 10)) === 3)
//  }

//  test("recur 3") {
//    assert(countChange(6, List(3, 2)) === 2)
//  }

    test("recur 4") {
      assert(countChange(24, List(6, 4, 2)) === 5)
    }




  //  test("single 2") {
  //    assert(countChange(15, List(2, 5, 10)) === 1)
  //  }
  //
  //  test("single 3") {
  //    assert(countChange(1, List(2, 5, 10)) === 0)
  //  }
  //
  //  test("single 4") {
  //    assert(countChange(21, List(2, 5, 10)) === 0)
  //  }
  //
  //  test("single 5") {
  //    assert(countChange(5, List(2, 5, 10)) === 1)
  //  }


  //  test("countChange: example given in instructions") {
  //    assert(countChange(4, List(1, 2)) === 3)
  //  }
  //
  //  test("countChange: sorted CHF") {
  //    assert(countChange(300, List(5, 10, 20, 50, 100, 200, 500)) === 1022)
  //  }
  //
  //  test("countChange: no pennies") {
  //    assert(countChange(301, List(5, 10, 20, 50, 100, 200, 500)) === 0)
  //  }
  //
  //  test("countChange: unsorted CHF") {
  //    assert(countChange(300, List(500, 5, 50, 100, 20, 200, 10)) === 1022)
  //  }
}
