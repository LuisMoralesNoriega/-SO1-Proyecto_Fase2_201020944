package Estructuras;

import java.util.ArrayList;

public class AsignacionP {

	public int idproceso;
	public int tiempo;
	public boolean valuado;
	public ArrayList<Recurso> Lreasig ;
	
	public AsignacionP() {
		// TODO Auto-generated constructor stub
		this.idproceso = -1;
		this.tiempo = -1;
		this.valuado = false;
		this.Lreasig =  new ArrayList<>();
	}
	
	public AsignacionP(int idp,int t){
		this.idproceso = idp;
		this.tiempo = t;
		this.valuado = false;
		this.Lreasig =  new ArrayList<>();
	}

}
