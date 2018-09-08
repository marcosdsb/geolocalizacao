package com.aplicacao.geolocalizacao.api.mapeador;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class GeolocalizadorWoeidMapper {

	private Integer distance;
	private String title;
	private String location_type;
	private String woeid;
	private String latt_long;
}
