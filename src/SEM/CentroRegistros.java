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
		while(!registros.isEmpty()) {
			this.finalizar(registros.get(0));
		}
	}

	public void registrarFinal(String patente) {
		for(int i = 0; i < registros.size(); i++) {
			if(registros.get(i).getPatente().equals(patente)) {
				this.notifyObservers("Fin de: \n" + registros.get(i).toString());
				this.finalizar(registros.get(i));
				i--;
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