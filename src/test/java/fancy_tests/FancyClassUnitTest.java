package fancy_tests;

import org.junit.Assert;
import org.junit.Test;


public class FancyClassUnitTest
{
    @Test
    public void testList() throws Exception
    {
        String[] names = {"Hello", "World"};
        Assert.assertEquals("Hello", names[0]);
        Assert.assertEquals("World", names[1]);
    }
}