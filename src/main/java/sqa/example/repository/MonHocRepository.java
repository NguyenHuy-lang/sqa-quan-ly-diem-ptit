package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqa.example.model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {}
