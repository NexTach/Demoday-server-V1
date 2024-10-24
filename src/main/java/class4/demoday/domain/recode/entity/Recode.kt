package class4.demoday.domain.recode.entity

import class4.demoday.global.member.entity.Member
import jakarta.persistence.*

@Entity
@Table(name = "recode")
open class Recode(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "recode_id")
    val id: Long?,
    @ManyToOne(cascade = [CascadeType.ALL]) @JoinColumn(name = "user_id")
    val userId: Member,
    @Column(name = "value", nullable = false)
    var value: Int = 0,
    @Column(name = "recode_type", nullable = false)
    val recodeType: RecodeType,
)