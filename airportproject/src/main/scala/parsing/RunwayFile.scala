package parsing
import model.Runway

case class RunwayFile(filepath: String) {
  val runways = CSV.read(filepath, Runway.fromCsvLine)

  val runwaysMap: Map[String, List[Runway]] = runways.groupBy(_.airportId)

  def getRunwayByAirport(airportId: String): List[Runway] = {
    runwaysMap.get(airportId) match {
      case Some(runway) => runwaysMap(airportId)
      case None => List(Runway("", "", ""))
    }
  }
}
