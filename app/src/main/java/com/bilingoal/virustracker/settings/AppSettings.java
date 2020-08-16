package com.bilingoal.virustracker.settings;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.bilingoal.virustracker.R;

public class AppSettings extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        Preference privacyPolicy = findPreference("privacy");
        privacyPolicy.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://covid19tracker-0.flycricket.io/privacy.html")));
            return true;
        });

        Preference feedback = findPreference("feedback");
        feedback.setOnPreferenceClickListener(preference -> {
            startActivity(Intent.createChooser(new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","sm.maksim@icloud.com", null))
                            .putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject))
                            .putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.email_body)),
                    getResources().getString(R.string.email_title)));
            return true;
        });
    }
}
