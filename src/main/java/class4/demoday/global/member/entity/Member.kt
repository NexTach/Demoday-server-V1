package class4.demoday.global.member.entity

import class4.demoday.global.security.roles.Roles
import jakarta.persistence.*

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(unique = true, nullable = false, length = 200, name = "phone_number")
    var phoneNumber: String,
    @Column(nullable = false, length = 200)
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: Roles
) {
}