package SEM;

public class AplicacionClienteManual implements AplicacionCliente, GeneradorDeRegistros {
	protected CentroRegistros centroRegistros = CentroRegistros.getCentro();
	protected Celular celular;
	protected String patente;
	
	public AplicacionClienteManual(Celular celular) {
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
	
	protected void enviarRespuestaDeInicio(RegistroAplicacion registro) {
		System.out.print("Hora de inicio: " + registro.getHoraDeInicio());
		System.out.print("Hora máxima de fin: " + registro.getHoraDeFin());
	}
	
	public void finalizarEstacionamiento() {
		if(centroRegistros.estaVigente(this.patente)) {
			this.enviarRespuestaDeFin(centroRegistros.registrarFinal(this.patente));
		}
	}
	
	protected void enviarRespuestaDeFin(Registro registro) {
		System.out.print("Hora de inicio: " + registro.getHoraDeInicio());
		System.out.print("Hora máxima de fin: " + registro.getHoraDeFin());
	}
	
	public int getSaldoActual() {
		return this.celular.getSaldoActual();
	}
	
	public AplicacionCliente cambiarModo() {
		return new AplicacionClienteAutomatica(this.celular);
	}
}