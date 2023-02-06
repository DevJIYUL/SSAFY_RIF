package team.a501.rif.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordChangeRequest {
    @NotBlank(message = "현재 비밀번호는 필수 입력하셔야합니다.🐳")
    private String currentPassword;
    @NotBlank(message = "바꾸실 비밀번호를 입력해 주세요")
    @Pattern(regexp = "[ㄱ-ㅎa-zA-Z0-9]{4,12}")
    private String newPassword;
    @NotBlank(message = "바꾸실 비밀번호를 다시 입력해 주세요")
    @Pattern(regexp = "[ㄱ-ㅎa-zA-Z0-9]{4,12}")
    private String newPasswordConfirm;
}
