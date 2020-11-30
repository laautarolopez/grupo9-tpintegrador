package SEM;

public class ModoManual extends Modo{
	protected Celular celular;
	protected String patente;
	
	public ModoManual(AplicacionCliente aplicacion) {
		super(aplicacion);
	}
	
	@Override
	public void cambiarModo() {
		aplicacion.setModo(new ModoAutomatico(aplicacion));
	}
	
	@Override
	public void iniciarEstacionamiento(AplicacionCliente app) throws Exception {
		app.generarRegistro();
	}

	@Override
	public boolean finalizacionManual() {
		return true;
	}

	@Override
	public boolean requiereNotificaciones() {
		return true;
	}
	
	@Override
	public void walking() {
		estado.aconsejarInicio(aplicacion);
	}
	
	@Override
	public void driving() {
		estado.aconsejarFinal(aplicacion);
	}
}