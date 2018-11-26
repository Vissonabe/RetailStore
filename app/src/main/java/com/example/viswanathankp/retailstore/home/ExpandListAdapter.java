package com.example.viswanathankp.retailstore.home;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.viswanathankp.retailstore.R;
import com.example.viswanathankp.retailstore.model.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandListAdapter extends BaseExpandableListAdapter {

  private Context context;
  private List<String> expandableListTitle;
  private Map<String, List<Item>> expandableListDetail;

  public ExpandListAdapter(Context context, List<String> expandableListTitle,
      Map<String, List<Item>> expandableListDetail) {
    this.context = context;
    this.expandableListTitle = expandableListTitle;
    this.expandableListDetail = expandableListDetail;
  }

  @Override
  public Object getChild(int listPosition, int expandedListPosition) {
    return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
        .get(expandedListPosition);
  }

  @Override
  public long getChildId(int listPosition, int expandedListPosition) {
    return expandedListPosition;
  }

  @Override
  public View getChildView(int listPosition, final int expandedListPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    final Item expandedListText = (Item) getChild(listPosition, expandedListPosition);
    if (convertView == null) {
      LayoutInflater layoutInflater = (LayoutInflater) this.context
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = layoutInflater.inflate(R.layout.expand_list_item, null);
    }
    TextView expandedListTextView = (TextView) convertView
        .findViewById(R.id.listTitle);
    expandedListTextView.setText(expandedListText.getName());
    return convertView;
  }

  @Override
  public int getChildrenCount(int listPosition) {
    return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
        .size();
  }

  @Override
  public Object getGroup(int listPosition) {
    return this.expandableListTitle.get(listPosition);
  }

  @Override
  public int getGroupCount() {
    return this.expandableListTitle.size();
  }

  @Override
  public long getGroupId(int listPosition) {
    return listPosition;
  }

  @Override
  public View getGroupView(int listPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {
    String listTitle = (String) getGroup(listPosition);
    if (convertView == null) {
      LayoutInflater layoutInflater = (LayoutInflater) this.context.
          getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = layoutInflater.inflate(R.layout.expand_list_group, null);
    }
    TextView listTitleTextView = (TextView) convertView
        .findViewById(R.id.groupTitle);
    listTitleTextView.setTypeface(null, Typeface.BOLD);
    listTitleTextView.setText(listTitle);
    return convertView;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int listPosition, int expandedListPosition) {
    return true;
  }
}
