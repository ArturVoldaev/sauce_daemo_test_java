package utils;

import fw.ApplicationManager;
import model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    public static final ApplicationManager app = new ApplicationManager();
    @DataProvider
    static public Iterator<Object[]> negativeUsers() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"test", "test"});
        list.add(new Object[]{"test1", ""});
        list.add(new Object[]{"", "test"});
        list.add(new Object[]{"!@#$", "!@#$"});
//        list.add(new Object[]{app.getBaseHelper().LOCKED_OUT_USER, app.getBaseHelper().PASSWORD});

        return list.iterator();
    }

    @DataProvider
    static public Iterator<Object[]> negativeUsersFromCVS() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/negativeUsers.csv")));

        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new User().setUserName(split[0]).setPassword(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    static public Iterator<Object[]> positiveUsers() {
        System.out.println("fsdfsdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ app.getBaseHelper().STANDARD_USER, app.getBaseHelper().PASSWORD});
        list.add(new Object[]{app.getBaseHelper().PROBLEM_USER, app.getBaseHelper().PASSWORD});
        list.add(new Object[]{app.getBaseHelper().PERF_USER, app.getBaseHelper().PASSWORD});
        System.out.println(list);
        return list.iterator();
    }
}
