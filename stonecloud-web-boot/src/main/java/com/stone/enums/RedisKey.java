package com.stone.enums;

public enum RedisKey {
  /**
   * 短信验证码
   */
  SMS_CODE(String.class),
  
  /**
   * 邮件验证码
   */
  EMAIL_CODE(String.class),

  /**
   * 控制短信频率
   */
  SMS_FREQUENCY(String.class),

  /**
   * 保存 用户身份证和姓名验证是否成功的key
   */
  IDCARDVALID_CODE(Boolean.class),

  SHORT_URL(String.class),

  FAMILY(String.class);

  private Class<?> clazz;

  private <T> RedisKey(Class<T> clazz) {
    this.clazz = clazz;
  }

  @SuppressWarnings("unchecked")
  public <T> Class<T> getClazz() {
    return (Class<T>) clazz;
  }
}
