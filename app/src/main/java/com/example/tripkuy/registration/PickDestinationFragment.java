package com.example.tripkuy.registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tripkuy.R;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.models.Pengguna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PickDestinationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PickDestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PickDestinationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MoveFragmentListener listener;

    private Button btn_selesai, btnPrev;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Pengguna pengguna;

    private OnFragmentInteractionListener mListener;

    public PickDestinationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PickDestinationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PickDestinationFragment newInstance(String param1, String param2) {
        PickDestinationFragment fragment = new PickDestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_pick_destination, container, false);
        btn_selesai = (Button) rootView.findViewById(R.id.btn_selesai);
        btnPrev = (Button) rootView.findViewById(R.id.btn_prev);
        btn_selesai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getActivity(),
                        String.valueOf(pengguna.getNama()), Toast.LENGTH_SHORT).show();
                class AddEmployee extends AsyncTask<Void, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(rootView.getContext(), "Menambahkan...", "Tunggu...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(rootView.getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        String URL_ADD_PENGGUNA = "http://10.100.53.87//tripkuy/pengguna_post.php";
                        String result = "";
                        try {
                            URL url = new URL(URL_ADD_PENGGUNA);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                            String post_data =
                                    URLEncoder.encode("nama", "UTF-8") + "=" + URLEncoder.encode("DIMASTYO", "UTF-8") + "&" +
                                            URLEncoder.encode("usia", "UTF-8") + "=" + URLEncoder.encode("22", "UTF-8") + "&" +
                                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode("test@mail.com", "UTF-8") + "&"+
                                            URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode("LAKI", "UTF-8");
                            bufferedWriter.write(post_data);
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            outputStream.close();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                            String line = "";
                            while ((line = bufferedReader.readLine()) != null){
                                result += line;
                            }
                            bufferedReader.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }

                AddEmployee ae = new AddEmployee();
                ae.execute();
//                Intent intent = new Intent(getActivity(), Dashboard.class);
//                startActivity(intent);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.move(1);
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else if (context instanceof MoveFragmentListener) {
            listener = (MoveFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void retrieve_pengguna(Pengguna _pengguna) {
        pengguna = _pengguna;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
