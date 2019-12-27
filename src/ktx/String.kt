package ktx

fun String.isValidUserName(): Boolean {
    val pattern = "\\w+[a-zA-Z0-9]+".toRegex()
    return matches(pattern)
}