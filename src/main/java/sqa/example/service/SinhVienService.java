package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.SinhVien;
import sqa.example.repository.SinhVienRepository;

@Service
@RequiredArgsConstructor
public class SinhVienService {
	private final SinhVienRepository sinhVienRepository;

	public SinhVien findById(Integer id) {
        return sinhVienRepository.findById(id).get();
    }
}
