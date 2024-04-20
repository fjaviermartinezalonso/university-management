package Proyecto;

public class Profesor extends Persona implements Comparable<Profesor>{

	String Departamento;
	String Horas_asignables;
	String Docencia_impartida;
	
	String getDepartamento (){
		return this.Departamento;
	}
	
	String getHoras_asignables (){
		return this.Horas_asignables;
	}
	
	String getDocencia_impartida (){
		return this.Docencia_impartida;
	}
	
	void setDocencia_impartida(String docencia){
		this.Docencia_impartida = docencia;
	}
	
	public int compareTo(Profesor p){
		String X;
		String Y;
		X = (this.Docencia_impartida).trim();
		X = X.replace(";", "");
		Y = (p.Docencia_impartida).trim();
		Y = Y.replace(";", "");
		String[] a = X.split("\\s");
		String[] b = Y.split("\\s");
		Integer A = a.length;
		Integer B = b.length;
		if(A==B){
			return(this.ID_Persona.compareTo(p.getID_Persona()));
		}
		else{
		return(A.compareTo(B));
		}
  	}
	
	Profesor(String ID_Persona, String Tipo, String Nombre, String Fecha_nacimiento, String Departamento, String Horas_asignables, String Docencia_impartida){
		this.ID_Persona = ID_Persona;
		this.Tipo = Tipo;
		this.Nombre = Nombre;
		this.Fecha_nacimiento = Fecha_nacimiento;
		this.Departamento = Departamento;
		this.Horas_asignables = Horas_asignables;
		this.Docencia_impartida = Docencia_impartida;
	}
	
}
