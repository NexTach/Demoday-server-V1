package class4.demoday.global.member.entity

import jakarta.persistence.*

@Entity
open class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(unique = true, nullable = false, length = 200, name = "phone_number")
    var phoneNumber: String,
    @Column(nullable = false, length = 200)
    val password: String,
) {
}