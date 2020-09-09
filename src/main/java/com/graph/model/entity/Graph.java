package com.graph.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "graph")
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gen")
    @SequenceGenerator(name = "id_seq_gen", sequenceName = "id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
    private List<Edge> edges;
}
