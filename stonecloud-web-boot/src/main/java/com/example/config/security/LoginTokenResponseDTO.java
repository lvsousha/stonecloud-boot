package com.example.config.security;

import java.io.Serializable;

import com.example.model.AdminUser;

import lombok.Data;

/**
 * 登录成功令牌
 * 
 * @author lb
 *
 */
@Data
public class LoginTokenResponseDTO implements Serializable {

  private static final long serialVersionUID = 940644291147800571L;

  /**
   * 登录用户信息
   */
  private AdminUser loginInfo;

  /**
   * 认证令牌
   */
  private String authToken;

  /**
   * 刷新令牌
   */
  private String refreshToken;

}
