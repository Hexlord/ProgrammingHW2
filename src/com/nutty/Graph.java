package com.nutty;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by SashaBoss on 20.03.2017.
 */
public interface Graph {
    interface Vertex {
        String getName();
    }
    
    List<Vertex> getVertexList();
    
    Set<Vertex> getNeighbors(Vertex v);
    
    // Optional ( unused )
    interface Edge {
        int getWeight();
    }
    
    Map<Vertex, Edge> getConnections(Vertex v);
}

