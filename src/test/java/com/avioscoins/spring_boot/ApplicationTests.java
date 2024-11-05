package com.avioscoins.spring_boot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// Endpoint was also manually tested using Postman: https://www.postman.com/
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
    private MockMvc mockMvc; 

	@Test
	void returnsAllCoinsWhenNoCabinSpecified() throws Exception {
		this.mockMvc.perform(get("/coins/LHR/LAX")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().json("{\r\n" + //
								"    \"F\": 9000.0,\r\n" + //
								"    \"W\": 5400.0,\r\n" + //
								"    \"J\": 6750.0,\r\n" + //
								"    \"M\": 4500.0\r\n" + //
								"}"));
	}

	@Test
	void returnsCoinsWhenRouteIsReversed() throws Exception {
		this.mockMvc.perform(get("/coins/YYZ/LGW")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().json("{\r\n" + //
								"    \"F\": 6500.0,\r\n" + //
								"    \"W\": 3900.0,\r\n" + //
								"    \"J\": 4875.0,\r\n" + //
								"    \"M\": 3250.0\r\n" + //
								"}"));
	}

	@Test
	void returnsCabinCoinsWhenCabinSpecified() throws Exception {
		this.mockMvc.perform(get("/coins/LHR/LAX?cabin=W")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().json("{\r\n" + //
								"    \"W\": 5400.0\r\n" + //
								"}"));
	}

	@Test
	void errorsOnIncorretRouteWithValidAirports() throws Exception {
		String url = "/coins/LGW/LAX";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isNotFound())
            .andExpect(jsonPath("$.detail").value("No value present"));
	}
	
	@Test
	void errorsOnIncorretRouteWithNoExistingAirports() throws Exception {
		String url = "/coins/L/X";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isNotFound())
            .andExpect(jsonPath("$.detail").value("No enum constant com.avioscoins.spring_boot.Types.Airports.L"));
	}

	@Test
	void errorsOnIncorretCabin() throws Exception {
		String url = "/coins/LHR/LAX?cabin=z";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isNotFound())
            .andExpect(jsonPath("$.detail").value("Invalid cabin code input: z"));
	}

}
