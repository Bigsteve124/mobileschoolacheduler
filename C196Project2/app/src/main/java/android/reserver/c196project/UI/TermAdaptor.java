package android.reserver.c196project.UI;

import android.content.Context;
import android.content.Intent;
import android.reserver.c196project.R;
import android.reserver.c196project.entities.Term;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TermAdaptor  extends RecyclerView.Adapter<TermAdaptor.TermViewHolder> {

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;


    public TermAdaptor(Context context) {
        mInflater= LayoutInflater.from(context);
        this.context = context;
    }

     class TermViewHolder extends RecyclerView.ViewHolder  {
        private final TextView termItemView;


        private TermViewHolder(@NonNull View itemView) {
            super(itemView);
            termItemView=itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Term current=mTerms.get(position);
                    Intent intent=new Intent(context,TermDetails.class);
                    intent.putExtra("id",current.getTermID());
                    intent.putExtra("title",current.getTermTitle());
                    intent.putExtra("start date",current.getTermStart());
                    intent.putExtra("end date",current.getTermEnd());
                    context.startActivity(intent);

                }
            });
        }
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if(mTerms!=null){
            Term current=mTerms.get(position);
            holder.termItemView.setText(current.getTermTitle());
           // holder.termItemView.setText(current.getTermStart());//
            //holder.termItemView.setText(current.getTermEnd());//

        }
        else {
            holder.termItemView.setText("No Course Name");
        }


    }

    @Override
    public int getItemCount() {
        if(mTerms!=null) {
            return mTerms.size();
        }
        else return 0;
    }
    public void setTerms(List<Term> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }
   // public Term getTermPosition(int position){
       // return mTerms.get(position);//
    }




