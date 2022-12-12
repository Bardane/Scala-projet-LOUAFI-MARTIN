import parsing.{CountryFile, AirportFile, RunwayFile}

object Dev extends App {
  val countries = CountryFile("./././resources/countries.csv")
  println(countries.countries)
  println(countries.getCountryByCode("XY"))

//  val airports = AirportFile("./././resources/airports.csv")
//  println(airports.getAirportByCountryCode("FR"))
//
//  val runways = RunwayFile("./././resources/runways.csv")
//  val lfpg_runways = runways.getRunwayByAirport("LFPG")
//  println(lfpg_runways.flatMap(x => x.airportId))
}
