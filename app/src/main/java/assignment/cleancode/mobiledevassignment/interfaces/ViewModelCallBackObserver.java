package assignment.cleancode.mobiledevassignment.interfaces;


import assignment.cleancode.mobiledevassignment.enums.ViewModelEventsEnum;

public interface ViewModelCallBackObserver<T> {

    void onObserve(ViewModelEventsEnum event, T eventMessage);

}
