import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.mockito.MockitoSugar

class WordCounterSpec extends AnyFlatSpec with Matchers with MockitoSugar {
  "WordCounter" should "count words correctly" in {
    val translator = mock[Translator]
    when(translator.translate("flower")).thenReturn("flower")
    when(translator.translate("flor")).thenReturn("flower")
    when(translator.translate("blume")).thenReturn("flower")

    val wordCounter = WordCounter.builder().withTranslator(translator).build()
    wordCounter.addWords("flower", "flor", "blume")
    wordCounter.count("flower") shouldBe 3
    wordCounter.count("flor") shouldBe 3
    wordCounter.count("blume") shouldBe 3
  }

  it should "not count words with non-alphabetic characters" in {
    val translator = mock[Translator]
    val wordCounter = WordCounter.builder().withTranslator(translator).build()
    wordCounter.addWords("flower", "flor!", "blume")
    wordCounter.count("flower") shouldBe 2
  }

  it should "return 0 for words that have not been added" in {
    val translator = mock[Translator]
    val wordCounter = WordCounter.builder().withTranslator(translator).build()
    wordCounter.count("flower") shouldBe 0
  }

  it should "allow adding multiple words at once" in {
    val translator = mock[Translator]
    when(translator.translate("flower")).thenReturn("flower")
    when(translator.translate("flor")).thenReturn("flower")
    when(translator.translate("blume")).thenReturn("flower")

    val wordCounter = WordCounter.builder().withTranslator(translator).build()
    wordCounter.addWords(Seq("flower", "flor", "blume"): _*)
    wordCounter.count("flower") shouldBe 3
  }
}
