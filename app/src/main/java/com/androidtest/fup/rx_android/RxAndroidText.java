package com.androidtest.fup.rx_android;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidtest.fup.dextest.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;

//import java.util.Observable;

/**
 * Created by wuxin on 2018/5/27.
 */

public class RxAndroidText extends Activity{

    private Button mBtn;
    private TextView mTx;
    private long eventId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rx_android);

        mBtn = (Button) findViewById(R.id.sender);
        mTx = (TextView) findViewById(R.id.status);
    }


    private Observable<String> createObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                mBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emitter.onNext("eventId:" + eventId++);
                    }
                });

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        mBtn.setOnClickListener(null);
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Observable<String> observable = createObservable();

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mTx.setText("get text:" + s);
            }
        });
    }

    private Observable<String> createObservable1() {
        return Observable.create(new Observable.On())
    }
}
