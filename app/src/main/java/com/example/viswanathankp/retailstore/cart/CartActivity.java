package com.example.viswanathankp.retailstore.cart;

import static com.example.viswanathankp.retailstore.utils.Constants.ITEM_ADDED;
import static com.example.viswanathankp.retailstore.utils.Constants.ITEM_KEY;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.viswanathankp.retailstore.R;
import com.example.viswanathankp.retailstore.db.CartItem;
import com.example.viswanathankp.retailstore.detail.DetailActivity;
import com.example.viswanathankp.retailstore.detail.DetailViewModel;
import com.example.viswanathankp.retailstore.model.Item;
import dagger.android.AndroidInjection;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.parceler.Parcels;

public class CartActivity extends AppCompatActivity implements CartItemClickListener {

  @BindView(R.id.cart_recycler) RecyclerView mRecyclerView;
  @BindView(R.id.total_txt) TextView totalTxt;
  private CartAdapter adapter;
  private CartViewModel cartViewModel;

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    getSupportActionBar().setTitle(R.string.cart_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(R.layout.activity_cart);
    ButterKnife.bind(this);
    setupRecyclerView();
    initViewmodel();
  }

  private void initViewmodel(){
    cartViewModel = ViewModelProviders.of(this, viewModelFactory).get(CartViewModel.class);
    cartViewModel.getAllCartItems().observe(this, this::updateCartData);
  }

  private void setupRecyclerView(){
    adapter = new CartAdapter(this);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(adapter);
  }

  private void updateCartData(List<CartItem> cartItems) {
    totalTxt.setText(String.format(Locale.getDefault(), "Total Price is %.2f", cartViewModel.getTotalPrice(cartItems)));
    adapter.setCartItemList(cartItems);
  }

  @Override
  public void onCartItemClicked(CartItem item) {
    navigateToDetailsActivity(cartViewModel.getItem(item));
  }

  private void navigateToDetailsActivity(Item item) {
    Intent explicit = new Intent(this, DetailActivity.class);
    explicit.putExtra(ITEM_KEY, Parcels.wrap(item));
    explicit.putExtra(ITEM_ADDED, true);
    startActivity(explicit);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
