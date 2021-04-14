package intro

import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class PasswordSpec extends AnyWordSpec with Matchers with ScalaCheckDrivenPropertyChecks {


  "addition" should {
    "be associative" in {
      forAll { (x: Int, y: Int, z: Int) =>
        (x + y) + z shouldBe x + (y + z)
      }
    }
  }


  "randomTest" should {
    "be passing" in {
      val genx = Gen.choose(5, 10)
      val geny = Gen.oneOf(1, 3, 5)
      val genz = Gen.alphaLowerStr
      forAll(genx, geny, genz) { (x, y, z) =>
        println(x, y, z)
        true shouldBe true
      }
    }
  }

  "Gen test" should {
    "do what we want" in {
      val genx = Gen.choose(1, 5)
      val geny = Gen.oneOf(100, 105)
      val genz =
      forAll(genx, geny) { (x, y) =>
        println(x, y)
        true shouldBe true
      }
    }
  }

  Gen.choose(5, 10)
  Gen.oneOf(1, 3, 5)
  Gen.alphaLowerStr
  Gen.stringOfN(10, Gen.alphaNumChar)
//  Gen.stringOfN(10, Gen.oneOf(Gen.alphaNumChar, Gen.choose(1, 10)))

}



