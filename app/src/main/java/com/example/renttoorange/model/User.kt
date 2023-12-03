import java.util.UUID

data class User(
    val userId: String = UUID.randomUUID().toString(),
    val email: String,
    val password: String,
    val username: String,
    val userType: UserType,
    val image: ByteArray? = null  // New field for storing the image
)

enum class UserType {
    RENTER, LANDLORD, ADMIN
}
