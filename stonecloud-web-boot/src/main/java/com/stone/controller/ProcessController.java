package com.stone.controller;

import java.util.HashMap;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "atart")
    @ResponseBody
    public String atart() {
      log.info("启动回避仲裁员");
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("assistantId", "助理");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("AVOID_ARBITRATOR", map);
        return "提交成功.流程Id为：" + processInstance.getId();
    }

    @RequestMapping(value = "/audit")
    @ResponseBody
    public Object audit(String operate, String taskId) {
      taskService.setVariableLocal(taskId, "operateType", operate);
      taskService.setVariable(taskId, "operate", operate);
      taskService.complete(taskId);
        return "成功";
    }
}
