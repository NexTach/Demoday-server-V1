package class4.demoday.domain.marker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/marker")
@Tag(name = "Map", description = "지도 마커 관련 API")
@RequiredArgsConstructor
public class MarkerController {

    @GetMapping("/all")
    @Operation(summary = "모든 지도 마커 조회", description = "모든 지도 마커를 조회합니다.")
    public void getAllMapMarkers() {

    }
}
