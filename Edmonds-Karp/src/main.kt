import com.sun.corba.se.impl.orbutil.graph.Graph
import java.io.*
import java.util.*

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

    


}

/*
    Input
        G: Lista de nodos y arcos
        s: Nodo entrada
        t: Nodo salida
        c: --
    output
 */
fun FordFulkerson() : Int{


    return 1
}

internal class nodo{

}

internal class arco{

}