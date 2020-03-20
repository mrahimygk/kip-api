package routing

import application.redirect
import com.google.gson.Gson
import crypto.hash
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.content.TextContent
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import ktx.isValidEmail
import pojo.User
import routing.routeutil.Register

val avatars = listOf(
    "https://image.shutterstock.com/image-photo/agave-cactus-abstract-natural-pattern-600w-1056037874.jpg",
    "https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg",
    "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
    "https://www.seiu1000.org/sites/main/files/imagecache/hero/main-images/camera_lense_0.jpeg",
    "https://images.befunky.com/prismic/5a38fcecab5ce98e1021cfd08aa0a33884990f5e_en-resize-imges-img.jpg",
    "https://www.pythoncircle.com/media/uploads/desktop-wallpaper-change-python-20190823-d0843f2cd287490f8e1eeaa712e0f689.jpeg",
    "https://www.w3schools.com/howto/img_avatar.png",
    "https://gymkhana.iitb.ac.in/profiles/media/profile_picture/6f905fffb00b4b34a5d36118b9bbd702.png",
    "https://raisdelivery.dz/Pictures/av.png",
    "https://www.screengeek.net/wp-content/uploads/2018/11/avatar-movie.jpg",
    "http://icon-library.com/images/avatar-icon-images/avatar-icon-images-4.jpg",
    "https://cdn.zoomg.ir/2018/9/2325322d-7b52-4fda-bd1c-132ac7dbfb0b.jpg",
    "https://mgnews.ir/wp-content/uploads/2019/05/Avatar-2.jpg",
    "https://img.ibxk.com.br/2020/01/07/07100612723101.jpg",
    "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg"
)

fun Routing.register(userDao: UserDao) {
    post<Register> {

        val registration = call.receive<Parameters>()
        val pass = registration["pass"] ?: return@post call.redirect(it)
        val email = registration["email"] ?: return@post call.redirect(it)

        val registerValidation = Register(email)

        when {
            pass.length < 8 -> call.redirect(registerValidation.copy(error = "Password is bad"))
            !email.isValidEmail() -> call.redirect(registerValidation.copy(error = "Not valid email"))
            userDao.getUser(email) != null -> call.redirect(registerValidation.copy(error = "user exists/email taken"))
            else -> {
                val hash = hash(pass)
                val newUser = User(email, avatars.random(), hash)

                userDao.createUser(newUser)
                call.respond(TextContent(Gson().toJson(newUser), ContentType.Application.Json))
            }
        }
    }
}