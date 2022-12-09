package parsing
import model.Runway
import model.Runway.airportId

case class runwayFile(filepath: String) extends csvFile(filepath) {
  val runways = data.map(Runway(_))

  val runwaysMap: Map[String, List[Runway]] = runways.groupBy(airportId(_).get)

  def getRunwayByAirport(airportId: String): Option[List[Runway]] = runwaysMap.get(airportId)
}
