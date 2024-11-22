// Author: Samuel McDowall
class LoginSystem {

    //* Creating mutable map of user types
    private val regularUsers = mutableMapOf<String, RegularUser>()
    private val adminUsers = mutableMapOf<String, AdminUser>()

    init {
        adminUsers["admin"] = AdminUser("admin", "letmein")
        regularUsers["student1"] = RegularUser("student1", "labadmin")
        regularUsers["student2"] = RegularUser("student2", "racecar")
    }

    fun login(username: String, password: String): User? {

        //* User types
        val regularUser = regularUsers[username]
        val adminUser = adminUsers[username]


        //* Checking for User type
        if (regularUser?.password == password) {
            return regularUser
        }

        if (adminUser?.password == password) {
            return adminUser
        }

        //! If no match
        return null
    }

}