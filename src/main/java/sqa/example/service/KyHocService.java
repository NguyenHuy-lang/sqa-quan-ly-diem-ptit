package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.KyHoc;
import sqa.example.repository.KyHocRepository;

@Service
@RequiredArgsConstructor
public class KyHocService {
    private final KyHocRepository kyHocRepository;
    public Integer getIdKyHocByNameKyHoc(String name) {
        KyHoc kyHoc = kyHocRepository.getKyHocByName(name);
        return kyHoc.getId();
    }
}
