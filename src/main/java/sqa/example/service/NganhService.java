package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.repository.NganhRepository;

@Service
@RequiredArgsConstructor
public class NganhService {
    private final NganhRepository nganhRepository;
    public Integer getIdOfNganhByName(String name) {
        return nganhRepository.getNganhByTen(name).getId();
    }
}
