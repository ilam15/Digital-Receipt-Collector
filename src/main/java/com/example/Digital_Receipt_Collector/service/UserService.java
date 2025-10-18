package com.example.Digital_Receipt_Collector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Receipt_Collector.entity.User;
import com.example.Digital_Receipt_Collector.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User updateUser(Long id, User user) {
		return userRepository.findById(id)
				.map(existing -> {
					existing.setName(user.getName());
					existing.setEmail(user.getEmail());
					existing.setContact_no(user.getContact_no());
					existing.setJoin_date(user.getJoin_date());
					return userRepository.save(existing);
				})
				.orElseGet(() -> {
					user.setId(id);
					return userRepository.save(user);
				});
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
