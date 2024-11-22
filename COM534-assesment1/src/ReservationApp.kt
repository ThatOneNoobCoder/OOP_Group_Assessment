// (Tim Reynolds)

// ------ ReservationApp.kt ------


class ReservationApp(private val bookingSystem: BookingSystem) {

    fun start() {

        val user = "student1"

        while (true) {
            println(
                """
                \nChoose an option:
                1 - Search Labs
                2 - Book Computer
                3 - View Bookings
                4 - Cancel Bookings
                5 - Exit
            """.trimIndent()
            )

            when (readLine()!!.toIntOrNull()) {
                1 -> searchLabs()
                2 -> makeBooking(user)
                3 -> displayBookings(user)
                4 -> cancelBooking(user)
                5 -> break
                else -> println("Please choose a valid option.")
            }
        }
    }

    private fun searchLabs() {
        println("Enter building code:")
        val building = readLine() ?: ""
        println("OS Type (Windows, Linux, Mac):")
        val os = readLine() ?: ""
        val labs = bookingSystem.findLabs(building, os)

        if (labs.isNotEmpty()) {
            println("Available labs:")
            labs.forEach { println("Room ${it.roomNumber}, Building ${it.building}, OS: ${it.osType}") }
        } else {
            println("No labs match your search.")
        }
    }

    private fun makeBooking(user: String) {
        println("Building code:")
        val building = readLine() ?: ""
        println("Room number:")
        val room = readLine() ?: ""
        println("Date (yyyy-MM-dd):")
        val date = readLine() ?: ""
        println("Time slot (9-11am, 11am-1pm, 1-3pm, 3-5pm):")
        val slot = readLine() ?: ""

        val bookingId = bookingSystem.reserveComputer(user, building, room, date, slot)
        if (bookingId != null) println("Reserved successfully! Computer ID: $bookingId") else println("Unable to book. Please try again.")
    }

    private fun displayBookings(user: String) {
        val bookings = bookingSystem.getBookings(user)
        if (bookings.isNotEmpty()) {
            println("Your bookings:")
            bookings.forEach { println("Computer ${it.computerId} on ${it.date} at ${it.timeSlot}") }
        } else {
            println("No bookings found.")
        }
    }

    private fun cancelBooking(user: String) {
        println("Computer ID:")
        val computerId = readLine() ?: ""
        println("Booking date (yyyy-MM-dd):")
        val date = readLine() ?: ""
        println("Time slot:")
        val slot = readLine() ?: ""

        if (bookingSystem.removeBooking(user, computerId, date, slot)) println("Booking cancelled.") else println("Booking not found.")
    }
}
