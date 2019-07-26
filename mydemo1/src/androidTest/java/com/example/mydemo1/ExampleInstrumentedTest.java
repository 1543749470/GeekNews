package com.example.mydemo1;

import android.content.Context;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.jy.bean.BaseResponse;
import com.jy.bean.ListData;
import com.jy.http.HttpManager;
import com.jy.http.MySercer;
import com.jy.utils.RxUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.mydemo1", appContext.getPackageName());
    }

    @Test
    public void http() {
        HttpManager.getInstance().getServer(MySercer.class)
                .get("wxarticle/chapters/json")
                .compose(RxUtils.<BaseResponse<List<ListData>>>rxScheduleThread())
                .compose(RxUtils.<List<ListData>>changeResult())
                .subscribe(new Consumer<List<ListData>>() {
                    @Override
                    public void accept(List<ListData> listData) throws Exception {
                        Log.e("listData", "accept: " + listData.toString());
                    }
                });
    }

}