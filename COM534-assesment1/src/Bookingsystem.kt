// (Tim Reynolds)

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingSystem {
    private val labs = mutableListOf<Lab>()
    private val reservations = mutableListOf<Booking>()
    private val timeSlots = setOf("9-11am", "11am-1pm", "1-3pm", "3-5pm")

    init {
        setupLabs()
    }

    private fun setupLabs() {
        // Data of labs/computers
        labs.add(Lab("A", "101", "Windows", listOf(Computer("1"), Computer("2"))))
        labs.add(Lab("A", "102", "Linux", listOf(Computer("1"), Computer("2"), Computer("3"))))
        labs.add(Lab("B", "201", "Mac", listOf(Computer("1"))))
        labs.add(Lab("B", "202", "Windows", listOf(Computer("1"), Computer("2"))))
    }

    private fun isInNextWeek(date: LocalDate): Boolean {
        // Checks date is within next week
        val today = LocalDate.now()
        val startNextWeek = today.plusDays((DayOfWeek.MONDAY.value - today.dayOfWeek.value + 7) % 7L)
        val endNextWeek = startNextWeek.plusDays(4)
        return date.isAfter(today) && date in startNextWeek..endNextWeek
    }

    fun findLabs(building: String, osType: String): List<Lab> {
        // Return labs that match building and OS type
        return labs.filter { it.building == building && it.osType == osType }
    }

    fun reserveComputer(user: String, building: String, room: String, dateStr: String, slot: String): String? {
        // Reserve a computer for the user in the specified lab
        val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        if (!isInNextWeek(date) || slot !in timeSlots) return null

        val lab = labs.find { it.building == building && it.roomNumber == room }
        lab?.computers?.firstOrNull { !it.isBooked }?.let { computer ->
            computer.isBooked = true
            val booking = Booking(user, "${lab.roomNumber}-${computer.id}", dateStr, slot)
            reservations.add(booking)
            return booking.computerId
        }
        return null
    }

    fun getBookings(user: String): List<Booking> {
        // Get bookings for a user
        return reservations.filter { it.user == user }
    }

    fun removeBooking(user: String, computerId: String, dateStr: String, slot: String): Boolean {
        // Remove booking by computer ID
        val booking = reservations.find {
            it.user == user && it.computerId == computerId && it.date == dateStr && it.timeSlot == slot
        }

        return if (booking != null) {
            reservations.remove(booking)
            val computerIdParts = computerId.split("-")
            if (computerIdParts.size > 1) {
                val labRoom = computerIdParts[0]
                val computerIdParsed = computerIdParts[1]

                val lab = labs.find { it.roomNumber == labRoom }
                lab?.computers?.find { it.id == computerIdParsed }?.isBooked = false
            }
            true
        } else false
    }


}
