import java.util.UUID

data class User(
    val userId: String = UUID.randomUUID().toString(),
    val email: String,
    val username: String,
    val userType: UserType,
    val image: String
)

enum class UserType {
    RENTER, LANDLORD, ADMIN
}
