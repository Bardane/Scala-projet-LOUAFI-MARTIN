package csv
import scala.io.Source

class csvFile(csvPath: String) {

  def parseCSV: Iterator[String] = Source.fromFile(csvPath).getLines

  def readCSV: Unit = parseCSV.foreach(println)
}
