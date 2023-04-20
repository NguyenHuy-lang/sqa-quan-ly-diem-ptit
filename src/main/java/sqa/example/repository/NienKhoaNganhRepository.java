package sqa.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NienKhoaNganh;

@Repository
public interface NienKhoaNganhRepository extends JpaRepository<NienKhoaNganh, Integer> {
    @Query(value = "SELECT * FROM tbl_nien_khoa_nganh nkn WHERE nkn.id = :user_id LIMIT 1", nativeQuery = true)
    NienKhoaNganh findBySinhVienId(@Param("user_id") Integer user_id);
    
	@Query(value = "SELECT * FROM tbl_nien_khoa_nganh nkn WHERE nkn.id = :nien_khoa_nganh_nam_hoc_ky_hoc_id LIMIT 1", nativeQuery = true)
    NienKhoaNganh findByByNienKhoaNganhNamHocKyHocId(@Param("nien_khoa_nganh_nam_hoc_ky_hoc_id") Integer id);
    
	@Query(value = "SELECT * FROM tbl_nien_khoa_nganh nkn WHERE nkn.nien_khoa_id =:nien_khoa_id AND nkn.nganh_id = :nganh_id", nativeQuery = true)
    NienKhoaNganh findByNienKhoaAndNganhId(@Param("nien_khoa_id") Integer nien_khoa_id, @Param("nganh_id") Integer nganh_id);
	
	@Query(value = "SELECT * FROM tbl_nien_khoa_nganh nkn WHERE nkn.nganh_id =:nganh_id", nativeQuery = true)
    List<NienKhoaNganh> findAllByNganhId(@Param("nganh_id") Integer nganh_id);
}
