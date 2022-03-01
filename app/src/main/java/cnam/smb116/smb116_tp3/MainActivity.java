    package cnam.smb116.smb116_tp3;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends ListActivity {

        //    private static List<String> listeUE; // question4
        private List<String> listeUE;


        private EditText saisiUEEditTxt;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.liste_ue);

            this.saisiUEEditTxt = findViewById(R.id.saisie_ue_edittxt);


//            this.listeUE = new ArrayList<String>();
//            listeUE.add("INA134"); listeUE.add("SAE125"); listeUE.add("MAA131");
//            listeUE.add("ELE102"); listeUE.add("ELA134"); listeUE.add("ELE135");
//            listeUE.add("INA136"); listeUE.add("SAE126"); listeUE.add("MAA136");
//            listeUE.add("ELA136"); listeUE.add("ELE137");

            //initializeList(); //question4
            loadListeUE(); //question5

//        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeUE));
            //Question6
            setListAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1, listeUE));

        }
//Question2
        public void onClickAjouterUE(View view){

            this.listeUE.add(saisiUEEditTxt.getText().toString());
            updateList();
        }
        //Question2
        private void updateList(){
            final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
            runOnUiThread(new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
//Question3
        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);

            Intent intent = new Intent(this, SecondActivity.class);
            String str = getListView().getItemAtPosition(position).toString();
            intent.putExtra("ue", str);
            startActivity(intent);
        }

        @Override
        protected void onResume() {
            super.onResume();

            Intent intent = getIntent();
            String ueToRemove = intent.getStringExtra("ueToRemove");
            if (ueToRemove != ""){
                this.listeUE.remove(ueToRemove);
                saveListeUE(); //question5
                updateList();
            }
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            saveListeUE(); //question5
        }

        //question 4
        private void initializeList(){
            if (this.listeUE == null){
                this.listeUE = new ArrayList<String>();
                listeUE.add("INA134"); listeUE.add("SAE125"); listeUE.add("MAA131");
                listeUE.add("ELE102"); listeUE.add("ELA134"); listeUE.add("ELE135");
                listeUE.add("INA136"); listeUE.add("SAE126"); listeUE.add("MAA136");
                listeUE.add("ELA136"); listeUE.add("ELE137");
            }
        }

        //question5
        private void saveListeUE(){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor sEdit = prefs.edit();
            sEdit.putInt("listeUE_size",listeUE.size());
            for(int i = 0; i < listeUE.size(); i++){
                sEdit.putString("listeUE_"+i, listeUE.get(i));
            }
            sEdit.commit();
        }

        //question5
        private void loadListeUE(){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            int size = prefs.getInt("listeUE_size", -1);
            this.listeUE = new ArrayList<String>();
            if(size==-1){
                listeUE.add("INA134"); listeUE.add("SAE125"); listeUE.add("MAA131");
                listeUE.add("ELE102"); listeUE.add("ELA134"); listeUE.add("ELE135");
                listeUE.add("INA136"); listeUE.add("SAE126"); listeUE.add("MAA136");
                listeUE.add("ELA136"); listeUE.add("ELE137");
            }else{
                for(int i = 0; i < size; i++){
                    listeUE.add(prefs.getString("listeUE_"+i, "NULL"));
                }
            }

//prefs.edit().clear();

        }
}