package Estructuras;

public class Recurso {

	public int id;
	public boolean asignado;
	public boolean yafueasignado;
	
	public Recurso() {
		// TODO Auto-generated constructor stub
		this.id = -1;
		this.asignado = false;
		this.yafueasignado = false;
	}

	public Recurso(int r){
		this.id = r;		
		this.asignado = false;
		this.yafueasignado = false;
	}
	
	public Recurso(int r, boolean as, boolean ya){
		this.id = r;		
		this.asignado = as;
		this.yafueasignado = ya;
	}
}
