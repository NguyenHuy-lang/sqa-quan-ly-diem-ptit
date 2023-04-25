package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sqa.example.model.NamHoc;
import sqa.example.repository.NamHocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NamHocService {
    private final NamHocRepository namHocRepository;
    @Query(value = "SELECT nh.id FROM tbl_nam_hoc nh WHERE nh.ten = :name", nativeQuery = true)
    public Integer getIdNamHocByName(@Param("name") String name) {
        return namHocRepository.getIdNamHocByName(name).getId();
    }
    public List<NamHoc> getAllNamHoc() {
        return namHocRepository.findAll();
    }
}
