package id.co.test.gateway.services.service;

import id.co.test.gateway.services.dto.PegawaiRequestDTO;
import id.co.test.gateway.services.dto.PegawaiResponseDTO;
import id.co.test.gateway.services.dto.Response;

public interface Service {

	PegawaiResponseDTO getPegawai(String idPegawai) throws Exception;
	
	Response savePegawai(PegawaiRequestDTO request) throws Exception;
}
