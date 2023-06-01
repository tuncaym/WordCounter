import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class WordCounterServiceSpec extends AnyFlatSpec with Matchers with ScalatestRouteTest {
  "WordCounterService" should "add words and return their count" in {
    val wordCounter = WordCounter.builder().withTranslator(new DummyTranslator).build()
    val route = WordCounterService.route(wordCounter)

    Post("/addWords", "hello world") ~> route ~> check {
      responseAs[String] shouldEqual "Words added"
    }

    Get("/count/hello") ~> route ~> check {
      responseAs[String] shouldEqual "1"
    }

    Get("/count/world") ~> route ~> check {
      responseAs[String] shouldEqual "1"
    }
  }
}
