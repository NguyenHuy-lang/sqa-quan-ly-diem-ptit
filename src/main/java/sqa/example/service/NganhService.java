package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.Nganh;
import sqa.example.repository.NganhRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NganhService {
    private final NganhRepository nganhRepository;
    public Integer getIdOfNganhByName(String name) {
        return nganhRepository.getNganhByTen(name).getId();
    }
    public List<Nganh> findAll() {
        return nganhRepository.findAll();
    }

    public Nganh findById(Integer id) {
        return nganhRepository.findById(id).get();
    }
}
