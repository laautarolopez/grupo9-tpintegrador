package SEM;

public abstract class Estado {
	private Modo modo;
	
	public Estado(Modo modo) {
		this.modo = modo;
	}
	
	public Modo getModo() {
		return this.modo;
	}
	
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	public abstract void driving();
	
	public abstract void walking();
}