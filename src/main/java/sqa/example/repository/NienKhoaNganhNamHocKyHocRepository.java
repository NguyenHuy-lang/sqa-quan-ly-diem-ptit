package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;

import java.util.List;

@Repository
public interface NienKhoaNganhNamHocKyHocRepository extends JpaRepository<sqa.example.model.NienKhoaNganhNamHocKyHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_nien_khoa_nganh_nam_hoc_ky_hoc nknnhkh " +
            "WHERE nknnhkh.nam_hoc_ky_hoc_id = :nam_hoc_ky_hoc_id " +
            "and nknnhkh.nien_khoa_nganh_id = :nien_khoa_nganh_id", nativeQuery = true)
    NienKhoaNganhNamHocKyHoc getNienKhoaNganhNamHocKyHoc(@Param("nam_hoc_ky_hoc_id") Integer nam_hoc_ky_hoc_id,
                                                         @Param("nien_khoa_nganh_id") Integer nien_khoa_nganh_id
                                                         );
    @Query(value = "SELECT * FROM tbl_nien_khoa_nganh_nam_hoc_ky_hoc nknnhkh " +
            "WHERE nknnhkh.nam_hoc_ky_hoc_id = :nam_hoc_ky_hoc_id", nativeQuery = true)
    List<NienKhoaNganhNamHocKyHoc> getListNienKhoaNganhNamHocKyHoc(@Param("nam_hoc_ky_hoc_id") Integer nam_hoc_ky_hoc_id);

}
