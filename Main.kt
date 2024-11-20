fun main() {
    val loginSystem = LoginSystem()

    // * Testing Login System

    val admin1 = loginSystem.login("admin", "letmein")
    val student1 = loginSystem.login("student1", "labadmin")
    println(admin1)


    val room = Room("1", "Spark", 5)


}