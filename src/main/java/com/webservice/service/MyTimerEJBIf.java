package com.webservice.service;

import java.util.List;

import com.webservice.model.MyTimer;

public interface MyTimerEJBIf {

	public boolean addUpdateDelMyTimer(MyTimer myTimer) throws Exception;

	public MyTimer getMyTimerById(Integer id) throws Exception;

	public List<MyTimer> getMyTimersByFilter(MyTimer myTimer) throws Exception;


}
