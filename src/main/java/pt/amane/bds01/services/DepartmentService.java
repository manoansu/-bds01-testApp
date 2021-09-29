package pt.amane.bds01.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.bds01.dto.DepartmentDTO;
import pt.amane.bds01.entities.Department;
import pt.amane.bds01.repositories.DepartmentRepository;
import pt.amane.bds01.services.exception.ObjectNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;

	@Transactional(readOnly = true)
	public DepartmentDTO findById(Long id) {
		Optional<Department> dpt = repository.findById(id);
		Department department = dpt.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Department.class.getName()));
		return new DepartmentDTO(department);
	}

	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll() {
		List<Department> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public DepartmentDTO create(DepartmentDTO dto) {
		Department department = new Department();
		department.setName(dto.getName());
		department = repository.save(department);
		return new DepartmentDTO(department);
	}

	@Transactional
	public DepartmentDTO update(Long id, DepartmentDTO dto) {
		try {
			Department department = repository.getOne(id);
			department.setName(dto.getName());
			department = repository.save(department);
			return new DepartmentDTO(department);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Id not found! Id: " + id);
		}		 
	}

}
