package com.arun.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.arun.dto.EmployeeCountResponse;
import com.arun.dto.EmployeeRegistrationRequest;
import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Department;
import com.arun.model.Employee;
import com.arun.model.Login;
import com.arun.model.ResetPasswordToken;
import com.arun.model.UserRole;
import com.arun.repository.DepartmentRepository;
import com.arun.repository.EmployeeRepository;
import com.arun.repository.LoginRepository;
import com.arun.repository.ResetPasswordTokenRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ResetPasswordTokenRepository tokenRepository;
	@Autowired
	private JavaMailSender mailSender;
	
	public ResponseEntity<String> register(EmployeeRegistrationRequest request,MultipartFile file) {
		
		boolean employeeExists = loginRepository.findByEmail(request.getEmail()).isPresent();
		
		if(employeeExists) {
			throw new IllegalStateException("Email already exists");
		}
		
		Employee employee = null;
		try {
			employee = new Employee(
						request.getFirstname(),
						request.getDepartment(),
						request.getLastName(),
						request.getDob(),
						request.getGender(),
						request.getMartialStatus(),
						file.getBytes(),
						request.getEmployeeType(),
						request.getDesignation(),
						request.getJoiningDate(),
						request.getCountry(),
						request.getState(),
						request.getCity(),
						request.getAddress(),
						request.getPin(),
						request.getPhone(),
						request.getEmail(),
						request.getQualification()
					);
		} catch (IOException e) {

		}

		String encodeedPassword = passwordEncoder.encode(request.getPassword());
		Login login = new Login(
					request.getEmail(),
					encodeedPassword,
					UserRole.USER,
					employee
				);
		
		employeeRepository.save(employee);
		loginRepository.save(login);
		
		return ResponseEntity.ok("Employee successfully registered");
	}

	public ResponseEntity<String> update(Employee newEmployee, MultipartFile file, long empId) {
		
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Could not find employee with id: "+empId));
		employee.setFirstName(newEmployee.getFirstName());
		employee.setDepartment(newEmployee.getDepartment());
		employee.setLastName(newEmployee.getLastName());
		employee.setDob(newEmployee.getDob());
		employee.setGender(newEmployee.getGender());
		try {
			employee.setPhoto(file.getBytes());
		} catch (IOException e) {
		}
		employee.setEmployeeType(newEmployee.getEmployeeType());
		employee.setDesignation(newEmployee.getDesignation());
		employee.setJoiningDate(newEmployee.getJoiningDate());
		employee.setCountry(newEmployee.getCountry());
		employee.setState(newEmployee.getState());
		employee.setCity(newEmployee.getCity());
		employee.setAddress(newEmployee.getAddress());
		employee.setPin(newEmployee.getPin());
		employee.setPhone(newEmployee.getPhone());
		employee.setEmail(newEmployee.getEmail());
		employee.setQualification(newEmployee.getQualification());
		
		Login login = loginRepository.findByEmployee(employee);
		
		login.setEmail(newEmployee.getEmail());
		
		employeeRepository.save(employee);
		loginRepository.save(login);
		
		return ResponseEntity.ok("Employee details updated successfully");
	}

	public ResponseEntity<String> lock(long empId) {
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Could not find employee with id: "+empId));
		Login login = loginRepository.findByEmployee(employee);
		
		login.setLocked(true);
		loginRepository.save(login);
		
		return ResponseEntity.ok("Account locked successfully");
	}
	
	public ResponseEntity<String> unlock(long empId) {
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Could not find employee with id: "+empId));
		Login login = loginRepository.findByEmployee(employee);
		
		login.setLocked(false);
		loginRepository.save(login);
		
		return ResponseEntity.ok("Account unlocked successfully");
	}
	
	public ResponseEntity<String> changePassword(String currentPassword, String newPassword, long empId)  {
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Could not find employee with id: "+empId));
		Login login = loginRepository.findByEmployee(employee);
		String status = "";
		if(passwordEncoder.matches(currentPassword, login.getPassword())) {
			login.setPassword(passwordEncoder.encode(newPassword));
			loginRepository.save(login);
			status = "Password changed successfully";
		}
		else {
			status = "Current password is incorrect!!";
		}
		
		return ResponseEntity.ok(status);
	}

	public EmployeeCountResponse count() {
		long active =  loginRepository.countByUserRoleAndLocked(UserRole.USER,false);
		long inActive = loginRepository.countByUserRoleAndLocked(UserRole.USER,true);
		
		EmployeeCountResponse count = new EmployeeCountResponse(active, inActive, active+inActive);
		return count;
	}

	public ResponseEntity<List<Employee>> getByDepartment(long depId) {
		Department department = departmentRepository.findById(depId).orElseThrow(()->new ResourceNotFoundException("Department not found with id: "+depId));
		return ResponseEntity.ok(employeeRepository.findByDepartment(department));
	}

	public String buildMail(String link) {
		return "<!doctype html>\r\n"
				+ "<html lang=\"en-US\">\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\r\n"
				+ "  <title>Reset Password Email Template</title>\r\n"
				+ "  <meta name=\"description\" content=\"Reset Password.\">\r\n"
				+ "  <style type=\"text/css\">\r\n"
				+ "    a:hover {\r\n"
				+ "      text-decoration: underline !important;\r\n"
				+ "    }\r\n"
				+ "  </style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\r\n"
				+ "  <!--100% body table-->\r\n"
				+ "  <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\" style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\r\n"
				+ "    <tr>\r\n"
				+ "      <td>\r\n"
				+ "        <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "          <tr>\r\n"
				+ "            <td style=\"height:80px;\">&nbsp;</td>\r\n"
				+ "          </tr>\r\n"
				+ "          <tr>\r\n"
				+ "            <td style=\"text-align:center;\">\r\n"
				+ "              <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\r\n"
				+ "                <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\r\n"
				+ "              </a>\r\n"
				+ "            </td>\r\n"
				+ "          </tr>\r\n"
				+ "          <tr>\r\n"
				+ "            <td style=\"height:20px;\">&nbsp;</td>\r\n"
				+ "          </tr>\r\n"
				+ "          <tr>\r\n"
				+ "            <td>\r\n"
				+ "              <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\r\n"
				+ "                <tr>\r\n"
				+ "                  <td style=\"height:40px;\">&nbsp;</td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                  <td style=\"padding:0 35px;\">\r\n"
				+ "                    <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\r\n"
				+ "                      requested to reset your password</h1>\r\n"
				+ "                    <span style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\r\n"
				+ "                    <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\r\n"
				+ "                      We cannot simply send you your old password. A unique link to reset your\r\n"
				+ "                      password has been generated for you. To reset your password, click the\r\n"
				+ "                      following link and follow the instructions.\r\n"
				+ "                    </p>\r\n"
				+ "                    <a href=\""+link+"\"; style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\r\n"
				+ "                      Password</a>\r\n"
				+ "                  </td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                  <td style=\"height:40px;\">&nbsp;</td>\r\n"
				+ "                </tr>\r\n"
				+ "              </table>\r\n"
				+ "            </td>\r\n"
				+ "          <tr>\r\n"
				+ "            <td style=\"height:20px;\">&nbsp;</td>\r\n"
				+ "          </tr>\r\n"
				+ "          <tr>\r\n"
				+ "            <td style=\"text-align:center;\">\r\n"
				+ "              <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.rakeshmandal.com</strong></p>\r\n"
				+ "            </td>\r\n"
				+ "          </tr>\r\n"
				+ "          <tr>\r\n"
				+ "            <td style=\"height:80px;\">&nbsp;</td>\r\n"
				+ "          </tr>\r\n"
				+ "        </table>\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "  </table>\r\n"
				+ "  <!--/100% body table-->\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
	}
	public String sendEmail(String email,String to) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
			helper.setText(email,true);
			helper.setTo(to);
			helper.setSubject("Reset your password");
			helper.setFrom("iamarun58@gmail.com");
			mailSender.send(mimeMessage);
			return "Email sent successfully";
		}catch(MessagingException ex) {
			throw new IllegalArgumentException("Failed to send mail");
		}
		
	}

	public ResponseEntity<String> forgotPassword(String email) {
		Employee employee = employeeRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Employee not found with email: "+email));
		String token = UUID.randomUUID().toString();
		
		ResetPasswordToken resetPasswordToken = new ResetPasswordToken(token,employee,LocalDateTime.now().plusHours(1));
		tokenRepository.save(resetPasswordToken);
		
		String link = "http://localhost:8080/employee/validateToken?token="+token;
		String mail = buildMail(link);
		sendEmail(mail,email);
		
		return ResponseEntity.ok("Rest password email sent successfully");
	}

	public ResponseEntity<String> validateToken(String token) {
		ResetPasswordToken token1 = tokenRepository.findByToken(token).orElseThrow(()->new ResourceNotFoundException("Invalid token"));
		if(token1.getExpiresAt().isBefore(LocalDateTime.now())) {
			return ResponseEntity.ok("Token expired");
		}
		return ResponseEntity.ok("Token validated successfully");
	}

	public ResponseEntity<String> resetPassword(String token,String password) {
		ResetPasswordToken token1 = tokenRepository.findByToken(token).orElseThrow(()->new ResourceNotFoundException("Invalid token"));
		Employee employee = token1.getEmployee();
		Login user = loginRepository.findByEmployee(employee);
		user.setPassword(passwordEncoder.encode(password));
		loginRepository.save(user);
		return ResponseEntity.ok("Password changed successfully");
	}
	
}
