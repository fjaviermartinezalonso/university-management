package Proyecto;

public class Aviso {
	
	String texto;
	String[] listaAvisos = {"Fecha de ingreso incorrecta.", "Nota media incorrecta.", "Número de horas incorrecto", "Profesor inexistente.", "Materia inexistente.", "Grupo inexistente.", "Grupo ya asignado.", "Se genera solape.", "Alumno inexistente.", "No cumple requisitos.", "Ya es alumno de la materia indicada.", "Alumno sin asignaciones.", "No hay profesores."};
	
	Aviso(int nAviso){
		this.texto = listaAvisos[nAviso];
	}
	
	Aviso(String texto){
		this.texto = texto;
	}
	
	public String gettexto(){
		return this.texto;
	}
	
}