package id.co.test.gateway.services.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import id.co.test.gateway.services.dto.PegawaiRequestDTO;
import id.co.test.gateway.services.dto.PegawaiResponseDTO;
import id.co.test.gateway.services.dto.Response;
import id.co.test.gateway.services.service.Service;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
	
	RestTemplate restTemplate;
	
	@Value("${pegawai.get.url}")
	private String getPegawaiUrl;
	
	@Value("${pegawai.post.url}")
	private String postPegawaiUrl;
	
	@Autowired
	public ServiceImpl (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public PegawaiResponseDTO getPegawai(String idPegawai) throws Exception {
		if (StringUtils.isBlank(idPegawai))
			throw new Exception();
		
		String url = getPegawaiUrl.replaceAll("{idPegawai}", idPegawai);
		ResponseEntity<PegawaiResponseDTO> response = restTemplate.getForEntity(url, PegawaiResponseDTO.class);
		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new Exception();
		}
		return response.getBody();
	}

	@Override
	public Response savePegawai(PegawaiRequestDTO request) throws Exception {
		if (StringUtils.isBlank(request.getNama()) || StringUtils.isBlank(request.getAlamat()))
			throw new Exception();
		
		ResponseEntity<Response> response = restTemplate.postForEntity(postPegawaiUrl, request, Response.class);
		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new Exception();
		}
		return response.getBody();
	}

}
