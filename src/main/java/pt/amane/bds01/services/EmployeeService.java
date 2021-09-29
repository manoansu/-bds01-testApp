package pt.amane.bds01.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.bds01.dto.EmployeeDTO;
import pt.amane.bds01.entities.Department;
import pt.amane.bds01.entities.Employee;
import pt.amane.bds01.repositories.EmployeeRepository;
import pt.amane.bds01.services.exception.ObjectNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Transactional(readOnly = true)
	public EmployeeDTO findById(Long id) {
		Optional<Employee> emp = repository.findById(id);
		Employee employee = emp.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Employee.class.getName()));
		return new EmployeeDTO(employee);
	}

	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAll(Pageable pageable) {
		Page<Employee> list = repository.findAll(pageable);
		return list.map(x -> new EmployeeDTO(x));
	}

	@Transactional
	public EmployeeDTO create(EmployeeDTO dto) {
		Employee emp = new Employee();
		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setDepartment(new Department(dto.getDepartmentId(), null));
		emp = repository.save(emp);
		return new EmployeeDTO(emp);
	}

	@Transactional
	public EmployeeDTO update(Long id, EmployeeDTO dto) {
		try {
			Employee employee = repository.getOne(id);
			employee.setName(dto.getName());
			employee.setEmail(dto.getEmail());
			employee.setDepartment(new Department(dto.getDepartmentId(), null));
			employee = repository.save(employee);
			return new EmployeeDTO(employee);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Id not found! Id: " + id);
		}
	}

}
