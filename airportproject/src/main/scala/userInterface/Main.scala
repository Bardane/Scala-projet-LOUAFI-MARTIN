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
    val countryCodeOrName = formatInput(readLine("Please type the Country " + Console.MAGENTA + "code"
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
    println("Please select which "  + Console.MAGENTA +  "report " + Console.RESET + "you want to see")
    println(Console.MAGENTA + "1. Top 10 countries with most airports")
    println(Console.BLUE + "2. Top 10 countries with least airports")
    println(Console.RED + "3. Type of runway surface per country " )
    println(Console.YELLOW + "4. Top 10 most common runway latitude" + Console.RESET)
    val choice = readLine("Enter Your Choice: ")

    choice match {
      case "1" => println(storingQueryApp.Report("1"))
      case "2" => println(storingQueryApp.Report("2"))
      case "3" => println(storingQueryApp.Report("3"))
      case "4" => println(storingQueryApp.Report("4"))
      case _ => println("This is not a valid option \uD83D\uDE14 let's retype it ")
        reports()
    }
    val mainMenu = readLine(Console.GREEN + "Press enter to go back to menu")
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
