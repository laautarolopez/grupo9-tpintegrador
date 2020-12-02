package SEM;

public class PuntoDeVenta extends PuntoDeCarga implements GeneradorDeRegistros {
	private String zona;
	private int monto;
	
	public PuntoDeVenta(Sistema sistema, String zona) {
		super(sistema);
		this.zona = zona;
		this.monto = 0;
	}
	
	public void setMonto(int monto) {
		this.monto = monto;
	}
	
	public void iniciarEstacionamiento(String patente, int horas) throws Exception {
		if (horas > 0) {
			this.setMonto(monto + (horas * sistema.getValorDeHora()));
			RegistroPuntoDeVenta registro = new RegistroPuntoDeVenta(sistema, patente,this.zona, horas);
			sistema.registrarInicio(registro);
		}
	}
}
