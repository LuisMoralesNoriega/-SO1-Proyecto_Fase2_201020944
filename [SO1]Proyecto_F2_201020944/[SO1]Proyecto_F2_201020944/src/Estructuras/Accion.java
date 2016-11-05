package Estructuras;

import java.util.ArrayList;

public class Accion {

	public int id;
	public ArrayList<Integer> Lrecursos;
	public Accion() {
		// TODO Auto-generated constructor stub
		this.Lrecursos =  new ArrayList<>();
	}

	public Accion(int pa){
		this.id = pa;
		this.Lrecursos =  new ArrayList<>();
	}
}
