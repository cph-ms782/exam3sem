package facades;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import dto.RecipeDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;

/**
 *
 */
public class RecipeFacade {

    private static RecipeFacade instance;
    private static String recipeSearchList = null;
    private static List<RecipeDTO> recipeList = new ArrayList();
    private static Boolean isActivatedOnceRecipeSearchList = false;
    private static Boolean isActivatedOnce = false;

    //Private Constructor to ensure Singleton
    private RecipeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RecipeFacade getRecipeFacade() {
        if (instance == null) {
            instance = new RecipeFacade();
        }
        return instance;
    }

    public String getRecipeApi(String urlApi) throws MalformedURLException, IOException {
        URL url = new URL(urlApi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        String jsonStr;
        try (Scanner scan = new Scanner(con.getInputStream(), "UTF-8")) {
            jsonStr = null;
            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
        }
        return jsonStr;
    }

    public String getAllRecipeTitles() throws IOException, InterruptedException, ExecutionException {

        System.out.println("------------> getAllRecipeTitles");
        List<String> URLS = new ArrayList();
        URLS.add("http://46.101.217.16:4000/allRecipes");
        System.out.println("isActivatedOnce" + isActivatedOnceRecipeSearchList);
        if (isActivatedOnceRecipeSearchList) {
            return recipeSearchList;
        }
        isActivatedOnceRecipeSearchList = true;
        return getAllRecipeTitlesData(URLS);
    }

    private String getAllRecipeTitlesData(List<String> URLS) throws ProtocolException, IOException, InterruptedException, ExecutionException {

        System.out.println("recipeSearchList: " + recipeSearchList);
        if (recipeSearchList == null ) {

            Queue<Future<String>> queue = new ArrayBlockingQueue(URLS.size());
            ExecutorService workingJack = Executors.newCachedThreadPool();
            for (String url : URLS) {
                Future<String> future;
                future = workingJack.submit(() -> {
                    return getRecipeApi(url);
                });
                queue.add(future);
            }
            while (!queue.isEmpty()) {
                Future<String> cpo = queue.poll();
                if (cpo.isDone()) {
                    try {
                        System.out.println("inde i getAllRecipeTitlesData");
                        recipeSearchList = cpo.get();
                    } catch (NullPointerException ex) {
                        System.out.println("NullPointerException: " + ex);
                    }
                } else {
                    queue.add(cpo);
                }
            }
            workingJack.shutdown();
                System.out.println("recipeSearchList (as string): " + recipeSearchList);
        }
        return recipeSearchList;
    }

//    public List<RecipeDTO> getAllRecipes(String recipeID) throws IOException, InterruptedException, ExecutionException {
//
//        System.out.println("------------> getAllRecipes");
//        List<String> URLS = new ArrayList();
//        URLS.add("http://46.101.217.16:4000/recipe/" + recipeID);
//        System.out.println("isActivatedOnce" + isActivatedOnce);
//        if (isActivatedOnce) {
//            return recipeList;
//        }
//        isActivatedOnce = true;
//        return getAllRecipesData(URLS);
//    }
//    public List<RecipeDTO> getAllRecipesData(List<String> URLS) throws ProtocolException, IOException, InterruptedException, ExecutionException {
//
//        System.out.println("recipeList size: " + recipeList.size());
//        if (recipeList.size() == 0) {
//            Queue<Future<JsonObject>> queue = new ArrayBlockingQueue(URLS.size());
//            Gson gson = new GsonBuilder()
//                    .setPrettyPrinting()
//                    .serializeNulls()
//                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//                    .create();
//            ExecutorService workingJack = Executors.newCachedThreadPool();
//            for (String url : URLS) {
//                Future<JsonObject> future;
//                future = workingJack.submit(() -> {
//                    JsonObject jsonObject = new JsonParser().parse(getRecipeApi(url)).getAsJsonObject();
//                    return jsonObject;
//                });
//                queue.add(future);
//            }
//            while (!queue.isEmpty()) {
//                Future<JsonObject> cpo = queue.poll();
//                if (cpo.isDone()) {
//                    System.out.println("inde i getAllRecipesData");
//                    for (JsonElement el : cpo.get().get("teams").getAsJsonArray()) {
//                        recipeList.add(new RecipeDTO(
//                                el.getAsJsonObject().get("name").getAsString(),
//                                el.getAsJsonObject().get("crestUrl").getAsString(),
//                                Integer.parseInt(el.getAsJsonObject().get("id").getAsString()),
//                                el.getAsJsonObject().get("shortName").getAsString(),
//                                el.getAsJsonObject().get("tla").getAsString(),
//                                el.getAsJsonObject().get("address").getAsString(),
//                                el.getAsJsonObject().get("website").getAsString(),
//                                el.getAsJsonObject().get("clubColors").getAsString(),
//                                el.getAsJsonObject().get("venue").getAsString()
//                        ));
//                    }
//                } else {
//                    queue.add(cpo);
//                }
//            }
//            workingJack.shutdown();
//            for (int i = 0; i < recipeList.size(); i++) {
//                System.out.println(recipeList.get(i));
//            }
//        }
//        System.out.println("recipeList size: " + recipeList.size());
//        return recipeList;
//    }
}
