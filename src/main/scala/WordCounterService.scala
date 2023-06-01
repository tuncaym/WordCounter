import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

object WordCounterService extends App {

  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")
  
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  def route(wordCounter: WordCounter): Route =
    path("addWords") {
      post {
        entity(as[String]) { words =>
          wordCounter.addWords(words.split(" "): _*)
          complete("Words added")
        }
      }
    } ~
      path("count" / Segment) { word =>
        get {
          complete(wordCounter.count(word).toString)
        }
      }

  val wordCounter = WordCounter.builder().withTranslator(new DummyTranslator).build()
  Http().bindAndHandle(route(wordCounter), host, port)
}
