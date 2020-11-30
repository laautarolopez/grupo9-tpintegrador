package SEM;

public class AplicacionInspector {
	private Sistema sistema;
	private Inspector inspector;
	
	public AplicacionInspector(Sistema sistema, Inspector inspector) {
		this.sistema = sistema;
		this.inspector = inspector;
	}
	
	public boolean estaVigente(String patente) {
		return sistema.estaVigente(patente);
	}
	
	public void altaDeInfraccion(String patente) {
		if (!this.estaVigente(patente)) {
			Infraccion infraccion = new Infraccion(patente, inspector);
			sistema.registrarInfraccion(infraccion);
		}
	}
}