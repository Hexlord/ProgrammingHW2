/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty;

import java.util.*;

import static com.nutty.MyGraph.BFSVertexVisitStatus.NOT_VISITED;
import static com.nutty.MyGraph.BFSVertexVisitStatus.VISITED;

/**
 * Created by SashaBoss on 20.03.2017.
 */
public class MyGraph implements Graph {
    
    
    class MyVertex implements Graph.Vertex {
        String name;
        
        public MyVertex(String name) {
            this.name = name;
        }
        
        @Override
        public String getName() {
            return name;
        }
    }
    
    ArrayList<Vertex> vertexList;
    Map<Vertex, Set<Vertex>> connectedVertexIdListMap;
    
    public MyGraph(int vertexCount) {
        
        vertexList = new ArrayList<Vertex>();
        connectedVertexIdListMap = new HashMap<Vertex, Set<Vertex>>();
        
        for (int i = 0;
             i < vertexCount;
             ++i) {
            Vertex v = new MyVertex("Vertex " + (i + 1));
            vertexList.add(v);
            connectedVertexIdListMap.put(v, new HashSet<Vertex>());
        }
        
        
    }
    
    public void connect(Vertex v1, Vertex v2) {
        Set<Vertex> connectedVertexIdList =
                connectedVertexIdListMap.get(v1);
        connectedVertexIdList.add(v2);
        
        connectedVertexIdList =
                connectedVertexIdListMap.get(v2);
        connectedVertexIdList.add(v1);
    }
    
    @Override
    public ArrayList<Vertex> getVertexList() {
        return vertexList;
    }
    
    @Override
    public Set<Vertex> getNeighbors(Vertex v) {
        return connectedVertexIdListMap.get(v);
    }
    
    @Override
    public Map<Vertex, Edge> getConnections(Vertex v) {
        return null;
    }
    
    enum BFSVertexVisitStatus {
        NOT_VISITED,
        VISITED
    }
    
    static class BFSVertexInfo {
        private final BFSVertexVisitStatus visitStatus;
        private final int distance;
        private final Vertex previous;
        
        public BFSVertexInfo() {
            visitStatus = NOT_VISITED;
            distance = Integer.MAX_VALUE;
            previous = null;
        }
        
        public BFSVertexInfo(BFSVertexVisitStatus visitStatus,
                             int distance,
                             Vertex previous) {
            this.visitStatus = visitStatus;
            this.distance = distance;
            this.previous = previous;
        }
        
        public BFSVertexVisitStatus getVisitStatus() {
            return visitStatus;
        }
        
        public int getDistance() {
            return distance;
        }
        
        public Vertex getPrevious() {
            return previous;
        }
        
    }
    
    public static int performBFS(MyGraph g,
                                 Vertex start,
                                 Vertex end) {
        
        
        ArrayList<Vertex> vertexList = g.getVertexList();
        Map<Vertex, BFSVertexInfo> vertexInfoList =
                new HashMap<Vertex, BFSVertexInfo>();
        
        for (Vertex v : vertexList) {
            vertexInfoList.put(v, new BFSVertexInfo());
        }
        
        vertexInfoList.put(start,
                new BFSVertexInfo(
                        VISITED,
                        0,
                        null));
        
        
        Deque<Vertex> queue = new ArrayDeque<Vertex>();
        queue.add(start);
        
        while (queue.size() > 0) {
            Deque<Vertex> newQueue = new ArrayDeque<Vertex>();
            
            for (Vertex from : queue) {
                
                BFSVertexInfo fromInfo =
                        vertexInfoList.get(from);
                
                Set<Vertex> neighbors =
                        g.getNeighbors(from);
                
                for (Vertex to : neighbors) {
                    BFSVertexInfo info =
                            vertexInfoList.get(to);
                    
                    if (info.getVisitStatus() == NOT_VISITED) {
                        BFSVertexInfo newInfo =
                                new BFSVertexInfo(
                                        VISITED,
                                        fromInfo.getDistance() + 1,
                                        from);
                        vertexInfoList.put(to, newInfo);
                        newQueue.add(to);
                    }
                    
                }
            }
            
            queue = newQueue;
        }
        
        if (vertexInfoList.get(end).getVisitStatus() == VISITED) {
            return vertexInfoList.get(end).getDistance();
        } else {
            return -1;
        }
    }
}
