package SEM;

public class Zona {
	private String nombre;
	
	public Zona(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public boolean esZonaDeEstacionamiento() {
		return false;
	}
}