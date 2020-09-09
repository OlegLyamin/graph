package com.graph.service;

import com.graph.exception.Error;
import com.graph.exception.HandledException;
import com.graph.model.dto.request.EdgeRequestDTO;
import com.graph.model.dto.response.EdgeResponseDTO;
import com.graph.model.entity.Edge;
import com.graph.repository.EdgeRepository;
import com.graph.repository.GraphRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;
    private final GraphRepository graphRepository;
    private final ModelMapper modelMapper;

    public EdgeService(EdgeRepository edgeRepository, GraphRepository graphRepository, ModelMapper modelMapper) {
        this.edgeRepository = edgeRepository;
        this.graphRepository = graphRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public EdgeResponseDTO createEdge(EdgeRequestDTO request) {
        checkExistGraph(request.getGraphId());

        Edge edge = new Edge();

        modelMapper.map(request, edge);

        edge = edgeRepository.save(edge);

        return mapEdgeEntityToDTO(edge);
    }

    @Transactional
    public List<EdgeResponseDTO> createEdges(List<EdgeRequestDTO> request) {
        List<EdgeResponseDTO> toReturn = new ArrayList<>();

        for (EdgeRequestDTO requestDTO : request) {
            checkExistGraph(requestDTO.getGraphId());

            Edge edge = new Edge();

            modelMapper.map(requestDTO, edge);

            edge = edgeRepository.save(edge);

            toReturn.add(mapEdgeEntityToDTO(edge));
        }

        return toReturn;
    }

    @Transactional
    public EdgeResponseDTO updateEdge(Long id, EdgeRequestDTO request) {
        Edge edge = edgeRepository.findById(id).orElseThrow(() ->
                new HandledException(Error.EDGE_NOT_FOUND, "Edge not found"));

        modelMapper.map(request, edge);

        Edge savedEdge = edgeRepository.save(edge);

        return mapEdgeEntityToDTO(savedEdge);
    }

    @Transactional
    public void removeEdge(Long id) {
        edgeRepository.delete(edgeRepository.findById(id).orElseThrow(() ->
                new HandledException(Error.EDGE_NOT_FOUND, "Edge not found")));
    }

    @Transactional
    public void removeEdges(List<Long> ids) {
        ids.forEach(id -> {
            edgeRepository.delete(edgeRepository.findById(id).orElseThrow(() ->
                    new HandledException(Error.EDGE_NOT_FOUND, "Edge not found")));
        });
    }

    private void checkExistGraph(Long graphId) {
        if (!graphRepository.existsById(graphId)) {
            throw new HandledException(Error.GRAPH_NOT_FOUND, "Graph not found");
        }
    }

    private EdgeResponseDTO mapEdgeEntityToDTO(Edge entity) {
        return modelMapper.map(entity, EdgeResponseDTO.class);
    }

}
