package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun printPrimes(upperRange:Int): MutableList<Int> {
    if(upperRange<=1){return(mutableListOf())}
    val primes: MutableList<Int> = mutableListOf(2) // list of all primes from 0 to upper limit, starting at 2
    val numbers: MutableList<Int> = (2..upperRange).toMutableList() // list of numbers from 2 to upper limit
    var rep = upperRange // rep is used for ending the loop early if needed
    var i:Int = 0
    while (rep > 0) {
        i = primes.last() // set i to the current highest prime number
        while (i <= upperRange) { // while the current prime + prime*n is less than our upper limit
            if (i in numbers) {
                numbers.remove(i) // remove the prime + prime*n
            }
            i += primes.last() // add the current prime number to i
        }
        if (numbers.isNotEmpty()) { // make sure the size of numbers is above 0
            if (numbers.first() !in primes) { // if the first number isn't in the current list of prime numbers
                primes.add(numbers.first()) // add the first number to the list of prime numbers
            }
        }
        rep -= 1
        if(numbers.isEmpty()){rep=0} // if the number list is prematurely empty, end the loop
    }
    return(primes)
}

fun unitTests(): MutableList<Boolean>{
    val passed:MutableList<Boolean> = mutableListOf() // make a list of passed tests, starts empty
    val tests: List<List<Int>> = listOf(listOf(1,0),listOf(10,4),listOf(100,25),listOf(1000,168)) // make a list of cases to test (at upper lim of 1 you expect 0 primes, 100 you expect 25 and so on)
    for(i in tests.indices) {
        val primes = printPrimes(tests[i][0]) // set primes from function using upper lim
        passed.add((primes.count()==tests[i][1])) // test the count of expected primes vs the returned prime count, add result to the output
    }
    return(passed) // return outcome of all test cases
}

fun main(){
    val highestNumber = 1
    val primes = printPrimes(highestNumber)
    println("There are ${primes.count()} primes from 0-${highestNumber}:\n${primes}\n")

    // now i'll run unit tests that test if certain ranges gives the right number of primes
    println(unitTests())
}

