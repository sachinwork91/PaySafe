package com.paysafe;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Customer[]> {

    public static final int CUSTOMER_LOADER = 22;
    private Toolbar mtoolbar;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initializeActivity();
        getSupportLoaderManager().initLoader(CUSTOMER_LOADER, null, this);
   }

   public void initializeActivity(){
       mRecyclerView = (RecyclerView) findViewById(R.id.customer_recycler_view);
       mprogressBar = (ProgressBar) findViewById(R.id.progress_bar);
       mRecyclerView.setHasFixedSize(false);
       mLayoutManager = new LinearLayoutManager(this);
       mRecyclerView.setLayoutManager(mLayoutManager);
       mAdapter = new MyAdapter(new Customer[0]);
       mRecyclerView.setAdapter(mAdapter);
   }

    public void setToolbar(){
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        mtoolbar.setTitle(R.string.app_name);
        setSupportActionBar(mtoolbar);
    }

    @NonNull
    @Override
    public Loader<Customer[]> onCreateLoader(int id, @Nullable Bundle args) {
         return new AsyncTaskLoader<Customer[]>(this) {
             Customer[] customerData;

            @Override
            public Customer[] loadInBackground() {
                customerData = ApiCaller.getCustomers();
                return customerData;
            }

            @Override
            protected void onStartLoading() {
                if(customerData!=null){
                    deliverResult(customerData);
                    mprogressBar.setVisibility(View.GONE);
                }else{
                    mprogressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Customer[]> loader, Customer[] data) {
        mAdapter.loadData(data);
        mAdapter.notifyDataSetChanged();
        mprogressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Customer[]> loader) {
      mAdapter.loadData(new Customer[0]);
    }
}
