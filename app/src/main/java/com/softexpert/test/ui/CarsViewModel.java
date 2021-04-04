package com.softexpert.test.ui;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softexpert.test.data.models.CarsResponse;
import com.softexpert.test.data.repository.CarsRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CarsViewModel extends ViewModel {

    private MutableLiveData<CarsResponse> carsListMutableLiveData;
    private CarsRepository carsRepository;
    private CompositeDisposable disposable = new CompositeDisposable();

    public CarsViewModel() {
        carsRepository = new CarsRepository();
        carsListMutableLiveData = new MutableLiveData<>();
    }

    public void getCarsList(int page) {
        disposable.add(
                carsRepository
                        .getCarsList(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<CarsResponse>() {
                            @Override
                            public void onSuccess(@NonNull CarsResponse carsResponse) {
                                carsListMutableLiveData.postValue(carsResponse);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        }));
    }

    public MutableLiveData<CarsResponse> getCarsListMutableLiveData() {
        return carsListMutableLiveData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
