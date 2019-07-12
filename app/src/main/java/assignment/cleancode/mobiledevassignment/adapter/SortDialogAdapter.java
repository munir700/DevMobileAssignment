package assignment.cleancode.mobiledevassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.databinding.RowSortDialogItemBinding;
import assignment.cleancode.mobiledevassignment.models.SortModel;


public class SortDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<SortModel> SortModelList;
    private OnClickListener onClickListener;
    private int selection;

    public SortDialogAdapter(Context context, List<SortModel> SortModelList, int selection, OnClickListener onClickListener) {
        this.SortModelList = SortModelList;
        this.context = context;
        this.onClickListener = onClickListener;
        this.selection = selection;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSortDialogItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.row_sort_dialog_item,
                parent, false);

        return new DataHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final DataHolder dataHolder = (DataHolder) holder;

        final SortModel SortModel = SortModelList.get(position);

        dataHolder.binding.setModel(SortModel);
        dataHolder.binding.setContext(context);

        if(selection == SortModel.getId()){
            dataHolder.binding.itemText.setChecked(true);
        }

        dataHolder.binding.contraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    dataHolder.binding.itemText.setChecked(true);
                    onClickListener.onClick(position, SortModel);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return SortModelList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        RowSortDialogItemBinding binding;

        DataHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnClickListener{

        void onClick(int position, SortModel SortModel);
    }

}