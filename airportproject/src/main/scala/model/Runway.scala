package model

case class Runway(row: String) extends csvRow(row)

object Runway {
  def airportId(runway: Runway): Option[String] = runway.getColumn(2)
  def runwayType(runway: Runway): Option[String] = runway.getColumn(5)
  def runwayLatitude(runway: Runway): Option[String] = runway.getColumn(8)
}
