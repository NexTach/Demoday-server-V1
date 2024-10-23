package class4.demoday.domain.marker.repository;

import class4.demoday.domain.marker.entity.Marker;
import class4.demoday.domain.marker.entity.MarkerTypes;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    List<Marker> findByMarkerType(MarkerTypes markerType);

    @Query("SELECT m FROM Marker m WHERE m.x BETWEEN :xMin AND :xMax AND m.y BETWEEN :yMin AND :yMax")
    List<Marker> findAllByCoordinates(
            @Param("xMin") double xMin,
            @Param("xMax") double xMax,
            @Param("yMin") double yMin,
            @Param("yMax") double yMax
    );

    List<Marker> findByAddress(String address);

    @Query("SELECT m FROM Marker m WHERE m.x BETWEEN :xMin AND :xMax AND m.y BETWEEN :yMin AND :yMax AND m.markerType = :markerType")
    List<Marker> findByMarkerTypeAndXBetweenAndYBetween(
            MarkerTypes markerType,
            double xMin,
            double xMax,
            double yMin,
            double yMax
    );
}