package SEM;

public class ModoManual extends Modo{
	protected CentroRegistros centroRegistros = CentroRegistros.getCentro();
	protected Celular celular;
	protected String patente;
	
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

}