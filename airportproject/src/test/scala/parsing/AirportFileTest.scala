package parsing

import org.scalatest.funsuite.AnyFunSuite
class AirportFileTest extends AnyFunSuite {
  test("AirportFile.getAirportByCountryCode") {
    assert(AirportFile("./././resources/airports.csv").getAirportByCountryCode("FR").size == 789)
    assert(AirportFile("./././resources/airports.csv").getAirportByCountryCode("XY")(0).airportCountry == "")
  }
}
