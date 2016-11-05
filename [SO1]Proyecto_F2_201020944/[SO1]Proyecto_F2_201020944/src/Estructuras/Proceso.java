package Estructuras;

import java.util.ArrayList;

public class Proceso {

	public int id;
	public ArrayList<Accion> Lacciones;
	
	public Proceso() {
		// TODO Auto-generated constructor stub
		this.Lacciones = new ArrayList<Accion>();
	}

	public Proceso(int p){
		this.id = p;
		this.Lacciones = new ArrayList<Accion>();
	} 
}
