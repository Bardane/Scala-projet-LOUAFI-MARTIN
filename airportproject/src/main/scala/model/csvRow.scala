package model

abstract class csvRow(row: String) {
  val line = row.split(",").map(_.replace("\"", ""))

  def getColumn(i: Int): Option[String] = line.size match {
    case size if i >= size => None
    case size if i < size && line(i) == "" => Some("Unknown")
    case size if i < size => Some(line(i))
  }
}
