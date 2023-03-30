package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoa;
import sqa.example.repository.NienKhoaRepository;

@Service
@RequiredArgsConstructor
public class NienKhoaService {
    private final NienKhoaRepository nienKhoaRepository;
    public NienKhoa getNienKhoaById(Integer id) {
        return nienKhoaRepository.getById(id);
    }

    public Integer getIdNienKhoaByName(String name) {
        return nienKhoaRepository.getNienKhoaByName(name).getId();
    }
}
