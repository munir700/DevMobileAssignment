package assignment.cleancode.mobiledevassignment.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.adapter.SortDialogAdapter;
import assignment.cleancode.mobiledevassignment.models.SortModel;
import assignment.cleancode.mobiledevassignment.preferences.PreferenceHandler;
import assignment.cleancode.mobiledevassignment.utils.AppConstants;

import static assignment.cleancode.mobiledevassignment.utils.AppConstants.INDEX_DATE_ASC;
import static assignment.cleancode.mobiledevassignment.utils.AppConstants.INDEX_DATE_DESC;


public class SortDialog extends Dialog {
    public static final int INITIAL_POSITION = -1;
    private Context context;
    private PreferenceHandler preferenceHandler;

    private List<SortModel> SortModelList;
    private int selectedPosition = INITIAL_POSITION;

    public SortDialog(@NonNull Context context, List<SortModel> SortModelList, PreferenceHandler preferenceHandler) {
        super(context);
        this.context = context;
        this.SortModelList = SortModelList;
        this.preferenceHandler = preferenceHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sort);

        initUI();
    }

    private void initUI() {

        findViewById(R.id.close_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortDialog.this.dismiss();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.sorting_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        SortDialogAdapter sortDialogAdapter = new SortDialogAdapter(context, SortModelList, preferenceHandler.getSortDialogSelection(), new SortDialogAdapter.OnClickListener() {
            @Override
            public void onClick(int position, SortModel propSortingModel) {
                int selectedId = propSortingModel.getId();
                preferenceHandler.setSortDialogSelection(selectedId);
                selectedPosition = selectedId;
                setIndexSort(selectedId);
                SortDialog.this.dismiss();
            }
        });

        recyclerView.setAdapter(sortDialogAdapter);

    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    private void setIndexSort(int position) {

        switch (position) {
            case INDEX_DATE_DESC:
                preferenceHandler.setLastSelectedSort(AppConstants.DATE_DESC);
                preferenceHandler.setLastSelectedSortTitle(context.getString(R.string.STR_SORT_DATE_DESC));
                break;
            case INDEX_DATE_ASC:
                preferenceHandler.setLastSelectedSort(AppConstants.DATE_ASC);
                preferenceHandler.setLastSelectedSortTitle(context.getString(R.string.STR_SORT_DATE_ASC));
                break;
            default:
                preferenceHandler.setLastSelectedSort("");
                break;
        }

    }

}
