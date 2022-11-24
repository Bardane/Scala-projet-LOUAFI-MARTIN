package model

case class Countries() {
}

object Countries {
  def fromCSV(line: Array[String]): Option[Countries]
}