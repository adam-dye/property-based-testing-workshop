package calc

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CalculatorSpec extends AnyWordSpec with Matchers {

  // EXERCISES
  //
  // 1. Turn these test into Property Based Tests
  //    - Verify the Exception in eval() WITHOUT hard-coding a particular test case
  //    - Fix the bug and verify that the Exception no longer occurs
  //    - Write a property-based test as documentation,
  //      specifically to check the absence of an error in this scenario
  //
  // 2. Write property based tests to verify the following mathematical laws:
  //    - + and * are commutative -- x + y == y + x
  //    - + and * are associative -- (x + y) + z == x + (y + z)
  //
  // 3. (Harder) Write a general property based test to help verify
  //    that eval() *never* throws an exception,
  //    no matter what expression we throw at it
  //    - You'll need to create a Gen[Expr] that produces Exprs that are 2-3 levels deep
  //    - Look at Gen.sized and Gen.resize for inspiration

  val three = Num(3)
  val four = Num(4)

  "Calculator.eval" should {
    "eval an addition" in {
      Calculator.eval(Add(three, four)) shouldBe 7
    }

    "eval a subtraction" in {
      Calculator.eval(Sub(three, four)) shouldBe -1
    }

    "eval a multiplication" in {
      Calculator.eval(Mul(three, four)) shouldBe 12
    }

    "eval a division" in {
      Calculator.eval(Div(three, four)) shouldBe 0
    }
  }
}
