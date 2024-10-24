package class4.demoday.domain.individualmarker.entity

import class4.demoday.global.member.entity.Member
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "individual_marker")
open class IndividualMarker(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "marker_id")
    val id: Long?,
    @Column(name = "marker_name")
    var name: String,
    @Column(name = "coordinate_x")
    var x: Double,
    @Column(name = "coordinate_y")
    var y: Double,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val owner: Member
)