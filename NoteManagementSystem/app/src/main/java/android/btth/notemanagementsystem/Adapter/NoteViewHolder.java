package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.view.ContextMenu;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    TextView txtName, txtCat, txtPrio, txtSTT, txtPlan, txtCre;
    LinearLayout note_item_layout;


    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtCat = (TextView) itemView.findViewById(R.id.txtCat);
        txtPrio = (TextView) itemView.findViewById(R.id.txtPrio);
        txtSTT = (TextView) itemView.findViewById(R.id.txtSTT);
        txtPlan = (TextView) itemView.findViewById(R.id.txtPlan);
        txtCre = (TextView) itemView.findViewById(R.id.txtCre);
        note_item_layout = itemView.findViewById(R.id.note_item_layout);
        note_item_layout.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(this.getAdapterPosition(),001,0,"Delete");
        menu.add(this.getAdapterPosition(),002,1,"Edit");
    }
}
