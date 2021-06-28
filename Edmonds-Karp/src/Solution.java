import java.io.*;
import java.util.*;

import java.lang.*;
import java.util.LinkedList;




public class Solution {

    public static void main(String[] args) {
        /*
        int graph[][] = new int[][] {
                { 0, 16, 13, 0, 0, 0 },
                { 0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },
                { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },
                { 0, 0, 0, 0, 0, 0 }
        };
        //Return 23
        */


        int graph[][] = new int[][] {
                {0,3,0,0},
                {0,5,4,0},
                {2,0,0,3},
                {0,0,3,0},
        };
        //Return 3


        System.out.println("Max flow " + fordFulkerson(graph, 0, 3));
    }


    /*
    Input
        G: Lista de nodos y arcos
        s: Nodo entrada
        t: Nodo salida
        c: --
    output
        maximo flujo: int
    */
    //private int FordFulkerson(g: Pair<List<Nodo>,List<Arco>>, s: Nodo, t: Nodo, int c){
        /*
            FOR cada arco (u,v) en A
                f[u,v]::=0; f[v,u]::=0
            ENDFOR

            WHILE existe un camino de
                aumento p en Gf
                cf(p)::= capacidad mínima de p
                FOR cada arco (u,v) en p
                    f[u,v]::=f[u,v]+cf(p)
                    f[v,u]::=-f[u,v]
                ENDFOR
            ENDWHILE;
        */
        //return 1;
    //}

    //-------------------------------------------------------------------------------------

    static final int V = 4;

    public static boolean bfs(int rGraph[][], int s, int t, int parent[]){

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
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
    public static int fordFulkerson(int graph[][], int s, int t){
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];

        int max_flow = 0;

        while (bfs(rGraph, s, t, parent)) {
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

}


