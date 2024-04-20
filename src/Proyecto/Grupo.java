package Proyecto;

public class Grupo {
	
	String ID_Grupo;
	String Dia_Grupo;
	String Hora_Grupo;
	
	public String getID_Grupo(){
		return this.ID_Grupo;
	}	
	
	public String getDia_Grupo(){
		return this.Dia_Grupo;
	}
		
	public String getHora_Grupo(){
		return this.Hora_Grupo;
	}
	
	Grupo(){
		
	}
	
	Grupo(String ID_Grupo, String Dia_Grupo, String Hora_Grupo){
		this.ID_Grupo = ID_Grupo;
		this.Dia_Grupo = Dia_Grupo;
		this.Hora_Grupo = Hora_Grupo;
	}
	
}
