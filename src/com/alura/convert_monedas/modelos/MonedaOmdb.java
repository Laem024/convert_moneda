package com.alura.convert_monedas.modelos;

import java.util.Map;

public record MonedaOmdb(String time_last_update_utc, String time_next_update_utc, String base_code, Map<String, Double> conversion_rates) {
}
