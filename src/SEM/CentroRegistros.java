package SEM;

import java.util.ArrayList;
import java.util.List;

public class CentroRegistros extends Observable {
	private List<RegistroDeEstacionamiento> registros = new ArrayList<RegistroDeEstacionamiento>();
	
	public void registrarInicio(RegistroDeEstacionamiento registro) {
		registros.add(registro);
		this.notifyObservers("Inicio de: \n" + registro.toString());
	}
	
	public void finalizarTodos() {
		for(RegistroDeEstacionamiento i : registros) {
			this.finalizar(i);
		}
	}
	
	public void registrarFinal(String patente) {
		for(RegistroDeEstacionamiento i : registros) {
			if(i.getPatente().equals(patente)) {
				this.finalizar(i);
				this.notifyObservers("Fin de: \n" + i.toString());
			}
		}
	}
	
	private void finalizar(RegistroDeEstacionamiento registro) {
		registro.finalizar();
		registros.remove(registro);
	}
	
	public boolean estaVigente(String patente) {
		for(RegistroDeEstacionamiento i : registros) {
			if(i.getPatente().equals(patente)) {
				return i.estaVigente();
			}
		}
		return false;
	}
}