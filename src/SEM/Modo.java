package SEM;

public abstract class Modo {
	Estado estado;
	AplicacionCliente aplicacion;
	
	public Modo(AplicacionCliente aplicacion) {
		this.estado = new EstadoWalking(this);
		this.aplicacion = aplicacion;
	}
	
	public Estado getEstado() {
		return this.estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
		
	public void walking() {
		this.estado.walking();
	}
	
	public void driving() {
		this.estado.driving();
	}
	
	public abstract void cambiarModo();

	public abstract void iniciarEstacionamiento();

	public abstract void finalizarEstacionamiento();
	
	protected abstract void realizarAccionWalkingADriving();
	
	protected abstract void realizarAccionDrivingAWalking();
}