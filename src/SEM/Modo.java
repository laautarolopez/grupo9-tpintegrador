package SEM;

public abstract class Modo {
	AplicacionCliente aplicacion;
	Estado estado;
	
	public Modo(AplicacionCliente aplicacion) {
		this.aplicacion = aplicacion;
	}
		
	public abstract void iniciarEstacionamiento(AplicacionCliente aplicacionCliente) throws Exception;

	public abstract boolean finalizacionManual();
	
	public abstract boolean requiereNotificaciones();
	
	public abstract void cambiarModo();
	
	public abstract void walking();
	
	public abstract void driving();
	
}
