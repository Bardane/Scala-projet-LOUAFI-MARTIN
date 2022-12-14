package userInterface
import parsing._
import model._
import storingQuery._

import scala.io.StdIn.readLine

object Main extends App {

  val storingQueryApp = storingQuery.Projet
  val countryFileProj = storingQueryApp.countryFileProj
  val runwayFileProj = storingQueryApp.runwayFileProj
  val airportFileProj = storingQueryApp.airportFileProj


  selectMenu()


  /** Functions */

  def query(): Unit = {
    val countryCodeOrName = formatInput(readLine("Plesae type the Country " + Console.MAGENTA + "code"
                                                                            + Console.RESET + " or "
                                                                            + Console.MAGENTA + "name"
                                                                            + Console.RESET + ": \n"))
      storingQueryApp.Query(countryCodeOrName) match {
        case "Wrong Country" =>
          println("This country does not exist \uD83D\uDE14 but let's retype it !\n")
          query()
        case _ => println(storingQueryApp.Query(countryCodeOrName))
      }
    val mainMenu = readLine(Console.GREEN + "Press enter to go back to menu")
    mainMenu match {
      case _ => selectMenu()
    }
  }

  def reports(): Unit = {
    print(storingQueryApp.Report())
    val mainMenu = readLine("Press enter to go back to menu")
    mainMenu match {
      case _ => selectMenu()
    }
  }

  def formatInput(input: String): String = input.length match {
    case 2 => input.toUpperCase
    case _ => input.toLowerCase.capitalize
  }

  def selectMenu(): Unit = {
    println(Console.CYAN + "_____________________" + Console.RESET)
    println("Scala Airport Project")
    println(Console.MAGENTA + "1. Query")
    println(Console.BLUE + "2. Reports ")
    println(Console.RED +"Q. Quit " + Console.RESET)
    val queryOrReports = formatInput(readLine("Enter Your Choice: "))
    queryOrReports match {
      case "1" => query()
      case "2" => reports()
      case "Q" => println(Console.GREEN + "Quitting the app...")
      case _ =>
        println(Console.RED +"This is not a valid option, please retype\n")
        selectMenu()

    }
  }

}
