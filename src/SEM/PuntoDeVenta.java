package SEM;

public class PuntoDeVenta extends PuntoDeCarga implements GeneradorDeRegistros {
	protected Zona zona;
	protected CentroRegistros centroRegistros = CentroRegistros.getCentro();
	protected int monto;
	
	public PuntoDeVenta(Zona zona) {
		this.zona = zona;
		this.monto = 0;
	}
	
	public void iniciarEstacionamiento(String patente, int monto) {
		int montoExacto = monto / 40 * 40;
		if (montoExacto >= 40) {
			this.monto += montoExacto;
			RegistroPuntoDeVenta registro = new RegistroPuntoDeVenta(patente,this.zona, montoExacto);
			centroRegistros.registrarInicio(registro);
		}
	}
}
