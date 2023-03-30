package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NienKhoa;

@Repository
public interface NienKhoaRepository extends JpaRepository<NienKhoa, Integer> {
    @Query(value = "SELECT * FROM tbl_nien_khoa nk WHERE nk.ten = :ten", nativeQuery = true)
    NienKhoa getNienKhoaByName(@Param("ten") String ten);
}
