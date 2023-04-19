package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.Nganh;

@Repository
public interface NganhRepository extends JpaRepository<Nganh, Integer> {}
