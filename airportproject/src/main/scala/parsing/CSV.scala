package parsing
import scala.io.Source

object CSV {
  def read[A](filepath: String, parser: Array[String] => Option[A], sep: String = ","): List[A] = {
    val file = Source.fromFile(filepath)
    val lines = file.getLines.drop(1).toList
    file.close()

    lines.flatMap(line => parser(line.split(sep).map(_.replace("\"", ""))))
  }
}
