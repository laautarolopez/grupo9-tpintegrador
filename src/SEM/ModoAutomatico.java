package SEM;

public class ModoAutomatico extends Modo {
	
	public ModoAutomatico(AplicacionCliente aplicacion) {
		super(aplicacion);
	}
	
	@Override
	public void cambiarModo() {
		aplicacion.setModo(new ModoManual(aplicacion));
	}
	
	@Override
	public void driving(AplicacionCliente app) throws Exception {
		if(this.estado == "walking") {
			app.finalizarEstacionamiento();
			this.estado = "driving";
		}
	}
	
	@Override
	public void walking(AplicacionCliente app) throws Exception {
		if(this.estado == "driving") {
			app.generarRegistro();
			this.estado = "walking";
		}
	}

	@Override
	public void iniciarEstacionamiento(AplicacionCliente aplicacionCliente) throws Exception {
		throw new Exception("El modo automático de la app no permite iniciar estacionamientos de forma manual");
		
	}

	@Override
	public boolean finalizacionManual() {
		return false;
	}

	@Override
	public boolean requiereNotificaciones() {
		return false;
	}
}
