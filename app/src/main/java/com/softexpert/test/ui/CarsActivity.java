package com.softexpert.test.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softexpert.test.R;
import com.softexpert.test.ui.adapter.CarsRecyclerAdapter;
import com.softexpert.test.utils.PaginationListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.softexpert.test.utils.PaginationListener.PAGE_START;

public class CarsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    // views
    @BindView(R.id.activity_cars_receycler)
    RecyclerView carsRecyclerView;
    @BindView(R.id.activity_cars_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    LinearLayoutManager layoutManager;

    // vars
    private CarsRecyclerAdapter adapter;
    private CarsViewModel viewModel;
    private int currentPage = PAGE_START;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        ButterKnife.bind(this);
        initVars();
        initViews();
        getCarsList();
    }

    private void initVars() {
        viewModel = new ViewModelProvider(this).get(CarsViewModel.class);
        layoutManager = new LinearLayoutManager(this);
    }

    private void initViews() {
        refreshLayout.setOnRefreshListener(this);
        carsRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        carsRecyclerView.setLayoutManager(layoutManager);
        adapter = new CarsRecyclerAdapter(this, new ArrayList<>());
        carsRecyclerView.setAdapter(adapter);

        // add scroll listener while user reach in bottom load more will call
        carsRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                viewModel.getCarsList(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        viewModel.getCarsList(currentPage);
        refreshLayout.setRefreshing(false);
    }

    private void getCarsList() {
        isLoading = true;
        viewModel.getCarsList(currentPage);
        viewModel.getCarsListMutableLiveData().observe(this, data -> {
            isLoading = false;
            isLastPage = data == null || data.getData().size() == 0;
            if (data != null && data.getData().size() != 0) {
                adapter.addItems(data.getData());
            }
        });
    }
}