package class4.demoday.domain.marker.entity

import jakarta.persistence.*

@Entity
@Table(name = "marker")
open class Marker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marker_id")
    val markerId: Long,
    @Column(name = "x_coordinate")
    var x: Double,
    @Column(name = "y_coordinate")
    var y: Double,
    @Column(name = "marker_type")
    @Enumerated(EnumType.STRING)
    var markerType: MarkerTypes,
    @Column(name = "address")
    var address: String,
)