package SEM;

public abstract class Modo {
	protected String estado;
		
	public abstract void iniciarEstacionamiento(AplicacionCliente aplicacionCliente) throws Exception;

	public abstract boolean finalizacionManual();
	
	public abstract boolean requiereNotificaciones();
	
	void walking(AplicacionCliente app) throws Exception{
		if(this.estado == "driving" && this.requiereNotificaciones()){
			app.aconsejarInicio();
		}
	}
	
	void driving(AplicacionCliente app) throws Exception{
		if(this.estado == "driving" && this.requiereNotificaciones()){
			app.aconsejarFinal();
		}
	}
	
}
