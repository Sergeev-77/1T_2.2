package org.example

import scala.io.StdIn.readLine

object App {
  def main(args : Array[String]) {

    println("")
    println("task a")
    println("")

    var str = "Hello, Scala!"
    println( str.reverse)
    println( str.toLowerCase())
    println( str.replace("!", ""))
    println( str + " and goodbye python!")

    println("")
    println("task b")
    println("")

    println("Enter annual income:")
    var  income = readLine().toFloat
    println("Enter percent of bonus:")
    val bonus = readLine().toFloat
    println("Enter compensation sum:")
    val comp = readLine().toFloat
    println("Enter tax rate percent:")
    val tax = readLine().toFloat

    def monthlyNet(income: Float = income, bonus: Float = bonus,
                   comp: Float = comp, tax: Float = tax, penalty: Float = 0): Float = {
      var monthlyNet = (income * (1 + bonus / 100) + comp - penalty) * (1 - tax / 100) / 12
      return monthlyNet
    }

    var personMonthlyNet = monthlyNet(income, bonus, comp, tax)
    println("The monthly net income is " + personMonthlyNet)

    println("")
    println("task c")
    println("")
//    Напишите программу, которая рассчитывает для каждого сотрудника отклонение(в процентах)
//    от среднего значения оклада на уровень всего отдела.
//    В итоговом значении должно учитываться в большую или меньшую сторону отклоняется размер оклада.
//    На вход вышей программе подаются все значения, аналогичные предыдущей программе,
//    а также список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75

    val salaries = List(100, 150, 200, 80, 120, 75)
    var incomes = List[Float]()
    for (i <- salaries){
      var iMonthlyNet = monthlyNet(i)
      incomes = incomes :+ iMonthlyNet
    }

    val meanMonthlyNet = incomes.sum / incomes.size
    var incomeDiff = incomes.map(_ - meanMonthlyNet)
    incomeDiff = incomeDiff.map(_ / meanMonthlyNet)
    println("The monthly net incomes differences from mean is " + incomeDiff)

    println("")
    println("task d")
    println("")
    //Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел)
    // необходимую сумму с учетом результатов прошлого задания.
    // Добавьте его зарплату в список и вычислите значение самой высокой зарплаты и самой низкой.

    println("Enter annual income:")
    income = readLine().toFloat
    println("Enter penalty:")
    var penalty = readLine().toFloat
    personMonthlyNet = monthlyNet(income=income,penalty=penalty)
    println("The monthly net income is " + personMonthlyNet)
    incomes = incomes :+ personMonthlyNet
    println("The monthly net incomes is " + incomes)
    println("The max monthly net income is " + incomes.max)
    println("The min monthly net income is " + incomes.min)

    println("")
    println("task e")
    println("")
  //Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей.
  // Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.
    personMonthlyNet = monthlyNet(income = 350)
    incomes = incomes :+ personMonthlyNet
    personMonthlyNet = monthlyNet(income = 90)
    incomes = incomes :+ personMonthlyNet
    println("The monthly net incomes is " + incomes)
    incomes = incomes.sorted
    println("The sorted monthly net incomes is " + incomes)

    println("")
    println("task f")
    println("")

    //Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч.
    // Вычислите самостоятельно номер сотрудника в списке так,
    // чтобы сортировка не нарушилась и добавьте его на это место.
    income = 130
    personMonthlyNet = monthlyNet(income)
    println("The monthly net income is " + personMonthlyNet)
    var rating: Int = 0
    for((x,i) <- incomes.view.zipWithIndex) {
      if (x < personMonthlyNet) {
        rating = i
      }
    }
    println("The rating of monthly net income is " + (rating + 1))
    incomes = incomes.take(rating+1) ++ List(personMonthlyNet) ++ incomes.drop(rating+1)
    println("The sorted monthly net incomes is " + incomes)

    println("")
    println("task g")
    println("")
    //Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
    //На входе программе подается «вилка» зарплаты специалистов уровня middle.

    println("Enter min monthly net income for middle:")
    var minIncome = readLine().toFloat
    println("Enter max monthly net income for middle:")
    val maxIncome = readLine().toFloat

    for ((x, i) <- incomes.view.zipWithIndex) {
      if ( (minIncome < x) && (x < maxIncome) ) {
        println("middle" + i +": " + x)
      }
    }

    println("")
    println("task h")
    println("")

    //Однако наступил кризис и ваши сотрудники требуют повысить зарплату.
    // Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7%

    val inflation:Float = 7
    println("The sorted monthly net incomes before indexing is " + incomes)
    incomes = incomes.map(_ * (1 + inflation/100))
    println("The sorted monthly net incomes after indexing is " + incomes)
  }
}
