package android.reserver.c196project.UI;

import android.content.Context;
import android.content.Intent;
import android.reserver.c196project.R;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.entities.Instructor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InstructorAdaptor extends RecyclerView.Adapter<InstructorAdaptor.InstructorViewHolder> {
private List<Instructor>mInstructors;
private final Context context;
private final LayoutInflater mInflater;


public InstructorAdaptor(Context context) {
        mInflater= LayoutInflater.from(context);
        this.context = context;
        }

public class InstructorViewHolder extends RecyclerView.ViewHolder  {
    private final TextView instructorItemView;

    public InstructorViewHolder(@NonNull View itemView) {
        super(itemView);
        instructorItemView=itemView.findViewById(R.id.textView4);
        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position=getAdapterPosition();
                final Instructor current=mInstructors.get(position);
                Intent intent=new Intent(context,InstructorDetails.class);
                intent.putExtra("ID",current.getInstructorID());
                intent.putExtra("Name",current.getInstructorName());
                intent.putExtra("Phone Number",current.getPhoneNum());
                intent.putExtra("Email",current.getEmail());
                context.startActivity(intent);


            }
        });
    }
}

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.instructor_list_item,parent,false);
        return new InstructorAdaptor.InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        if(mInstructors!=null){
           Instructor current=mInstructors.get(position);
            holder.instructorItemView.setText(current.getInstructorName());


        }
        else {
            holder.instructorItemView.setText("No Instructor Available");
            holder.instructorItemView.setText("No Instructor ID");
        }


    }

    @Override
    public int getItemCount() {
        if(mInstructors!=null) {
            return mInstructors.size();
        }
        else return 0;
    }
    public void setInstructors(List<Instructor> instructors){
        mInstructors=instructors;
        notifyDataSetChanged();
    }


}