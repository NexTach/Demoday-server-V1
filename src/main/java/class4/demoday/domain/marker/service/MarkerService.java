package class4.demoday.domain.marker.service;

import class4.demoday.domain.marker.entity.Marker;
import class4.demoday.domain.marker.entity.MarkerTypes;

import java.util.List;

public interface MarkerService {
    List<Marker> getAllMapMarkers();
    List<Marker> getMapMarkersByCoordinates(double xMin, double xMax, double yMin, double yMax);
    List<Marker> getMapMarkersByType(MarkerTypes type);
    Marker getMapMarkerById(Long id);
}