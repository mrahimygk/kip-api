package routing.routeutil

import io.ktor.locations.Location


@Location("/note/{id}/delete")
class DeleteNote(val id: Int)

@Location("/note/{id}")
data class ViewNote(val id: Int)

@Location("/note")
class NewNote()