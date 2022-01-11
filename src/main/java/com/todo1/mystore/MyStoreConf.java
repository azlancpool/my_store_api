/**
 * 
 */
package com.todo1.mystore;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cnaranjo
 *
 */
@Configuration
public class MyStoreConf {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
