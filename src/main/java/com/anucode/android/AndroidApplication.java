package com.anucode.android;

import com.anucode.android.entities.Roles;
import com.anucode.android.entities.StudentAuth;
import com.anucode.android.repository.RolesRepository;
import com.anucode.android.repository.StudentAuthRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class AndroidApplication {

	@Autowired
	private StudentAuthRepository studentAuthRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@PostConstruct
	public void initUsers() {

		Roles adminRole = new Roles(1L, "ROLE_ADMIN");
		Roles studentRole = new Roles(2L, "ROLE_STUDENT");

		rolesRepository.save(adminRole);
		rolesRepository.save(studentRole);

		Set<Roles> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);

		Set<Roles> studentRoles = new HashSet<>();
		studentRoles.add(studentRole);

		List<StudentAuth> users = Stream.of(
				new StudentAuth(101, "admin", "adpaswrd", adminRoles),
				new StudentAuth(102, "user1", "pwd1", studentRoles),
				new StudentAuth(103, "user2", "pwd2", studentRoles),
				new StudentAuth(104, "user3", "pwd3", studentRoles)
		).collect(Collectors.toList());
		studentAuthRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(AndroidApplication.class, args);
	}

}
