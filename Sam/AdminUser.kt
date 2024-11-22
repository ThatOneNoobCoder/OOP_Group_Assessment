// Author: Samuel McDowall
class AdminUser (
    username: String,
    override val password: String
): User(username, password){
    override fun toString(): String {
        return username
    }
}