package com.zy.net.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author:zhangyue
 * @date:2020/9/10
 */
public class BaseObservable {
    public static <T> void doObservable(Observable<T> t, BaseObserver<T> observer){
        t.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
