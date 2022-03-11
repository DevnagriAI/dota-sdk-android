
# SDK Integration Steps:

# Include the SDK

As a first step a new repository needs to be added to the root build.gradle:

    allprojects {

        repositories {

            ...

            maven { url 'https://jitpack.io' }


        }

    }


    dependencies {

        implementation 'com.github.DevnagriAI:dota-sdk-android:1.0.0@aar' {   
              transitive(true)
        }
        
        ...

    }
 

 
# Configuration

Initialise the SDK in your application class and add the API_KEY from DevNagri. Classes inheriting from Application should overwrite attachBaseContext to enable translations outside of the activity context:


    public class MainApplication extends Application {

      @Override

      public void onCreate() {

          super.onCreate();
          val strings = R.string::class.java.fields.map { it.name }
          val arrays = R.array::class.java.fields.map { it.name }
          val plurals = R.plurals::class.java.fields.map { it.name }
          DevNagriTranslationSdk.init(applicationContext, "API_KEY" , strings, arrays, plurals)

      }

      @Override

      protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(DevNagriTranslationSdk.wrapApplicationContext(newBase));

      } 


    }
 

Additionally, you need to inject the SDK in each activity, e.g. by creating a base activity which all other activities inherit from:

    public class BaseActivity extends AppCompatActivity {

      @NonNull
      @Override

      public AppCompatDelegate getDelegate() {

        return DevNagriTranslationSdk.fetchAppDelegate(this, super.getDelegate());

      }

    }


# Change language

In case you don't want to use the system language, you can set a different language in the updateAppLocale method. The language code (locale) needs to be present in a release from Devnagri.


    val locale = Locale("hi");
    DevNagriTranslationSDK.updateAppLocale(context:this, locale:locale);


# Get Supported languages

You can get supported languages for the SDK using this method.
This will return hashmap of language code and language name

    DevNagriTranslationSDK.getAllSupportableLanguages();
 

# Translate String, List and Map on runtime

You can use these methods anywhere in your project and these will provide translation for current active locale in callback method.


# Get Translation of a Specific String.


    DevNagriTranslationSdk.getTranslationOfString("SampleText"){ translation ->
  	   // use translated text here       
    }
 

# Get Translations of an Array of Strings.

    val arrayList = arrayListOf ("SampleText1","SampleText2","SampleText3")

    DevNagriTranslationSdk.getTranslationOfStrings(arrayList){ translations ->
  	   // use translated text here       
    }
 
 
# Get Translations Of HashMap 

    val map = hashMapOf (Pair("A","SampleText1"), Pair("B","SampleText2"), Pair("C","SampleText3") )

    DevNagriTranslationSdk.getTranslationOfMap(map){ translations ->
       // use translated map here
    }
 
 
# Usage

Translations can be used as usual in layouts:

    <TextView android:text="@string/translation_key" />


And inside code:

    TextView text = (TextView) findViewById(R.id.text_id);
    text.setText(R.string.translation_key);
