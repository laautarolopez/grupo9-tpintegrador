package SEM;

public class ModoManual extends Modo {
	
	public ModoManual(AplicacionCliente aplicacion) {
		super(aplicacion);
	}
	
	@Override
	public void cambiarModo() {
		aplicacion.setModo(new ModoAutomatico(aplicacion));
	}
	
	@Override
	public void iniciarEstacionamiento() {
			aplicacion.realizarEstacionamiento();
	}

	@Override
	public void finalizarEstacionamiento() {
		aplicacion.realizarFinalizacion();
	}
	
	@Override
	protected void realizarAccionWalkingADriving() {
		aplicacion.aconsejarFinal();
	}

	@Override
	protected void realizarAccionDrivingAWalking() {
		aplicacion.aconsejarInicio();
	}
}