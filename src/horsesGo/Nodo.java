/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsesGo;

import java.util.ArrayList;

/**
 *
 * @author Joan
 */
public class Nodo {
    
    private Nodo padre;
    private int profundidad;
    private String operador;
    private int turno;    
    private int[][] tablero;
    private int limite;

    public Nodo(Nodo padre, int profundidad, String operador, int turno, int[][] tablero, int limite) {
        this.padre = padre;
        this.profundidad = profundidad;
        this.operador = operador;
        this.turno = turno;        
        this.tablero = tablero;
        this.limite = limite;
    }
    
    //Por implementar
    
    public double utilidad(){        
        
        double utilidad = 0;
        ArrayList<Nodo> nodosHijos = movimientosValidos(turno);
        int cantidadMovimientos = nodosHijos.size();       
        
        if(cantidadMovimientos == 0){
            utilidad = utilidadNodoHoja();
        }
        else if (profundidad == limite){            
            double cantidadMovsBlanca = movimientosValidos(1).size();
            double cantidadMovsNegra = movimientosValidos(2).size();
            
            utilidad = (cantidadMovsBlanca - cantidadMovsNegra) / (cantidadMovsBlanca + cantidadMovsNegra);
            //System.out.println("Limite alcanzado: B:" + cantidadMovsBlanca + " N:" + cantidadMovsNegra + " U:" + utilidad);
            
        }else{           
            
            ArrayList<Double> utilidades = utilidadesNodos(nodosHijos);
            
            if(turno == 1){
                utilidad = utilidades.get(posMayorUtilidad(utilidades));
            }else{
                utilidad = utilidades.get(posMenorUtilidad(utilidades));
            }            
        }        
        //System.out.println("U: " + utilidad);
        return utilidad;
    }
    
    public ArrayList<Double> utilidadesNodos(ArrayList<Nodo> nodos){
        
        ArrayList<Double> utilidadesNodos = new ArrayList<>();       
        
        for (int i = 0; i < nodos.size(); i++) {
            utilidadesNodos.add(nodos.get(i).utilidad());            
        }
        
        return utilidadesNodos;        
    }
    
    
    public int posMenorUtilidad(ArrayList<Double> utilidades){        
        
        int posMenorUtilidad = 0;
        double menorUtilidad = utilidades.get(0);
        double utilidad = 0;
        
        for (int i = 1; i < utilidades.size(); i++) {     
            
            utilidad = utilidades.get(i);
            
            if(utilidad < menorUtilidad){
                menorUtilidad = utilidad;
                posMenorUtilidad = i;
            }
            
        }
        
        return posMenorUtilidad;
    }
    
    public int posMayorUtilidad(ArrayList<Double> utilidades){        
        
        int posMayorUtilidad = 0;
        double mayorUtilidad = utilidades.get(0);
        double utilidad = 0;
        
        for (int i = 1; i < utilidades.size(); i++) {     
            
            utilidad = utilidades.get(i);
            
            if(utilidad > mayorUtilidad){
                mayorUtilidad = utilidad;
                posMayorUtilidad = i;
            }
            
        }
        
        return posMayorUtilidad;
    }   
    
    
    
    public double utilidadNodoHoja(){        
        
        double utilidad=0;
        
        if(turno == 1){ //Si el turno actual es de "MAX" (MAX pierde)
            utilidad = -1.0;
        }else{
            utilidad = 1.0;
        } 
        
        return utilidad;        
    }
    
    //Recibe una ficha (1 o 2), un movimiento (arribaDerecha, ...) y devuelve un nodo con el movimiento aplicado a la ficha   
    
    public Nodo mover(int ficha, String movimiento){
        
        Posicion posActual = posFicha(ficha);
        Posicion posDestino = posActual.mover(movimiento);
        int[][] newTablero = new int[limite][limite];
        
        for (int i = 0; i < limite; i++) {
            for (int j = 0; j < limite; j++) {
                newTablero[i][j] = tablero[i][j];
            }
        }
        
        newTablero[posActual.getFila()][posActual.getColumna()] = 3;
        newTablero[posDestino.getFila()][posDestino.getColumna()] = ficha;  //Pone la ficha en la nueva posición
        
        return new Nodo(this, this.profundidad + 1, movimiento, turnoInvertido(), newTablero, limite);
    }      
    
    //Recibe una ficha (1 o 2) y devuelve los movimientos válidos de dicha ficha como nodos
    
    public ArrayList<Nodo> movimientosValidos(int ficha){       
        
        ArrayList<Nodo> movimientosValidos = new ArrayList<>();
        String movimientos[] = {"arribaDerecha", "derechaArriba", "derechaAbajo", "abajoDerecha", "abajoIzquierda", "izquierdaAbajo", "izquierdaArriba", "arribaIzquierda"};
        
        Posicion posInicial = posFicha(ficha);
        Posicion posDestino = new Posicion(0, 0);        
        
        for (int i = 0; i < 8; i++) {            
            posDestino = posInicial.mover(movimientos[i]);
            
            if(validarMovimiento(posDestino)){
                movimientosValidos.add(mover(ficha, movimientos[i]));                
            }                     
        }
        
        return movimientosValidos;        
    }
    
    public boolean validarMovimiento(Posicion posDestino){               
        
        boolean valido = false;        
        
        //Revisa si la posicion está en el tablero   
        
        boolean enTablero = ((posDestino.getFila() >=0) &&(posDestino.getFila() <limite) &&
                          (posDestino.getColumna() >=0) && (posDestino.getColumna() <limite));
        
        //Si está en el tablero, revisa si la casilla está vacía        
        if(enTablero){
            valido = (tablero[posDestino.getFila()][posDestino.getColumna()] == 0);
        }
        
        return valido;        
    }
    
    
    //Devuelve la posición de la ficha ingresada como parámetro (1 o 2)
    
    public Posicion posFicha(int ficha){
        
        Posicion posicion = null;
        
        for (int i = 0; i < limite; i++) {
            for (int j = 0; j < limite; j++) {
                if(tablero[i][j] == ficha){
                    posicion = new Posicion(i, j);
                    break;
                }
            }
        }
        
        return posicion;
    }
    
    public int turnoInvertido(){
        
        if(turno == 1){
            return 2;
        }else{
            return 1;
        }
    }
    
    public boolean finJuego(){        
        
        int cantidadMovValidos = movimientosValidos(turno).size();
        
        return (cantidadMovValidos == 0);        
    }
    
    //Fin Por implementar

    //Metodos get y set

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
    
    
    
    
    
    
    
}
