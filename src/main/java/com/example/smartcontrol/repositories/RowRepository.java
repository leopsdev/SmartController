package com.example.smartcontrol.repositories;

import com.example.smartcontrol.domain.rows.Rows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RowRepository extends JpaRepository<Rows,String> {
    Rows findByModel(String model);
}
