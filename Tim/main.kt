fun main() {
    val bookingSystem = BookingSystem()
    
    // Sam's project work

    val loginSystem = LoginSystem()

    val room = Room(1, "Spark", 5)
    //end

    println("++ ++ Computer Reservation System! ++ ++")
    val user = "student123"


    while (true) {
        println("\nChoose an option:")
        println("1. Search labs by building and OS type")
        println("2. Book a computer")
        println("3. View current bookings")
        println("4. Cancel a booking")
        // Sam's project work
        println("5. View list of bookings (Admin only)")
        println("6. Add new room (Admin only)")
        //end
        println("7. Exit")
        print("Enter choice: ")

        when (readln()) {
            "1" -> {
                // Search labs
                print("Enter building (e.g., A, B): ")
                val building = readln()
                print("Enter OS type (e.g., Windows, Mac, Linux): ")
                val osType = readln()

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
                val building = readln()
                print("Enter room number (e.g., 101, 102): ")
                val inputRoom = readln()
                print("Enter date (yyyy-MM-dd): ")
                val dateStr = readln()
                print("Enter time slot (e.g., 9-11am, 11am-1pm): ")
                val timeSlot = readln()

                val computerId = bookingSystem.reserveComputer(user, building, inputRoom, dateStr, timeSlot)
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
                val computerId = readln()
                print("Enter date (yyyy-MM-dd): ")
                val dateStr = readln()
                print("Enter time slot (e.g., 9-11am, 11am-1pm): ")
                val timeSlot = readln()

                val success = bookingSystem.removeBooking(user, computerId, dateStr, timeSlot)
                if (success) {
                    println("Booking cancelled successfully.")
                } else {
                    println("No booking found to cancel.")
                }
            }

            // Sam's project work

            "5" -> {
                println("Enter username: ")
                val inputUsername = readln()

                println("Enter password: ")
                val inputPassword = readln()

                room.addNewRoom(loginSystem.login(inputUsername, inputPassword))
            }

            "6" -> {
                println("Enter username: ")
                val inputUsername = readln()

                println("Enter password: ")
                val inputPassword = readln()

                room.addNewRoom(loginSystem.login(inputUsername, inputPassword))
            }
            // end

            "7" -> {
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
