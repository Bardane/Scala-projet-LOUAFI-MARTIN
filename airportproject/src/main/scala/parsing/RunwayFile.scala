package parsing
import model.Runway
import model.Runway.airportId

case class RunwayFile(filepath: String) {
  """val runways = data.map(Runway(_))

  val runwaysMap: Map[String, List[Runway]] = runways.groupBy(airportId(_).get)

  def getRunwayByAirport(airportId: String): Option[List[Runway]] = runwaysMap.get(airportId)"""
}
