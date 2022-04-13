package codesquad.be.todoserver.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTodoDto {

	@NotBlank
	@Size(max = 250)
	private String title;

	@Size(max = 500)
	private String contents;

	@NotBlank
	@Size(max = 20)
	private String user;

	@NotBlank
	@Size(max = 10)
	private String status;

}
