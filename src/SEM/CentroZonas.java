package SEM;

import java.util.ArrayList;
import java.util.List;

public class CentroZonas {
	private List<String> zonas = new ArrayList<String>();
	
	public void agregarZona(String zona) {
		if(!this.esZonaDeEstacionamiento(zona)) {
			zonas.add(zona);
		}
	}
	
	public void eliminarZona(String zona) {
		if(this.esZonaDeEstacionamiento(zona)) {
			zonas.remove(zona);
		}
	}
	
	public boolean esZonaDeEstacionamiento(String zona) {
		return zonas.contains(zona);
	}
	
	public void validarZona(String zona) throws Exception{
		if(!this.esZonaDeEstacionamiento(zona)) {
			throw new Exception("La zona en la que se intenta iniciar un estacionamiento no es parte del sistema");
		}
		
	}
}