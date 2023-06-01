### WordCounterService

The `WordCounterService` is an Akka HTTP microservice that provides an API for counting the occurrences of words. It uses a `WordCounter` object to keep track of the word counts.

#### Usage

To use the `WordCounterService`, you need to create a `WordCounter` object using its `Builder` class. The `Builder` class allows you to configure the `WordCounter` object by specifying a `Translator` object that will be used to translate words before counting them.

Here is an example of how you can create a `WordCounter` object and start the `WordCounterService`:

```scala
val wordCounter = WordCounter.builder().withTranslator(new DummyTranslator()).build()
val route = WordCounterService.route(wordCounter)
Http().bindAndHandle(route, "localhost", 8080)
