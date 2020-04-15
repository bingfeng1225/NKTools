package cn.qd.peiwen.pwtools.rxjava;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by nick on 2017/9/5.
 */

public abstract class ResponseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        this.onStart();
    }

    @Override
    public void onNext(T entity) {
        this.onSuccess(entity);
    }

    @Override
    public void onError(Throwable e) {
        this.onFailure(e);
        this.onCompleted();
    }

    @Override
    public void onComplete() {
        this.onCompleted();
    }

    protected abstract void onStart();

    protected abstract void onSuccess(T entity);

    protected abstract void onFailure(Throwable e);

    protected abstract void onCompleted();
}
