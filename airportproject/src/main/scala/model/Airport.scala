package model

case class Airport(row: String) extends csvRow(row)

object Airport {
  def airportId(airport: Airport): Option[String] = airport.getColumn(1)
  def airportCountry(airport: Airport): Option[String] = airport.getColumn(8)
}
