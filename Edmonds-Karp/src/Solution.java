import kotlin.time.MeasureTimeKt;

import java.io.*;
import java.util.*;

import java.lang.*;
import java.util.LinkedList;

public class Solution {

    static int V = 4;

    public static void main(String[] args) {

        int graph[][] = new int[][] {
                { 0, 16, 13, 0, 0, 0 },
                { 0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },
                { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },
                { 0, 0, 0, 0, 0, 0 }
        }; //Return 23

        V = 6;
        long ini = System.nanoTime();
        System.out.println("Max flow POLL + CHECK" + fordFulkersonDeque(graph, 0, 5));
        long fin = System.nanoTime();
        System.out.println("Tiempo: " + (fin - ini));

        ini = System.nanoTime();
        System.out.println("Max flow POLL" + fordFulkerson(graph, 0, 5));
        fin = System.nanoTime();
        System.out.println("Tiempo: " + (fin - ini));


        /*
        int graph1[][] = new int[][] {
                {0,3,0,0},
                {0,5,4,0},
                {2,0,0,3},
                {0,0,3,0},
        }; //Return 3

        V = 4;
        long ini1 = System.nanoTime();
        System.out.println("Max flow " + fordFulkerson(graph1, 0, 3));
        long fin1 = System.nanoTime();
        System.out.println("Tiempo: " + (fin1 - ini1));
        */
    }

    public static boolean bfsDeque(int rGraph[][], int s, int t, int parent[]){

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return false;
    }

    public static int fordFulkersonDeque(int graph[][], int s, int t){
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];

        int max_flow = 0;

        while (bfsDeque(rGraph, s, t, parent)) {
            int path_flow = Integer.MAX_VALUE;

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[v][u] += path_flow;
                rGraph[u][v] -= path_flow;
            }

            max_flow += path_flow;
        }

        return max_flow;
    }


    public static boolean bfs(int rGraph[][], int s, int t, int parent[]){
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return false;
    }

    public static int fordFulkerson(int graph[][], int s, int t){
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }





}


 /*
    PROCEDURE FordFulkerson (G,s,t,c)
        FOR cada arco (u,v) en A
            f[u,v]::=0; f[v,u]::=0
        ENDFOR

        WHILE existe un camino de aumento p en Gf
            cf(p)::= capacidad mínima de p
            FOR cada arco (u,v) en p
                f[u,v]::=f[u,v]+cf(p)
                f[v,u]::=-f[u,v]
            ENDFOR
        ENDWHILE;

       RETURN f
    */

