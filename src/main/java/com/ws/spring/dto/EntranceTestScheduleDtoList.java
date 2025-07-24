package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class EntranceTestScheduleDtoList {

	private Long id;

	private String testTypeName;

	private String testCenterName;

	public EntranceTestScheduleDtoList(Long id, String testTypeName, String testCenterName) {
		super();
		this.id = id;
		this.testTypeName = testTypeName;
		this.testCenterName = testCenterName;
	}
}
