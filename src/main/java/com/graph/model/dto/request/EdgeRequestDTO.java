package com.graph.model.dto.request;

import com.graph.model.entity.Graph;
import com.graph.model.enums.EdgeType;
import lombok.Data;

import javax.persistence.*;

@Data
public class EdgeRequestDTO {

    private String name;

    private Integer pointFrom;

    private Integer pointTo;

    private EdgeType type;

    private Integer weight;

    private String color;

    private String properties;

    private Long graphId;
}
