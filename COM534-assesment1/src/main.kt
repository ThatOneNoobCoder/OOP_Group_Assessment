// ( Tim Reynolds)

fun main() {
    val bookingSystem = BookingSystem()

    println("++ ++ Computer Reservation System! ++ ++")
    var user = "student123"


    while (true) {
        println("\nChoose an option:")
        println("1. Search labs by building and OS type")
        println("2. Book a computer")
        println("3. View current bookings")
        println("4. Cancel a booking")
        println("5. Exit")
        print("Enter choice: ")

        when (readLine()) {
            "1" -> {
                // Search labs
                print("Enter building (e.g., A, B): ")
                val building = readLine()!!
                print("Enter OS type (e.g., Windows, Mac, Linux): ")
                val osType = readLine()!!

                val labs = bookingSystem.findLabs(building, osType)
                if (labs.isEmpty()) {
                    println("No labs found in $building with $osType OS.")
                } else {
                    println("Available labs in $building with $osType OS:")
                    labs.forEach { println("Room ${it.roomNumber}, Computers: ${it.computers.size}") }
                }
            }

            "2" -> {
                // Book a computer
                print("Enter building (e.g., A, B): ")
                val building = readLine()!!
                print("Enter room number (e.g., 101, 102): ")
                val room = readLine()!!
                print("Enter date (yyyy-MM-dd): ")
                val dateStr = readLine()!!
                print("Enter time slot (e.g., 9-11am, 11am-1pm): ")
                val timeSlot = readLine()!!

                val computerId = bookingSystem.reserveComputer(user, building, room, dateStr, timeSlot)
                if (computerId != null) {
                    println("Successfully booked computer ID: $computerId")
                } else {
                    println("Failed to book a computer. Please try again")
                }
            }

            "3" -> {
                // View current bookings
                val bookings = bookingSystem.getBookings(user)
                if (bookings.isEmpty()) {
                    println("You have no bookings.")
                } else {
                    println("Your current bookings:")
                    bookings.forEach { println("Booking ID: ${it.computerId}, Date: ${it.date}, Time Slot: ${it.timeSlot}") }
                }
            }

            "4" -> {
                // Cancel a booking
                print("Enter computer ID to cancel (e.g., 101-1): ")
                val computerId = readLine()!!
                print("Enter date (yyyy-MM-dd): ")
                val dateStr = readLine()!!
                print("Enter time slot (e.g., 9-11am, 11am-1pm): ")
                val timeSlot = readLine()!!

                val success = bookingSystem.removeBooking(user, computerId, dateStr, timeSlot)
                if (success) {
                    println("Booking cancelled successfully.")
                } else {
                    println("No booking found to cancel.")
                }
            }

            "5" -> {
                // Exit
                println("Exiting the system.")
                break
            }

            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }
}
