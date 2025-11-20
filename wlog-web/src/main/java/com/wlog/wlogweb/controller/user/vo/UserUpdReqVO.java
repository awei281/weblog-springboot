 package com.wlog.wlogweb.controller.user.vo;

 import io.swagger.v3.oas.annotations.media.Schema;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import javax.validation.constraints.Pattern;
 import javax.validation.constraints.Size;

 /**
  * @author： wsw
  * @date： 2025/11/17 15:45
  * @describe：
  */
 @Data
 @Builder
@NoArgsConstructor
@AllArgsConstructor
 public class UserUpdReqVO {

     @Schema(description = "用户编号", example = "1024")
     private Long id;

     @Schema(description = "用户名")
     private String username;

     @Schema(description = "新密码")
     @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$", message = "新密码 数字、字母 组成")
     @Size(min = 3, max = 30, message = "新密码长度为 4-30 个字符")
     private String password;

     @Schema(description = "老密码")
     private String rePassword;

 }
