package xyz.soalatihan.dancok;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class CameraImportFragment extends Activity {

    private WebView webView;
    private EditText urlEditText;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = (EditText) findViewById(R.id.urlField);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new MyWebViewClient());

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);

        Button openUrl = (Button) findViewById(R.id.goButton);
        openUrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = urlEditText.getText().toString();
                if (validateUrl(url)) {
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl(url);

                    CameraImportFragment.this.progress.setProgress(0);
                }
            }

            private boolean validateUrl(String url) {
                return true;
            }
        });

    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            CameraImportFragment.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }
    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }
}