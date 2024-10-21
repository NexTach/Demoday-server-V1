package class4.demoday.domain.marker.controller;

import class4.demoday.domain.marker.dto.GetMapMarkersByCoordinatesRequest;
import class4.demoday.domain.marker.entity.Marker;
import class4.demoday.domain.marker.entity.MarkerTypes;
import class4.demoday.domain.marker.service.MarkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marker")
@Tag(name = "Map", description = "지도 마커 관련 API")
@RequiredArgsConstructor
public class MarkerController {

    private final MarkerService markerService;

    @GetMapping("/all")
    @Operation(summary = "모든 지도 마커 조회", description = "모든 지도 마커를 조회합니다.")
    public List<Marker> getAllMapMarkers() {
        return markerService.getAllMapMarkers();
    }

    @GetMapping("/coordinates")
    @Operation(summary = "좌표 범위 내 지도 마커 조회", description = "좌표 범위 내 지도 마커를 조회합니다.")
    public List<Marker> getMapMarkersByCoordinates(@RequestBody GetMapMarkersByCoordinatesRequest request) {
        return markerService.getMapMarkersByCoordinates(
                request.getXMin(),
                request.getXMax(),
                request.getYMin(),
                request.getYMax()
        );
    }

    @GetMapping("/type")
    @Operation(summary = "타입별 지도 마커 조회", description = "타입별 지도 마커를 조회합니다.")
    public List<Marker> getMapMarkersByType(@RequestParam MarkerTypes type) {
        return markerService.getMapMarkersByType(type);
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 지도 마커 조회", description = "ID로 지도 마커를 조회합니다.")
    public Marker getMapMarkerById(@PathVariable Long id) {
        return markerService.getMapMarkerById(id);
    }
}