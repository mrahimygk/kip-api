package pojo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

data class ApiResponse(
    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("status_text")
    val statusText: String,

    val data: Any?
) {
    constructor(input: Pair<String, Int>) : this(input.second, input.first, null)
}

fun ApiResponse.toTextContent() = TextContent(Gson().toJson(this), ContentType.Application.Json)

fun ApiResponse.copy(errorCode: Pair<String, Int>) = ApiResponse(errorCode)