import java.util.UUID

data class User(
    val userId: String = UUID.randomUUID().toString(),  // Generates a unique ID for each user
    val email: String,
    val password: String,
    val username: String,
    val userType: UserType
)

enum class UserType {
    RENTER, LANDLORD, ADMIN
}
