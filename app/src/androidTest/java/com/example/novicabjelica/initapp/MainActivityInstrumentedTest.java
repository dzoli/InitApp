package com.example.novicabjelica.initapp;

import android.app.Activity;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
//            App myApp = (App)InstrumentationRegistry.getTargetContext().getApplicationContext();
            App myApp = ApplicationProvider.getApplicationContext();
            myApp.setDispatchingActivityInjector(createFakeActivityInjector());
        }

    };

    private MainContract.Presenter mockUserAction = mock(MainContract.Presenter.class);

    @Test
    public void clickOnFabCallToCreateTopic() {
        onView(withId(R.id.fab)).perform(click());

        verify(mockUserAction).createTopic((MainActivity)mActivityTestRule.getActivity());
    }


    private DispatchingAndroidInjector<Activity> createFakeActivityInjector() {
        final AndroidInjector<Activity> injector = new AndroidInjector<Activity>() {
            @Override public void inject(Activity instance) {
                if (instance instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity)instance;
                    mainActivity.presenter = mockUserAction;
                }
            }
        };

        final AndroidInjector.Factory<Activity> factory = new AndroidInjector.Factory<Activity>() {

            @Override public AndroidInjector<Activity> create(Activity instance) {
                return injector;
            }
        };

        Map<Class<?>, Provider<AndroidInjector.Factory<?>>> map = new HashMap<>(1);
        HashMap<Class<?>, Provider<AndroidInjector.Factory<?>>> injectorFactoriesWithClassKeys = new HashMap<>();
        Provider<AndroidInjector.Factory<?>> provider = new Provider<AndroidInjector.Factory<?>>() {

            @Override public AndroidInjector.Factory<? extends Activity> get() {
                return factory;
            }
        };
        map.put(MainActivity.class, provider);
        return DispatchingAndroidInjector_Factory.newInstance(map, new HashMap<String, Provider<AndroidInjector.Factory<?>>>());
    }

}
