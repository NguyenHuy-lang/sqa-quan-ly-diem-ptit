package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.LopHocPhan;

import java.util.List;

@Repository
public interface LopHocPhanRepository extends JpaRepository<LopHocPhan, Integer> {
    @Query(value = "SELECT * FROM tbl_lop_hoc_phan lhp WHERE " +
            "lhp.nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id = :nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id", nativeQuery = true)
    List<LopHocPhan> getLopHocPhan(@Param("nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id")
                             Integer nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id);
}
