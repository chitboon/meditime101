
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitbit.api.model.APIResourceCredentials;

import model.MedicineData;
import model.MedicineDataAO;
import model.ResourceCredentials;
import model.ResourceCredentialsAO;

public class medicineServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		MedicineDataAO md = new MedicineDataAO();
		
		List<MedicineData> medicineJson = md.getMedicineData();
		
		request.setAttribute("medicine", medicineJson);
		
		
	    request.getRequestDispatcher("/checkMedicine.jsp").forward(request, response);
		
		PrintWriter out = response.getWriter();
		out.println(medicineJson.get(0));
	}
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out =response.getWriter();
		
		
		String name = request.getParameter("name");
		int tablet = Integer.parseInt(request.getParameter("tablet"));
		String date = request.getParameter("date");
		String duration = request.getParameter("duration");
		out.println(duration);
		
		medicineBean med = new medicineBean();
		med.setDate(date);
		med.setDuration(duration);
		med.setName(name);
		med.setTablet(tablet);
		

		int intDuration = 0;
		int dayToFinish = 0;
		
		if(duration.equals("once"))
		{
			intDuration = 1;	
			dayToFinish = tablet/intDuration;
		}
		
		else if(duration.equals("twice"))
		{
			intDuration = 2;	
			dayToFinish = tablet/intDuration;	
		}
		else if (duration.equals("thrice"))
		{
			intDuration = 3;
			 dayToFinish = tablet/intDuration;
		}
		else if(duration.equals("thrice"))
		{
			intDuration = 4;
			dayToFinish = tablet/intDuration;
		}
		
		
		String stDay = date.substring(0, 2);
		String stMonth = date.substring(3, 5);
		String stYear = date.substring(6, 10);
		
		
		int intDay = Integer.parseInt(stDay);
		int intMonth = Integer.parseInt(stMonth);
		int intYear = Integer.parseInt(stYear);
		
		
		int finishDate = intDay + dayToFinish;

		Random randomNumber = new Random();
		long randomInt = randomNumber.nextInt(10000000);
		
//		MedicineData medDate = new MedicineData();
//		
//		medDate.setDuration(duration);
//		medDate.setName(name);
//		medDate.setStartDate(date);
//		medDate.setTablet(tablet);
//		

		 MedicineData md = new MedicineData();
         MedicineDataAO ao = new MedicineDataAO();


         
		if(intMonth == 1)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);			
		
			out.print(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 2)
		{
			if(finishDate>28)
			{
			finishDate = finishDate - 28;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 3)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 4)
		{
			if(finishDate>30)
			{
			finishDate = finishDate - 30;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 5)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 6)
		{
			if(finishDate>30)
			{
			finishDate = finishDate - 30;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 7)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 8)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 9)
		{
			if(finishDate>30)
			{
			finishDate = finishDate - 30;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 10)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 11)
		{
			if(finishDate>61)
			{
				finishDate = finishDate - 61;
				
				intMonth = 1;
				stYear = "2014";
				
				//out.println(finishDate);
				
				request.setAttribute("intDay", finishDate);
				request.setAttribute("intMonth", intMonth);
				
		         ao.add(randomInt, name, date, Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear, duration, tablet);
		         request.setAttribute("id",randomInt);
		         request.setAttribute("name",name);
		         request.setAttribute("startDate",date);
		         request.setAttribute("endDate",Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		         request.setAttribute("duration",duration);
		         request.setAttribute("tablet",tablet);
				
			}
			
			else if(finishDate>30)
			{
			finishDate = finishDate - 30;

			intMonth++;
			
			//out.println(finishDate);
			
			request.setAttribute("intDay", finishDate);
			request.setAttribute("intMonth", intMonth);
			
	         ao.add(randomInt, name, date, Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear, duration, tablet);
	         request.setAttribute("id",randomInt);
	         request.setAttribute("name",name);
	         request.setAttribute("startDate",date);
	         request.setAttribute("endDate",Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
	         request.setAttribute("duration",duration);
	         request.setAttribute("tablet",tablet);
			
			
			out.print(Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
			else
			{
				//out.println(finishDate);
				
				request.setAttribute("intDay", finishDate);
				request.setAttribute("intMonth", intMonth);
				ao.add(randomInt, name, date, Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear, duration, tablet);
		         request.setAttribute("id",randomInt);
		         request.setAttribute("name",name);
		         request.setAttribute("startDate",date);
		         request.setAttribute("endDate",Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		         request.setAttribute("duration",duration);
		         request.setAttribute("tablet",tablet);
				
		 		out.print(Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		else if (intMonth == 12)
		{
			if(finishDate>31)
			{
			finishDate = finishDate - 31;
			intDay = finishDate;
			intMonth++;
			
			request.setAttribute("intDay", intDay);
			request.setAttribute("intMonth", intMonth);
			//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
			}
		}
		
//		 request.getRequestDispatcher("/checkMedicine.jsp").forward(request, response);

	}

}
