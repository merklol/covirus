package com.bilingoal.covirus.settings;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.bilingoal.covirus.R;

import static com.bilingoal.covirus.utils.Constants.EMAIL;
import static com.bilingoal.covirus.utils.Constants.PRIVACY_POLICY_URL;

public class AppSettings extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        Preference privacyPolicy = findPreference("privacy");
        privacyPolicy.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse(PRIVACY_POLICY_URL)));
            return true;
        });

        Preference feedback = findPreference("feedback");
        feedback.setOnPreferenceClickListener(preference -> {
            startActivity(Intent.createChooser(new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",EMAIL, null))
                            .putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject))
                            .putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.email_body)),
                    getResources().getString(R.string.email_title)));
            return true;
        });
    }
}
