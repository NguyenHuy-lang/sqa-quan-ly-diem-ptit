package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.ThangDiem;

@Repository
public interface ThangDiemRepository extends JpaRepository<ThangDiem, Integer> {
    @Query(value = "SELECT * FROM tbl_thang_diem td WHERE td.diem_chu = :ten", nativeQuery = true)
    ThangDiem findByName(@Param("ten") String ten);
}
