package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.udacity.gradle.builditbigger.backend.myApi.model.FetchJokes;
import com.vishavlakhtia.myandroidlibrary.jokeDisplay;
import android.util.Pair;
import android.view.Display;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context,String>, Void,String> {
    private static MyApi myApiService = null;
    private Context mContext;
    public EndpointsAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.sayHi(new FetchJokes()).execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        startJokeDisplayActivity(result);
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
    }
    private void startJokeDisplayActivity(String resut) {
        Intent intent = new Intent(mContext, jokeDisplay.class);
        intent.putExtra(jokeDisplay.INTENT_JOKE, resut);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}

