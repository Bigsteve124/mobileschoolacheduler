package android.reserver.c196project.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.reserver.c196project.DAO.CourseDAO;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Database;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Course;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ForeignKey;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class CourseAdaptor extends RecyclerView.Adapter<CourseAdaptor.CourseViewHolder> {

    private List<Course>mCourses;
    private final Context context;
    private final LayoutInflater mInflater;



    public CourseAdaptor(Context context) {
        mInflater= LayoutInflater.from(context);
        this.context = context;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder  {
         private final TextView courseItemView;


        private CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseItemView=itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    int termid=0;
                    final Course current = mCourses.get(position);
                    Context vContext = v.getContext();
                    if (context instanceof Activity) {
                        // Cast the context to Activity
                        Activity activity = (Activity) context;
                        Intent termIntent = activity.getIntent();
                        if (context instanceof CourseList)
                        {
                            termid=termIntent.getIntExtra("termid",termid);
                        }
                        else
                        {
                            termid=termIntent.getIntExtra("id",0);
                        }
                    }
                    Intent intent=new Intent(context,CourseDetails.class);
                    intent.putExtra("id",current.getCourseID());
                    intent.putExtra("title",current.getCourseTitle());
                    intent.putExtra("start date",current.getStartDate());
                    intent.putExtra("end date",current.getEndDate());
                    intent.putExtra("Selected Status",current.getCourseStatus());
                    intent.putExtra("instructor name",current.getInstructorName());
                    intent.putExtra("instructor phone number",current.getInstructorPhoneNum());
                    intent.putExtra("instructor email",current.getInstructorEmail());
                    intent.putExtra("termid",termid);
                    if (context instanceof CourseList)
                    {
                        ((CourseList) context).activityResultLauncher.launch(intent);
                    }
                    else
                    {
                        context.startActivity(intent);
                    }

                }

            });
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_list_item,parent,false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if(mCourses!=null){
            Course current=mCourses.get(position);
            holder.courseItemView.setText(current.getCourseTitle());
        }
        else {
            holder.courseItemView.setText("No Course Name");
        }


    }

    @Override
    public int getItemCount() {
        if(mCourses!=null) {
            return mCourses.size();
        }
        else return 0;
    }
    public void setCourses(List<Course> courses){
        mCourses=courses;
        notifyDataSetChanged();
    }


}
