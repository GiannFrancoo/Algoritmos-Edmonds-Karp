import java.util.*;

public class Solution {

    private static int cantNodos;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cantArcos, s, t, u, v;
        cantNodos = scanner.nextInt();
        cantArcos = scanner.nextInt();
        s = scanner.nextInt()-1;
        t = scanner.nextInt()-1;

        int grafo[][] = new int[cantNodos][cantNodos];

        for(int i = 0; i < cantArcos; i++){
            u = scanner.nextInt()-1;
            v = scanner.nextInt()-1;
            grafo[u][v] = scanner.nextInt();
        }

        System.out.println(edmonds_karp(grafo, s, t));
    }

    public static boolean bfs(int grafoResidual[][], int s, int t, int padre[]){
        boolean visitado[] = new boolean[cantNodos];
        for (int i = 0; i < cantNodos; ++i)
            visitado[i] = false;

        Queue<Integer> cola = new ArrayDeque<Integer>();
        cola.add(s);
        visitado[s] = true;
        padre[s] = -1;

        while (!cola.isEmpty()) {

            int u = cola.poll();

            for (int v = 0; v < cantNodos; v++) {
                if (!visitado[v] && grafoResidual[u][v] > 0) {
                    if (v == t) {
                        padre[v] = u;
                        return true;
                    }
                    cola.add(v);
                    padre[v] = u;
                    visitado[v] = true;
                }
            }
        }
        return false;
    }

    public static int edmonds_karp(int grafo[][], int s, int t){
        int u, v;

        int grafoResidual[][] = new int[cantNodos][cantNodos];

        for (u = 0; u < cantNodos; u++)
            for (v = 0; v < cantNodos; v++)
                grafoResidual[u][v] = grafo[u][v];

        int padre[] = new int[cantNodos];

        int flujoMaximo = 0;

        while (bfs(grafoResidual, s, t, padre)) {

            int caminoMenorFlujo = Integer.MAX_VALUE;
            for (v = t; v != s; v = padre[v]) {
                u = padre[v];
                caminoMenorFlujo = Math.min(caminoMenorFlujo, grafoResidual[u][v]);
            }

            for (v = t; v != s; v = padre[v]) {
                u = padre[v];
                grafoResidual[u][v] -= caminoMenorFlujo;
                grafoResidual[v][u] += caminoMenorFlujo;
            }

            flujoMaximo += caminoMenorFlujo;
        }

        return flujoMaximo;
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

