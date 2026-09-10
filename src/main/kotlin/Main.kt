package org.example


// i'm not quite sure what you meant by two implementations since my first idea was to use
// the sorting function. Also, you didn't tell use what to do on the edge cases, so i just had
// it not mark them as overlapping meetings. I assume runtime for the first algorithm (without sorting first)
// would be somewhere around n^2 and the one i implemented might be closer to O(nlogn)+(n-1)! (i think) but
// it could probably be lowered if i just return right when there is a conflict.

fun makeMeeting(startTime: Int, endTime: Int): MutableList<Int>{
    return(mutableListOf(startTime, endTime)) // returns a list to represent a meeting as just the start time and the end time
}

fun checkMeetings(meetingList: MutableList<MutableList<Int>>): Boolean{ // input is a list of meetings and outputs pairs of conflicting meetings
    val sortedMeetings = meetingList.sortedBy{it.first()}
    var conflictedMeetings: MutableList<MutableList<MutableList<Int>>> = mutableListOf()
    for(i in sortedMeetings.indices){
        if(i!=sortedMeetings.count()-1) {
            if (sortedMeetings[i][1] > sortedMeetings[i + 1][0]) {
                conflictedMeetings.add(mutableListOf(sortedMeetings[i], sortedMeetings[i + 1]))
            }
        }
    }
    return(conflictedMeetings.count()>0)
}

fun unitTests(): MutableList<Boolean> { // returns a meeting list of meetings represented by int start and end time
    val testResults: MutableList<Boolean> = mutableListOf()
    var testMeetings: MutableList<MutableList<Int>> = mutableListOf(
        makeMeeting(1000, 1030),
        makeMeeting(1100, 1230),
        makeMeeting(1130,1200),
        makeMeeting(1300,1430),
        makeMeeting(1430,1300),
        makeMeeting(1430,1500)
    )
    testResults.add(checkMeetings(testMeetings)==true)
    testMeetings = mutableListOf(
        makeMeeting(1200,1300),
        makeMeeting(1300,1400),
        makeMeeting(1400,1500)
    )
    testResults.add(checkMeetings(testMeetings)==false)
    return(testResults)
}
fun main() {
    println(unitTests())
}