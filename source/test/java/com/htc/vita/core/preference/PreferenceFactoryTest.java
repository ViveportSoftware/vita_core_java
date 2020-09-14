package com.htc.vita.core.preference;

import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PreferenceFactoryTest {
    @Test
    public void dummy_0_getInstance() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
    }

    @Test
    public void dummy_1_loadPreferences() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences0 = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences0);
        Preferences preferences1 = preferenceFactory.loadPreferences("customLabel");
        Assert.assertNotNull(preferences1);
        Assert.assertNotEquals(preferences0, preferences1);
    }

    @Test
    public void dummy_1_loadPreferencesAsync() throws Exception {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Future<Preferences> preferencesFuture0 = preferenceFactory.loadPreferencesAsync();
        Assert.assertNotNull(preferencesFuture0);
        Future<Preferences> preferencesFuture1 = preferenceFactory.loadPreferencesAsync("customLabel");
        Assert.assertNotNull(preferencesFuture1);
        Assert.assertNotEquals(preferencesFuture0, preferencesFuture1);
        Assert.assertNotEquals(preferencesFuture0.get(), preferencesFuture1.get());
    }

    @Test
    public void preferences_00_allKeys() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Set<String> keys = preferences.allKeys();
        Assert.assertNotNull(keys);
        Assert.assertEquals(0, keys.size());
    }

    @Test
    public void preferences_01_clear() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertNotNull(preferences.clear());
    }

    @Test
    public void preferences_02_commit() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertFalse(preferences.commit());
    }

    @Test
    public void preferences_03_getCategory() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(preferences.getCategory()));
    }

    @Test
    public void preferences_04_getLabel() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(preferences.getLabel()));
    }

    @Test
    public void preferences_05_hasKey() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertFalse(preferences.hasKey(null));
        Assert.assertFalse(preferences.hasKey("someKey"));
    }

    @Test
    public void preferences_06_initialize() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertNotNull(preferences.initialize());
    }

    @Test
    public void preferences_07_parseBoolean() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertFalse(preferences.parseBoolean("someKey"));
        Assert.assertTrue(preferences.parseBoolean("someKey", true));
        Assert.assertFalse(preferences.parseBoolean("someKey", false));
    }

    @Test
    public void preferences_08_parseDouble() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertEquals(0.0D, preferences.parseDouble("someKey"), 0.0000000001D);
        Assert.assertEquals(0.1D, preferences.parseDouble("someKey", 0.1D), 0.0000000001D);
    }

    @Test
    public void preferences_09_parseFloat() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertEquals(0.0F, preferences.parseFloat("someKey"), 0.0000000001F);
        Assert.assertEquals(0.1F, preferences.parseFloat("someKey", 0.1F), 0.0000000001F);
    }

    @Test
    public void preferences_10_parseInt() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertEquals(0, preferences.parseInt("someKey"));
        Assert.assertEquals(1, preferences.parseInt("someKey", 1));
    }

    @Test
    public void preferences_11_parseLong() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertEquals(0L, preferences.parseLong("someKey"));
        Assert.assertEquals(1L, preferences.parseLong("someKey", 1L));
    }

    @Test
    public void preferences_12_parseString() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertNull(preferences.parseString("someKey"));
        Assert.assertEquals("someValue", preferences.parseString("someKey", "someValue"));
    }

    @Test
    public void preferences_13_put() {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Preferences preferences = preferenceFactory.loadPreferences();
        Assert.assertNotNull(preferences);
        Assert.assertNotNull(preferences.put("someKey0", true));
        Assert.assertNotNull(preferences.put("someKey1", 0.1d));
        Assert.assertNotNull(preferences.put("someKey2", 0.2f));
        Assert.assertNotNull(preferences.put("someKey3", 3));
        Assert.assertNotNull(preferences.put("someKey4", 123456789123L));
        Assert.assertNotNull(preferences.put("someKey5", "someValue"));
    }

    @Test
    public void preferences_14_commitAsync() throws Exception {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Future<Preferences> preferencesFuture = preferenceFactory.loadPreferencesAsync();
        Assert.assertNotNull(preferencesFuture);
        Preferences preferences = preferencesFuture.get();
        Assert.assertNotNull(preferences);
        Assert.assertFalse(preferences.commitAsync().get());
    }

    @Test
    public void preferences_15_initializeAsync() throws Exception {
        PreferenceFactory preferenceFactory = PreferenceFactory.getInstance();
        Assert.assertNotNull(preferenceFactory);
        Future<Preferences> preferencesFuture = preferenceFactory.loadPreferencesAsync();
        Assert.assertNotNull(preferencesFuture);
        Preferences preferences = preferencesFuture.get();
        Assert.assertNotNull(preferences);
        Assert.assertNotNull(preferences.initializeAsync().get());
    }
}
