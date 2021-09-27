package pt.amane.bds01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.amane.bds01.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
