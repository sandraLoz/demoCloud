package es.opplus.cloud.democloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.opplus.cloud.democloud.dto.UserDto;
import es.opplus.cloud.democloud.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Api(tags = "DemoUser API")
@Tag(name = "DemoUser API")

@RestController
@RequestMapping("/User")
public class DemoUserController {

	private Logger logger = LoggerFactory.getLogger(DemoUserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/")
	// @ApiOperation(value = "Create User", nickname = "Create User", response =
	// UserDto.class)
	@Operation(summary = "Create User", tags = "Create User")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
			@ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad request") })
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userCreate) {
		try {
			return new ResponseEntity<UserDto>(this.userService.createUser(userCreate), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			throw e;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete User", tags = "Delete User")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
//			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad request"),
//			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
//			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<String> deleteUserById(@PathVariable(required = true, name = "id") Long userId) {
		try {
			if (this.userService.deleteUserById(userId) > 0)
				return new ResponseEntity<>("Deleted User by userId " + userId, HttpStatus.OK);
			else
				return new ResponseEntity<>("User Delete Error", HttpStatus.BAD_REQUEST);

		} catch (ResponseStatusException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/")
	@Operation(summary = "Find all Users", tags = "Find all Users")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
//			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad request"),
//			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
//			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<UserDto>> findAll() {
		try {
			return ResponseEntity.ok(this.userService.findAll());

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}")
	@Operation(summary = "Find User By id", tags = "Find User By id")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
//			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad request"),
//			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
//			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<UserDto> findUserById(@PathVariable(required = true, name = "id") Long id)
			throws ResponseStatusException {

		return ResponseEntity.ok(this.userService.findUserById(id));

	}

	@PutMapping("/{id}")
	@Operation(summary = "User Update", tags = "User Update")
	/*@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })*/
	public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userUpd,
			@PathVariable(required = true, name = "id") Long id) {

		return ResponseEntity.ok(this.userService.updateUserById(id, userUpd));

	}
}
