package com.graph.controller;

import com.graph.model.dto.request.EdgeRequestDTO;
import com.graph.model.dto.response.EdgeResponseDTO;
import com.graph.service.EdgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Edges API", description = "API edges")
@RestController
@RequestMapping(value = "/edge", produces = "application/json")
public class EdgeController {

    private final EdgeService edgeService;

    public EdgeController(EdgeService edgeService) {
        this.edgeService = edgeService;
    }

    @Operation(description = "Create edge")
    @PostMapping
    public ResponseEntity<EdgeResponseDTO> createEdge(@RequestBody EdgeRequestDTO request) {
        return ResponseEntity.ok(edgeService.createEdge(request));
    }

    @Operation(description = "Create edges")
    @PostMapping(value = "/edges")
    public ResponseEntity<List<EdgeResponseDTO>> createEdges(@RequestBody List<EdgeRequestDTO> request) {
        return ResponseEntity.ok(edgeService.createEdges(request));
    }

    @Operation(description = "Update edge")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EdgeResponseDTO> updateEdge(@RequestBody EdgeRequestDTO request, @PathVariable Long id) {
        return ResponseEntity.ok(edgeService.updateEdge(id, request));
    }

    @Operation(description = "Delete edge")
    @DeleteMapping(value = "/{id}")
    public void deleteEdge(@PathVariable Long id) {
        edgeService.removeEdge(id);
    }

    @Operation(description = "Delete edge")
    @DeleteMapping(value = "/edges")
    public void deleteEdge(@Param("ids") List<Long> ids) {
        edgeService.removeEdges(ids);
    }
}
