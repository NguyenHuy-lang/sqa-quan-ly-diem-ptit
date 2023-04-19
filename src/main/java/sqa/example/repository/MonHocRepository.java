package sqa.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sqa.example.model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc nknnhkhmh WHERE nknnhkhmh.nien_khoa_nganh_nam_hoc_ky_hoc_id = :nien_khoa_nganh_nam_hoc_ky_hoc_id", nativeQuery = true)
    List<MonHoc> findAllByNienKhoaNganhNamHocKyHocId(@Param("nien_khoa_nganh_nam_hoc_ky_hoc_id") Integer nganh_id);
}
