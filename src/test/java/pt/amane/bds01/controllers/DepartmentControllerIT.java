package pt.amane.bds01.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
public class DepartmentControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/departments")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].name").value("Braga"));
		result.andExpect(jsonPath("$[1].name").value("Famalicão"));
		result.andExpect(jsonPath("$[2].name").value("Management"));
	}
	
	@Test
	void deleteShouldReturnBadRequestWhenItHasDepentId() throws Exception {
		
		Long dependentId = 1L;
		
		ResultActions result = mockMvc.perform(delete("/departments/{id}", dependentId));
		
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	void deleteShouldReturnNotFoundWhenDoesNontExistId() throws Exception {
		
		Long nonExistId = 50L;
		
		ResultActions result = mockMvc.perform(delete("/departments/{id}", nonExistId));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	void deleteShouldReturnNoContentWhenIndependentId() throws Exception {
		
		Long independentId = 4L;
		
		ResultActions result = mockMvc.perform(delete("/departments/{id}", independentId));
		
		result.andExpect(status().isNoContent());
	}
}
