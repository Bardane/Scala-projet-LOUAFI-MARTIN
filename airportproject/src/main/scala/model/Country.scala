package model

case class Country(row: String) extends csvRow(row)

object Country {
  def countryCode(country: Country): Option[String] = country.getColumn(1)
  def countryName(country: Country): Option[String] = country.getColumn(2)
}

