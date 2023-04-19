package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.SinhVien;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer> {}
