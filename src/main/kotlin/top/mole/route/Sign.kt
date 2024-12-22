package top.mole.route

import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.sessions.clear
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import org.ktorm.dsl.eq
import org.ktorm.entity.EntitySequence
import org.ktorm.entity.add
import org.ktorm.entity.find
import top.mole.model.User
import top.mole.model.UserTable
import java.security.MessageDigest

fun Application.configureSignRoute(
    users: EntitySequence<User, UserTable>
) {
    routing {
        route("sign") {
            val sha256 = MessageDigest.getInstance("SHA-256")
            fun String.sha256(): String = sha256.digest(this.toByteArray()).joinToString("") { "%02x".format(it) }
            put {
                val user = User {
                    email = call.receive<String>()
                    passwordHash = call.receive<String>().sha256()
                }
                users.add(user)
            }
            post {
                val email = call.receive<String>()
                val passwordHash = call.receive<String>().sha256()
                val user = users.find {
                    it.email eq email
                    it.passwordHash eq passwordHash
                }
                if (user != null) {
                    call.sessions.set(user)
                    call.respondText("Signed in!")
                } else {
                    call.respondText("Invalid credentials.")
                }
            }
            delete {
                val user = call.sessions.get<User>()
                if (user != null) {
                    call.sessions.clear<User>()
                }
            }
        }
    }
}