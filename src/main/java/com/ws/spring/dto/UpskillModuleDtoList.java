package com.ws.spring.dto;
 
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
public class UpskillModuleDtoList {
 
	
	 private long moduleId;
	    private String moduleName;
 
		public UpskillModuleDtoList(long moduleId, String moduleName) {
			super();
			this.moduleId = moduleId;
			this.moduleName = moduleName;
		}

}