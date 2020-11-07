package SEM;

public class AplicacionInspector {
	private CentroRegistros centroRegistros = CentroRegistros.getCentro();
	private CentroInfracciones centroInfracciones = CentroInfracciones.getCentro();
	private Inspector inspector;
	
	public AplicacionInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	public boolean estaVigente(String patente) {
		return centroRegistros.estaVigente(patente);
	}
	
	public void altaDeInfraccion(String patente) {
		if (!this.estaVigente(patente)) {
			Infraccion infraccion = new Infraccion(patente, inspector);
			centroInfracciones.registrarInfraccion(infraccion);
		}
	}
}