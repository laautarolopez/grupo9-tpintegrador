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
		ArrayList<RegistroDeEstacionamiento> aux = new ArrayList<RegistroDeEstacionamiento>();
		
		while(!registros.isEmpty() && registros.get(0).getPatente() != patente) {
			aux.add(registros.get(0));
			registros.remove(0);
		}
		
		if(!registros.isEmpty()) {
			RegistroDeEstacionamiento aFinalizar = registros.get(0);
			this.notifyObservers("Fin de: \n" + aFinalizar.toString());
			this.finalizar(aFinalizar);
			registros = aux;
		}else {
			registros.addAll(aux);
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
