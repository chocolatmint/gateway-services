package id.co.test.gateway.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.test.gateway.services.dto.PegawaiRequestDTO;
import id.co.test.gateway.services.dto.PegawaiResponseDTO;
import id.co.test.gateway.services.dto.Response;
import id.co.test.gateway.services.service.Service;

@RestController
@RequestMapping("/pegawai")
public class Controller {

	Service service;
	
	@Autowired
	public Controller (Service service) {
		this.service = service;
	}
	
	@GetMapping(value = "/{idPegawai}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PegawaiResponseDTO> getPegawai (@PathVariable String idPegawai) {
		PegawaiResponseDTO pegawai;
		try {
			pegawai = service.getPegawai(idPegawai);
		} catch(Exception e) {
			return new ResponseEntity<PegawaiResponseDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PegawaiResponseDTO>(pegawai, HttpStatus.OK);
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> savePegawai (@RequestBody PegawaiRequestDTO request) {
		try {
			return new ResponseEntity<Response>(service.savePegawai(request), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
