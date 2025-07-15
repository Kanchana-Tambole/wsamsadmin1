package com.ws.spring.dto;
 
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
public class UpskillCategoryDtoList {
 
	

    private long categoryId;
    private String categoryName;
 
	public UpskillCategoryDtoList(long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

}