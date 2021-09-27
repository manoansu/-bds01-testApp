package pt.amane.bds01.dto;

import java.io.Serializable;

import pt.amane.bds01.entities.Department;

public class DepartmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public Long id;
	public String name;

	public DepartmentDTO() {
	}

	public DepartmentDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public DepartmentDTO(Department department) {
		id = department.getId();
		name = department.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
