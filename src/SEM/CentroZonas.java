package SEM;

import java.util.ArrayList;

public class CentroZonas {
	protected ArrayList<String> zonas = new ArrayList<String>();
	
	protected static CentroZonas centro = null;
	
	protected CentroZonas() {}
	
	public static CentroZonas getCentro() {
		if(centro == null) {
			centro = new CentroZonas();
			return centro;
		}else {
			return centro;
		} 
	}
	
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
		for(String z : zonas) {
			if(z == zona) {
				return true;
			}
		}
		return false;
	}
	
	public void validarZona(String zona) throws Exception{
		if(!this.esZonaDeEstacionamiento(zona)) {
			throw new Exception("La zona en la que se intenta iniciar un estacionamiento no es parte del sistema");
		}
		
	}
}