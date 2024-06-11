package cr.ac.una.proyecto_progra4.Response;


import java.util.ArrayList;
import java.util.List;

import cr.ac.una.proyecto_progra4.domain.Factura;

public class FacturasResponse {
    List<Factura> facturas;
    private int page;
    private int pageSize;
    private int ultimaPagina;
    private String nuevoCodigo;


    
    public FacturasResponse() {
        facturas = new ArrayList<>();
    }
    public List<Factura> getFacturas() {
        return facturas;
    }
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getUltimaPagina() {
        return ultimaPagina;
    }
    public void setUltimaPagina(int ultimaPagina) {
        this.ultimaPagina = ultimaPagina;
    }
    public String getNuevoCodigo() {
        return nuevoCodigo;
    }
    public void setNuevoCodigo(String nuevoCodigo) {
        this.nuevoCodigo = nuevoCodigo;
    }
    
}
