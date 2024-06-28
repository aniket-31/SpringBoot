package net.springboot.employeemanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.springboot.employeemanagement.jwt.JwtUtils;
import net.springboot.employeemanagement.jwt.LoginRequest;
import net.springboot.employeemanagement.jwt.LoginResponse;
import net.springboot.employeemanagement.model.Employee;
import net.springboot.employeemanagement.service.EmployeeService;

@Controller
public class EmployeeController {
	
	   @Autowired
	    private JwtUtils jwtUtils;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	@Autowired
	private EmployeeService employeeService;

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return findPaginated(1, model); 
	}
	
	 @PreAuthorize("hasRole('ADMIN')")
	 @GetMapping("/showNewEmployeeForm")
	 public String showNewEmployeeForm(Model model) {
	     Employee employee = new Employee();
	     model.addAttribute("employee", employee);
	     return "new_employee";
	 }
	 
	 @PreAuthorize("hasRole('ADMIN')")
	 @PostMapping("/saveEmployee")
	 public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	     employeeService.saveEmployee(employee);
	     return "redirect:/";
	 }
	 
	 @PreAuthorize("hasRole('ADMIN')")
	 @GetMapping("/showFormForUpdate/{id}")
	 public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
	  Employee employee = employeeService.getEmployeeById(id);
	  model.addAttribute("employee", employee);
	  return "update_employee";
	 }
	 
	 @PreAuthorize("hasRole('ADMIN')")
	 @GetMapping("/deleteEmployee/{id}")
	 public String deleteEmployee(@PathVariable (value = "id") long id) {
	  this.employeeService.deleteEmployeeById(id);
	  return "redirect:/";
	 }
	 
	 @GetMapping("/page/{pageNo}")
	 public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	     int pageSize = 5;

	     Page < Employee > page = employeeService.findPaginated(pageNo, pageSize);
	     List < Employee > listEmployees = page.getContent();

	     model.addAttribute("currentPage", pageNo);
	     model.addAttribute("totalPages", page.getTotalPages());
	     model.addAttribute("totalItems", page.getTotalElements());
	     model.addAttribute("listEmployees", listEmployees);
	     return "index";
	 }
	 
	  @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
	        Authentication authentication;
	        try {
	            authentication = authenticationManager
	                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	        } catch (AuthenticationException exception) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("message", "Bad credentials");
	            map.put("status", false);
	            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	        }

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

	        List<String> roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());

	        LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

	        return ResponseEntity.ok(response);
	    }
}
