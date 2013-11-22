package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MedicineData;
import model.MedicineDataAO;

public class MedicineHistory extends HttpServlet {

		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {
			
			MedicineDataAO md = new MedicineDataAO();
			
			List<MedicineData> medicineJson = md.getPastData();
			
			request.setAttribute("medicine", medicineJson);
			
			
		    request.getRequestDispatcher("/medicineHistory.jsp").forward(request, response);
			
		}
		
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
	}
}
