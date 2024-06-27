package ramlaljava.testComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ramlaljava.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject(); //initialize extent object from other class
	ExtentTest test; //create object for each test case and stores
	ThreadLocal thread = new ThreadLocal(); //making thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName()); //creating extentReport before starting any testCase
		//test entry dynamically and automatically created
		
		//making thread safe for each test case to avoid concurrency issue
		thread.set(test); //unique thread ID created for each test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		((ExtentTest) thread.get()).log(Status.PASS, "success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		((ExtentTest) thread.get()).fail(result.getThrowable()); //failing dynamically as per respective thread ID
		
		//initialize driver
		try {
			d = (WebDriver) result.getTestClass().getRealClass().getField("d").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//take screenshot and attach to report
		String filePath = null;
		try {
			 filePath = getScreenshot(result.getMethod().getMethodName(), d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ExtentTest) thread.get()).addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush(); //extentReport stops and finally report generated
	}

	
}
