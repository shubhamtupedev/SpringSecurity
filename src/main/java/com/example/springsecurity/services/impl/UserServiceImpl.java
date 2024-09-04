package com.example.springsecurity.services.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entityDTO.UserDTO;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ApiResponseDto<?>> saveUser(UserDTO userDTO) throws ValidationException, ServiceException {
		try {
			if (!userRepository.findByEmail(userDTO.getEmail()).isEmpty()) {
				throw new ValidationException("Registration Failed! Email already exists " + userDTO.getEmail());
			}

			if (!userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).isEmpty()) {
				throw new ValidationException(
						"Registration Failed! Phone number already exists " + userDTO.getPhoneNumber());
			}

			User userDetails = new User(userDTO);

			String decodedPassword = new String(Base64.getDecoder().decode(userDTO.getPassword()));
			validatePasswordPolicy(decodedPassword);
			userDetails.setPassword(passwordEncoder.encode(decodedPassword));

			userRepository.save(userDetails);

			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
					"User registration completed successfully!"));
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getUser(String email, int page, int size, String sortBy, String sortOrder)
			throws ServiceException {
		try {
			Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
			Pageable pageable = PageRequest.of(page, size, sort);
			email = (email == null) ? "%" : email;
			Page<User> userList = userRepository.findByFilters(email, pageable);
			System.out.println(userList.getContent());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList.getContent()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getUser(String email) throws ServiceException {
		try {
			Long userId = null;
			Optional<User> userList = userRepository.findByEmail(email);
			if (userList != null && !userList.isEmpty()) {
				User user = userList.get();
				userId = user.getId();
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userRepository.findById(userId)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> deleteUser(String username) throws ValidationException, ServiceException {
//        try {
//            if (userRepository.findByUserName(username) == null) {
//                throw new UserNotFoundException("User Not Exists! Kindly check username.");
//            }
//            User user = userRepository.findByUserName(username);
//            userRepository.deleteById(user.getId());
//            saveTransaction();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Deleted Successfully"));
//        } catch (UserNotFoundException userNotFoundException) {
////            saveTransaction();
//            throw new UserNotFoundException(userNotFoundException.getMessage());
//        } catch (Exception exception) {
////            saveTransaction();
//            exception.printStackTrace();
//            throw new UserServiceLogicException();
//        }
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> updateUser(String username, User user)
			throws ValidationException, ServiceException {
//        try {
//            if (userRepository.findByUserName(username) == null) {
//                throw new UserNotFoundException("User Not Exists! Kindly check username.");
//            }
//            User userList = userRepository.findByUserName(username);
////            userList.setUserName(user.getUserName());
//            userList.setPassword(passwordEncoder.encode(user.getPassword()));
//            userList.setEmail(user.getEmail());
////            userList.setMobileNo(user.getMobileNo());
//            Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
//            userList.setModifiedDate(modifiedDate);
////            userList.setModifiedBy(user.getUserName());
//            userRepository.save(userList);
//            saveTransaction();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Updated Successfully"));
//        } catch (UserNotFoundException userNotFoundException) {
////            saveTransaction();
//            throw new UserNotFoundException(userNotFoundException.getMessage());
//        } catch (Exception exception) {
////            saveTransaction();
//            exception.printStackTrace();
//            throw new UserServiceLogicException();
//        }
	}

	@Override
	public void update(User user) throws ServiceException {
		userRepository.save(user);
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws ValidationException, ServiceException {
		try {
			List<User> userList = userRepository.findAll();
//            saveTransaction();
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList));
		} catch (Exception exception) {
//            saveTransaction();
			exception.printStackTrace();
			throw new ServiceException();
		}
	}

	public void validatePasswordPolicy(String password) throws ValidationException, ServiceException {

		Integer passwordMinLength = Integer.parseInt("8");
		Integer passwordMaxLength = Integer.parseInt("15");
		String passwordRegexExp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@.#$%^&*])[a-zA-Z0-9!@.#$%^&*]" + "{"
				+ passwordMinLength + "," + passwordMaxLength + "}";

		if (password.length() < passwordMinLength || password.length() > passwordMaxLength) {
			throw new ValidationException("The password must be at least " + passwordMinLength + " - "
					+ passwordMaxLength + " characters long.");
		}

		if (!Pattern.matches(passwordRegexExp.trim(), password)) {
			throw new ValidationException(
					"Password must include at least one uppercase letter, one lowercase letter, one number, and one special character.");
		}

	}
}
