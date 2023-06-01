
trait Translator {
  def translate(word: String): String
}

class DummyTranslator extends Translator {
  def translate(word: String): String = word
}