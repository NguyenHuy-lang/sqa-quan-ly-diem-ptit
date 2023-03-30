package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.repository.MonHocRepository;

@Service
@RequiredArgsConstructor
public class MonHocService {
    private final MonHocRepository monHocRepository;
    public Integer getIdMonHocByName(String name) {
        return monHocRepository.getMonHocByName(name).getId();
    }
}
