package com.stone.utils.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;

public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Main main = new Main();
    main.avoidStaffProcess();
  }

  public void avoidStaffProcess() {
    ProcessEngine processEngine = init();
//    deploy(processEngine, "processes/avoidStaffProcess.bpmn.xml");
    deploy(processEngine, "processes/holiday-request.bpmn20.xml");
  }
  
  public Deployment deploy(ProcessEngine processEngine, String bpmnFile) {
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = repositoryService.createDeployment()
        .addClasspathResource(bpmnFile)
        .deploy();
    return deployment;
  }
  
  
  public ProcessEngine init() {
    ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
        .setJdbcUrl("jdbc:mysql://localhost:3306/springboot?useUnicode=yes&characterEncoding=UTF-8&useSSL=true")
        .setJdbcUsername("root")
        .setJdbcPassword("root")
        .setJdbcDriver("com.mysql.jdbc.Driver")
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

      ProcessEngine processEngine = cfg.buildProcessEngine();
      return processEngine;
  }
}
