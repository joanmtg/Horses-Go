/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsesGo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Joan
 */
public class Programa {
    
    private int size;
    private int[][] tablero;
    private int turno = 1; //Cambar a 1. 1:blanca, 2:negra
    
    //1 - caballo blanco
    //2 - caballo negro
    
    public Programa(int size){
        this.size = size;
        tablero = new int[size][size];       
        
        Posicion posBlanca, posNegra;
        
        posBlanca = posAleatoria();       
                
        do{
            posNegra = posAleatoria();            
        }while(posBlanca.equalPos(posNegra));      
        
        tablero[posBlanca.getFila()][posBlanca.getColumna()] = 1;
        tablero[posNegra.getFila()][posNegra.getColumna()] = 2;        
        
        System.out.println("Blanca: (" + posBlanca.getFila() + "," + posBlanca.getColumna() + ")");
        System.out.println("Negra: (" + posNegra.getFila() + "," + posNegra.getColumna() + ")");      
        
        mostrarTablero();
    }
    
    public void movimientoMaquina(){       
        
        Nodo nodoRaiz = new Nodo(null, 0, null, 1, tablero, size);           
        
        ArrayList<Nodo> movValidosMaquina = nodoRaiz.movimientosValidos(1);
        ArrayList<Double> utilidades = nodoRaiz.utilidadesNodos(movValidosMaquina);        
        int posNodoMayorUtilidad = nodoRaiz.posMayorUtilidad(utilidades);
        
        for (int i = 0; i < movValidosMaquina.size(); i++) {
            Nodo nodo = movValidosMaquina.get(i);
            System.out.println(nodo.getOperador() + " " + utilidades.get(i));            
        }
        
        Nodo nodo = movValidosMaquina.get(posNodoMayorUtilidad);  
        
        System.out.println("Decisión: " + nodo.getOperador());
        
        
        tablero = nodo.getTablero();        
        
    }
    
    
    public Posicion posAleatoria(){        
        Random rn = new Random();          
        
        int i = rn.nextInt(size);
        int j = rn.nextInt(size);           
        
        Posicion pos = new Posicion(i, j);
        
        return pos;
        
    }
    
    public void mostrarTablero(){
        
        for (int i = 0; i < tablero.length; i++) {
            
            for (int j = 0; j < tablero[i].length; j++) {                
                
                System.out.print(tablero[i][j] +  " ");
            }
            System.out.println("");            
        }
        System.out.println("");
    }
    
      
    
    public void marcarMovimientosValidosJugador(){
        
        Nodo nodo = new Nodo(null, 0, null, 2, tablero, size);
        
        ArrayList<Nodo> movimientosValidos = nodo.movimientosValidos(2);
        
        Posicion pos = null;
        
        for (int i = 0; i < movimientosValidos.size(); i++) {
            
            pos = movimientosValidos.get(i).posFicha(2);
            tablero[pos.getFila()][pos.getColumna()] = 4;            
        }
        
    }
    
    public void limpiarMovimientosValidos(){                           
          
        for (int i = 0; i < size; i++) {            
            
            for (int j = 0; j < size; j++) {
                if(tablero[i][j] == 4){
                    tablero[i][j] = 0;
                }
            }            
        }         
    }
    
    
    public boolean finJuego(){
        
        Nodo nodo = new Nodo(null, 0, null, turno, tablero, size);        
        
        int cantidadMovValidos = nodo.movimientosValidos(turno).size();
        
        return (cantidadMovValidos == 0);        
    }
    
    
    public Posicion posFichaTurno(){
        
        Posicion posicion = null;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tablero[i][j] == turno){
                    posicion = new Posicion(i, j);
                    break;
                }
            }
        }
        
        return posicion;
    }
    
    
    public void cambiarTurno(){
        
        if(turno == 1){
            this.turno = 2;
        }else{
            this.turno = 1;
        }        
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;        
    }
    
    
    
    
    
}
