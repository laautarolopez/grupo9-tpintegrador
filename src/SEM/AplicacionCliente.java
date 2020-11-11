package SEM;

public class AplicacionCliente implements GeneradorDeRegistros {
	private CentroRegistros centroRegistros = CentroRegistros.getCentro();
	private CentroCelulares centroCelulares = CentroCelulares.getCentroCelulares();
	private Celular celular;
	private String patente;
	private Modo modo;
	
	public AplicacionCliente(Celular celular) {
		this.celular = celular;
	}
	
	public void iniciarEstacionamiento(String patente, int horas) {
		if(this.getSaldoActual() >= GeneradorDeRegistros.valorDeHora) {
			this.patente = patente;
			RegistroAplicacion registro = new RegistroAplicacion(patente, celular.getZona(),
																 celular);
			centroRegistros.registrarInicio(registro);
			this.enviarRespuestaDeInicio(registro);
		} else {
			System.out.print("Saldo insuficiente. Estacionamiento no permitido.");
		}
	}
	
	private void enviarRespuestaDeInicio(RegistroAplicacion registro) {
		System.out.print("Hora de inicio: " + registro.getHoraDeInicio());
		System.out.print("Hora máxima de fin: " + registro.getHoraDeFin());
	}
	
	public void finalizarEstacionamiento() {
		if(centroRegistros.estaVigente(this.patente)) {
			Registro registro = centroRegistros.registrarFinal(this.patente);
			int costoTotal = registro.calcularCosto();
			centroCelulares.restarSaldo(celular.getNumero(), costoTotal);
			this.enviarRespuestaDeFin(registro);
		}
	}
	
	private void enviarRespuestaDeFin(Registro registro) {
		System.out.print("Hora de inicio: " + registro.getHoraDeInicio());
		System.out.print("Hora máxima de fin: " + registro.getHoraDeFin());
	}
	
	public int getSaldoActual() {
		return this.celular.getSaldoActual();
	}
	
	public void setModo(Modo modoNuevo) {
		this.modo = modoNuevo;
	}
}