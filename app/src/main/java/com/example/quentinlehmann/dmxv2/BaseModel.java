package com.example.quentinlehmann.dmxv2;

/**
 * Représente une classe avec des outils de notification lors ce qu'un champs change.
 */
public class BaseModel {

    /**
     * Interface servant à faire la notification
     */
    public interface PropertyChangedListener
    {
        void OnPropertyChanged (String propertyName);
    }

    /**
     * Interface servant à faire la notification
     */
    private PropertyChangedListener onPropertyChanged;

    /**
     * Renvoie l'interface de notification d'événement
     * @return eventNotifier
     */
    public PropertyChangedListener getOnPropertyChanged() {
        return onPropertyChanged;
    }

    /**
     * Renseigne l'interface de notification d'événement
     * @param onPropertyChanged
     */
    public void setOnPropertyChanged(PropertyChangedListener onPropertyChanged) {
        this.onPropertyChanged = onPropertyChanged;
    }

    /**
     * Sert à notifier un changement d'état sur un champs
     * @param propertyName
     */
    protected void NotifyPropertyChanged (String propertyName) {
        if (onPropertyChanged != null)
            getOnPropertyChanged().OnPropertyChanged(propertyName);
    }
}
