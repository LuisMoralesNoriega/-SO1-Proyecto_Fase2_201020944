package Estructuras;

import java.util.ArrayList;

public class Tiempo {
	
	public int id;
	public ArrayList<Proceso> Lprocesos;
	
	public Tiempo() {
		// TODO Auto-generated constructor stub
		this.Lprocesos = new ArrayList<Proceso>();
	}
	
	public Tiempo(int t){
		this.id = t;
		this.Lprocesos = new ArrayList<Proceso>();
	}

}
