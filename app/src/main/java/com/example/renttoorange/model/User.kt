import java.util.UUID

data class User(
    var userId: String = "",
    var email: String = "",
    var username: String = "",
    var userType: UserType = UserType.RENTER, // Assuming UserType.RENTER is a default value
    var image: String = ""
)

enum class UserType {
    RENTER, LANDLORD, ADMIN
}
