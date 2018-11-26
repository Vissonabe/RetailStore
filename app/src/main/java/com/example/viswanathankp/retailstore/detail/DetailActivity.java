package com.example.viswanathankp.retailstore.detail;

import static com.example.viswanathankp.retailstore.utils.Constants.ITEM_ADDED;
import static com.example.viswanathankp.retailstore.utils.Constants.ITEM_KEY;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.viswanathankp.retailstore.R;
import com.example.viswanathankp.retailstore.cart.CartActivity;
import com.example.viswanathankp.retailstore.db.CartDao;
import com.example.viswanathankp.retailstore.db.CartDatabase;
import com.example.viswanathankp.retailstore.db.CartItem;
import com.example.viswanathankp.retailstore.detail.customview.CustomButton;
import com.example.viswanathankp.retailstore.home.HomeViewModel;
import com.example.viswanathankp.retailstore.model.Item;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity  {

  @BindView(R.id.img) ImageView imgView;
  @BindView(R.id.title) TextView titleView;
  @BindView(R.id.price) TextView priceView;
  @BindView(R.id.btn) CustomButton button;
  @BindView(R.id.remove_btn) CustomButton removeBtn;

  private Item item;
  private DetailViewModel detailViewModel;
  private boolean isItemInCart;

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    getSupportActionBar().setTitle(R.string.detail_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    initViewmodel();
    item = Parcels.unwrap(getIntent().getParcelableExtra(ITEM_KEY));
    isItemInCart = getIntent().getBooleanExtra(ITEM_ADDED, false);
    bindView(item, isItemInCart);
    invalidateOptionsMenu();
  }

  private void initViewmodel(){
    detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
  }

  private void bindView(Item item, boolean value){
    titleView.setText(item.getName());
    priceView.setText(String.valueOf(item.getPrice()));
    if(value){
      button.setVisibility(View.GONE);
      removeBtn.setVisibility(View.VISIBLE);
    } else {
      button.setVisibility(View.VISIBLE);
      removeBtn.setVisibility(View.GONE);
    }
  }

  private void showSnackbar(View view, String text){
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .addCallback(new Snackbar.Callback() {
          @Override
          public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);
            finish();
          }
        })
        .show();
  }

  @OnClick(R.id.remove_btn)
  public void removeItemFromCart(View view) {
    removeBtn.setEnabled(false);
    detailViewModel.removeItemFromCart(item);
    showSnackbar(view,getString(R.string.removed_text));
  }

  @OnClick(R.id.btn)
  public void addToCart(View view) {
    button.setEnabled(false);
    detailViewModel.saveItemToCart(item);
    showSnackbar(view,getString(R.string.added_text));
  }

  private void navigateToCart(){
    Intent explicit = new Intent(this, CartActivity.class);
    startActivity(explicit);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.cart:
        navigateToCart();
        return true;
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.cart_menu, menu);
    MenuItem item = menu.findItem(R.id.cart);
    if(isItemInCart){
      item.setVisible(false);
    } else {
      item.setVisible(true);
    }
    return true;
  }
}
