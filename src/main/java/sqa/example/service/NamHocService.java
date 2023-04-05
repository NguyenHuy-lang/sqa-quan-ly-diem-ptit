package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NamHoc;
import sqa.example.repository.NamHocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NamHocService {
    private final NamHocRepository namHocRepository;
    public Integer getIdNamHocByName(String name) {
        return namHocRepository.getIdNamHocByName(name).getId();
    }
    public List<NamHoc> getAllNamHoc() {
        return namHocRepository.findAll();
    }
}
