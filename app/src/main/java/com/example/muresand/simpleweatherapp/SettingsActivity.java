package com.example.muresand.simpleweatherapp;


import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.muresand.simpleweatherapp.util.AppSettingsUtil;
import com.example.muresand.simpleweatherapp.util.FileParser;
import com.example.muresand.simpleweatherapp.util.GeneralSettingsModel;
import com.example.muresand.simpleweatherapp.util.LocationHelper;
import com.example.muresand.simpleweatherapp.util.LocationModel;
import com.example.muresand.simpleweatherapp.util.StringUtils;
import com.example.muresand.simpleweatherapp.util.UnitOfMeasurement;

import java.util.ArrayList;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    // TODO: make initial model + compare + save only if different on exiting
    private GeneralSettingsModel mGeneralSettingsModel;
    private boolean mShouldSave;

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {

        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));

    }

    public void updateSettingsChanged(boolean settingsWereChanged) {
        if (mShouldSave != settingsWereChanged) {
            mShouldSave = settingsWereChanged;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupActionBar();
        mGeneralSettingsModel = AppSettingsUtil.loadGeneralSettings(this);
    }

    /**
        Save data on leaving settings
     */
    @Override
    public void onBackPressed() {
        // save general settings
        if (mGeneralSettingsModel != null && mShouldSave) {
            AppSettingsUtil.saveGeneralSettings(this, mGeneralSettingsModel);

            Intent intent = new Intent("settings-changed-event");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

            mShouldSave = false;
        }

        super.onBackPressed();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || ManualLocationSelectorPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {

        private static final String TAG_FRAGMENT = "general_settings_fragment";

        private SwitchPreference mAutoDetectLocationSwitch;
        private PreferenceScreen mManualLocationScreenPreference;
        private ListPreference mUnitOfMeasurementListPreference;
        private SwitchPreference mAnimationsEnabledSwitch;
        private ListPreference mNumberOfDaysInForecastList;

        private SettingsActivity mParentSettingsActivity;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.

            mParentSettingsActivity = (SettingsActivity) getActivity();

            /*
            final FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack("general");
            transaction.commit();
*/

            mAutoDetectLocationSwitch = (SwitchPreference) findPreference("auto_detect_location_switch");
            mAnimationsEnabledSwitch = (SwitchPreference) findPreference("animations_enabled_switch");
            mManualLocationScreenPreference = (PreferenceScreen) findPreference("manual_location_selector_screen");
            mUnitOfMeasurementListPreference = (ListPreference) findPreference("unit_of_measurement_list");
            mNumberOfDaysInForecastList = (ListPreference) findPreference("number_of_days_forecast_list");

            // TODO: check if settings really changed
            mParentSettingsActivity.updateSettingsChanged(true);

            // set the current settings
            final GeneralSettingsModel currentSettings = mParentSettingsActivity.mGeneralSettingsModel;
            if (currentSettings != null) {
                mAutoDetectLocationSwitch.setChecked(currentSettings.isAutoDetectLocation());
                mAnimationsEnabledSwitch.setChecked(currentSettings.isAnimationsEnabled());
                mUnitOfMeasurementListPreference.setValue(setUnitOfMeasurementSelectedListValue(currentSettings.getUnitOfMeasurement()));
                mNumberOfDaysInForecastList.setValue(Integer.toString(currentSettings.getNumberOfDaysInForecast()));

                if (currentSettings.isAutoDetectLocation()) {
                    mManualLocationScreenPreference.setEnabled(false);
                    LocationModel autoDetectedLocation = LocationModel.fromAndroidNativeLocation(LocationHelper.getLastKnownBestLocation(getContext()), getContext());
                    mManualLocationScreenPreference.setSummary(String.format("%s, %s", autoDetectedLocation.getCity(), autoDetectedLocation.getCountry()));
                    AppSettingsUtil.saveLocationSettings(getContext(), autoDetectedLocation);
                }
                else {
                    LocationModel locationModel = AppSettingsUtil.loadLocationSettings(getContext());
                    mManualLocationScreenPreference.setSummary(String.format("%s, %s", locationModel.getCity(), locationModel.getCountry()));
                }
            }

            // prepare for settings updates
            mAnimationsEnabledSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    currentSettings.setAnimationsEnabled((boolean)newValue);
                    return true;
                }
            });

            mAutoDetectLocationSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    mManualLocationScreenPreference.setEnabled(!(boolean)newValue);
                    currentSettings.setAutoDetectLocation((boolean)newValue);

                    if (currentSettings.isAutoDetectLocation()) {
                        mManualLocationScreenPreference.setEnabled(false);
                        LocationModel autoDetectedLocation = LocationModel.fromAndroidNativeLocation(LocationHelper.getLastKnownBestLocation(getContext()), getContext());
                        mManualLocationScreenPreference.setSummary(String.format("%s, %s", autoDetectedLocation.getCity(), autoDetectedLocation.getCountry()));
                        AppSettingsUtil.saveLocationSettings(getContext(), autoDetectedLocation);
                    }
                    else {
                        LocationModel locationModel = AppSettingsUtil.loadLocationSettings(getContext());
                        mManualLocationScreenPreference.setSummary(String.format("%s, %s", locationModel.getCity(), locationModel.getCountry()));
                    }

                    return true;
                }
            });

            mUnitOfMeasurementListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    mParentSettingsActivity.sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, newValue);
                    currentSettings.setUnitOfMeasurement(getSelectedUnitOfMeasurementFromList((String) newValue));
                    return true;
                }
            });

            mNumberOfDaysInForecastList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    mParentSettingsActivity.sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, newValue);
                    currentSettings.setNumberOfDaysInForecast(Integer.parseInt((String) newValue));
                    return true;
                }
            });

            getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    LocationModel locationModel = AppSettingsUtil.loadLocationSettings(getContext());
                    mManualLocationScreenPreference.setSummary(String.format("%s, %s", locationModel.getCity(), locationModel.getCountry()));
                }
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private UnitOfMeasurement getSelectedUnitOfMeasurementFromList(String newValue) {
            int selectedValue = Integer.parseInt(newValue);
            switch (selectedValue) {
                case -1:
                    return UnitOfMeasurement.METRIC;
                case 0:
                    return UnitOfMeasurement.IMPERIAL;
                case 1:
                    return UnitOfMeasurement.DEFAULT;
                default:
                    return UnitOfMeasurement.METRIC;
            }
        }

        private String setUnitOfMeasurementSelectedListValue(UnitOfMeasurement unitOfMeasurement) {
            switch (unitOfMeasurement) {
                case METRIC:
                    return "-1";

                case IMPERIAL:
                    return "0";

                case DEFAULT:
                    return "1";

                default:
                    return "-1";
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class ManualLocationSelectorPreferenceFragment extends PreferenceFragment {

        private ListView mLocationSelectorListView;
        private ProgressBar mProgressSpinner;
        private TextView mCityListErrorTextView;
        private SearchView mLocationSelectorSearchView;

        private ManualLocationSelectionListAdapter mLocationSelectorArrayAdapter;
        private ArrayList<LocationModel> mInitialLocationsList;
        private LocationModel mSelectedLocation;

        private class FileParsingAsyncTask extends AsyncTask<Context, Void, ArrayList<LocationModel>> {

            @Override
            protected ArrayList<LocationModel> doInBackground(Context... input) {
                return FileParser.parseCityJsonArrayFromFile(input[0]);
            }

            @Override
            protected void onPostExecute(ArrayList<LocationModel> result) {
                super.onPostExecute(result);

                mLocationSelectorArrayAdapter = new ManualLocationSelectionListAdapter(getContext(), result);
                mLocationSelectorListView.setAdapter(mLocationSelectorArrayAdapter);
                mProgressSpinner.setVisibility(View.GONE);

                if (result == null) {
                    mCityListErrorTextView.setVisibility(View.VISIBLE);
                }

                mInitialLocationsList = result;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                mProgressSpinner.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setHasOptionsMenu(false);
            mSelectedLocation = AppSettingsUtil.loadLocationSettings(getContext());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View currentView = inflater.inflate(R.layout.pref_manual_location_selector, container, false);
            mLocationSelectorListView = currentView.findViewById(R.id.locationSelectorListView);
            mProgressSpinner = currentView.findViewById(R.id.cityListProgressSpinner);
            mCityListErrorTextView = currentView.findViewById(R.id.cityListErrorTextView);
            mLocationSelectorSearchView = currentView.findViewById(R.id.locationSelectorSearchView);
            mLocationSelectorSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {

                    ArrayList<LocationModel> queriedList = new ArrayList<LocationModel>();
                    if (mLocationSelectorArrayAdapter == null || mLocationSelectorArrayAdapter.getLocationsList() == null) {
                        return false;
                    }

                    if (mCityListErrorTextView.getVisibility() == View.VISIBLE) {
                        mCityListErrorTextView.setVisibility(View.GONE);
                    }

                    for (LocationModel location : mInitialLocationsList) {
                        if (StringUtils.containsIgnoreCase(location.getCity(), query)) {
                            queriedList.add(location);
                        }
                    }

                    mLocationSelectorArrayAdapter.setLocationsList(queriedList);
                    mLocationSelectorArrayAdapter.notifyDataSetChanged();

                    if (queriedList.size() == 0) {
                        mCityListErrorTextView.setText("No results found...");
                        mCityListErrorTextView.setVisibility(View.VISIBLE);
                    }

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    if (mCityListErrorTextView.getVisibility() == View.VISIBLE) {
                        mCityListErrorTextView.setVisibility(View.GONE);
                    }

                    if (newText == null || newText.equals("")) {
                        mLocationSelectorArrayAdapter.setLocationsList(mInitialLocationsList);
                        mLocationSelectorArrayAdapter.notifyDataSetChanged();
                    }

                    return false;
                }

            });

            mLocationSelectorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int pos = adapterView.getPositionForView(view);
                    mSelectedLocation = mLocationSelectorArrayAdapter.getItem(pos);
                    AppSettingsUtil.saveLocationSettings(getContext(), mSelectedLocation);
                    getFragmentManager().popBackStackImmediate();
                }
            });

            return currentView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            new FileParsingAsyncTask().execute(getContext());
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
