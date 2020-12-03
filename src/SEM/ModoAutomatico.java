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
	public void iniciarEstacionamiento() {
		System.out.println("El modo automatico de la app no permite iniciar estacionamientos de forma manual");
	}
	
	@Override
	public void finalizarEstacionamiento() {
		System.out.println("El modo automático de la app no permite finalizar estacionamientos de forma manual");
	}

	@Override
	protected void realizarAccionWalkingADriving() {
		aplicacion.realizarFinalizacion();
	}

	@Override
	protected void realizarAccionDrivingAWalking() {
		aplicacion.realizarEstacionamiento();
	}
}