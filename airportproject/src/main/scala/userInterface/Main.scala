package userInterface
import parsing._
import model._
import storingQuery._



object Main {

  val storingQueryApp = storingQuery.Projet
  val countryFileProj = storingQueryApp.countryFileProj
  val runwayFileProj = storingQueryApp.runwayFileProj
  val airportFileProj = storingQueryApp.airportFileProj

  def main(args: Array[String]): Unit =
    {
      println("Scala Airport Project")

      def formatInput(input: String): String = input.length match
        {
        case 2 => input.toUpperCase
        case _ => input.toLowerCase.capitalize
      }

      def query(): Unit = {
        println("Enter a name of its code")
      }
    }



}
