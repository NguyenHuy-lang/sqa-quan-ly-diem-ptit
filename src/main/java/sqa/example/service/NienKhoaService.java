package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoa;
import sqa.example.repository.NienKhoaRepository;

@Service
@RequiredArgsConstructor
public class NienKhoaService {
    private final NienKhoaRepository nienKhoaRepository;
	
    public NienKhoa findById(Integer id) {
        return nienKhoaRepository.findById(id).get();
    }
}
