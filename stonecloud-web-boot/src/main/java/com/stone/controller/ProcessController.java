package com.stone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "test")
    @ResponseBody
    public void test(@RequestBody JSONObject param) {
      log.info("启动回避仲裁员" + param.getString("id"));
    }

    @RequestMapping(value = "start")
    @ResponseBody
    public String start() {
      log.info("启动回避仲裁员");
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("assistantId", "助理");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("AVOID_ARBITRATOR", map);
        return "提交成功.流程Id为：" + processInstance.getId();
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<JSONObject> list() {
      List<Task> tasks = taskService.createTaskQuery().list();
      List<JSONObject> list = new ArrayList<>();
      for(Task task : tasks) {
        JSONObject model = new JSONObject();
        ProcessDefinition processDefinition= repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        model.put("process", processDefinition.getName());
        model.put("id", task.getId());
        model.put("name", task.getName());
        model.put("def", task.getTaskDefinitionKey());
        list.add(model);
      }
        return list;
    }

    @RequestMapping(value = "/audit")
    @ResponseBody
    public Object audit(@RequestBody JSONObject param) {
      String taskId = param.getString("taskId");
      String operate = param.getString("operate");
      taskService.setVariableLocal(taskId, "operateType", operate);
      taskService.setVariable(taskId, "operate", operate);
      taskService.complete(taskId);
        return "成功";
    }
}
