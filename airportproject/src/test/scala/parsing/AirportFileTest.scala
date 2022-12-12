package parsing

import org.scalatest.funsuite.AnyFunSuite
class AirportFileTest extends AnyFunSuite {
  test("AirportFile.getAirportByCountryCode") {
    assert(AirportFile("./././resources/airports.csv").getAirportByCountryCode("FR").size == 789)
  }
}
