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
		
	}

	@Override
	public void finalizarEstacionamiento() {
		
	}
	
	@Override
	protected void realizarAccionWalkingADriving() {
		
	}

	@Override
	protected void realizarAccionDrivingAWalking() {
		
	}
}