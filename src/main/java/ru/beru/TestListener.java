package ru.beru;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends Preset implements ITestListener {

    @Override
    public void onTestStart(ITestResult tr) {

    }

    @Override
    public void onTestFailure(ITestResult tr) {
        Date dat = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        screen.saveAllureScreenshotError("ERROR " + simpleDateFormat.format(dat));
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
    }

    @Override
    public void onStart(ITestContext tr) {
    }

    @Override
    public void onFinish(ITestContext tr) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult res) {
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
    }
}
