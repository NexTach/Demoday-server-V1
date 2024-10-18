package class4.demoday.global.member.entity

import class4.demoday.global.security.roles.Roles
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(unique = true, nullable = false, length = 200)
    val phoneNumber: String,
    @Column(nullable = false, length = 200)
    val password: String,
    val role: Roles
) {
}