package testing.example.com;


import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import android.support.test.uiautomator.By;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.lang.Thread;
import java.lang.InterruptedException;

public class MainActivityTest {

    private String getLauncherPackageName(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    private UiDevice uiDevice;
    @Before
    public void setUp() throws Exception {

        // 初始化
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        String launcherPackageName = getLauncherPackageName();
        assertThat(launcherPackageName,notNullValue());
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)),5000);

    }


    @Test
    public void onCreate() throws UiObjectNotFoundException {


        // 打开APP
        UiObject openApp = new UiObject(new UiSelector().text("TestingExample"));
        openApp.click();

        //初始化一个UiDevice对象
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        //通过id找到显示结果的textview
        UiObject tvResult = mDevice.findObject(new UiSelector().resourceId("testing.example.com:id/test").className(TextView.class));
        //判断结果与预期是否匹配
        assertEquals(tvResult.getText(), "Hello World!");


        //Thread.sleep(2000);
        /*//19-27 行 其实就是用这个框架提供的功能来直接启动你的app.
        //这里其实主要就是要找到你的app那个textview 然后点击他 具体api自己去慢慢看吧
        //getUiDevice().pressHome();
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        UiObject myApp = appViews.getChildByText(new UiSelector()
                .className("android.widget.TextView"), "TestingExample");
        //要等到新的窗口出来才继续往下走
        myApp.clickAndWaitForNewWindow();
        //初始化一个UiDevice对象
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //通过id找到显示结果的textview
        UiObject tvResult = mDevice.findObject(new UiSelector().resourceId("testing.example.com:id/test").className(TextView.class));
        //判断结果与预期是否匹配
        assertEquals(tvResult.getText(), "计算结果：7");*/
    }
}