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
	
	
	public Registro registrarFinal(String patente) {
		Registro registro = null;
		for(Registro i : registros) {
			if(i.getPatente() == patente) {
				registros.remove(i);
				registro = i;
			}
		}
		this.notifyObservers(registros);
		return this.retornarRegistro(registro);
	}
	
	private Registro retornarRegistro(Registro registro) {
		if(registro != null) {
			return registro;
		} else {
			return null;
		}
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
