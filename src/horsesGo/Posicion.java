/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsesGo;

import java.util.ArrayList;
import javafx.geometry.Pos;

/**
 *
 * @author Joan
 */
public class Posicion {
    
    private int fila;
    private int columna;

    public Posicion(int fila, int Columna) {
        this.fila = fila;
        this.columna = Columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public boolean equalPos(Posicion pos2){
        
        return ( (fila == pos2.getFila()) && (columna == pos2.getColumna()) );        
    }
    
    public Posicion mover(String movimiento){
        
        Posicion pos = null;
        
        switch(movimiento){
            
            case "arribaDerecha":
                pos = new Posicion(fila-2, columna+1);
                break;
            case "derechaArriba":
                pos = new Posicion(fila-1, columna+2);
                break;
            case "derechaAbajo":
                pos = new Posicion(fila+1, columna+2);
                break;
            case "abajoDerecha":
                pos = new Posicion(fila+2, columna+1);
                break;
            case "abajoIzquierda":
                pos = new Posicion(fila+2, columna-1);
                break;
            case "izquierdaAbajo":
                pos = new Posicion(fila+1, columna-2);
                break;
            case "izquierdaArriba":
                pos = new Posicion(fila-1, columna-2);
                break;
            case "arribaIzquierda":
                pos = new Posicion(fila-2, columna-1);
                break;
        }        
        return pos;        
    }  
    
    /*
    
    public Posicion arribaDerecha(){             
        Posicion pos = new Posicion(fila-2, columna+1);
        return pos;
    }
    
    public Posicion derechaArriba(){             
        Posicion pos = new Posicion(fila-1, columna+2);
        return pos;
    }
    
    public Posicion derechaAbajo(){             
        Posicion pos = new Posicion(fila+1, columna+2);
        return pos;
    }
    
    public Posicion abajoDerecha(){             
        Posicion pos = new Posicion(fila+2, columna+1);
        return pos;
    }
    
    
    public Posicion abajoIzquierda(){             
        Posicion pos = new Posicion(fila+2, columna-1);
        return pos;
    }
    
    public Posicion izquierdaAbajo(){             
        Posicion pos = new Posicion(fila+1, columna-2);
        return pos;
    }   
    
    public Posicion izquierdaArriba(){             
        Posicion pos = new Posicion(fila-1, columna-2);
        return pos;
    }    
    
    public Posicion arribaIzquierda(){             
        Posicion pos = new Posicion(fila-2, columna-1);
        return pos;
    }
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
