package com.example.viswanathankp.retailstore.home;

import static com.example.viswanathankp.retailstore.utils.Constants.ITEM_KEY;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.viswanathankp.retailstore.R;
import com.example.viswanathankp.retailstore.cart.CartActivity;
import com.example.viswanathankp.retailstore.detail.DetailActivity;
import com.example.viswanathankp.retailstore.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcels;

public class HomeActivity extends AppCompatActivity {

  @BindView(R.id.expandView) ExpandableListView expandableListView;
  List<String> expandableListTitle;
  Map<String, List<Item>> expandableListDetail;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);
    initViewmodel();
  }

  private void initViewmodel(){
    HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    homeViewModel.getItemLiveData().observe(this, this::updateData);
  }

  private void setupExpandView(Map<String, List<Item>> stringListMap) {
    expandableListDetail = stringListMap;
    expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
    ExpandListAdapter expandableListAdapter = new ExpandListAdapter(this, expandableListTitle, expandableListDetail);
    expandableListView.setAdapter(expandableListAdapter);
    expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
      List<Item> group = expandableListDetail.get(expandableListTitle.get(groupPosition));
      if (group != null) {
        navigateToDetailsActivity(group.get(childPosition));
      }
      return false;
    });
  }

  private void navigateToDetailsActivity(Item item) {
      Intent explicit = new Intent(this, DetailActivity.class);
      explicit.putExtra(ITEM_KEY, Parcels.wrap(item));
      startActivity(explicit);
  }

  private void updateData(Map<String,List<Item>> stringListMap) {
    setupExpandView(stringListMap);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.cart:
        navigateToCart();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void navigateToCart() {
    Intent explicit = new Intent(this, CartActivity.class);
    startActivity(explicit);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.cart_menu, menu);
    return true;
  }
}
