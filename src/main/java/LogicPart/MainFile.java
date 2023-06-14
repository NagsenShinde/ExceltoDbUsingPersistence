package LogicPart;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MainFile {
	
	static final Logger logger = Logger.getLogger(MainFile.class);
	public static void main(String args[]) throws SchedulerException
	{
		BasicConfigurator.configure();
		JobDetail job = JobBuilder.newJob(Mainlogic2.class).build();
		logger.info("Creating the job" +job);
		
		Trigger t2 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? *  *")).build();
		logger.info("Trigger Information"+t2);
		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
		logger.info("Scheduling the job---->" +s);
		s.start();
		s.scheduleJob(job,t2);
		
//		Mainlogic2 mainlogic2 = new Mainlogic2();
//		try {
//			mainlogic2.book1();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
