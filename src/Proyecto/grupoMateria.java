package Proyecto;

public class grupoMateria implements Comparable<grupoMateria>{

		String ID_Materia;
		String Nombre_Materia;
		String Siglas;
		String Requisitos;
		String Grupo;
		int Dia;
		int Hora;
		String DiaSemana;
		
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
		
		public String getGrupo (){
			return this.Grupo;
		}
		
		public int getDia (){
			return this.Dia;
		}

		public int getHora (){
			return this.Hora;
		}
		
		public String getDiaSemana (){
			return this.DiaSemana;
		}
		
		public int compareTo(grupoMateria gP){
			if(this.Dia==gP.getDia()){
				Integer X = this.Hora;
				Integer Y = gP.getHora();
				return (X.compareTo(Y));
			}
			Integer A = Dia;
			Integer B = gP.getDia();
			return(A.compareTo(B));	
		}
		
		grupoMateria (){
			
		}
		
		grupoMateria(String ID_Materia, String Nombre_Materia, String Siglas, String Requisitos, String Grupo, String Dia, String Hora){
			this.ID_Materia = ID_Materia;
			this.Nombre_Materia = Nombre_Materia;
			this.Siglas = Siglas;
			this.Requisitos = Requisitos;
			this.Grupo = Grupo;
			this.Hora = Integer.parseInt(Hora);
			this.DiaSemana = Dia;
			if(Dia.equals("L")){
				this.Dia = 1;
			}
			if(Dia.equals("M")){
				this.Dia = 2;
			}
			if(Dia.equals("X")){
				this.Dia = 3;
			}
			if(Dia.equals("J")){
				this.Dia = 4;
			}
			if(Dia.equals("V")){
				this.Dia = 5;
			}	
		}
}
