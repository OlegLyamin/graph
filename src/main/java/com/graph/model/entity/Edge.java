package com.graph.model.entity;

import com.graph.model.enums.EdgeType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "edge")
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gen")
    @SequenceGenerator(name = "id_seq_gen", sequenceName = "id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "point_from")
    private Integer pointFrom;

    @Column(name = "point_to")
    private Integer pointTo;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private EdgeType type;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "color")
    private String color;

    @Column(name = "properties")
    private String properties;

    @ManyToOne
    @JoinColumn(name = "graph_id", referencedColumnName = "id")
    private Graph graph;
}
