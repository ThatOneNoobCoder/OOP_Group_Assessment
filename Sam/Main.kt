fun main() {
    // Author: Samuel McDowall
    val loginSystem = LoginSystem()

    val room = Room(1, "Spark", 5)

    while (true) {
        println("\nChoose an option")
        println("\t1. View list of bookings (Admin only)")
        println("\t2. Add new room (Admin only)")
        println("\t3. Exit")
        println("Enter choice: ")

        when (readln()) {
            "1" -> {
                println("Enter username: ")
                val inputUsername = readln()

                println("Enter password: ")
                val inputPassword = readln()

                room.showBookings(loginSystem.login(inputUsername, inputPassword))
            }

            "2" -> {
                println("Enter username: ")
                val inputUsername = readln()

                println("Enter password: ")
                val inputPassword = readln()

                room.addNewRoom(loginSystem.login(inputUsername, inputPassword))
            }

            "3" -> {
                println("Exiting the system, goodbye..")
                break
            }

            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }

}