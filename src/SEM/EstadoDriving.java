package SEM;

public class EstadoDriving extends Estado {

	public EstadoDriving(Modo modo) {
		super(modo);
	}

	@Override
	public void driving() {}

	@Override
	public void walking() {
		this.getModo().setEstado(new EstadoWalking(this.getModo()));
		this.getModo().realizarAccionDrivingAWalking();
	}
}