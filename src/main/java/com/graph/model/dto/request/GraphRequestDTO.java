package com.graph.model.dto.request;

import com.graph.model.entity.Edge;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class GraphRequestDTO {

    private String name;
}
