package parsing
import scala.io.Source

abstract class csvFile(filepath: String) {
  val file = Source.fromFile(filepath)
  val data : List[String] = file.getLines.drop(1).toList
  file.close()
}
