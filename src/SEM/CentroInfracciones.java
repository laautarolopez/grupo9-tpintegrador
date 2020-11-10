package SEM;

import java.util.ArrayList;

public class CentroInfracciones {
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
	}
}