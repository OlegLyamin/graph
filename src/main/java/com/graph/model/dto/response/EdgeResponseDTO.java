package com.graph.model.dto.response;

import com.graph.model.entity.Graph;
import com.graph.model.enums.EdgeType;
import lombok.Data;

import javax.persistence.*;

@Data
public class EdgeResponseDTO {

    private Long id;

    private String name;

    private Integer pointFrom;

    private Integer pointTo;

    private EdgeType type;

    private Integer weight;

    private String color;

    private String properties;

    private Long graphId;
}
