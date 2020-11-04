package SEM;

import java.util.ArrayList;

public class CentroRegistros/*TODO Observable*/ {
	private ArrayList<Registro> registros = new ArrayList<Registro>();
	
	private static CentroRegistros centro = null;
	
	private CentroRegistros() {}
	
	public static CentroRegistros getCentro() {
		if(centro == null) {
			centro = new CentroRegistros();
			return centro;
		}else {
			return centro;
		}
	}
	
	public void registrarInicio(Registro registro) {
		registros.add(registro);
	//TODO notificar observers de inicio
	}
	
	
	public void registrarFinal(String patente) {
		for(Registro i : registros) {
			if(i.getPatente() == patente) {
				registros.remove(i);
			}
		}
	//TODO notificar observers de salida
	}
	
	public boolean estaVigente(String patente) {
		for(Registro i : registros) {
			if(i.getPatente() == patente) {
				return i.estaVigente();
			}
		}
		return false;
	}
}
