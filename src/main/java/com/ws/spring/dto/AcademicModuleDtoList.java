package com.ws.spring.dto;
 
import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;
 
@Data

@Builder

@NoArgsConstructor

public class AcademicModuleDtoList {
 
	

	 private long moduleId;

	    private String moduleName;
 
		public AcademicModuleDtoList(long moduleId, String moduleName) {

			super();

			this.moduleId = moduleId;

			this.moduleName = moduleName;

		}



}

 