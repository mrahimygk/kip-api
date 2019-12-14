package routing.routes

import io.ktor.locations.Location


@Location("/kweet/{id}/delete")
class DeleteKweet(val id: Int)

@Location("/kweet/{id}")
data class ViewKweet(val id: Int)

@Location("/post-new")
class NewKweet()