package LogicPart;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
public class Mainlogic2 implements Job {
	private static final Logger log = Logger.getLogger(Mainlogic2.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("my-persistence-unit");
		  EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		  EntityTransaction entitytrasaction = entitymanager.getTransaction();
		try {
			entitytrasaction.begin();
	         String file = "C:/Users/nagsen.shinde/Documents/studentInfo.xlsx";
	         FileInputStream fileinputstring = new FileInputStream(file);
	         XSSFWorkbook workbook = new XSSFWorkbook(fileinputstring);
			 XSSFSheet sheet = workbook.getSheet("Sheet1");
			   Iterator<Row>iterator = sheet.iterator();
			 // System.out.println("Hiii");
			  iterator.next();
			  while(iterator.hasNext())
			  {
				  Row row = iterator.next();
				  PersonEntityClass entityclass = new PersonEntityClass();
				  entityclass.setId((int) row.getCell(0).getNumericCellValue());
				  entityclass.setName(row.getCell(1).getStringCellValue());
				  entityclass.setPhoneno((long) row.getCell(2).getNumericCellValue());
				  System.out.println(entityclass);
				  entitymanager.persist(entityclass);   
			  }
		
	      entitytrasaction.commit();
	      System.out.println("Data imported sucessfully");
	 
	}	 
    catch(Exception e)
		{
    	if(entitytrasaction!= null  && entitytrasaction.isActive())
		{
			entitytrasaction.rollback();
		}
		System.out.println("data not found");
		}
  }
		
}

    
		    


