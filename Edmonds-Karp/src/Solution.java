import java.io.*;
import java.util.*;

public class Solution {

    private static int cantNodos;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cantArcos, s, t, u, v, c;
        cantNodos = scanner.nextInt();
        cantArcos = scanner.nextInt();
        s = scanner.nextInt()-1;
        t = scanner.nextInt()-1;


        List<Arco>[] grafo = new LinkedList[cantNodos];
        for(int i = 0; i < cantNodos; i++){
            grafo[i] = new LinkedList();
        }

        Edmonds_Karp ekps = new Edmonds_Karp(grafo, s, t);

        for(int i = 0; i < cantArcos; i++){
            u = scanner.nextInt()-1;
            v = scanner.nextInt()-1;
            c = scanner.nextInt();
            ekps.agregarArco(u, v, c);
        }

        System.out.println(ekps.maximoFlujo());
    }

    private static class Arco {
        protected int origen;
        protected int destino;
        protected int flujoActual;
        protected int capacidad;
        protected Arco residuo;

        public Arco(int origen, int destino, int capacidad){
            this.origen = origen;
            this.destino = destino;
            this.capacidad = capacidad;
        }

        public int capacidadDisponible(){
            return capacidad - flujoActual;
        }
    }

    private static class Edmonds_Karp {

        protected List<Arco>[] grafo;
        protected int s;
        protected int t;

        public Edmonds_Karp(List<Arco>[] grafo, int s, int t){
            this.grafo = grafo;
            this.s = s;
            this.t = t;
        }

        public int maximoFlujo(){
            int maximoFlujo = 0;
            int minimoFlujoCamino = 0;

            int minimoFlujo = Integer.MAX_VALUE;

            Arco[] camino = new Arco[cantNodos]; //Luego se crea en bfs

            while(bfs(camino)){
                //recuperar el minimoFlujo del camino
                minimoFlujo = Integer.MAX_VALUE;
                for(Arco arco = camino[t]; arco != null; arco = camino[arco.origen]){
                    minimoFlujo = Math.min(minimoFlujo, arco.capacidadDisponible());
                }

                //incrementrar / decrementar el arco
                for(Arco arco = camino[t]; arco != null; arco = camino[arco.origen]){
                    arco.flujoActual = arco.flujoActual + minimoFlujo;
                    arco.residuo.flujoActual = arco.residuo.flujoActual - minimoFlujo;
                }

                maximoFlujo += minimoFlujo;
            }

            return maximoFlujo;
        }

        public boolean bfs(Arco[] camino){

            //Inicializa todos false;
            boolean[] visitados = new boolean[cantNodos];

            Queue<Integer> cola = new ArrayDeque();
            visitados[s] = true;
            cola.add(s);

            //Construir el camino hacia el nodo "t"
            //camino = new Arco[cantNodos];
            while(!cola.isEmpty()){
                int nodo = cola.poll();

                if (nodo == t) {
                    return true;
                }

                for(Arco arco : grafo[nodo]){
                    if(!visitados[arco.destino] && arco.capacidadDisponible() > 0){

                        if(arco.destino == t){
                            camino[arco.destino] = arco;
                            return true;
                        }

                        cola.add(arco.destino);
                        camino[arco.destino] = arco;
                        visitados[arco.destino] = true;
                    }
                }
            }

            //Debido a que no llego al nodo "t"
            return false;
        }

        public void agregarArco(int origen, int destino, int capacidad){
            Arco a = new Arco(origen, destino, capacidad);
            Arco b = new Arco(destino, origen, 0);
            a.residuo = b;
            b.residuo = a;
            grafo[origen].add(a);
            grafo[destino].add(b);
        }


    }

}