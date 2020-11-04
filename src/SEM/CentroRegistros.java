package SEM;

import java.util.ArrayList;

public class CentroRegistros extends Observable {
	protected ArrayList<Registro> registros = new ArrayList<Registro>();
	
	protected static CentroRegistros centro = null;
	
	protected CentroRegistros() {}
	
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
		this.notifyObservers(registros);
	}
	
	
	public void registrarFinal(String patente) {
		for(Registro i : registros) {
			if(i.getPatente() == patente) {
				registros.remove(i);
			}
		}
		this.notifyObservers(registros);
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
