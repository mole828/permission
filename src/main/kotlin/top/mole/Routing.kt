package top.mole

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.sha1
import io.ktor.utils.io.charsets.Charset
import kotlinx.serialization.Serializable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import top.mole.model.User
import top.mole.model.UserTable
import top.mole.route.configureSignRoute
import java.security.MessageDigest

fun Application.configureRouting() {
    val database = Database.connect(
        url = "jdbc:postgresql://10.0.0.42:5432/app",
        driver = "org.postgresql.Driver",
        user = "app",
        password = "app"
    )
    val users = database.sequenceOf(UserTable)
    configureSignRoute(users)
}
