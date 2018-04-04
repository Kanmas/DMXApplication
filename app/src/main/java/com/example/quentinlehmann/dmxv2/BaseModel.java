package com.example.quentinlehmann.dmxv2;

/**
 * Created by victor.mendele on 04/04/2018.
 */

public class BaseModel {

    public interface PropertyChangedListener
    {
        void OnPropertyChanged (String propertyName);
    }

    private PropertyChangedListener onPropertyChanged;

    public PropertyChangedListener getOnPropertyChanged() {
        return onPropertyChanged;
    }

    public void setOnPropertyChanged(PropertyChangedListener onPropertyChanged) {
        this.onPropertyChanged = onPropertyChanged;
    }

    protected void NotifyPropertyChanged (String propertyName) {
        if (onPropertyChanged != null)
            getOnPropertyChanged().OnPropertyChanged(propertyName);
    }
}
