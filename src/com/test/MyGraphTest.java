package com.test;

import com.nutty.Graph;
import com.nutty.MyGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by SashaBoss on 20.03.2017.
 */
public class MyGraphTest {
    @Test
    public void testPerformBFS() {
        
        MyGraph g = new MyGraph(6);
        // 0___1___3
        //  \__2___4___
        //      \__5___|
        g.connect(g.getVertexList().get(0), g.getVertexList().get(1));
        g.connect(g.getVertexList().get(0), g.getVertexList().get(2));
        g.connect(g.getVertexList().get(1), g.getVertexList().get(3));
        g.connect(g.getVertexList().get(2), g.getVertexList().get(4));
        g.connect(g.getVertexList().get(2), g.getVertexList().get(5));
        g.connect(g.getVertexList().get(4), g.getVertexList().get(5));
        
        Graph.Vertex start = g.getVertexList().get(0);
        Graph.Vertex end = g.getVertexList().get(5);
        
        int expectedDistance = 2;
        
        int algorithmDistance =
                MyGraph.performBFS(g, start, end);
        
        assertEquals(expectedDistance, algorithmDistance);
        
    }
    
}
