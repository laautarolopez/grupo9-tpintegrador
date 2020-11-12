package SEM;

import java.time.LocalDateTime;

public class Infraccion {
	private String patente;
	private LocalDateTime fechaYHora;
	private Inspector inspector;
	private String zona;
	
	public Infraccion(String patente, Inspector inspector) {
		this.patente = patente;
		this.fechaYHora = LocalDateTime.now();
		this.inspector = inspector;
		this.zona = inspector.getZona();
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public LocalDateTime getFechaYHora() {
		return this.fechaYHora;
	}
	
	public Inspector getInspector() {
		return this.inspector;
	}
	
	public String getZona() {
		return this.zona;
	}
}