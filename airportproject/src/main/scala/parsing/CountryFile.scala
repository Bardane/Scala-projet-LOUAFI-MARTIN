package parsing
import model.Country

import scala.collection.mutable.HashMap

case class CountryFile(filepath: String){
  val countries = CSV.read(filepath, Country.fromCsvLine, ",")

  val countriesMap: HashMap[String, Country] = HashMap()
  val countriesIdMap: HashMap[String, String] = HashMap()

  def loadCountries(): Unit = countries.map(country => countriesMap.addOne(country.countryCode -> country))
  def loadCountryIds(): Unit = countries.map(country => countriesIdMap.addOne(country.countryName -> country.countryCode))

  def getCountryByCode(countryCode: String): Option[Country] = {
    countriesMap.get(countryCode)
  }

  def getCountryByName(countryName: String): Option[Country] = {
    countriesIdMap.get(countryName).flatMap(getCountryByCode)
  }

  loadCountries()
  loadCountryIds()

}
