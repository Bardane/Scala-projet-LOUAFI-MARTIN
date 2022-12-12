package parsing

import org.scalatest.funsuite.AnyFunSuite

class RunwayFileTest extends AnyFunSuite {
  test("RunwayFile.getRunwayByAirport") {
    assert(RunwayFile("./././resources/runways.csv").getRunwayByAirport("LFPG").size == 4)
  }
}
