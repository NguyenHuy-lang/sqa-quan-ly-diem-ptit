package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.KyHoc;
import sqa.example.repository.KyHocRepository;

@Service
@RequiredArgsConstructor
public class KyHocService {
	private final KyHocRepository kyHocRepository;

	public KyHoc findById(Integer id) {
		return kyHocRepository.findById(id).get();
	}
	
	public List<KyHoc> findAll() {
		return kyHocRepository.findAll();
	}
}
