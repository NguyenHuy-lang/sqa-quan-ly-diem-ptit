package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.Nganh;
import sqa.example.repository.NganhRepository;

@Service
@RequiredArgsConstructor
public class NganhService {
    private final NganhRepository nganhRepository;
	
    public List<Nganh> findAll() {
        return nganhRepository.findAll();
    }
	
    public Nganh findById(Integer id) {
        return nganhRepository.findById(id).get();
    }
}
