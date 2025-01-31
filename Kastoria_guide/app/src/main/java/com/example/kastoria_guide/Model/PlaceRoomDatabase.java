package com.example.kastoria_guide.Model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
You annotate the class to be a Room database with @Database and use the annotation parameters to
declare the entities that belong in the database and set the version number.
Each entity corresponds to a table that will be created in the database.
Database migrations are beyond the scope of this codelab,
so we set exportSchema to false here to avoid a build warning.
In a real app, you should consider setting a directory for Room to use to export
the schema so you can check the current schema into your version control system.
 */
@Database(entities = {Place.class}, version = 1, exportSchema = false)
//The database class for Room must be abstract and extend RoomDatabase
public abstract class PlaceRoomDatabase extends RoomDatabase {

    public abstract PlaceDao PlaceDao();

    private static volatile PlaceRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
//We've created an ExecutorService with a fixed thread pool that you will use to run database
// operations asynchronously on a background thread
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//The database exposes DAOs through an abstract "getter" method for each @Dao.
    /*
    getDatabase returns the singleton. It'll create the database the first time it's accessed,
    using Room's database builder to create a RoomDatabase object in the application
    context from the WordRoomDatabase class and names it "word_database".
     */
    static PlaceRoomDatabase getDatabase(final Context context) {
//We've defined a singleton, WordRoomDatabase, to prevent having multiple instances of the database
// opened at the same time.
        if (INSTANCE == null) {
            synchronized (PlaceRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlaceRoomDatabase.class, "Place_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//will execute only one time, The first time the program  run, before database build
            //if you want to run in again must delete the database
            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.

                //εδώ σβήνεις την βάση κάθε φορά που ξεκινά η εφαρμογή
               // PlaceDao dao = INSTANCE.PlaceDao();
                /*
                dao.deleteAll();
/*
                //Place place=new Place("Europahotel","https://www.europahotelkastoria.gr/images/pages/page1-img1.jpg",4.2f,40.521720703862485f, 21.264272168957074f,"Hotel",0,"Ξενοδοχείο , στο κέντρο της πόλης της Καστοριάς","https://www.europahotelkastoria.gr/el/");
                dao.insert(new Place("Europahotel","https://www.europahotelkastoria.gr/images/pages/page1-img1.jpg",4.2f,40.521720703862485f, 21.264272168957074f,"Hotel",0,"Ξενοδοχείο , στο κέντρο της πόλης της Καστοριάς","https://www.europahotelkastoria.gr/el/"));
                dao.insert(new Place("Allahou","https://www.allahou.gr/images/yootheme/home_slide_01a.jpg",4.8f,40.521465223697966f, 21.266702348745078f,"Hotel",0,"Μια πρώην κατοικία νεοκλασσικού στυλ στην Καστοριά, προσφέρει υψηλού επιπέδου φιλοξενία στον επισκέπτη.","https://www.allahou.gr/index.php/el/"));
                dao.insert(new Place("Hotel Enastron View ","https://www.hotelenastron.gr/photos/365.jpg",4.9f,40.55143206874627f, 21.251436231490455f,"Hotel",0,"Φιλόξενο κατάλυμα με θέα στο βουνό","http://www.hotelenastron.gr/"));
                dao.insert(new Place("Hotel Doltso","https://assets.hotelwize.com/site-media/45/img_6766-a-1.jpg?_=3a797ac1&format=jpg&width=1920&height=1080",4.9f,40.516455f,21.271833f,"Hotel",0,"Γραφικό ξενοδοχείο σε πρώην αρχοντικό","https://www.doltsohotel.gr/"));
                dao.insert(new Place("Hotel Kastoria","https://www.hotel-kastoria.gr/images/phocagallery/hotelkastoria/thumbs/phoca_thumb_l_5.jpg",4.4f,40.521925f,21.273909f,"Hotel",0,"Χαλαρό ξενοδοχείο με καφέ/μπαρ","http://www.lake.gr/"));
                dao.insert(new Place("Παλιά Πόλη","https://5d13b8ffa7.cbaul-cdnwnd.com/b842cecc403b45cd137df819ad458173/200000074-b2c93b2c95-public/paliapolifota.jpg",4.6f,40.51552735224801f, 21.271769782450342f,"Rest",0,"Ένα ζεστό παραδοσιακό περιβάλλον με πολλές παλιές καστοριανές συνταγές","https://paliapoli.webnode.gr/"));
                dao.insert(new Place("Κατσαρόλα","https://lh3.googleusercontent.com/p/AF1QipN3n8ZHLzWgdq49JeYvZSEL8IWG0m_9Q8PFCVQ=w1080-h608-p-no-v0",4.6f,40.522591f,21.264773f,"Rest",0,"Παραδοσιακό εστιατόριο","https://restaurant-50540.business.site/"));
                dao.insert(new Place("Η Πάπια","https://lh5.googleusercontent.com/p/AF1QipPwXqyWT0MqMutyvyvNmWnEB5hiV5fR5hSq3OW9=w425-h240-k-no",4.4f,40.525493f,21.263521f,"Rest",0,"Πίτσα","http://www.papia.gr/"));
                dao.insert(new Place("Εν Καιρώ","https://lh5.googleusercontent.com/p/AF1QipO5t4Fsvx2XzjWocYzj0tbfdIwIhWV5noGs87G0=w426-h240-k-no",4.7f,40.515261f,21.265964f,"Rest",0,"Μικρά πιάτα","https://kafepoteionenkairw.business.site/"));
                dao.insert(new Place("Basilico ","https://www.basilico.gr/wp-content/uploads/2020/03/Basilico_060.jpg",4.7f,40.523088f,21.264177f,"Rest",0,"Ιταλική κουζίνα","https://www.basilico.gr/index.php/reservationsapp/"));
                dao.insert(new Place("Λαογραφικό Μουσείο","https://lh5.googleusercontent.com/p/AF1QipPpziL7U6StUG5bYUTImx-0DD_D92cjd53eEnme=w408-h306-k-no",4.7f,40.516385f,21.273546f,"Sights",0,"Λαογραφικό Μουσείο","https://www.digitalkastoria.gr/el/culture-tourism-events-el/museums-visiting-spaces-el/traditional-mansions-el/arxontiko-neratzi-aivazi-el"));
                dao.insert(new Place("Βυζαντινό Μουσείο","https://www.bmk.gr/wp-content/uploads/2016/02/arxiki-selida.jpg",4.6f,40.519117f,21.268479f,"Sights",0,"Βυζαντινό Μουσείο","https://www.bmk.gr/"));
                dao.insert(new Place("Ενυδρείο Καστοριάς","https://lh5.googleusercontent.com/p/AF1QipPz99lgzrtVLKIhHYfYyCJCpV7UqbSXkXoeNqzB=w408-h245-k-no",4.5f,40.512575f,21.253382f,"Sights",0,"Ενυδρείο","http://enydriokastorias.gr/"));
                dao.insert(new Place("Σπήλαιο του Δράκου","https://lh5.googleusercontent.com/p/AF1QipM-6RNHRQscBn7dnMiIkvV1P4eDtWAA-lSmMVol=w408-h544-k-no",4.7f,40.505122f,21.283633f,"Sights",0,"Σπήλαιο","https://www.spilaiodrakoukast.gr/"));
                dao.insert(new Place("Βυζαντινά Τείχη","https://lh5.googleusercontent.com/p/AF1QipNJC02pUBa3buHpJArZvt-5rF7DRMmLivztOJbz=w408-h544-k-no",4.0f,40.522765f,21.264623f,"Sights",0,"Βυζαντινά Τείχη","https://www.kastra.eu/castlegr.php?kastro=kastoria"));
                dao.insert(new Place("Prague Live Stage","https://lh5.googleusercontent.com/p/AF1QipPRWgkgnQ12-lpq_ZIO5n3ipgk5YPTE_TrHrVZ1=w427-h240-k-no",4.4f,40.519723f,21.257487f,"Entert",0,"Μπαρ με ζωντανή σκηνή","https://prague-kastoria.business.site/"));
                dao.insert(new Place("ΟΛΥΜΠΙΟΝ ","https://lh5.googleusercontent.com/p/AF1QipM7xn02e0fEEWPyHrxoHlSpRqLsTXb8CJJewJr3=w426-h240-k-no",4.9f,40.519790f,21.266250f,"Entert",0,"Κινηματογράφος","https://olympionhouse.gr/"));
                dao.insert(new Place("BACCARA","https://scontent.fskg4-1.fna.fbcdn.net/v/t1.18169-9/1833_40041929726_3110_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=825194&_nc_ohc=TZD5vITXU4wAX_ZSaUQ&_nc_ht=scontent.fskg4-1.fna&oh=00_AT_TZUUZXz5_coMf1M6K-E_Qyt6UQiFjB1gE5gY5ao9kOQ&oe=633D9166",4.4f,40.518626f,21.264865f,"Entert",0,"Καφετέρια","https://www.facebook.com/groups/40629107874/"));
                dao.insert(new Place("THE PASSENGER","https://lh5.googleusercontent.com/p/AF1QipOlFTv6p-DYijjPTErnjmHgMpqokzVWjqopWcv9=w426-h240-k-no",4.5f,40.520158f,21.263012f,"Entert",0,"Εσπρέσο μπαρ","https://m.facebook.com/ThePassengerKastoria?_rdr"));
                dao.insert(new Place("Περίπου Cocktail Bar","https://lh5.googleusercontent.com/p/AF1QipNWT_T-m0L96aKXTvanGKgGOpNMNQ75_BtRrdQ3=w408-h408-k-no",4.6f,40.515713f,21.273972f,"Entert",0,"Cocktail Bar","https://peripou.business.site/"));

/*
εδώ εισάγεις νέα δεδομένα για την βάση
                Place place = new Place("Hello");
                dao.insert(place);
                place = new Place("World");
                dao.insert(place);


 */
            });
        }
//        @Override
//        public void onOpen (SupportSQLiteDatabase db) {
//            // do something every time database is open
//
//            super.onOpen(db);
//        }

    };

}

