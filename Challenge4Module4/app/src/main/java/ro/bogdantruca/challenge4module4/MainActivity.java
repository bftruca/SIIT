package ro.bogdantruca.challenge4module4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mRecyclerViewLocations = findViewById(R.id.recycler_view_locations);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewLocations.setLayoutManager(layoutManager);

        LocationAdapter locationAdapter = new LocationAdapter(getLocations(), MainActivity.this);
        mRecyclerViewLocations.setAdapter(locationAdapter);
    }

    private List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();

        Location location1 = new Location();
        location1.setStringDate("Holiday 2017");
        location1.setStringLocation("Islands");
        location1.setStringImageUrl("https://i.dailymail.co.uk/i/pix/2017/05/19/00/40840E0900000578-4507438-Dubai_plans_to_develop_a_1_3billion_tourist_resort_on_two_man_ma-a-95_1495149392296.jpg");
        locations.add(location1);

        Location location2 = new Location();
        location2.setStringDate("Fall 2017");
        location2.setStringLocation("Rome");
        location2.setStringImageUrl("https://cdn.fodors.com/wp-content/uploads/2018/10/HERO_UltimateRome_Hero_shutterstock789412159.jpg");
        locations.add(location2);

        Location location3 = new Location();
        location3.setStringDate("Summer 2017");
        location3.setStringLocation("London");
        location3.setStringImageUrl("https://www.westjet.com/assets/wj-web/images/destinations/europe/dunilgw/london-737x426.jpg");
        locations.add(location3);

        Location location4 = new Location();
        location4.setStringDate("Winter 2017");
        location4.setStringLocation("Paris");
        location4.setStringImageUrl("http://etn.travel/wp-content/uploads/2017/02/paristale.jpg");
        locations.add(location4);

        Location location5 = new Location();
        location5.setStringDate("Spring 2018");
        location5.setStringLocation("San Francisco");
        location5.setStringImageUrl("https://www.visitcalifornia.com/sites/default/files/styles/welcome_image/public/VC_LivingTheDream_SupportingContent_MylesMcGuinness_SFBayBridge9MQ_6836-2_1280X640_0.jpg");
        locations.add(location5);

        Location location6 = new Location();
        location6.setStringDate("Summer 2018");
        location6.setStringLocation("Bucharest");
        location6.setStringImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Arcul_De_Triumf.jpg/406px-Arcul_De_Triumf.jpg");
        locations.add(location6);

        return locations;
    }
}
