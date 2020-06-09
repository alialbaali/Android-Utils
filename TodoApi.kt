package com.rsk

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.sessions.sessions

fun Routing.todoApi() {

//    route("/api/todo") {
//        get("/") {
//            call.respond(HttpStatusCode.OK, todos)
//        }
//
//        get("/user/{login}/{id}") {
//            call.parameters["login"]
//            call.parameters["id"]
//        }
//    }
    route("/api") {


        // 1
        header("Accept", value = "application/vnd.todoapi.v1+json") {

            handle {
                call.respond(todos)

//                call.sessions.
            }
        }
        // we can use a helper method 'accept', or 'header' with 'accept' name
        // these two calls do the same thing
        // 2
        accept(TodoContentV1){
            handle {
                call.respond(todos)
            }
        }

        route("/todos") {
            get("/") {
                call.respond(todos)
            }

            get("/{id}") {

                try {
                    val id = call.parameters["id"]!!.toInt()
                    call.respond(todos[id])
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }


            }
            post("/") {
                val todo = call.receive<TodoItem>()
                todos + todo
                call.respond(HttpStatusCode.Created, todos)
//                return@post
            }
        }
    }
}