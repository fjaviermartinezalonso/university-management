package Proyecto;

import java.util.*;

public class Materia {

	String ID_Materia;
	String Nombre_Materia;
	String Siglas;
	String Requisitos;
	String Grupos;
	LinkedList<Grupo> grupoList;
	
	public String getID_Materia (){
		return this.ID_Materia;
	}
	
	public String getNombre_Materia (){
		return this.Nombre_Materia;
	}
	
	public String getSiglas (){
		return this.Siglas;
	}
	
	public String getRequisitos (){
		return this.Requisitos;
	}
	
	public String getGrupos (){
		return this.Grupos;
	}
	
	public LinkedList<Grupo> getgrupoList (){
		return this.grupoList;
	}
	
	/**Rellena la lista de la materia con objetos del tipo Grupo que le corresponden.
	 * @param grupos String que contiene la informacion de los grupos que pertenecen a esta materia.
	 * @return Una LinkedList de Grupo pertenecientes a esta materia.
	 */
	public LinkedList<Grupo> generaListaGrupos(String grupos){
		LinkedList<Grupo> grupoList = new LinkedList<Grupo>();
		String[] simbolos;	
		grupos = grupos.replace(";","");
		simbolos = grupos.split(" ");
		String ID_Grupo;
		String Dia_Grupo;
		String Hora_Grupo;	
		for(int i = 0 ; i < simbolos.length; i++) {			
			ID_Grupo = simbolos[i]; i++;
			Dia_Grupo = simbolos[i]; i++;
			Hora_Grupo = simbolos[i];
			grupoList.add(new Grupo(ID_Grupo, Dia_Grupo, Hora_Grupo));
		}
		return grupoList;
	}
	
	Materia (){
		
	}
	
	Materia(String ID_Materia, String Nombre_Materia, String Siglas, String Requisitos, String Grupos){
		this.ID_Materia = ID_Materia;
		this.Nombre_Materia = Nombre_Materia;
		this.Siglas = Siglas;
		this.Requisitos = Requisitos;
		this.Grupos = Grupos;
		this.grupoList = generaListaGrupos(this.Grupos);
	}
	
}
