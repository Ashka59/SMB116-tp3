    package cnam.smb116.smb116_tp3;

    import android.app.Activity;
    import android.app.ListActivity;
    import android.content.Context;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;
    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;

    import java.util.ArrayList;
    import java.util.List;
//Question 6
    public class MyAdapter extends ArrayAdapter<String> {

        static class ViewHolderItem {
            TextView textViewItem;
        }

        private static int compteur =0;
        private static int cacheCount=0;
        private static int outOfCacheCount=0;

        private Context mContext;
        private int mRessource;
        private List<String> listUE;

        public MyAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);

            this.mContext = context;
            this.mRessource = resource;
            this.listUE = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            ViewHolderItem viewHolder;

            if(convertView==null){

                // inflate the layout
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mRessource, parent, false);

                // set up the ViewHolder
                viewHolder = new ViewHolderItem();
                viewHolder.textViewItem = (TextView) convertView.findViewById(android.R.id.text1);

                // store the holder with the view.
                convertView.setTag(viewHolder);

                MyAdapter.outOfCacheCount++;
                Log.i("compteur hors cache : ",MyAdapter.outOfCacheCount+"");

            }else{

                viewHolder = (ViewHolderItem) convertView.getTag();

                MyAdapter.cacheCount++;
                Log.i("compteur cache : ",MyAdapter.cacheCount+"");
            }


            viewHolder.textViewItem.setText(listUE.get(position));

            return convertView;
        }
}