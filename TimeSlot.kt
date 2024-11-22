data class TimeSlot(
    val timeslot: String,
    var isBooked: Boolean = false,
    var bookedBy: User? = null,
    var computer: Computer? = null
)