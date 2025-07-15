package com.ws.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ws.common.util.Constants;
import com.ws.spring.model.Role;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.RoleRepository;
import com.ws.spring.repository.UserProfileRepository;

@Configuration
public class AppCommandLineRunner implements CommandLineRunner {


	@Autowired
	RoleRepository roleRepository;


	@Autowired
	UserProfileRepository userProfileRepository;

	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Role superAdminRole = new Role(Constants.ROLE_ID_SUPERADMIN, "SuperAdmin");
		Role adminRole = new Role(Constants.ROLE_ID_ADMIN, "Admin");
		
		roleRepository.save(superAdminRole);
		roleRepository.save(adminRole);
		/*
		 * roleRepository.save(new Role(Constants.ROLE_ID_REPORTADMIN, "ReportAdmin"));
		 * roleRepository.save(new Role(Constants.ROLE_ID_APPROVER, "Approver"));
		 * roleRepository.save(new Role(Constants.ROLE_ID_OWNER, "Owner"));
		 */

		UserProfile superAdmin = new UserProfile();
		// superAdmin.setUserId(1001);
		superAdmin.setRole(superAdminRole);
		superAdmin.setUserName("superadmin1");
		superAdmin.setPassword(passwordEncoder.encode("user123"));
		superAdmin.setMobileNumber("9000000001");
		if (null == userProfileRepository.findByUserName(superAdmin.getUserName())) {
			userProfileRepository.save(superAdmin);
		}

		UserProfile admin = new UserProfile();
		// admin.setUserId(1002);
		admin.setRole(adminRole);
		admin.setUserName("admin1");
		admin.setPassword(passwordEncoder.encode("user123"));
		admin.setMobileNumber("9000000002");
		if (null == userProfileRepository.findByUserName(admin.getUserName())) {
			userProfileRepository.save(admin);
		}

		
	}
}
