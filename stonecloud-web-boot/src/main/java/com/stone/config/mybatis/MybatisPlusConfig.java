package com.stone.config.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MybatisPlusConfig {
  /***
   * plus 的性能优化
   * 
   * @return
   */
  @Bean
  public PerformanceInterceptor performanceInterceptor() {
    log.info("mybatis-plus性能分析插件");
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    /* <!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 --> */
    performanceInterceptor.setMaxTime(2L);
    /* <!--SQL是否格式化 默认false--> */
    performanceInterceptor.setFormat(false);
    performanceInterceptor.setWriteInLog(true);
    return performanceInterceptor;
  }

  /**
   * @Description : mybatis-plus分页插件 ---------------------------------
   * @Author : Liang.Guangqing
   * @Date : Create in 2017/9/19 13:59
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    log.info("mybatis-plus分页插件");
    return new PaginationInterceptor();
  }

  @Bean
  public ExecutorPlugin ExecutorPlugin() {
    return new ExecutorPlugin();
  }

}
