package Proyecto;

public class Alumno extends Persona {

	String Fecha_ingreso;
	String Materias_Superadas;
	String Nota_Expediente;
	String Docencia_Recibida;
	
	String getFecha_ingreso (){
		return this.Fecha_ingreso;
	}
	
	String getMaterias_Superadas (){
		return this.Materias_Superadas;
	}
	
	String getNota_Expediente (){
		return this.Nota_Expediente;
	}
	
	String getDocencia_Recibida (){
		return this.Docencia_Recibida;
	}
	
	void setMaterias_Superadas(String materias){
		this.Materias_Superadas = materias;
	}
	
	void setNota_Expediente(String nota){
		this.Nota_Expediente = nota;
	}
	
	void setDocencia_Recibida(String docencia){
		this.Docencia_Recibida = docencia;
	}
	
	Alumno(String ID_Persona, String Tipo, String Nombre, String Fecha_nacimiento, String Fecha_ingreso, String Materias_Superadas, String Nota_Expediente, String Docencia_Recibida){
		this.ID_Persona = ID_Persona;
		this.Tipo = Tipo;
		this.Nombre = Nombre;
		this.Fecha_nacimiento = Fecha_nacimiento;
		this.Fecha_ingreso = Fecha_ingreso;
		this.Materias_Superadas = Materias_Superadas;
		this.Nota_Expediente = Nota_Expediente;
		this.Docencia_Recibida = Docencia_Recibida;
	}
	
}
