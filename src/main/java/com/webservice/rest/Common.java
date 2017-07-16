package com.webservice.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

import com.webservice.common.Utils;
import com.webservice.dto.BikeExpenseTO;
import com.webservice.dto.ConstituentsTO;
import com.webservice.dto.DrugInfoTO;
import com.webservice.dto.EmployeeTO;
import com.webservice.dto.MyTimerTO;
import com.webservice.dto.ScheduleTO;
import com.webservice.dto.StringUtil;
import com.webservice.model.BikeExpense;
import com.webservice.model.DrugInfo;
import com.webservice.model.Employee;
import com.webservice.model.MyTimer;
import com.webservice.model.Property;
import com.webservice.model.StautusEnum;

public class Common {

	public static EmployeeTO transformToEmployeeTO(Employee emp){
		EmployeeTO employeeTO = new EmployeeTO();
		employeeTO.setLastName(emp.getLastName());
		employeeTO.setFirstName(emp.getFirstName());
		employeeTO.setEmail(emp.getEmail());
		employeeTO.setPhone(emp.getPhone());
		if(emp.getId() != null){
			employeeTO.setId(emp.getId());
		}
		return employeeTO;
	}

	
	public static DrugInfoTO transformToDrugTO(DrugInfo drugInfo){
		DrugInfoTO drugInfoTO = new DrugInfoTO();
		drugInfoTO.setForm(drugInfo.getForm());
		drugInfoTO.setId(drugInfo.getId());
		//drugInfoTO.setLstConstituents(drugInfo.getConstituents());
		drugInfoTO.setManufacturer(drugInfo.getManufacturer());
		drugInfoTO.setMedicine_id(drugInfo.getDrugId());
		drugInfoTO.setName(drugInfo.getName());
		drugInfoTO.setPackageForm(drugInfo.getPkgForm());
		drugInfoTO.setPrice(drugInfo.getPrice());
		//drugInfoTO.setSchedule(drugInfo.getSchedule());
		drugInfoTO.setSize(drugInfo.getSize());
		drugInfoTO.setStandardUnits(drugInfo.getSchedule());
		
		
		return drugInfoTO;
	}
	
	public static DrugInfo transformToDrugInfo(DrugInfoTO drugInfoTO){
		DrugInfo drugInfoo = new DrugInfo();
		drugInfoo.setForm(drugInfoTO.getForm());
		drugInfoo.setId(drugInfoTO.getId());
		List<ConstituentsTO> lst = drugInfoTO.getConstituents();
		StringBuilder str = new StringBuilder();
		if(lst != null && !lst.isEmpty()){
			
			for(ConstituentsTO row: lst){
				if(row.getName() != null){
					str.append(row.getName());
					str.append("_");
				}
				if(row.getStrength() != null){
					str.append(row.getStrength());
					str.append("|");
				}
			}
		}
		drugInfoo.setConstituents(str.toString());
		drugInfoo.setManufacturer(drugInfoTO.getManufacturer());
		drugInfoo.setDrugId(drugInfoTO.getMedicine_id());
		drugInfoo.setName(drugInfoTO.getName());
		drugInfoo.setPkgForm(drugInfoTO.getPackageForm());
		drugInfoo.setPrice(drugInfoTO.getPrice());
		ScheduleTO sch = drugInfoTO.getSchedule();
		if(sch != null){
			StringBuilder strrr = new StringBuilder();
			strrr.append(sch.getCategory());
			strrr.append("_");
			strrr.append(sch.getLabel());
			drugInfoo.setSchedule(strrr.toString());
		}
		
		drugInfoo.setSize(drugInfoTO.getSize());
		drugInfoo.setStandardUnits(drugInfoTO.getStandardUnits());
		
		
		return drugInfoo;
	}

	private static final String DATE_TIME_FORMAT="dd-MMM-yyyy";

	public static Date convertStringToDate(String srcDate) throws ParseException{
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.parse(srcDate);
	}

	public static String convertDateToString(Date srcDate){
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.format(srcDate);
	}

	public static Employee transformToEmployee(EmployeeTO empTo){
		Employee employee = new Employee();
		employee.setLastName(empTo.getLastName());
		employee.setFirstName(empTo.getFirstName());
		employee.setEmail(empTo.getEmail());
		employee.setPhone(empTo.getPhone());
		if(empTo.getId() != null){
			employee.setId(empTo.getId());
		}

		return employee;
	}


