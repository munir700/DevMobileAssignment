package assignment.cleancode.mobiledevassignment.preferences;

import android.content.Context;

import javax.inject.Inject;

import assignment.cleancode.mobiledevassignment.utils.AppConstants;


public class PreferenceHandler {

    private final String LAST_SELECTED_SORT = "pref_last_selected_sort";
    private final String LAST_SELECTED_SORT_TITLE = "pref_last_selected_sort_title";
    private final String SORT_DIALOG_SELECTION = "sort_dialog_selection";

    private Preference preference = null;
    private int sortDialogSelection = 0;
    private String lastSelectedSort = "";

    @Inject
    public PreferenceHandler(Context pContext) {
        preference = new Preference(pContext);
    }



    public int getSortDialogSelection() {
        this.sortDialogSelection = preference.getIntegerPrefrence(SORT_DIALOG_SELECTION, 0);
        return sortDialogSelection;
    }

    public void setSortDialogSelection(int sortDialogSelection) {
        this.sortDialogSelection = sortDialogSelection;
        preference.saveIntegerInPrefrence(SORT_DIALOG_SELECTION, sortDialogSelection);
    }

    public void removeKeyFromPreference(String key){
        preference.remove(key);
    }


    public void setString(String key, String value) {
        preference.saveStringInPrefrence(key, value);
    }

    public String getString(String key, String value) {
        return preference.getStringPrefrence(key, value);
    }

    public String getString(String key) {
        return preference.getStringPrefrence(key);
    }


    public String getLastSelectedSort() {
        lastSelectedSort = preference.getStringPrefrence(LAST_SELECTED_SORT, AppConstants.DATE_DESC);
        return lastSelectedSort;
    }

    public void setLastSelectedSort(String lastSelectedSort) {
        this.lastSelectedSort = lastSelectedSort;
        preference.saveStringInPrefrence(LAST_SELECTED_SORT, lastSelectedSort);
    }


    public String getLastSelectedSortTitle() {
        return preference.getStringPrefrence(LAST_SELECTED_SORT_TITLE, AppConstants.DATE_DESC);
    }

    public void setLastSelectedSortTitle(String lastSelectedSortTitle) {
        preference.saveStringInPrefrence(LAST_SELECTED_SORT_TITLE, lastSelectedSortTitle);
    }


    public boolean isContainBool(String key){
        return preference.getBooleanFlagPrefrence(key,false);
    }
    public void setBool(String key, boolean value){
        preference.saveBooleanFlagInPreference(key,value);
    }

}
