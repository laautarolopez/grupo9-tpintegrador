package SEM;

public class Sistema {
	private CentroCelulares centroCelulares;
	private CentroZonas centroZonas;
	private CentroInfracciones centroInfracciones;
	private CentroRegistros centroRegistros;
	
	public Sistema() {
		centroCelulares = new CentroCelulares();
		centroZonas = new CentroZonas();
		centroInfracciones = new CentroInfracciones();
		centroRegistros = new CentroRegistros();
	}
	
	// REGISTROS
	public void finalizarEstacionamientos() {
		centroRegistros.finalizarTodos();
	}
	
	public boolean estaVigente(String patente) {
		return centroRegistros.estaVigente(patente);
	}
	
	public void registrarInicio(RegistroDeEstacionamiento registro) {
		centroRegistros.registrarInicio(registro);
	}
	
	// INFRACCIONES
	public void registrarInfraccion(Infraccion infraccion) {
		centroInfracciones.registrarInfraccion(infraccion);
	}
	
	// CELULARES
	public void agregarSaldo(String numero, int monto) {
		centroCelulares.agregarSaldo(numero, monto);
	}
	
	public void restarSaldo(String numero, int monto) {
		centroCelulares.restarSaldo(numero, monto);
	}
	
	// ZONAS
	public void validarZona(String zona) throws Exception {
		centroZonas.validarZona(zona);
	}
}