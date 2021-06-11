package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.Priority;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrioAdapter extends  RecyclerView.Adapter<PrioAdapter.PrioViewHolder>  {

    private List<Priority> mListPriority;

    public void setData(List<Priority> list){
        this.mListPriority = list;
        notifyDataSetChanged();
    }
    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public PrioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_cat,parent,false);

        return new PrioViewHolder(view);

    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PrioViewHolder holder, int position) {
        Priority priority = mListPriority.get(position);
        if(priority ==null)
            return;
        holder.txtCatName.setText("Name: " + priority.getPrioName());
        holder.txtCatDate.setText("Created Date: " + priority.getTimeCre());
    }
    /**
     * Tra ve so luong item trong list
     * @return
     */
    @Override
    public int getItemCount() {
        if(mListPriority != null){
            return mListPriority.size();
        }
        return 0;
    }

    //Thiet ke view de truyen vao prioadapter
    public class PrioViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        private TextView txtCatName;
        private TextView txtCatDate;
        LinearLayout item_row;

        public PrioViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCatName = itemView.findViewById(R.id.txtCatName);
            txtCatDate = itemView.findViewById(R.id.txtCatDate);
            item_row = itemView.findViewById(R.id.item_row);
            item_row.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),001,0,"Delete");
            menu.add(this.getAdapterPosition(),002,1,"Edit");
        }
    }



}
