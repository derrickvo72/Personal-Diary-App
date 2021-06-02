package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.Category;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.CatViewHolder>  {


    private List<Category> mListCategory;


    public void setData(List<Category> list){
        this.mListCategory = list;
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
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_cat,parent,false);

        return new CatViewHolder(view);
    }

    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

        Category category = mListCategory.get(position);
        if(category ==null)
            return;
        holder.txtCatName.setText("Name: " + category.getCatName());
        holder.txtCatDate.setText("Created Date: " + category.getTimeCre());
    }

    /**
     * Tra ve so luong item trong list
     * @return
     */
    @Override
    public int getItemCount() {
        if(mListCategory != null){
            return mListCategory.size();
        }
        return 0;
    }



    //Thiet ke view de truyen vao catadapter
    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        private TextView txtCatName;
        private TextView txtCatDate;
        LinearLayout item_row;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            //Anh xa view thong qua find
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
