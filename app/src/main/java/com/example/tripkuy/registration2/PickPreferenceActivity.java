package com.example.tripkuy.registration2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.BackgroundWorker.AddPengguna;
import com.example.tripkuy.R;
import com.example.tripkuy.models.Pengguna;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickPreferenceActivity extends AppCompatActivity {
    private TextView txtUsia;
    private Button btnPrev,btn_selesai;
    private  String usia, jenis_kelamin, name, email;
    List<CheckBox> items = new ArrayList<CheckBox>();
    String kategori = "";
    AddPengguna addPengguna;
    ImageView waduk, bukit, telaga, zoo, museum, kebun, gurun, pemandian,
            makam, waterboom, airterjun, goa, lembah, pantai, taman, gunung, garden, sungai, monumen,
            galeri, candi, creative, hiburan, sains, pasar, desa;
    private CheckBox pref_airterjun;
    private CheckBox pref_bukit;
    private CheckBox pref_goa;
    private CheckBox pref_gurun;
    private CheckBox pref_kebun;
    private CheckBox pref_makam;
    private CheckBox pref_museum;
    private CheckBox pref_telaga;
    private CheckBox pref_waduk;
    private CheckBox pref_waterboom;
    private CheckBox pref_pemandian;
    private CheckBox pref_zoo;
    private CheckBox pref_lembah;
    private CheckBox pref_pantai;
    private CheckBox pref_park;
    private CheckBox pref_gunung;
    private CheckBox pref_garden;
    private CheckBox pref_sungai;
    private CheckBox pref_galery;
    private CheckBox pref_candi;
    private CheckBox pref_creative;
    private CheckBox pref_hiburan;
    private CheckBox pref_pasar;
    private CheckBox pref_desa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        Intent intent = getIntent();
        usia = intent.getStringExtra(GenderActivity.TAG_USIA);
        jenis_kelamin = intent.getStringExtra(GenderActivity.TAG_KELAMIN);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        name = account.getDisplayName();
        email = account.getEmail();
        txtUsia = findViewById(R.id.txt_usia);
        btnPrev = findViewById(R.id.btn_next);
        btn_selesai = findViewById(R.id.btn_selesai);
        buildCheckBoxes();
        buildImage();
    }

    public void onSelesaiClick(View view){
        Pengguna pengguna = new Pengguna();
        pengguna.setNama(name);
        pengguna.setEmail(email);
        pengguna.setUsia(Integer.parseInt(usia));
        pengguna.setGender(jenis_kelamin);
        pengguna.setPreferensi(kategori);
        addPengguna = new AddPengguna(this,pengguna);
        addPengguna.execute();
    }

    public void onPrevClick(View view){
        Intent intent = new Intent(this, GenderActivity.class);
        startActivity(intent);
    }

    public void checkBoxOnclick(View view){
        kategori = "";
        if (pref_airterjun.isChecked()) {
            String text= pref_airterjun.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_bukit.isChecked()) {
            String text= pref_bukit.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_goa.isChecked()) {
            String text= pref_goa.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_gurun.isChecked()) {
            String text= pref_gurun.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_kebun.isChecked()) {
            String text= pref_kebun.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_makam.isChecked()) {
            String text= pref_makam.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_museum.isChecked()) {
            String text= pref_museum.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_telaga.isChecked()) {
            String text= pref_telaga.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_waduk.isChecked()) {
            String text= pref_waduk.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_waterboom.isChecked()) {
            String text= pref_waterboom.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_pemandian.isChecked()) {
            String text= pref_pemandian.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_zoo.isChecked()) {
            String text= pref_zoo.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_lembah.isChecked()) {
            String text= pref_lembah.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_pantai.isChecked()) {
            String text= pref_pantai.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_park.isChecked()) {
            String text= pref_park.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_gunung.isChecked()) {
            String text= pref_gunung.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_garden.isChecked()) {
            String text= pref_garden.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_sungai.isChecked()) {
            String text= pref_sungai.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_galery.isChecked()) {
            String text= pref_galery.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_candi.isChecked()) {
            String text= pref_candi.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_creative.isChecked()) {
            String text= pref_creative.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_hiburan.isChecked()) {
            String text= pref_hiburan.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_pasar.isChecked()) {
            String text= pref_pasar.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }
        if (pref_desa.isChecked()) {
            String text= pref_desa.getText().toString();
            if(kategori == "")
                kategori += text;
            else
                kategori += ","+text;
        }


    }

    public void buildCheckBoxes(){
        pref_airterjun = ((CheckBox)findViewById(R.id.pref_airterjun));
        pref_bukit = ((CheckBox)findViewById(R.id.pref_bukit));
        pref_goa = ((CheckBox)findViewById(R.id.pref_goa));
        pref_gurun =((CheckBox)findViewById(R.id.pref_gurun));
        pref_kebun = ((CheckBox)findViewById(R.id.pref_kebun));
        pref_makam = ((CheckBox)findViewById(R.id.pref_makam));
        pref_museum = ((CheckBox)findViewById(R.id.pref_museum));
        pref_telaga = ((CheckBox)findViewById(R.id.pref_telaga));
        pref_waduk = ((CheckBox)findViewById(R.id.pref_waduk));
        pref_waterboom = ((CheckBox)findViewById(R.id.pref_waterboom));
        pref_pemandian = ((CheckBox)findViewById(R.id.pref_pemandian));
        pref_zoo = ((CheckBox)findViewById(R.id.pref_zoo));
        pref_lembah = ((CheckBox)findViewById(R.id.pref_lembah));
        pref_pantai = ((CheckBox)findViewById(R.id.pref_pantai));
        pref_park = ((CheckBox)findViewById(R.id.pref_park));
        pref_gunung = ((CheckBox)findViewById(R.id.pref_gunung));
        pref_garden = ((CheckBox)findViewById(R.id.pref_garden));
        pref_sungai = ((CheckBox)findViewById(R.id.pref_sungai));
        pref_galery =((CheckBox)findViewById(R.id.pref_monumen));
        pref_galery =((CheckBox)findViewById(R.id.pref_galery));
        pref_candi = ((CheckBox)findViewById(R.id.pref_candi));
        pref_creative = ((CheckBox)findViewById(R.id.pref_creative));
        pref_hiburan = ((CheckBox)findViewById(R.id.pref_hiburan));
        pref_hiburan = ((CheckBox)findViewById(R.id.pref_sains));
        pref_pasar = ((CheckBox)findViewById(R.id.pref_pasar));
        pref_desa = ((CheckBox)findViewById(R.id.pref_desa));
    }

    @Override
    protected void onDestroy() {

        items.clear();
        super.onDestroy();
    }

    public void buildImage(){
        waduk = findViewById(R.id.waduk);
        bukit = findViewById(R.id.bukit);
        telaga = findViewById(R.id.telaga);
        zoo = findViewById(R.id.zoo);
        museum = findViewById(R.id.museum);
        kebun = findViewById(R.id.kebun);
        gurun = findViewById(R.id.gurun);
        pemandian = findViewById(R.id.pemandian);
        makam = findViewById(R.id.makam);
        waterboom = findViewById(R.id.waterboom);
        airterjun = findViewById(R.id.airterjun);
        goa = findViewById(R.id.goa);
        lembah = findViewById(R.id.lembah);
        pantai = findViewById(R.id.pantai);
        taman = findViewById(R.id.park);
        gunung = findViewById(R.id.gunung);
        garden = findViewById(R.id.garden);
        sungai = findViewById(R.id.sungai);
        monumen = findViewById(R.id.monumen);
        galeri = findViewById(R.id.galery);
        candi = findViewById(R.id.candi);
        creative = findViewById(R.id.creative);
        hiburan = findViewById(R.id.hiburan);
        sains = findViewById(R.id.sains);
        pasar = findViewById(R.id.pasar);
        desa = findViewById(R.id.desa);

        Picasso.get().load("https://www.jejakpiknik.com/wp-content/uploads/2017/08/waduk-sermo-630x380.jpg").into(waduk);
        Picasso.get().load("https://www.alodiatour.com/wp-content/uploads/2018/05/foto-bukit-bintang-jogja.jpg").into(bukit);
        Picasso.get().load("https://explorewisata.com/wp-content/uploads/2018/09/blue-lagoon-jogja.jpg").into(telaga);

        Picasso.get().load("https://www.alodiatour.com/wp-content/uploads/2018/11/rute-kebun-binatang-gembira-loka.jpg").into(zoo);
        Picasso.get().load("https://dwiwarna.web.ugm.ac.id/wp-content/uploads/sites/19095/2019/01/Objek-Wisata-De-Mata-Trick-Eye-Museum-Jogja-1024x754.jpg").into(museum);
        Picasso.get().load("https://www.alodiatour.com/wp-content/uploads/2018/04/harga-tiket-kebun-buah-mangunan.jpg").into(kebun);

        Picasso.get().load("https://jogjaonstage.com/wp-content/uploads/2018/11/wisatagumukpasirparangkusumo.jpg").into(gurun);
        Picasso.get().load("https://www.yogyes.com/en/yogyakarta-tourism-object/nature-and-outdoor/pemandian-tirta-budi/1.jpg").into(pemandian);
        Picasso.get().load("https://www.yogyes.com/en/yogyakarta-tourism-object/pilgrimage-sites/makam-raja-raja-imogiri/1.jpg").into(makam);

        Picasso.get().load("https://eksotisjogja.com/wp-content/uploads/2016/08/Jogja-Bay-Waterpark-Terbesai.png").into(waterboom);
        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/e/e1/Kedungpedut.jpg").into(airterjun);
        Picasso.get().load("https://visitingjogja.com/wp-content/uploads/2017/11/kalisuci.jpg").into(goa);

        Picasso.get().load("https://i1.wp.com/www.njogja.co.id/wp-content/uploads/2015/06/goa_seplawan3-e1434011221583.jpg").into(lembah);
        Picasso.get().load("https://www.wowkeren.com/display/images/photo/2019/07/20/00265080.jpg").into(pantai);
        Picasso.get().load("https://www.nativeindonesia.com/wp-content/uploads/2020/03/Alun-Alun-Kidul.jpg").into(taman);

        Picasso.get().load("https://www.alodiatour.com/wp-content/uploads/2018/04/wisata-Gunung-Api-Purba-Nglanggeran.jpg").into(gunung);
        Picasso.get().load("https://www.hargatiket.net/wp-content/uploads/2018/11/Harga-Tiket-Taman-Pelangi-Jogja.jpg").into(garden);
        Picasso.get().load("https://antarejatour.com/wp-content/uploads/2019/05/Taman-Sungai-Mudal-Kulon-Progo-sumber-ig-satriarakasiwi.jpg").into(sungai);

        Picasso.get().load("https://rentalmobiljogja.id/wp-content/uploads/2017/04/monumen-jogja-kembali-02-1080x810.jpg").into(monumen);
        Picasso.get().load("https://2.bp.blogspot.com/-yltbT0mQfIM/WTT0_WWLO1I/AAAAAAAAbvw/yHfQ0gTFlk4qiAF-PUKNqN5yyQXe3LYYQCLcB/s1600/garotan2.jpg").into(galeri);
        Picasso.get().load("https://i0.wp.com/blog.reddoorz.com/wp-content/uploads/2018/04/prambanan-2010-2-of-2.jpg?resize=750%2C534&ssl=1").into(candi);

        Picasso.get().load("https://media-cdn.tripadvisor.com/media/photo-s/11/52/54/6b/20171120-214712-largejpg.jpg").into(creative);
        Picasso.get().load("https://tempatwisataseru.com/wp-content/uploads/2020/01/Kendaraan-Hias-di-Alun-alun-Wonosari-e1579477678313.jpg").into(hiburan);
        Picasso.get().load("alodiatour.com/wp-content/uploads/2018/11/lokasi-taman.jpg").into(sains);

        Picasso.get().load("https://merahputih.com/media/1b/a4/58/1ba458fdd5ed4f179c5838cf08944cf7.jpg").into(pasar);
        Picasso.get().load("https://www.yogyes.com/en/yogyakarta-tourism-object/other/kampung-edukasi-watu-lumbung/3.jpg").into(desa);
    }
}
//        Glide.with(this).load("https://www.jejakpiknik.com/wp-content/uploads/2017/08/waduk-sermo-630x380.jpg").into(waduk);
//        Glide.with(this).load("https://www.alodiatour.com/wp-content/uploads/2018/05/foto-bukit-bintang-jogja.jpg").into(bukit);
//        Glide.with(this).load("https://explorewisata.com/wp-content/uploads/2018/09/blue-lagoon-jogja.jpg").into(telaga);
//
//        Glide.with(this).load("https://www.alodiatour.com/wp-content/uploads/2018/11/rute-kebun-binatang-gembira-loka.jpg").into(zoo);
//        Glide.with(this).load("https://dwiwarna.web.ugm.ac.id/wp-content/uploads/sites/19095/2019/01/Objek-Wisata-De-Mata-Trick-Eye-Museum-Jogja-1024x754.jpg").into(museum);
//        Glide.with(this).load("https://www.alodiatour.com/wp-content/uploads/2018/04/harga-tiket-kebun-buah-mangunan.jpg").into(kebun);
//
//        Glide.with(this).load("https://jogjaonstage.com/wp-content/uploads/2018/11/wisatagumukpasirparangkusumo.jpg").into(gurun);
//        Glide.with(this).load("https://www.yogyes.com/en/yogyakarta-tourism-object/nature-and-outdoor/pemandian-tirta-budi/1.jpg").into(pemandian);
//        Glide.with(this).load("https://www.yogyes.com/en/yogyakarta-tourism-object/pilgrimage-sites/makam-raja-raja-imogiri/1.jpg").into(makam);
//
//        Glide.with(this).load("https://eksotisjogja.com/wp-content/uploads/2016/08/Jogja-Bay-Waterpark-Terbesai.png").into(waterboom);
//        Glide.with(this).load("https://upload.wikimedia.org/wikipedia/commons/e/e1/Kedungpedut.jpg").into(airterjun);
//        Glide.with(this).load("https://visitingjogja.com/wp-content/uploads/2017/11/kalisuci.jpg").into(goa);
//
//        Glide.with(this).load("https://i1.wp.com/www.njogja.co.id/wp-content/uploads/2015/06/goa_seplawan3-e1434011221583.jpg").into(lembah);
//        Glide.with(this).load("https://www.wowkeren.com/display/images/photo/2019/07/20/00265080.jpg").into(pantai);
//        Glide.with(this).load("https://www.nativeindonesia.com/wp-content/uploads/2020/03/Alun-Alun-Kidul.jpg").into(taman);
//
//        Glide.with(this).load("https://www.alodiatour.com/wp-content/uploads/2018/04/wisata-Gunung-Api-Purba-Nglanggeran.jpg").into(gunung);
//        Glide.with(this).load("https://www.hargatiket.net/wp-content/uploads/2018/11/Harga-Tiket-Taman-Pelangi-Jogja.jpg").into(garden);
//        Glide.with(this).load("https://antarejatour.com/wp-content/uploads/2019/05/Taman-Sungai-Mudal-Kulon-Progo-sumber-ig-satriarakasiwi.jpg").into(sungai);
//
//        Glide.with(this).load("https://rentalmobiljogja.id/wp-content/uploads/2017/04/monumen-jogja-kembali-02-1080x810.jpg").into(monumen);
//        Glide.with(this).load("https://2.bp.blogspot.com/-yltbT0mQfIM/WTT0_WWLO1I/AAAAAAAAbvw/yHfQ0gTFlk4qiAF-PUKNqN5yyQXe3LYYQCLcB/s1600/garotan2.jpg").into(galeri);
//        Glide.with(this).load("https://i0.wp.com/blog.reddoorz.com/wp-content/uploads/2018/04/prambanan-2010-2-of-2.jpg?resize=750%2C534&ssl=1").into(candi);
//
//        Glide.with(this).load("https://media-cdn.tripadvisor.com/media/photo-s/11/52/54/6b/20171120-214712-largejpg.jpg").into(creative);
//        Glide.with(this).load("https://tempatwisataseru.com/wp-content/uploads/2020/01/Kendaraan-Hias-di-Alun-alun-Wonosari-e1579477678313.jpg").into(hiburan);
//        Glide.with(this).load("alodiatour.com/wp-content/uploads/2018/11/lokasi-taman.jpg").into(sains);
//
//        Glide.with(this).load("https://merahputih.com/media/1b/a4/58/1ba458fdd5ed4f179c5838cf08944cf7.jpg").into(pasar);
//        Glide.with(this).load("https://www.yogyes.com/en/yogyakarta-tourism-object/other/kampung-edukasi-watu-lumbung/3.jpg").into(desa);