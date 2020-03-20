package ktx

fun String.isValidUserName(): Boolean {
    val pattern = "\\w+[a-zA-Z0-9]+".toRegex()
    return matches(pattern)
}

fun String.isValidEmail(): Boolean {
    val pattern = "\\w{2,}@\\w+\\.\\w{2,}".toRegex()
    return matches(pattern)
}