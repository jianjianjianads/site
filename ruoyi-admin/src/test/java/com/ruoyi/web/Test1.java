package com.ruoyi.web;

import com.ruoyi.project.domain.*;
import com.ruoyi.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

@SpringBootTest
public class Test1 {
    @Autowired
    private IElevatorFactoryService elevatorFactoryService;

    @Autowired
    private  IProjectElevatorService projectElevatorService;

    @Autowired
    private IProjectSiteService projectSiteService;

    @Autowired
    private ISiteContractorService siteContractorService;

    @Autowired
    private IProjectTowerService projectTowerService;

    @Test
    public void test1(){
        ProjectElevator projectElevator =new ProjectElevator();
        ElevatorFactory elevatorFactory =new ElevatorFactory();
        elevatorFactory.setFactoryName("日");
        projectElevator.setElevatorFactory(elevatorFactory);
        List<ProjectElevator> projectElevators =projectElevatorService.selectProjectElevatorList(projectElevator);
        System.out.println(projectElevators);
    }
    @Test
    public void test2(){
        ProjectSite projectSite =new ProjectSite();
        SiteContractor siteContractor=new SiteContractor();
        siteContractor.setContractorName("中国");
        projectSite.setSiteContractor(siteContractor);
        List<ProjectSite> projectSites =projectSiteService.selectProjectSiteList(projectSite);
        System.out.println(projectSites);
    }
    @Test
    public void test4(){
        ProjectElevator projectElevator =new ProjectElevator();
        ProjectSite  projectSite=new ProjectSite();
        projectSite.setSiteName("聚福");
        projectElevator.setProjectSite(projectSite);
        List<ProjectElevator> projectElevators =projectElevatorService.selectProjectElevatorList(projectElevator);
        System.out.println(projectElevators);
    }
    @Test
    public  void  test5(){
        ProjectTower projectTower =new ProjectTower();
        ProjectSite  projectSite=new ProjectSite();
        projectSite.setSiteName("聚福");
        projectTower.setProjectSite(projectSite);
        List<ProjectTower> projectTowers=projectTowerService.selectProjectTowerList(projectTower);
        System.out.println(projectTowers);

    }
}
