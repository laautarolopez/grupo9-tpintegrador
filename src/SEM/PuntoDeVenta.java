package SEM;

public class PuntoDeVenta extends puntoDeCarga implements GeneradorDeRegistros {
	protected Zona zona;
	private CentroRegistros centroRegistros = CentroRegistros.getCentro();
	
	public PuntoDeVenta(Zona zona) {
		this.zona = zona;
	}
	
	public void iniciarEstacionamiento(String patente, int monto) {
		RegistroPuntoDeVenta registro = new RegistroPuntoDeVenta(patente,this.zona, monto);
		centroRegistros.registrarInicio(registro);
	}
}
