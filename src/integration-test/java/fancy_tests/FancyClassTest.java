package fancy_tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fancy.Fancy;
import org.approvaltests.Approvals;
import org.junit.*;
import test_helper.LogAppenderTestRule;

import java.util.Arrays;


public class FancyClassTest
{
    @ClassRule
    public static LogAppenderTestRule appender = new LogAppenderTestRule("approval_test");

    @Test
    public void testList() throws Exception
    {
        String[] names = {"Llewellyn", "James", "Dan", "Jason", "Katrina"};
        Arrays.sort(names);
        Approvals.verifyAll("", names);
    }

    @Test
    public void testListToJson() throws Exception
    {
        String[] names = {"Llewellyn", "James", "Dan", "Jason", "Katrina"};
        Arrays.sort(names);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Approvals.verify(gson.toJson(names));
    }

    @Test
    public void testLogs() throws Exception
    {
        Fancy fancy = new Fancy();
        String greeting = fancy.hello("you");
        String logOutput = appender.getOutput();
        System.out.println("log output: " + logOutput);
        Approvals.verify(logOutput);
        Assert.assertEquals("Hello, you", greeting);
    }
}