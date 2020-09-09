drop table if exists graph;

create table graph
(
    id   bigint not null
        constraint graph_pkey primary key,
    name varchar(100)
);

drop table if exists edge;

create table edge
(
    id         bigint not null
        constraint edge_pkey primary key,
    graph_id   bigint not null
        constraint graph_edge_reference references graph,
    point_from       int,
    point_to         int,
    type       varchar(100),
    weight     int,
    name       varchar(100),
    color      varchar(100),
    properties varchar(100)
);