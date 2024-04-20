package Proyecto;

import java.io.*;
import java.util.*;

public class Proyecto {

	/**Se encarga de verificar si al asignar el grupo de la materia indicada al profesor se generará solape.
	 * @param listaProfesor LinkedList que contiene los profesores del fichero "personas.txt".
	 * @param listaMateria LinkedList que contiene las materias del fichero "materias.txt".
	 * @param IDprofesor Identificador único de cada profesor dentro del sistema.
	 * @param IDmateria Identificador único de la materia de la base de datos.
	 * @param IDgrupo Identificador del grupo de la materia especificada.
	 * @return Retorna 1 si se genera solape. En otro caso, retorna un 0.
	 */
	public static int generaSolape(LinkedList<Persona> listaProfesor,
			LinkedList<Materia> listaMateria, String IDprofesor,
			String IDmateria, String IDgrupo) {
		Iterator<Persona> itListaP = listaProfesor.iterator();
		Iterator<Materia> itListaM = listaMateria.iterator();
		Iterator<Materia> itListaM2 = listaMateria.iterator();
		Persona p;
		Materia m;
		Grupo g;
		String Docencia_impartida;
		String Dgrupo = ""; // dia del grupo a asignar
		String Hgrupo = ""; // hora del grupo a asignar
		String Dgrupo2; // dia del grupo de la materia que se esta evaluando del profesor
		String Hgrupo2; // hora del grupo de la materia que se esta evaluando del profesor
		String matProfe;
		String grupProfe;
		String[] gruposYhoras;
		while (itListaM.hasNext()) {
			m = itListaM.next();
			if ((m.getID_Materia()).equals(IDmateria)) {
				Iterator<Grupo> itListaG = (m.getgrupoList()).iterator();
				while (itListaG.hasNext()) {
					g = itListaG.next();
					if ((g.getID_Grupo()).equals(IDgrupo)) {
						Dgrupo = g.getDia_Grupo();
						Hgrupo = g.getHora_Grupo();
					}
				}
			}
		}
		// Ahora iremos viendo los horarios concretos de cada asignatura para ver si coinciden con los del nuevo grupo
		while (itListaP.hasNext()) {
			p = itListaP.next();
			if ((p.getID_Persona()).equals(IDprofesor)) {
				Docencia_impartida = ((Profesor) p).getDocencia_impartida();
				Docencia_impartida = Docencia_impartida.trim();
				if (Docencia_impartida.length() == 0)
					continue;
				Docencia_impartida = Docencia_impartida.replace(";", "");
				gruposYhoras = Docencia_impartida.split("\\s");
				for (int i = 0; i < gruposYhoras.length; i++) {
					matProfe = gruposYhoras[i];
					i++;
					grupProfe = gruposYhoras[i];
					while (itListaM2.hasNext()) {
						m = itListaM2.next();
						if ((m.getID_Materia()).equals(matProfe)) {
							Iterator<Grupo> itListaG2 = (m.getgrupoList())
									.iterator();
							while (itListaG2.hasNext()) {
								g = itListaG2.next();
								if ((g.getID_Grupo()).equals(grupProfe)) {
									Dgrupo2 = g.getDia_Grupo();
									Hgrupo2 = g.getHora_Grupo();
									if (Dgrupo.equals(Dgrupo2)) {
										if (Hgrupo.equals(Hgrupo2))
											return 1;
										if ((Hgrupo
												.equals(Integer.toString(((Integer
														.parseInt(Hgrupo2)) + 1)))))
											return 1;
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}

	/**Comprueba que el grupo que se pretende asignar al profesor indicado no haya sido asignado todavia.
	 * @param lista  LinkedList que contiene las materias del sistema.
	 * @param IDmateria Identificador único de las materias.
	 * @param IDgrupo Identificador del grupo que pertenece a la materia indicada.
	 * @return 1 si el grupo ya ha sido asignado, 0 en otro caso.
	 */
	public static int grupoAsignado(LinkedList<Persona> lista,
			String IDmateria, String IDgrupo) {
		Iterator<Persona> itLista = lista.iterator();
		Persona p;
		String Docencia_impartida;
		String[] gruposYhoras;
		while (itLista.hasNext()) {
			p = itLista.next();
			Docencia_impartida = ((Profesor) p).getDocencia_impartida();
			Docencia_impartida = Docencia_impartida.trim();
			if (Docencia_impartida.length() == 0)
				continue;
			Docencia_impartida = Docencia_impartida.replace(";", "");
			gruposYhoras = Docencia_impartida.split("\\s");
			for (int i = 0; i < gruposYhoras.length; i++) {
				if (gruposYhoras[i].equals(IDmateria)) {
					if (gruposYhoras[i + 1].equals(IDgrupo)) {
						return 1;
					}
				}
				i++;
			}
		}
		return 0;
	}

	/**Verifica que exista la persona del que se posee el identificador.
	 * @param lista LinkedList que puede contener a los alumnos o a los profesores.
	 * @param ID Identificador de la persona a comprobar su existencia en el sistema.
	 * @return 1 en el caso de que exista la persona y 0 si no existe.
	 */
	public static int existePersona(LinkedList<Persona> lista, String ID) {
		Iterator<Persona> itLista = lista.iterator();
		Persona p;
		while (itLista.hasNext()) {
			p = itLista.next();
			if ((p.getID_Persona()).equals(ID))
				return 1;
		}
		return 0;
	}

	/**Verifica que exista la materia del que se posee el identificador.
	 * @param lista LinkedList que contiene las materias del sistema.
	 * @param ID Identificador único de las materias.
	 * @return 0 si la materia no existe; 1 si existe.
	 */
	public static int existeMateria(LinkedList<Materia> lista, String ID) {
		Iterator<Materia> itLista = lista.iterator();
		Materia m;
		while (itLista.hasNext()) {
			m = itLista.next();
			if ((m.getID_Materia()).equals(ID))
				return 1;
		}
		return 0;
	}

	/**Verifica que exista el grupo con el identificador correspondiente a la materia indicada.
	 * @param lista LinkedList que contiene las materias del sistema.
	 * @param IDmat Identificador único de las materias.
	 * @param IDgrupo Identificador del grupo que pertenece a la materia indicada.
	 * @return 0 si no existe el grupo; 1 si existe.
	 */
	public static int existeGrupoMateria(LinkedList<Materia> lista,
			String IDmat, String IDgrupo) {
		Iterator<Materia> itLista = lista.iterator();
		Materia m;
		while (itLista.hasNext()) {
			m = itLista.next();
			if ((m.getID_Materia()).equals(IDmat)) {
				LinkedList<Grupo> g = m.getgrupoList();
				Iterator<Grupo> itListag = g.iterator();
				Grupo t;
				while (itListag.hasNext()) {
					t = itListag.next();
					if ((t.getID_Grupo()).equals(IDgrupo))
						return 1;
				}
				return 0;
			}
		}
		return 0;
	}

	/**Busca un grupo concreto dentro de los posibles de una materia.
	 * @param lista LinkedList que contiene las materias del sistema.
	 * @param ID_Materia Identificador único de las materias.
	 * @param ID_Grupo Identificador del grupo que pertenece a la materia indicada.
	 * @return Un objeto del tipo "Grupo" correspondiente al grupo y materia indicados.
	 */
	public static Grupo buscaGrupo(LinkedList<Materia> lista,
			String ID_Materia, String ID_Grupo) {
		Iterator<Materia> itLista = lista.iterator();
		Materia m;
		Grupo t;
		while (itLista.hasNext()) {
			m = itLista.next();
			if ((m.getID_Materia()).equals(ID_Materia)) {
				LinkedList<Grupo> g = m.getgrupoList();
				Iterator<Grupo> itListag = g.iterator();
				while (itListag.hasNext()) {
					t = itListag.next();
					if ((t.getID_Grupo()).equals(ID_Grupo)) {
						return t;
					}
				}
			}
		}
		return t = new Grupo();
	}
	
	/**Busca una persona concreta de la base de datos.
	 * @param lista LinkedList que puede contener a los alumnos o a los profesores.
	 * @param ID Identificador único de la persona en el sistema.
	 * @return Un objeto del tipo "Persona" correspondiente a los criterios de búsqueda.
	 */
	public static Persona buscaPersona(LinkedList<Persona> lista, String ID) {
		Iterator<Persona> itLista = lista.iterator();
		Persona p;
		while (itLista.hasNext()) {
			p = itLista.next();
			if ((p.getID_Persona()).equals(ID))
				return p;
		}
		return p = new Persona();
	}

	/**Busca una materia concreta de la base de datos.
	 * @param lista LinkedList que contiene las materias del sistema.
	 * @param ID Identificador único de las materias.
	 * @return Un objeto del tipo "Materia" correspondiente a los criterios de búsqueda.
	 */
	public static Materia buscaMateria(LinkedList<Materia> lista, String ID) {
		Iterator<Materia> itLista = lista.iterator();
		Materia m;
		while (itLista.hasNext()) {
			m = itLista.next();
			if ((m.getID_Materia()).equals(ID))
				return m;
		}
		return m = new Materia();
	}

	/**Se encarga de comprobar si la fecha indicada es correcta o no.
	 * @param fecha Fecha a comprobar.
	 * @return 1 si el formato es correcto; 0 si no lo es.
	 */
	public static int fechaCorrecta(String fecha){
		String[] trozos = fecha.split("/|\\*");
		if(trozos[0].length() != 2) return 0;
		if(trozos[1].length() != 2) return 0;
		if(Integer.parseInt(trozos[1]) > 12) return 0;
		if(trozos[2].length() != 4) return 0;
		return 1;
	}
	
	
	/**Convierte a formato DATE para calcular la edad con métodos de CALENDAR.
	 * @param date Fecha en formato String a transformar a formato Date.
	 * @return Un objeto del tipo Date con la fecha indicada.
	 */
	public static Date crearFechas(String date) {
		String[] param = date.split("/|\\*");
		int dia = Integer.parseInt(param[0]);
		int mes = Integer.parseInt(param[1]);
		int anio = Integer.parseInt(param[2]);
		Calendar fecha = Calendar.getInstance();
		fecha.clear(); // establece valores a 0
		fecha.set(anio, mes - 1, dia);
		return fecha.getTime();
	}

	/**Calcula la edad numericamente.
	 * @param fechaNacimiento Objeto Date con la fecha de nacimiento del sujeto.
	 * @param fechaActual Objeto Date con la fecha actual.
	 * @return El valor de la edad del sujeto como un entero.
	 */
	public static int calcularEdad(Date fechaNacimiento, Date fechaActual) {
		Calendar fechaAct = Calendar.getInstance();
		fechaAct.setTime(fechaActual);

		Calendar fechaNac = Calendar.getInstance();
		fechaNac.setTime(fechaNacimiento);

		int dif_anios = fechaAct.get(Calendar.YEAR)
				- fechaNac.get(Calendar.YEAR);
		int dif_meses = fechaAct.get(Calendar.MONTH)
				- fechaNac.get(Calendar.MONTH);
		int dif_dias = fechaAct.get(Calendar.DAY_OF_MONTH)
				- fechaNac.get(Calendar.DAY_OF_MONTH);

		// Si está en ese año pero todavía no los ha cumplido.
		if (dif_meses < 0 || (dif_meses == 0 && dif_dias < 0))
			dif_anios--;
		return dif_anios;
	}

	/**Guarda los cambios de las listas de personas en el archivo de entrada personas.txt.
	 * @param f Archivo en el que se guardaran los cambios.
	 * @param listaA LinkedList de los alumnos.
	 * @param listaB LinkedList de los profesores.
	 */
	
	public static void escribeArchivo(File f, LinkedList<Persona> listaA, LinkedList<Persona> listaB) {
		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			Iterator<Persona> itListaA = listaA.iterator();
			Iterator<Persona> itListaB = listaB.iterator();
			if(itListaA.hasNext()) {
				Persona p = itListaA.next();
				wr.println(p.getID_Persona() + '\n' + p.getTipo() + '\n' + p.getNombre() + '\n' + p.getFecha_nacimiento() + '\n' + ((Alumno) p).getFecha_ingreso() + '\n' + ((Alumno) p).getMaterias_Superadas() + '\n' + ((Alumno) p).getNota_Expediente() + '\n' + ((Alumno) p).getDocencia_Recibida());
			}
			while (itListaA.hasNext()) {
				Persona p = itListaA.next();
				wr.println("*" + '\n' + p.getID_Persona() + '\n' + p.getTipo() + '\n' + p.getNombre() + '\n' + p.getFecha_nacimiento() + '\n' + ((Alumno) p).getFecha_ingreso() + '\n' + ((Alumno) p).getMaterias_Superadas() + '\n' + ((Alumno) p).getNota_Expediente() + '\n' + ((Alumno) p).getDocencia_Recibida());
			}
			while (itListaB.hasNext()) {
				Persona p = itListaB.next();
				wr.println("*" + '\n' + p.getID_Persona() + '\n' + p.getTipo() + '\n' + p.getNombre() + '\n' + p.getFecha_nacimiento() + '\n' + ((Profesor) p).getDepartamento() + '\n' + ((Profesor) p).getHoras_asignables() + '\n' + ((Profesor) p).getDocencia_impartida());
			}
			wr.close();
			bw.close();
		} catch (IOException e) {};
	}
	
	/**Guarda la lista de profesores ordenada por carga docente en el fichero indicado.
	 * @param f Fichero en el que se guardara la lista ordenada.
	 * @param listaB LinkedList de Profesor.
	 */
	public static void escribeSalida(File f, LinkedList<Profesor> listaB) {
		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			Iterator<Profesor> itListaB = listaB.iterator();
			if(itListaB.hasNext()) {
				Persona p = itListaB.next();
				wr.println(p.getID_Persona() + '\n' + p.getTipo()+ '\n' + p.getNombre() + '\n' + p.getFecha_nacimiento()+ '\n' + ((Profesor) p).getDepartamento() + '\n'+ ((Profesor) p).getHoras_asignables() + '\n'+ ((Profesor) p).getDocencia_impartida());
			}
			while (itListaB.hasNext()) {
				Persona p = itListaB.next();
				wr.println("*" + '\n' + p.getID_Persona() + '\n' + p.getTipo()+ '\n' + p.getNombre() + '\n' + p.getFecha_nacimiento()+ '\n' + ((Profesor) p).getDepartamento() + '\n'+ ((Profesor) p).getHoras_asignables() + '\n'+ ((Profesor) p).getDocencia_impartida());
			}
			wr.close();
			bw.close();
		} catch (IOException e) {}
	}

	/**Guarda en el archivo indicado la lista de materias de un alumno ordenadas cronologicamente.
	 * @param f Fichero en el que se guardara el horario.
	 * @param listaB LinkedList que contiene los grupos en los que esta matriculado el alumno.
	 */
	public static void escribeCalendario(File f, LinkedList<grupoMateria> listaB) {
		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			Iterator<grupoMateria> itListaB = listaB.iterator();
			if(itListaB.hasNext()) {
				grupoMateria p = itListaB.next();
				wr.println(p.getID_Materia() + '\n' + p.getNombre_Materia()+ '\n' + p.getSiglas() + '\n' + p.getDiaSemana() + " " + p.getHora());
			}
			while (itListaB.hasNext()) {
				grupoMateria p = itListaB.next();
				wr.println("*" + '\n' + p.getID_Materia() + '\n' + p.getNombre_Materia()+ '\n' + p.getSiglas() + '\n' + p.getDiaSemana() + " " + p.getHora());
			}
			wr.close();
			bw.close();
		} catch (IOException e) {}
	}
	
	/**Encuentra el identificador mas alto de todos las personas de la base de datos.
	 * @param listaA LinkedList de Persona.
	 * @param listaB LinkedList de Persona.
	 * @return El valor del identificador mas alto en formato String.
	 */
	public static String buscarIDmasAlto(LinkedList<Persona> listaA, LinkedList<Persona> listaB) {
		String ID1 = "0";
		String ID2 = "0";
		Iterator<Persona> itListaA = listaA.iterator();
		Iterator<Persona> itListaB = listaB.iterator();
		while (itListaA.hasNext()) {
			Persona p = itListaA.next();
			if (Integer.parseInt(ID1) < (Integer.parseInt(p.getID_Persona()))) {
				ID1 = p.getID_Persona();
			}
		}
		while (itListaB.hasNext()) {
			Persona p = itListaB.next();
			if (Integer.parseInt(ID2) < (Integer.parseInt(p.getID_Persona()))) {
				ID2 = p.getID_Persona();
			}
		}
		if (Integer.parseInt(ID1) < Integer.parseInt(ID2))
			return ID2;
		else
			return ID1;
	}

	/**Actualiza la lista de avisos si ya existia, y si no la crea.
	 * @param f Fichero en el que se imprimiran los avisos del sistema.
	 * @param lista LinkedList de Aviso.
	 */
	public static void escribeAvisos(File f, LinkedList<Aviso> lista) {
		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			Iterator<Aviso> itLista = lista.iterator();
			while (itLista.hasNext()) {
				Aviso a = itLista.next();
				wr.println(a.gettexto());
			}
			wr.close();
			bw.close();
		} catch (IOException e) {
		}
		;
	}

	/**
	 * @param alumnoList LinkedList que contiene los alumnos.
	 * @param profesorList LinkedList que contiene los profesores.
	 * @param materiaList LinkedList que contiene las materias.
	 * @param avisoList LinkedList a la que se le van añadiendo los avisos para imprimirlos al final del programa.
	 * @param personas Referencia al archivo del que se leerán los datos del personal.
	 * @param materias Referencia al archivo del que se extraerán las materias.
	 * @param avisos Referencia al archivo en el que escriben los avisos generados.
	 * @param ID_siguiente Identificador (String) siguiente a asignar al insertar una nueva persona en el sistema.
	 * @param ID_Persona Identificador (String) único para cada persona del sistema.
	 * @param Tipo Indica si es alumno o profesor (String)
	 * @param Nombre (String) Nombre de la persona.
	 * @param Fecha_nacimiento (String) Fecha de nacimiento de la persona.
	 * @param Fecha_ingreso (String) Fecha de ingreso del Alumno.
	 * @param Materias_Superadas (String) Materias superadas del Alumno.
	 * @param Nota_Expediente (String) Nota del expediente del Alumno.
	 * @param Docencia_Recibida (String) Clases recibidas por el Alumno.
	 * @param Departamente (String) Departamento al que pertenece el Profesor.
	 * @param Horas_asignables (String) Número de horas posibles asignables al Profesor.
	 * @param Docencia_impartida (String) Clases impartidas por el Profesor.
	 * @param ID_Materia (String) Identificador único de la Materia en el sistema.
	 * @param Nombre_Materia (String) Nombre de la Materia.
	 * @param Siglas (String) Siglas de la Materia.
	 * @param Requisitos (String) Requisitos necesarios para cursar la Materia.
	 * @param Grupos (String) Grupos que posee la Materia.
	 * @param texto (String) Variable usada para escanear la lista de avisos que se proporcione.
	 * @param ID_Grupo (String) Identificador del Grupo de una Materia.
	 */
	public static void main(String[] args) throws IOException {

		LinkedList<Persona> alumnoList = new LinkedList<Persona>();
		LinkedList<Persona> profesorList = new LinkedList<Persona>();
		LinkedList<Materia> materiaList = new LinkedList<Materia>();
		LinkedList<Aviso> avisoList = new LinkedList<Aviso>();
		File personas = new File("personas.txt");
		File materias = new File("materias.txt");
		File avisos = new File("avisos.txt");

		String ID_siguiente; // Variable para la opcion 1
		String ID_Persona; // Variables de Persona
		String Tipo;
		String Nombre;
		String Fecha_nacimiento;
		String Fecha_ingreso; // Variables de Alumno
		String Materias_Superadas;
		String Nota_Expediente;
		String Docencia_Recibida;
		String Departamento; // Variables de Profesor
		String Horas_asignables;
		String Docencia_impartida;
		String ID_Materia; // Variables de Materia
		String Nombre_Materia;
		String Siglas;
		String Requisitos;
		String Grupos;
		String texto;
		String ID_Grupo;

		// LECTURA DE personas.txt Y CLASIFICACION Profesor - Alumno
		try {
			Scanner s = new Scanner(personas);
			while (s.hasNextLine()) {
				ID_Persona = s.nextLine();
				Tipo = s.nextLine();
				Nombre = s.nextLine();
				Fecha_nacimiento = s.nextLine();
				if (Tipo.equalsIgnoreCase("alumno")) {
					Fecha_ingreso = s.nextLine();
					Materias_Superadas = s.nextLine();
					Nota_Expediente = s.nextLine();
					try{
						Docencia_Recibida = s.nextLine();
					}catch(Exception e){
						Docencia_Recibida = new String();
					}
					alumnoList.add(new Alumno(ID_Persona, Tipo, Nombre,
							Fecha_nacimiento, Fecha_ingreso,
							Materias_Superadas, Nota_Expediente,
							Docencia_Recibida));
					if(s.hasNextLine()) s.nextLine();
				} else {
					Departamento = s.nextLine();
					Horas_asignables = s.nextLine();
					try{
						Docencia_impartida = s.nextLine();
					}catch(Exception e){
						Docencia_impartida = new String();
					}
					profesorList.add(new Profesor(ID_Persona, Tipo, Nombre,
							Fecha_nacimiento, Departamento, Horas_asignables,
							Docencia_impartida));
					if(s.hasNextLine()) s.nextLine();
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// LECTURA DE materias.txt
		try {
			Scanner s = new Scanner(materias);
			while (s.hasNextLine()) {
				ID_Materia = s.nextLine();
				Nombre_Materia = s.nextLine();
				Siglas = s.nextLine();
				try{
					Requisitos = s.nextLine();
				}catch(Exception e){
					Requisitos = new String();
				}
				Grupos = s.nextLine();
				materiaList.add(new Materia(ID_Materia, Nombre_Materia, Siglas,
						Requisitos, Grupos));
				if(s.hasNextLine()) s.nextLine();
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// LECTURA DE avisos.txt
		try {
			Scanner s = new Scanner(avisos);
			while (s.hasNextLine()) {
				texto = s.nextLine();
				avisoList.add(new Aviso(texto));
			}
			s.close();
		} catch (FileNotFoundException e) {}
		ID_siguiente = Integer.toString(Integer.parseInt(buscarIDmasAlto(alumnoList, profesorList)) + 1);
		File ejecucion = new File("ejecucion.txt");
		try {
			Scanner s = new Scanner(ejecucion);
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				String linea2 = linea; // para obtener simplemente que orden es
				String[] palabrejos = linea2.split("\\s");
				String orden = palabrejos[0];
				if ("InsertaPersona".equals(orden)) {
					try{
						String[] corta = linea.split("\"");
						String[] corta1 = corta[0].split("\\s");
						corta[2] = corta[2].trim();
						String[] corta2 = corta[2].split("\\s");
						ID_Persona = ID_siguiente;
						ID_siguiente = Integer.toString(Integer.parseInt(ID_siguiente) + 1);
						Nombre = corta[1];
						Fecha_nacimiento = corta2[0];
						if(fechaCorrecta(Fecha_nacimiento) == 0) {
							avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
							continue;
						}
						Tipo = corta1[1];
						if (Tipo.equals("alumno")) {
							Fecha_ingreso = corta2[1];
							if(fechaCorrecta(Fecha_ingreso) == 0) {
								avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
								continue;
							}
							if ((calcularEdad(crearFechas(Fecha_nacimiento), crearFechas(Fecha_ingreso))) < 15 || (calcularEdad(crearFechas(Fecha_nacimiento), crearFechas(Fecha_ingreso))) > 65) {
								avisoList.add(new Aviso(0));
								continue;
							}
							Materias_Superadas = new String();
							Nota_Expediente = corta2[2];
							if ((Float.parseFloat(Nota_Expediente)) < 0 || (Float.parseFloat(Nota_Expediente)) > 10) {
								avisoList.add(new Aviso(1));
								continue;
							}
							Docencia_Recibida = new String();
							alumnoList.add(new Alumno(ID_Persona, Tipo, Nombre,
									Fecha_nacimiento, Fecha_ingreso,
									Materias_Superadas, Nota_Expediente,
									Docencia_Recibida));
						}
						if (Tipo.equals("profesor")) {
							Departamento = corta[3];
							corta[4] = corta[4].trim();
							Horas_asignables = corta[4];
							if ((Integer.parseInt(Horas_asignables)) < 0 || (Integer.parseInt(Horas_asignables)) > 20) {
								avisoList.add(new Aviso(2));
								continue;
							}
							Docencia_impartida = new String();
							profesorList.add(new Profesor(ID_Persona, Tipo, Nombre,
									Fecha_nacimiento, Departamento,
									Horas_asignables, Docencia_impartida));
						}
						escribeArchivo(personas, alumnoList, profesorList);
						continue;
					}catch(Exception e){
						avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
						continue;
					}
				}
				if ("Asigna".equals(orden)) {
					try{
						String[] subOrdenes = linea.split("\\s");
						ID_Persona = subOrdenes[1];
						ID_Materia = subOrdenes[2];
						ID_Grupo = subOrdenes[3];

					// COMPROBACIONES
						if (existePersona(profesorList, ID_Persona) == 0) {
							avisoList.add(new Aviso(3));
							if (existeMateria(materiaList, ID_Materia) == 0) {
								avisoList.add(new Aviso(4));
								continue;
							} else {
								if (existeGrupoMateria(materiaList, ID_Materia,
										ID_Grupo) == 0) {
									avisoList.add(new Aviso(5));
									continue;
								} else
									continue;
							}
						}
						if (existeMateria(materiaList, ID_Materia) == 0) {
							avisoList.add(new Aviso(4));
							continue;
						}
						if (existeGrupoMateria(materiaList, ID_Materia, ID_Grupo) == 0) {
							avisoList.add(new Aviso(5));
							continue;
						}
						if (grupoAsignado(profesorList, ID_Materia, ID_Grupo) == 1) {
							avisoList.add(new Aviso(6));
							continue;
						}
						if (generaSolape(profesorList, materiaList, ID_Persona,
								ID_Materia, ID_Grupo) == 1) {
							avisoList.add(new Aviso(7));
							continue;
						}
						Persona p = buscaPersona(profesorList, ID_Persona);
						Docencia_impartida = ((Profesor) p).getDocencia_impartida();
						Docencia_impartida = Docencia_impartida.trim();
						if (Docencia_impartida.length() == 0)
							((Profesor) p).setDocencia_impartida(ID_Materia + " " + ID_Grupo); // no tenia carga docente
						if (Docencia_impartida.length() > 0)
							((Profesor) p).setDocencia_impartida(Docencia_impartida + "; " + ID_Materia + " " + ID_Grupo);
						escribeArchivo(personas, alumnoList, profesorList);
						continue;
					}catch(Exception e){
						avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
						continue;
					}
				}
				if ("Matricula".equals(orden)) {
					try{
						String[] corta = linea.split("\\s");
						ID_Persona = corta[1];
						ID_Materia = corta[2];
						ID_Grupo = corta[3];
						int cumpleR = 0;
						String daClase = "";
						if (existePersona(alumnoList, ID_Persona) == 0) {
							avisoList.add(new Aviso(8));
							if (existeMateria(materiaList, ID_Materia) == 0) {
								avisoList.add(new Aviso(4));
								continue;
							} else {
								if (existeGrupoMateria(materiaList, ID_Materia, ID_Grupo) == 0) {
									avisoList.add(new Aviso(5));
									continue;
								} else
									continue;
							}
						}
						if (existeMateria(materiaList, ID_Materia) == 0) {
							avisoList.add(new Aviso(4));
							continue;
						} else {
							if (existeGrupoMateria(materiaList, ID_Materia, ID_Grupo) == 0) {
								avisoList.add(new Aviso(5));
								continue;
							}
						}
						Materia M = buscaMateria(materiaList, ID_Materia);
						Requisitos = M.getRequisitos();
						Requisitos = Requisitos.trim();
						if (Requisitos.length() == 0) cumpleR = 1;
						if (Requisitos.length() > 0) { // si existen requisitos
							Requisitos = Requisitos.replace(",", "");
							String[] requisitos = Requisitos.split("\\s");
							Persona p = buscaPersona(alumnoList, ID_Persona);
							Materias_Superadas = ((Alumno) p).getMaterias_Superadas();
							Materias_Superadas = Materias_Superadas.trim();
							if (Materias_Superadas.length() > 0) {
								Materias_Superadas = Materias_Superadas.replace(",", "");
								String[] msuperadas = Materias_Superadas.split("\\s");
								int crequisitos = 0;
								for (int x = 0; x < requisitos.length; x++) {
									for (int y = 0; y < msuperadas.length; y++) {
										if ((requisitos[x].equals(msuperadas[y])))
											crequisitos++;
										
									}
								}
								if (crequisitos != requisitos.length) {
									avisoList.add(new Aviso(9));
									continue;
								} else {
									cumpleR = 1;
								}
							}
						}
						if (cumpleR == 1) {
							Persona a = buscaPersona(alumnoList, ID_Persona);
							String docencia = ((Alumno) a).getDocencia_Recibida();
							docencia = docencia.trim();
							if (docencia.length() > 0) {
								docencia = docencia.replace(";", "");
								String[] d = docencia.split("\\s");
								for (int i = 0; i < d.length; i++) {
									if (d[i].equals(ID_Materia)) {
										avisoList.add(new Aviso(10));
										daClase = "1";
										break;
									}
									i++;
								}
							}
							if (!(daClase.equals("1"))) {
								Persona p = buscaPersona(alumnoList, ID_Persona);
								Docencia_Recibida = ((Alumno) p)
										.getDocencia_Recibida();
								Docencia_Recibida = Docencia_Recibida.trim();
								if (Docencia_Recibida.length() == 0)
									((Alumno) p).setDocencia_Recibida(ID_Materia
											+ " " + ID_Grupo);
								if (Docencia_Recibida.length() > 0)
									((Alumno) p)
									.setDocencia_Recibida(Docencia_Recibida
											+ "; " + ID_Materia + " "
											+ ID_Grupo);
								escribeArchivo(personas, alumnoList, profesorList);
							}else{
								continue;
							}
						}else{
							avisoList.add(new Aviso(9));
							continue;
						}
						continue;
					}catch(Exception e){
						avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
						continue;
					}
				}

				if ("Evalua".equals(orden)) {
					try{				
						String[] corta = linea.split("\\s");
						ID_Materia = corta[1];
						String fichero = corta[2];
						if (existeMateria(materiaList, ID_Materia) == 0) {
							avisoList.add(new Aviso(4));
							continue;
						} else {
							File notas = new File(fichero);
							try {
								Scanner n = new Scanner(notas);
								while (n.hasNextLine()) {
									String personaynota = n.nextLine();
									String[] pyn = personaynota.split("\\s");
									ID_Persona = pyn[0];
									float nota = Float.parseFloat(pyn[1]);
									if (existePersona(alumnoList, ID_Persona) == 0) continue;
									Persona p = buscaPersona(alumnoList, ID_Persona);
									Docencia_Recibida = ((Alumno) p).getDocencia_Recibida();
									Docencia_Recibida = Docencia_Recibida.trim();
									if(Docencia_Recibida.length() == 0) continue;
									Docencia_Recibida = Docencia_Recibida.replace(";", "");
									String[]campos = Docencia_Recibida.split("\\s");
									Docencia_Recibida = "";
									for(int i=0; i<campos.length; i++){
										if(campos[i].equals(ID_Materia)){
											i++;
										}else{
											if(Docencia_Recibida.length()==0){
												Docencia_Recibida = Docencia_Recibida + campos[i] + " " + campos[i+1];
												i++;
											}else{
												Docencia_Recibida = Docencia_Recibida + "," + campos[i] + " " + campos[i+1];
												i++;
											}
										}
									}
									((Alumno)p).setDocencia_Recibida(Docencia_Recibida);
									if (nota >= 5) {
										Materias_Superadas = ((Alumno) p).getMaterias_Superadas();
										Materias_Superadas = Materias_Superadas.trim();
										if (Materias_Superadas.length() == 0)
											((Alumno) p).setMaterias_Superadas(ID_Materia);
										if (Materias_Superadas.length() > 0)
											((Alumno) p).setMaterias_Superadas(Materias_Superadas + ", " + ID_Materia);
										Nota_Expediente = ((Alumno) p).getNota_Expediente();
										float nota_expediente = Float.parseFloat(Nota_Expediente);
										Materias_Superadas = Materias_Superadas.replace(",", "");
										String[] Materias = Materias_Superadas.split("\\s");
										int nmaterias = Materias.length;
										String nuevanota = Float.toString(((nmaterias * nota_expediente) + nota) / (nmaterias + 1));
										((Alumno) p).setNota_Expediente(nuevanota);
									}
								}
								n.close();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
						escribeArchivo(personas, alumnoList, profesorList);
						continue;
					}catch(Exception e){
						avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
						continue;
					}
				}

				if ("ObtenCalendario".equals(orden)) {
					try{
						String[] corta = linea.split("\\s");
						ID_Persona = corta[1];
						File fichero = new File(corta[2]);
						if (existePersona(alumnoList, ID_Persona) == 0) {
							avisoList.add(new Aviso(8));
							continue;
						}
						Persona p = buscaPersona(alumnoList, ID_Persona);
						Docencia_Recibida = ((Alumno) p).getDocencia_Recibida();
						if(((Docencia_Recibida.trim()).length())==0){
							avisoList.add(new Aviso(11));
							continue;
						}
						Docencia_Recibida = Docencia_Recibida.replace(";", "");
						String[] matYgrupo = Docencia_Recibida.split("\\s");
						LinkedList<grupoMateria> gP = new LinkedList<grupoMateria>();
						String Grupo;
						String Dia;
						String Hora;
						for(int i=0; i < matYgrupo.length; i++){
							ID_Materia = matYgrupo[i];
							i++;
							Grupo = matYgrupo[i];
							Materia m = buscaMateria(materiaList,ID_Materia);
							Siglas = m.getSiglas();
							Nombre_Materia = m.getNombre_Materia();
							Requisitos = m.getRequisitos();
							Grupo g = buscaGrupo(materiaList, ID_Materia, Grupo);
							Dia = g.getDia_Grupo();
							Hora = g.getHora_Grupo();
							gP.add(new grupoMateria(ID_Materia, Nombre_Materia, Siglas, Requisitos, Grupo, Dia, Hora));
						}
						Collections.sort(gP);
						escribeCalendario(fichero, gP);
						continue;
					}catch(Exception e){
						avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
						continue;
					}
				}
				
				if ("OrdenaProfesoresPorCargaDocente".equals(orden)) {
					try{
						String[] corta = linea.split("\\s");
						File fichero = new File(corta[1]);
						if (profesorList.size() == 0){
							avisoList.add(new Aviso(12));
							continue;
						}	
						LinkedList<Profesor> p2 = new LinkedList<Profesor>();
						Iterator<Persona> itp = profesorList.iterator();
						while (itp.hasNext()) {
							Persona p = itp.next();
							ID_Persona = p.getID_Persona();
							Tipo = p.getTipo();
							Nombre = p.getNombre();
							Fecha_nacimiento = p.getFecha_nacimiento();
							Departamento = ((Profesor) p).getDepartamento();
							Horas_asignables = ((Profesor) p).getHoras_asignables();
							Docencia_impartida = ((Profesor) p).getDocencia_impartida();
							p2.add(new Profesor(ID_Persona, Tipo, Nombre, Fecha_nacimiento, Departamento, Horas_asignables, Docencia_impartida));
						}
						Collections.sort(p2);
						escribeSalida(fichero, p2);	
						continue;
					}catch(Exception e){
						avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
						continue;
					}
				}
				avisoList.add(new Aviso("Comando incorrecto:<" + linea  + ">"));
			}
			escribeAvisos(avisos, avisoList); // ANTES DE CERRAR EL PROGRAMA GUARDA LOS AVISOS EN EL TXT
			s.close();
		} catch (FileNotFoundException e) {System.out.println("No se ha encontrado un archivo que ejecutar");}
	}
}