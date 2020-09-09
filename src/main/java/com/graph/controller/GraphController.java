package com.graph.controller;

import com.graph.model.dto.request.GraphRequestDTO;
import com.graph.model.dto.response.GraphResponseDTO;
import com.graph.service.GraphService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Graphs API", description = "API graphs")
@RestController
@RequestMapping(value = "/graph", produces = "application/json")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @Operation(description = "Create graph")
    @PostMapping
    public ResponseEntity<GraphResponseDTO> createEdge(@RequestBody GraphRequestDTO request) {
        return ResponseEntity.ok(graphService.createGraph(request));
    }

    @Operation(description = "Update graph")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GraphResponseDTO> updateGraph(@RequestBody GraphRequestDTO request, @PathVariable Long id) {
        return ResponseEntity.ok(graphService.updateGraph(id, request));
    }

    @Operation(description = "Delete graph")
    @DeleteMapping(value = "/{id}")
    public void deleteEdge(@PathVariable Long id) {
        graphService.removeGraph(id);
    }

    @Operation(description = "Get graph by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GraphResponseDTO> getGraphById(@PathVariable Long id) {
        return ResponseEntity.ok(graphService.getGraphById(id));
    }

}
