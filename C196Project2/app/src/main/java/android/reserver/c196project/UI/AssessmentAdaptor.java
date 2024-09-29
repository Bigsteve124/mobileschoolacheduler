package android.reserver.c196project.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.reserver.c196project.R;
import android.reserver.c196project.entities.Assessment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssessmentAdaptor extends RecyclerView.Adapter<AssessmentAdaptor.AssessmentViewHolder> {
    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;
    private Assessment mAssessment;
    public List<Assessment> mAssociated;


    public AssessmentAdaptor(Context context) {
        mInflater= LayoutInflater.from(context);
        this.context = context;
    }

    public class AssessmentViewHolder extends RecyclerView.ViewHolder  {
        private final TextView assessmentItemView;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentItemView=itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    int courseID = 0;
                    Context vContext = v.getContext();
                    if (context instanceof Activity) {
                        if (context instanceof CourseDetails)
                        {
                            courseID = ((CourseDetails) context).cID;
                        }
                        // Cast the context to Activity
                        Activity activity = (Activity) context;
                        Intent courseIntent = activity.getIntent();
                        courseID=courseIntent.getIntExtra("courseid",courseID);
                    }
                    final Assessment current=mAssessments.get(position);
                    Intent intent=new Intent(context,AssessmentDetails.class);
                    intent.putExtra("courseid",courseID);
                    intent.putExtra("id",current.getAssessmentID());
                    intent.putExtra("title",current.getAssessmentTitle());
                    intent.putExtra("start date",current.getAssessmentStartDate());
                    intent.putExtra("end date",current.getAssessmentEndDate());
                    intent.putExtra("type",current.getAssessmentType());
                    if (context instanceof AssessmentList)
                    {
                        ((AssessmentList) context).activityResultLauncher.launch(intent);
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
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        if(mAssessments!=null){
            Assessment current=mAssessments.get(position);
            holder.assessmentItemView.setText(current.getAssessmentTitle());
        }
        else {
            holder.assessmentItemView.setText("No Course Name");
        }


    }

    @Override
    public int getItemCount() {
        if(mAssessments!=null) {
            return mAssessments.size();
        }
        else return 0;
    }
    public void setAssessments(List<Assessment> assessments){
        mAssessments=assessments;
        notifyDataSetChanged();
    }
    public void setAssociatedAssessment(List<Assessment> assessment){
        mAssociated=assessment;
        notifyDataSetChanged();
    }


}

