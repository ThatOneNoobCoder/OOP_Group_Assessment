fun main() {
    val bookingSystem = BookingSystem()

    // Example of searching for labs
    val labsFound = bookingSystem.searchLabs("Building A", "Windows")
    println("Labs found: $labsFound")


    val user = "John Doe"
    val bookingId = bookingSystem.bookComputer(user, "Building A", "101", "2024-11-06", "9-11am")
    println("Booking ID: $bookingId")

    // Example of viewing bookings
    val userBookings = bookingSystem.viewBookings(user)
    println("User bookings: $userBookings")

    // Example of canceling a booking
    val cancelSuccess = bookingSystem.cancelBooking(user, bookingId ?: "", "2024-11-06", "9-11am")
    println("Booking canceled: $cancelSuccess")
}
