package Principal;
import Estructuras.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import Analizador.AnalizadorS;
import Estructuras.Listas;
import Estructuras.Recurso;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int pasar = 0;
	String cadena = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void AsignacionProcesos(){
		this.pasar = 0;
		this.cadena = "";
		for (Tiempo t : Listas.Ltiempos) {
			for (Proceso p : t.Lprocesos) {
				for (Accion a : p.Lacciones) {
					if(a.id == 1){
						for (int r : a.Lrecursos) {
							boolean siexiste = false;
							boolean yafue = false;
							for (Recurso rec : Listas.Lrecursos) {
								if(rec.id == r){
									if(rec.asignado){
										rec.yafueasignado = true;
										yafue = true;
									}
									rec.asignado = true;
									siexiste = true;
								}
							}
							
							if(siexiste){
								AsignacionP ac = new AsignacionP(p.id,t.id);
								Recurso re = new Recurso(r,true,yafue);
								ac.Lreasig.add(re);
								Listas.Lprocesos.add(ac);
							}else{
								System.out.println("El recurso no se puede Asignar No EXISTE!!");
							}
						}
					}					
				}
			}
		}
		
		for (AsignacionP asig : Listas.Lprocesos) {
			System.out.println("Se asigno al proceso " + asig.idproceso);
			for (Recurso rec : asig.Lreasig) {
				System.out.println("Los recursos " + rec.id);
			}
		}
		
	}

	public void PasodeTiempos(int est,int tiempo){
		if(est == 1){
			for (AsignacionP asig : Listas.Lprocesos) {
				 if(asig.tiempo == tiempo){
					 for (Recurso r : asig.Lreasig) {
						System.out.println("P" + asig.idproceso + "-> R" + r.id + ";");
						if(!r.yafueasignado){
							cadena += "P" + asig.idproceso + "-> R" + r.id + ";";							
							r.yafueasignado = true;
						}else{
							cadena += "R" + r.id + "-> P" + asig.idproceso + ";";
						}
						
						for (AsignacionP asig2 : Listas.Lprocesos) {
							if(!asig2.valuado){
								asig2.valuado = true;
								if(asig.idproceso == asig2.idproceso && asig2.tiempo != tiempo){
									for (Recurso r2 : asig2.Lreasig) {
										System.out.println("P" + asig2.idproceso + "-> R" + r2.id + ";");
										if(!r2.yafueasignado){
											cadena += "P" + asig2.idproceso + "-> R" + r2.id + ";";
											r2.yafueasignado = true;
										}else{
											cadena += "R" + r2.id + "-> P" + asig2.idproceso + ";";											
										}					 
									}
								 }
							 }
						 }						 
					 }					 
				}
			}		
			try {
				this.CrearDot("node [shape=circle];" + cadena);
				String rutadot = "/home/luis/Documentos/Graficas/e1.dot";
				String rutapng = "/home/luis/Documentos/Graficas/e1.png";
				this.dibujar(rutadot, rutapng);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(est == 2){
			
		}else if(est == 3){
			
		}
	}
	
	public void CrearDot(String texto) throws IOException{
		
		String dot = "digraph G {" + texto +"}";
		String ruta = "/home/luis/Documentos/Graficas/e1.dot";
		File archivo = new File(ruta);
		BufferedWriter bw;
		
		if(archivo.exists()) {
		      bw = new BufferedWriter(new FileWriter(archivo));
		      bw.write(dot);
		} else {
		      bw = new BufferedWriter(new FileWriter(archivo));
		      bw.write(dot);
		}
		
		bw.close();
	}
	
	
	public void dibujar( String direccionDot, String direccionPng ){
		try{       
			ProcessBuilder pbuilder;
			pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", direccionPng, direccionDot );
			pbuilder.redirectErrorStream( true );
			//Ejecuta el proceso
			pbuilder.start();
		    
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Crefra
	 * me.
	 */
	public Ventana() {
		setFont(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1079, 451);
		getContentPane().setLayout(null);
		
		
		final JRadioButton Estrategia1 = new JRadioButton("Estrategia 1");
		Estrategia1.setBounds(26, 20, 122, 23);
		getContentPane().add(Estrategia1);
		
		final JRadioButton Estrategia2 = new JRadioButton("Estrategia 2");
		Estrategia2.setBounds(152, 20, 122, 23);
		getContentPane().add(Estrategia2);
		
		final JRadioButton Estrategia3 = new JRadioButton("Estrategia 3");
		Estrategia3.setBounds(277, 20, 149, 23);
		getContentPane().add(Estrategia3);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 51, 611, 294);
		getContentPane().add(tabbedPane);
		
		final JEditorPane EditorS = new JEditorPane();
		tabbedPane.addTab("Simulacion", null, EditorS, null);
		
		final JEditorPane EditorE = new JEditorPane();
		tabbedPane.addTab("Ejecucion", null, EditorE, null);
		
		final JLabel Img = new JLabel("");
		Img.setIcon(new ImageIcon("/home/luis/Documentos/Graficas/e1.png"));
		Img.setBounds(655, 55, 410, 282);
		getContentPane().add(Img);
		
		
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			AnalizadorS parser = null;
			public void actionPerformed(ActionEvent arg0) {
				Listas.Lrecursos.clear();
				Listas.Ltiempos.clear();
				String texto = "";
				if(tabbedPane.getSelectedIndex() == 0){
					texto = EditorS.getText();
				}else if(tabbedPane.getSelectedIndex() == 1){
					texto = EditorE.getText();
				}
				System.out.print("ENTRADA: " + texto + "\n");
				InputStream is = new ByteArrayInputStream(texto.getBytes());
				if(parser == null) parser = new AnalizadorS(is);  
	            else AnalizadorS.ReInit(is);
			    try{
			    	AnalizadorS.Inicio();
			    	System.out.println("Correcto");
			    }catch (Exception e){
			        System.out.println("NOK.");
			        System.out.println(e.getMessage());
			        AnalizadorS.ReInit(is);
			    }catch (Error e){
			        System.out.println("Oops.");
			        System.out.println(e.getMessage());
			    }
			    for (Recurso r : Listas.Lrecursos) {
					System.out.println("Guardado R" + r.id);
				}
			    
			    for (Tiempo t : Listas.Ltiempos) {
					System.out.println("Guardado T" + t.id);
					for (Proceso p : t.Lprocesos) {
						System.out.println("\tGuardado P" + p.id);
						for (Accion a : p.Lacciones) {
							if(a.id == 1){
								System.out.println("\t\tGuardado Asignar");
								for (int r : a.Lrecursos) {
									System.out.println("\t\t\tR" + r);
								}
							}else if(a.id == 2){
								System.out.println("\t\tGuardado Liberar");
								for (int r : a.Lrecursos) {
									System.out.println("\t\t\tR" + r);
								}
							}else if(a.id == 3){
								System.out.println("\t\tGuardado Terminar");
							}
							
						}
					}
				}
			    
			    //simular 
			    
			    if(Estrategia1.isSelected() == true){
			    	//asignacion de procesos
			    	AsignacionProcesos();
			    	
			    	
				}else if(Estrategia2.isSelected() == true){
					
				}else if(Estrategia3.isSelected() == true){
					
				}
			}	
			
		});
		btnEjecutar.setBounds(31, 357, 117, 25);
		getContentPane().add(btnEjecutar);
		
		JButton btnSimular = new JButton("Simular");
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Boton de simular
				int ntiempos = Listas.Ltiempos.size();
				if(ntiempos != pasar){
					pasar++;
				}
				//Paso de tiempos
		    	PasodeTiempos(1,pasar);
		    	ImageIcon imagen = new ImageIcon("/home/luis/Documentos/Graficas/e1.png");
		    	imagen.getImage().flush();
		    	Img.setIcon(imagen);
				
			}
		});
		btnSimular.setBounds(157, 357, 117, 25);
		getContentPane().add(btnSimular);
		
		
	}
}
