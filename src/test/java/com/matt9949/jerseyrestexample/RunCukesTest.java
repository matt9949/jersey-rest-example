package com.matt9949.jerseyrestexample;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "json:target/report.json", "html:target/html-report", "junit:target/surefire-reports/junit.xml"})
public class RunCukesTest {
}
