package service

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import repos.UserRepository
import java.io.File

private val FILE_PATH = ""

fun Route.user(userRepository: UserRepository) {

    post("upload-photo") {
        val part = call.receiveMultipart().readPart() as PartData.FileItem

        val file = File("/home/alialbaali/Projects/", "upload-${System.currentTimeMillis()}")

        part.streamProvider().buffered().use {
            file.outputStream().write(it.readBytes())
        }

        part.dispose

        call.respond(HttpStatusCode.OK)
    }
}