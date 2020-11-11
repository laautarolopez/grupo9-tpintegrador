package SEM;

public class AplicacionClienteAutomatica extends AplicacionClienteManual 
										 implements AplicacionCliente, MovementSensor {
	private String estado;
	
	public AplicacionClienteAutomatica(Celular celular) {
		super(celular);
	}
	
	@Override
	public void iniciarEstacionamiento(String patente, int horas) {
		if(!centroRegistros.estaVigente(patente) && Celular.gps.esZonaDeEstacionamiento()) {
			super.iniciarEstacionamiento(patente, horas);
		}
	}
	
	public void driving() {
		if(this.estado == "walking") {
			this.finalizarEstacionamiento();
			this.estado = "driving";
		}
	}
	
	public void walking() {
		if(this.estado == "driving") {
			this.iniciarEstacionamiento(patente, 1);
			this.estado = "walking";
		}
	}
	
	public AplicacionCliente cambiarModo() {
		return new AplicacionClienteManual(this.celular);
	}
}