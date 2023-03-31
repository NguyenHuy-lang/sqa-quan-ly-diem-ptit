package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;

import java.util.List;

@Repository
public interface NienKhoaNganhNamHocKyHocMonHocRepository extends JpaRepository<NienKhoaNganhNamHocKyHocMonHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc nknnhkhmh " +
				   "WHERE nknnhkhmh.nien_khoa_nganh_nam_hoc_ky_hoc_id = :nien_khoa_nganh_nam_hoc_ky_hoc_id", nativeQuery = true)
    List<NienKhoaNganhNamHocKyHocMonHoc> getNienKhoaNganhNamHocKyHocMonHoc
            (@Param("nien_khoa_nganh_nam_hoc_ky_hoc_id") Integer nien_khoa_nganh_nam_hoc_ky_hoc_id);

    @Query(value = "SELECT * FROM tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc nknnhkhmh " +
				   "WHERE nknnhkhmh.nien_khoa_nganh_nam_hoc_ky_hoc_id = :nien_khoa_nganh_nam_hoc_ky_hoc_id " +
				   "AND nknnhkhmh.mon_hoc_id = :mon_hoc_id", nativeQuery = true)
    NienKhoaNganhNamHocKyHocMonHoc getNienKhoaNganhNamHocKyHocMonHoc
            (@Param("nien_khoa_nganh_nam_hoc_ky_hoc_id") Integer nien_khoa_nganh_nam_hoc_ky_hoc_id,
             @Param("mon_hoc_id") Integer mon_hoc_id);

	@Query(value = "SELECT * FROM tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc nknnhkhmh " +
				   "WHERE nknnhkhmh.mon_hoc_id = :mon_hoc_id", nativeQuery = true)
	List<NienKhoaNganhNamHocKyHocMonHoc> getNienKhoaNganhNamHocKyHocMonHocByMonHoc
			(@Param("mon_hoc_id") Integer mon_hoc_id);
}
