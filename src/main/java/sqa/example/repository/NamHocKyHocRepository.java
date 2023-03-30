package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NamHocKyHoc;

import java.util.List;

@Repository
public interface NamHocKyHocRepository extends JpaRepository<NamHocKyHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_nam_hoc_ky_hoc nhkh WHERE nhkh.nam_hoc_id = :nam_hoc_id AND " +
            "nhkh.ky_hoc_id = :ky_hoc_id", nativeQuery = true)
    public NamHocKyHoc getNamHocKyHoc(@Param("nam_hoc_id") Integer nam_hoc_id,
                               @Param("ky_hoc_id") Integer ky_hoc_id);
    @Query(value = "SELECT * FROM tbl_nam_hoc_ky_hoc nhkh WHERE nhkh.nam_hoc_id = :nam_hoc_id" ,nativeQuery = true)
    public List<NamHocKyHoc> getNamHocKyHoc(@Param("nam_hoc_id") Integer nam_hoc_id);
}
