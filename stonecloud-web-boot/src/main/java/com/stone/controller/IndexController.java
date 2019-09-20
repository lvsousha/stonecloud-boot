package com.stone.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  private Logger log = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private RepositoryService repositoryService;

  @RequestMapping("/")
  public ModelAndView home() {
    log.info("Home");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("index");
    return mav;
  }

  @RequestMapping("/process/showPng")
  public void showPng(HttpServletResponse response) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
      BpmnModel bpmnModel =
          repositoryService.getBpmnModel("AVOID_ARBITRATOR:1:3f465cc3-db60-11e9-8946-0a0027000006");
      DefaultProcessDiagramGenerator defaultProcessDiagramGenerator =
          new DefaultProcessDiagramGenerator();
      List<String> highLightedActivities = new ArrayList<String>();
      List<String> highLightedFlows = new ArrayList<String>();
      is = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "JPG", highLightedActivities,
          highLightedFlows, "宋体", "宋体", "宋体", null, 5.0, false);
      response.setContentType("image/jpg");
      os = response.getOutputStream();
      byte[] content = new byte[1024];
      while (is.read(content) != -1) {
        os.write(content);
      }
      os.flush();
    } finally {
      if (is != null) {
        is.close();
      }
      if (os != null) {
        os.close();
      }
    }
  }

}
