package dominio;

public class Seguro {
	private int idSeguro;
    private String descripcion;
    private TipoSeguro tipo;
    public TipoSeguro getTipo() {
		return tipo;
	}

	public void setTipo(TipoSeguro tipo) {
		this.tipo = tipo;
	}

	private double costoContratacion;
    private double costoAsegurado;

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public double getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(double costoContratacion) {
        this.costoContratacion = costoContratacion;
    }

    public double getCostoAsegurado() {
        return costoAsegurado;
    }

    public void setCostoAsegurado(double costoAsegurado) {
        this.costoAsegurado = costoAsegurado;
    }
    
    @Override
    public String toString() {
        return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipo=" + tipo
                + ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
    }
}
