package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sqa.example.model.NamHocKyHoc;
import sqa.example.repository.NamHocKyHocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NamHocKyHocService {
    private final NamHocKyHocRepository namHocKyHocRepository;
    public NamHocKyHoc getNamHocKyHoc(Integer nam_hoc_id,
                                      Integer ky_hoc_id){
        return namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);

    }
    public List<NamHocKyHoc> getNamHocKyHoc(Integer nam_hoc_id){
        return namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id);
    }
}
