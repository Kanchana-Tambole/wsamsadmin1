package com.ws.spring.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class RoleDto {
	
	    private Long roleId;

	    private String roleName;
	    
	    private LocalDateTime insertedDate;
	    
	    private LocalDateTime updatedDate;

		public RoleDto(Long roleId, String roleName, LocalDateTime insertedDate, LocalDateTime updatedDate) {
			super();
			this.roleId = roleId;
			this.roleName = roleName;
			this.insertedDate = insertedDate;
			this.updatedDate = updatedDate;
		}
	    
	    

}
