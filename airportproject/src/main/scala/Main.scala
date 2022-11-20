import csv.csvFile

object Main extends App {

  //We define the CSV paths
  val airportsPath = "./././resources/airports.csv"
  val countriesPath = "./././resources/countries.csv"
  val runwaysPath = "./././resources/runways.csv"

  //We read the CSVs line by line
  val dfAirports = new csvFile(airportsPath)
  dfAirports.readCSV

  val dfCountries = new csvFile(countriesPath)
  dfCountries.readCSV

  val dfRunways = new csvFile(runwaysPath)
  dfRunways.readCSV


}
