package routing.routes

import io.ktor.locations.Location


@Location("/ktweet/{id}/delete")
class DeleteKtweet(val id: Int)

@Location("/ktweet/{id}")
data class ViewKtweet(val id: Int)

@Location("/ktweet")
class NewKtweet()