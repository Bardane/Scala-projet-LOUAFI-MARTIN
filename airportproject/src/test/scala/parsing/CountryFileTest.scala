package parsing

import org.scalatest.funsuite.AnyFunSuite
import model.Country

class CountryFileTest extends AnyFunSuite {
  test("CountryFile.getCountryByCode") {
    assert(CountryFile("./././resources/countries.csv").getCountryByCode("FR") == Country("FR","France"))
  }

  test("CountryFile.getCountryByName") {
    assert(CountryFile("./././resources/countries.csv").getCountryByName("France") == Country("FR", "France"))
  }
}
