package com.graph.service;

import com.graph.exception.Error;
import com.graph.exception.HandledException;
import com.graph.model.dto.request.GraphRequestDTO;
import com.graph.model.dto.response.EdgeResponseDTO;
import com.graph.model.dto.response.GraphResponseDTO;
import com.graph.model.entity.Edge;
import com.graph.model.entity.Graph;
import com.graph.repository.GraphRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GraphService {

    private final GraphRepository graphRepository;
    private final ModelMapper modelMapper;

    public GraphService(GraphRepository graphRepository, ModelMapper modelMapper) {
        this.graphRepository = graphRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public GraphResponseDTO createGraph(GraphRequestDTO request) {
        Graph graph = new Graph();
        graph.setName(request.getName());

        graph = graphRepository.save(graph);

        return mapGraphEntityToDTO(graph);

    }

    @Transactional
    public GraphResponseDTO updateGraph(Long id, GraphRequestDTO request) {
        Graph graph = graphRepository.findById(id).orElseThrow(() ->
                new HandledException(Error.GRAPH_NOT_FOUND, "Graph not found"));

        graph.setName(request.getName());

        Graph savedGraph = graphRepository.save(graph);

        return mapGraphEntityToDTO(savedGraph);
    }

    @Transactional
    public void removeGraph(Long id) {
        graphRepository.delete(graphRepository.findById(id).orElseThrow(() ->
                new HandledException(Error.GRAPH_NOT_FOUND, "Graph not found")));
    }

    @Transactional(readOnly = true)
    public GraphResponseDTO getGraphById(Long id) {
        Graph graph = graphRepository.findById(id).orElseThrow(() ->
                new HandledException(Error.GRAPH_NOT_FOUND, "Graph not found"));

        return mapGraphEntityToDTO(graph);
    }

    private GraphResponseDTO mapGraphEntityToDTO(Graph entity) {
        List<Long> edgeIds = new ArrayList<>();

        if (entity.getEdges() != null) {
            entity.getEdges().forEach(edge -> {
                edgeIds.add(edge.getId());
            });

        }
        GraphResponseDTO mappedGraph = modelMapper.map(entity, GraphResponseDTO.class);

        mappedGraph.setEdgeIds(edgeIds);

        return mappedGraph;
    }
}