	public static BikeExpenseTO transformToBikeExpenseTO(BikeExpense bikeExpense){
		BikeExpenseTO bikeExpenseTO = new BikeExpenseTO();
		bikeExpenseTO.setAmount(bikeExpense.getAmount());
		if(bikeExpense.getEventDate() != null){
			bikeExpenseTO.setEventDate(convertDateToString(bikeExpense.getEventDate()));
		}
		bikeExpenseTO.setId(bikeExpense.getId());
		if(bikeExpense.getMeterReading() != null){
			bikeExpenseTO.setMeterReading(bikeExpense.getMeterReading().toString());
		}

		if(bikeExpense.getPetrolQty() != null){
			bikeExpenseTO.setPetrolQty(String.format("%.2f", bikeExpense.getPetrolQty()));
		}
		if(bikeExpense.getPricePerLtr() != null){
			bikeExpenseTO.setPricePerLtr(String.format("%.2f", bikeExpense.getPricePerLtr()));
		}

		bikeExpenseTO.setReason(bikeExpense.getReason());
		return bikeExpenseTO;
	}

	public static BikeExpense transformToBikeExpense(BikeExpenseTO bikeExpenseTO) throws ParseException{
		BikeExpense bikeExpense = new BikeExpense();
		if(bikeExpenseTO.getAmount() != null){
			bikeExpense.setAmount(bikeExpenseTO.getAmount());
		}
		if(bikeExpenseTO.getEventDate() != null){
			bikeExpense.setEventDate(Common.convertStringToDate(bikeExpenseTO.getEventDate()));
		}
		if(bikeExpenseTO.getMeterReading() != null){
			bikeExpense.setMeterReading(Integer.parseInt(bikeExpenseTO.getMeterReading()));
		}
		if(bikeExpenseTO.getPetrolQty() != null){
			bikeExpense.setPetrolQty(Double.parseDouble(bikeExpenseTO.getPetrolQty()));
		}
		if(bikeExpenseTO.getId() != null){
			bikeExpense.setId(bikeExpenseTO.getId());
		}
		if(bikeExpenseTO.getPricePerLtr() != null){
			bikeExpense.setPricePerLtr(Double.parseDouble(bikeExpenseTO.getPricePerLtr()));
		}
		if(bikeExpenseTO.getReason() != null){
			bikeExpense.setReason(bikeExpenseTO.getReason());
		}

		return bikeExpense;
	}


	public static MyTimer transformToMyTimer(MyTimerTO myTimerTO) throws ParseException{
		MyTimer myTimer = new MyTimer();
		if(!StringUtil.isNullCombo(myTimerTO.getReason())){
			myTimer.setReason(myTimerTO.getReason());
		}
		if(!StringUtil.isNullCombo(myTimerTO.getStartDate())){
			myTimer.setStartDate(convertStringToDate(myTimerTO.getStartDate()));
		}
		if(myTimerTO.getId() != null){
			myTimer.setId(myTimerTO.getId());
		}
		if(!StringUtil.isNullCombo(myTimerTO.getStatus())){
			myTimer.setStatus(StautusEnum.valueOf(myTimerTO.getStatus()));
		}else{
			myTimer.setStatus(StautusEnum.A);
		}
		return myTimer;
	}

	public static MyTimerTO transformToMyTimerTO(MyTimer myTimer){
		MyTimerTO myTimerTO = new MyTimerTO();
		if(myTimer.getId() != null){
			myTimerTO.setId(myTimer.getId());
		}
		if(myTimer.getReason() != null){
			myTimerTO.setReason(myTimer.getReason());
		}
		if(myTimer.getStartDate() != null){

			myTimerTO.setStartDate(convertDateToString(myTimer.getStartDate()));
			String date = Utils.calDateInStr(myTimer.getStartDate());
			if(!StringUtil.isNullOrBlank(date)){
				myTimerTO.setTotalTime(date);
			}
			String nextEventDate = Utils.nextEventDays(myTimer.getStartDate());
			if(!StringUtil.isNullOrBlank(nextEventDate)){
				myTimerTO.setNextOccTime(nextEventDate);
			}
		}
		if(myTimer.getStatus() != null){
			myTimerTO.setStatus(myTimer.getStatus().toString());
		}
		return myTimerTO;
	}

	
	public static EmployeeTO transformToEmployeeTO(Property emp){
		EmployeeTO employeeTO = new EmployeeTO();
		employeeTO.setLastName(emp.getLastName());
		employeeTO.setFirstName(emp.getFirstName());
		employeeTO.setEmail(emp.getEmail());
		employeeTO.setPhone(emp.getPhone());
		return employeeTO;
	}
	
	public static Property transformToEmployeeee(EmployeeTO empTo){
		Property employee = new Property();
		employee.setLastName(empTo.getLastName());
		employee.setFirstName(empTo.getFirstName());
		employee.setEmail(empTo.getEmail());
		employee.setPhone(empTo.getPhone());
//		if(empTo.getId() != null){
//			employee.setId(empTo.getId());
//		}

		return employee;
	}
	

	public static Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		Map<String, String> responseObj = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

}
