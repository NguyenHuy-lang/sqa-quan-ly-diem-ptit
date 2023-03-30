package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.KetQua;
import sqa.example.repository.KetQuaRepository;

@Service
@RequiredArgsConstructor
public class KetQuaService {
    private final KetQuaRepository ketQuaRepository;
    public KetQua getKetQuaOfSinhVien(Integer sinh_vien_id, Integer lop_hoc_phan_id) {
        return ketQuaRepository.getKetQuaOfSinhVien(sinh_vien_id, lop_hoc_phan_id);
    }
}
