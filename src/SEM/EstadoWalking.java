package SEM;

public class EstadoWalking extends Estado {

	public EstadoWalking(Modo modo) {
		super(modo);
	}

	@Override
	public void driving() {
		this.getModo().setEstado(new EstadoDriving(this.getModo()));
		this.getModo().realizarAccionWalkingADriving();
	}

	@Override
	public void walking() {}
}