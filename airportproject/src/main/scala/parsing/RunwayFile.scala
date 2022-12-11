package parsing
import model.Runway

case class RunwayFile(filepath: String) {
  val runways = CSV.read(filepath, Runway.fromCsvLine)

  val runwaysMap: Map[String, List[Runway]] = runways.groupBy(_.airportId)

  def getRunwayByAirport(airportId: String): Option[List[Runway]] = runwaysMap.get(airportId)
}
