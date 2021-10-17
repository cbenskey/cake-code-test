package db.migration;

import java.sql.PreparedStatement;
import java.util.List;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cake.request.CakeRequest;

public class V2__Seed_Cake extends BaseJavaMigration {

    private static final String SEED_JSON = "[\n" +
            "  {\n" +
            "    \"title\" : \"Lemon cheesecake\",\n" +
            "    \"desc\" : \"A cheesecake made of lemon\",\n" +
            "    \"image\" : \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\n" +
            "  }, {\n" +
            "  \"title\" : \"victoria sponge\",\n" +
            "  \"desc\" : \"sponge with jam\",\n" +
            "  \"image\" : \"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"\n" +
            "}, {\n" +
            "  \"title\" : \"Carrot cake\",\n" +
            "  \"desc\" : \"Bugs bunnys favourite\",\n" +
            "  \"image\" : \"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"\n" +
            "}, {\n" +
            "  \"title\" : \"Banana cake\",\n" +
            "  \"desc\" : \"Donkey kongs favourite\",\n" +
            "  \"image\" : \"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"\n" +
            "}, {\n" +
            "  \"title\" : \"Birthday cake\",\n" +
            "  \"desc\" : \"a yearly treat\",\n" +
            "  \"image\" : \"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"\n" +
            "}" +
            "]";

    private static final String INSERT_SQL = "insert into cake(title, desc, image) values (?, ?, ?)";

    @Override
    public void migrate(final Context context) throws Exception {

        final ObjectMapper mapper = new ObjectMapper();

        final List<CakeRequest> rowsToInsert = mapper.readValue(SEED_JSON, new TypeReference<List<CakeRequest>>() {
        });

        try (PreparedStatement insert = context.getConnection().prepareStatement(INSERT_SQL)) {
            for (CakeRequest r : rowsToInsert) {
                insert.setString(1, r.getTitle());
                insert.setString(2, r.getDesc());
                insert.setString(3, r.getImage());
                insert.execute();
            }

        }
    }


}
