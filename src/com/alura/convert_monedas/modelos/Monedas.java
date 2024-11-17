package com.alura.convert_monedas.modelos;

import java.util.HashMap;
import java.util.Map;

public class Monedas {
    private String ultimaActualizacion;
    private String proximaActualizacion;
    private String codigoMoneda;
    private Map<String, Double> tasaDeConversion;

    public Monedas(MonedaOmdb monedaOmdb) {
        this.ultimaActualizacion = monedaOmdb.time_last_update_utc();
        this.proximaActualizacion = monedaOmdb.time_next_update_utc();
        this.codigoMoneda = monedaOmdb.base_code();
        this.tasaDeConversion = monedaOmdb.conversion_rates();
    }

    public String getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public double getMonedaConvertida(double cantidad, String codMoneda) {
        return cantidad * tasaDeConversion.get(codMoneda);
    }

}
