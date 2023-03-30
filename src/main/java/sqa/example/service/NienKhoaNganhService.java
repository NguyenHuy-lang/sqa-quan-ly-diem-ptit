package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoaNganh;
import sqa.example.repository.NienKhoaNganhRepository;

@Service
@RequiredArgsConstructor
public class NienKhoaNganhService {
    private final NienKhoaNganhRepository nienKhoaNganhRepository;
    NienKhoaNganh getNienKhoaNganhOfSinhVien(Integer user_id){
        return nienKhoaNganhRepository.getNienKhoaNganhOfSinhVien(user_id);
    }
}
