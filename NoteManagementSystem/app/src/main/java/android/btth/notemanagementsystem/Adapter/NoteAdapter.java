package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    Context context;
    List<NoteDetails> noteDetailsList;

    public NoteAdapter(Context context, List<NoteDetails> noteDetailsList) {
        this.context = context;
        this.noteDetailsList = noteDetailsList;
    }
    public void setData(List<NoteDetails> noteDetailsList)
    {
        this.noteDetailsList=noteDetailsList;
        notifyDataSetChanged();
    }
    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteDetails note = noteDetailsList.get(position);
        if(note == null){
            return;
        }
        holder.txtName.setText("Name: " + note.getNoteName());
        holder.txtCat.setText("Category: " + note.getCatName());
        holder.txtPrio.setText("Priority: " + note.getPrioName());
        holder.txtSTT.setText("Status: " + note.getSttName());
        holder.txtPlan.setText("Plan date: " + note.getTimePlan());
        holder.txtCre.setText("Created Date: " + note.getTimeCre());
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(context)
                .inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(view);
    }
    /**
     * Return Size of List Item*/
    @Override
    public int getItemCount() {
        if(noteDetailsList != null)
            return noteDetailsList.size();
        return 0;
    }
}
