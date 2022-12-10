package storingQuery

case class Projet(){

}

object Projet {

  def Query(countryCodeOrName: String): List[String]=
  {
    val countryExist : Option[Country] = getCountry(countryCodeOrName.replace("\"",""))
    countryExist.isEmpty match
    {
      case true => List(s"${countryCodeOrName} country code or name does not exist ")
      case false =>
        val head : String = s"Airports and runways for ${
          countryExist.Name()
        } : \n"

        val tail : List[String] =
          fullMap.get(countryExist.Code())
            .flatMap{
            airportRunways =>
              val airport: String = airportRunways
                ._1
                .Print() + "\n"
              airport::airportRunways._2.map{runway.Print() + "\n"}
          }
        head::tail
    }
  }

  def Report(): List[String]=
  {
    val countryAirport = countryFile.countryList
      .map{country =>
        val airportCode = airportFile.getAirportViaCode(country.Code())
        airpotCode.isEmpty match {
          case true => (country, 0)
          case false => (country, airportCode.size)
        }
      }
      .sortWith(_._2 > _._2)

    val head1 : String = "10 countries with highest number of airports (with count):\n"
    val highestAirports: List[String] =
      head1::countryAirport
        .take(10)
        .map{
          x => s"    - ${x._1.Name()} with ${x._2}\n"
        }

    val head2 : String = "10 countries with lowest number of airports (with count):\n"
    val lowestAirports: List[String] =
      head2 :: countryAirport
        .takeRight(10)
        .reverseMap {
          x => s"    - ${x._1.Name()} with ${x._2}\n"
        }

    val head3 : String = " Type of runways per country:\n"

    val runwayCountry : List[String] = countryFile.countryList.flatMap{
      country =>
        val countryNameStr : String = s"    - ${country.Name()} :\n"
        val runwayNum : List[String] =
          fullMap.get(country.Code())
            .flatMap{x => x._2}
            .filter{runway => runway.getColumn(5) != Some("Unknown")}
            .groupBy{runway => runway.getColumn(5)}
            .mapValues(_.size)
            .toList
            .map(x => s"        - ${x._1} (nb = ${x._2})\n")

        countryNameStr::runwayNum
    }
    val runwayCountryNum = head3::runwayCountry

    val head4 : String = "The top 10 most common runway latitude: \n"
    val mostCommonLatitude : List[String] =
      head4::runwayFile
        .runwayList
        .filter{runway => runway.getColumn(9) != Some("Unknown")}
        .groupBy(runway => runway.getColumn(5))
        .maValues(_.size)
        .toList
        .sortWith(_._2 > _._2)
        .take(10)
        .map(x => s"    - ${x._1} (nb = ${x._2})\n"))

    highestAirports:::lowestAirports:::runwayCountry:::mostCommonLatitude
  }
}


