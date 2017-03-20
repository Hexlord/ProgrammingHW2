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
        g.connect(g.at(0), g.at(1));
        g.connect(g.at(0), g.at(2));
        g.connect(g.at(1), g.at(3));
        g.connect(g.at(2), g.at(4));
        g.connect(g.at(2), g.at(5));
        g.connect(g.at(4), g.at(5));
        
        Graph.Vertex start = g.at(0);
        Graph.Vertex end = g.at(5);
        
        int expectedDistance = 2;
        
        int algorithmDistance =
                MyGraph.performBFS(g, start, end);
        
        assertEquals(expectedDistance, algorithmDistance);
        
    }
    
}
