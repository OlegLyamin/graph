package com.graph.model.dto.response;

import com.graph.model.entity.Edge;
import lombok.Data;

import java.util.List;

@Data
public class GraphResponseDTO {

    private Long id;

    private String name;

    private List<Long> edgeIds;

}
