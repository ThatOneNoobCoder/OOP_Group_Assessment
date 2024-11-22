class Room (
    private val roomId: Int,
    private val roomName: String,
    private val numberOfComputers: Int
) {

    // Variables
    private val rooms = mutableMapOf<String, Room>()
    private val bookings = mutableMapOf<String, MutableList<TimeSlot>>()

    private var createUniqueRoomId: Int = 1

    private val daysOfTheWeek = listOf (
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday"
    )

    private val timeSlots = listOf(
        TimeSlot("9am-11am"),
        TimeSlot("11am-1pm"),
        TimeSlot("1pm-3pm"),
        TimeSlot("3pm-5pm")
    )


    //* Initialise timeslots to days
    init {
        for (day in daysOfTheWeek) {
            bookings[day] = timeSlots.map { it.copy() }.toMutableList()
        }
    }


    //* Generate Room ID automatically
    private fun generateUniqueRoomId(): Int {
        createUniqueRoomId++
        return createUniqueRoomId
    }


    //* Show Bookings
    fun showBookings(user: User?) {
        //? Check for Admin user
        if (user !is AdminUser) {
            println("Only admins can view bookings.")
            return
        }

        //! If there are no rooms available
        if (rooms.isEmpty()) {
            println("No rooms available")
            return
        }

        // Loop through rooms
        for ((roomId, room) in rooms) {
            println("\nRoom Name: ${room.roomName}")
            println("Number of Computers: ${room.numberOfComputers}")

            var hasBookings = false

            // Loop through bookings
            for ((day, slots) in room.bookings) {
                val bookedSlots = slots.filter { it.bookedBy != null }

                // If booking isn't empty, continue
                if (bookedSlots.isNotEmpty()) {
                    hasBookings = true

                    println("\t$day")

                    // Display booked slots
                    bookedSlots.forEach { slot ->
                        println("\tTime: ${slot.timeslot}\n Booked by: ${slot.bookedBy?.username}")
                    }
                }
            }
            //! If there are no bookings
            if (!hasBookings) {
                println("\tNo bookings in '${room.roomName}'")
            }
        }
    }


    fun addNewRoom(user: User?) {
        //? Check for Admin user
        if (user !is AdminUser) {
            println("Only Admins can add new rooms.")
            return
        }

        // Automatically generate Unique ID
        val generatedRoomId = generateUniqueRoomId()

        // User inputs name of the Room
        println("\nEnter the name of the Room:")
        val inputRoomName = readln()

        // Admin enters the number of computers in the room
        println("\nEnter the number of computers in the room:")
        val inputNumberOfComputers = readln().toInt()

        //! Input validation so input isn't equal or below 0
        if (inputNumberOfComputers <= 0) {
            println("Invalid number of computers")
            return
        }

        // New room is created with: ID, Name and number of computers
        val room = Room(roomId, inputRoomName, inputNumberOfComputers)
        rooms[generatedRoomId.toString()] = room

        // Confirmation message
        println("Room '$inputRoomName' with '${inputNumberOfComputers}' computers has been added successfully!\n")
    }



}