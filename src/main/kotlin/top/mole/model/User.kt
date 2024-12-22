package top.mole.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.text

interface User : Entity<User> {
    companion object : Entity.Factory<User>()
//    val id: Long
    var email: String
    var passwordHash: String

    var nickname: String
}

object UserTable : Table<User>("user") {
    val email = text("email").primaryKey().bindTo { it.email }
    val passwordHash = text("password_hash").bindTo { it.passwordHash }
    val nickname = text("nickname").bindTo { it.nickname }
}

