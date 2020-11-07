package SEM;

import java.util.ArrayList;

public class CentroInfracciones extends Observable {
	protected ArrayList<Infraccion> infracciones = new ArrayList<Infraccion>();
	
	protected static CentroInfracciones centro = null;
	
	protected CentroInfracciones() {}
	
	public static CentroInfracciones getCentro() {
		if(centro == null) {
			centro = new CentroInfracciones();
			return centro;
		}else {
			return centro;
		} 
	}
	
	public void registrarInfraccion(Infraccion infraccion) {
		infracciones.add(infraccion);
		this.notifyObservers(infracciones);
	}
	
	// Devuelve true si eliminó la infracción de dicha patente; false en caso contrario.
	public boolean eliminarInfraccion(String patente) {
		for(Infraccion i : infracciones) {
			if(i.getPatente() == patente) {
				infracciones.remove(i);
				this.notifyObservers(infracciones);
				return true;
			}
		}
		return false;
	}
}