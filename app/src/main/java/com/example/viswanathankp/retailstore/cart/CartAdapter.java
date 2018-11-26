package com.example.viswanathankp.retailstore.cart;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.viswanathankp.retailstore.R;
import com.example.viswanathankp.retailstore.db.CartItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

  private List<CartItem> mCartItemList;
  private CartItemClickListener mListener;

  public CartAdapter(CartItemClickListener listener){
    mListener = listener;
  }

  public void setCartItemList(List<CartItem> items){
    mCartItemList = new ArrayList<>(items);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_item, viewGroup, false);
    return new CartViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
    if(i < mCartItemList.size()){
      CartItem item = mCartItemList.get(i);
      cartViewHolder.bindView(item);
    }
  }

  @Override
  public int getItemCount() {
    return mCartItemList == null ? 0 : mCartItemList.size();
  }

  public class CartViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.price) TextView priceView;
    @BindView(R.id.parent) ConstraintLayout parent;

    public CartViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.parent)
    public void itemClicked(View view){
      if(mListener != null){
        mListener.onCartItemClicked((CartItem) view.getTag());
      }
    }

    public void bindView(CartItem item){
      parent.setTag(item);
      titleView.setText(item.getName());
      priceView.setText(String.format(Locale.getDefault(), "%.2f", item.getPrice()));
    }
  }
}
