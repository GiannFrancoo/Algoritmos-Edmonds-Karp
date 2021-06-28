import com.sun.corba.se.impl.orbutil.graph.Graph
import java.io.*
import java.util.*

internal class Nodo(
    var id : Int
)

internal class Arco(
    var peso : Int,
    var origen : Nodo,
    var destino : Nodo,
)

/*
PROCEDURE FordFulkerson (G,s,t,c)

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

RETURN f

 */


fun main(args: Array<String>) {
    val G : Pair<List<Nodo>,List<Arco>>

}

/*
    Input
        G: Lista de nodos y arcos
        s: Nodo entrada
        t: Nodo salida
        c: --
    output
 */
private fun FordFulkerson(g: Pair<List<Nodo>,List<Arco>>, s: Nodo, t: Nodo, c:Int) : Int{

    for (i in 0..g.second.size){
        g.second[i].peso = 0
    }

    while(true){

    }

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


    return 1
}