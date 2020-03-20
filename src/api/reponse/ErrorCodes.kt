package api.reponse

object ErrorCodes {
    val SUCCESS = Pair("Success", 200)
    val EMPTY_PASSWORD = Pair("Empty password", 1001)
    val EMPTY_EMAIL = Pair("Empty email", 1002)
    val INVALID_PASSWORD = Pair("Invalid Password", 1003)
    val INVALID_EMAIL = Pair("Invalid Email", 1004)
    val USER_EXISTS = Pair("User exists / Email taken", 1005)
    val INVALID_LOGIN = Pair("Invalid login info", 1006)
}