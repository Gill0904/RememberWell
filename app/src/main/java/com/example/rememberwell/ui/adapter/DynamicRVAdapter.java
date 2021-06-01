package com.example.rememberwell.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rememberwell.MainActivity;
import com.example.rememberwell.R;
import com.example.rememberwell.ui.LoginActivity;
import com.example.rememberwell.ui.NuevoUsuarioActivity;
import com.example.rememberwell.ui.fragment.AgregarFragment;
import com.example.rememberwell.ui.fragment.registroFragment;
import com.example.rememberwell.ui.interfaces.LoadMore;
import com.example.rememberwell.ui.model.DynamicRVModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }
}

class  ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView name,contenido,mes,dia;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        contenido=itemView.findViewById(R.id.details);
        mes=itemView.findViewById(R.id.mes);
        dia=itemView.findViewById(R.id.dia_num);
    }
}

public class DynamicRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    public static String id;
    LoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<DynamicRVModel> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;
    public List<String> meses = new ArrayList<>();



    public DynamicRVAdapter(RecyclerView recyclerView,Activity activity, List<DynamicRVModel> items) {
        this.activity = activity;
        this.items = items;
        meses.clear();
        meses.add("Ene.");
        meses.add("Feb.");
        meses.add("Mar.");
        meses.add("Abr.");
        meses.add("May.");
        meses.add("Jun.");
        meses.add("Jul.");
        meses.add("Ago.");
        meses.add("Sep.");
        meses.add("Oct.");
        meses.add("Nov.");
        meses.add("Dic.");

        final LinearLayoutManager linearLayoutManager =(LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                    if (loadMore!=null)
                        loadMore.onLoadMore();
                    isLoading=true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(LoadMore loadMore){
        this.loadMore=loadMore;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rv_item_layout,parent,false);
            return new ItemViewHolder(view);
        }else if (viewType==VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rv_progress_bar,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder){
            DynamicRVModel item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.name.setText(item.getTitulo());
            viewHolder.contenido.setText(item.getContenido());
            viewHolder.dia.setText(item.getFecha().substring(8,10));
            int mesLet=Integer.parseInt(item.getFecha().substring(5,7))-1;
            viewHolder.mes.setText(meses.get(mesLet));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.id_item = item.getId();
                    try{
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(activity.getApplicationContext(), registroFragment.class);
                                activity.startActivity(intent);
                            }
                        }, 500);
                    } catch (Exception e) {
                        Log.e(TAG, "onCreateView", e);
                        throw e;
                    }
                }
            });
        }else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoded(){
        isLoading=false;
    }

}


