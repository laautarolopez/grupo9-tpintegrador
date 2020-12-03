package SEM;

import java.util.ArrayList;
import java.util.List;

public class CentroInfracciones {
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	
	public void registrarInfraccion(Infraccion infraccion) {
		infracciones.add(infraccion);
	}
	
	public boolean contieneInfraccion(String patente) {
		for(Infraccion i : infracciones) {
			if(i.getPatente().equals(patente)) {
				return true;
			}
		}
		return false;
	}
}