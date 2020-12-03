package SEM;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Sistema {
	private int valorDeHora;
	private CentroCelulares centroCelulares;
	private CentroZonas centroZonas;
	private CentroInfracciones centroInfracciones;
	private CentroRegistros centroRegistros;
	
	public Sistema() {
		this.valorDeHora = 40;
		centroCelulares = new CentroCelulares();
		centroZonas = new CentroZonas();
		centroInfracciones = new CentroInfracciones();
		centroRegistros = new CentroRegistros();
	}
	
	// GENERAL
	public boolean esHoraDeEstacionamiento() {
		Clock clock = Clock.system(ZoneId.of("GMT-3"));
		return (LocalDateTime.now(clock).getHour() < 7) 
			   || (LocalDateTime.now(clock).getHour() >= 20);
	}
	
	public void setValorDeHora(int valor) {
		this.valorDeHora = valor;
	}
	
	public int getValorDeHora() {
		return this.valorDeHora;
	}
	
	// REGISTROS
	public void finalizarEstacionamientos() {
		if(!this.esHoraDeEstacionamiento()) {
			centroRegistros.finalizarTodos();
		}
	}
	
	public boolean estaVigente(String patente) {
		return centroRegistros.estaVigente(patente);
	}
	
	public void registrarInicio(RegistroDeEstacionamiento registro) {
		centroRegistros.registrarInicio(registro);
	}
	
	public void registrarFinal(String patente) {
		centroRegistros.registrarFinal(patente);
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
	
	public int getSaldo(String numero) {
		return centroCelulares.saldoDe(numero);
	}
	
	// ZONAS
	public void agregarZona(String zona) {
		centroZonas.agregarZona(zona);
	}
	
	public void eliminarZona(String zona) {
		centroZonas.eliminarZona(zona);
	}
	
	public boolean esZonaDeEstacionamiento(String zona) {
		return centroZonas.esZonaDeEstacionamiento(zona);
	}
}