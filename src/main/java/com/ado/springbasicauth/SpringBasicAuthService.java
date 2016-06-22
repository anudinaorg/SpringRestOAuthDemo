package com.ado.springbasicauth;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ado.springbasicauth.domain.Employee;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SpringBasicAuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBasicAuthService.class);
	
	static Set<Employee> empSet = null;
	static{
		empSet = new HashSet<Employee>();
		for(int i=0;i<10;i++){
			empSet.add(new Employee(i, "EMPNAME"+i));
		}
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="/getjsonlist",method=RequestMethod.GET,headers = "Accept=application/json", produces = {"application/json"})
	@ResponseBody
	public Set<Employee> getAllEmployeJson(){
		return empSet;
	}
	
	@RequestMapping(value="/gethtmllist",method=RequestMethod.GET,produces={"text/html"},headers="Accept=text/html")
	@ResponseBody
	public String getAllEmployeHtml(){
		String retVal = "<html><body><table border=1>";
		for(Employee e:empSet){
			retVal += "<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td></tr>";
		}
		retVal += "</table></body></html>";
		return retVal;
	}
	
}
