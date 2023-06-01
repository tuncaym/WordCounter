import scala.collection.mutable

class WordCounter private(translator: Translator) {
  private val wordCounts = mutable.Map[String, Int]()

  def addWords(words: String*): Unit = {
    for (word <- words) {
      if (word.forall(_.isLetter)) {
        val translatedWord = translator.translate(word)
        val currentCount = wordCounts.getOrElse(translatedWord, 0)
        wordCounts(translatedWord) = currentCount + 1
      }
    }
  }

  def count(word: String): Int = {
    wordCounts.getOrElse(translator.translate(word), 0)
  }
}

object WordCounter {
  def builder(): Builder = Builder()

  case class Builder private (translator: Option[Translator] = None) {
    def withTranslator(translator: Translator): Builder = copy(translator = Some(translator))
    def build(): WordCounter = {
      val validTranslator = translator.getOrElse(throw new IllegalArgumentException("Translator is required"))
      new WordCounter(validTranslator)
    }
  }
}
