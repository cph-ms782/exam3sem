package facades;

import dto.RecipeDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class RecipeFacade {

    private static RecipeFacade instance;

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

    public String getData(String url) throws ProtocolException, IOException, InterruptedException, ExecutionException {

//        System.out.println("recipeSearchList: " + recipeSearchList);
//        if (recipeSearchList == null) {
        String data = null;
        
        Queue<Future<String>> queue = new ArrayBlockingQueue(1);
        ExecutorService workingJack = Executors.newCachedThreadPool();
        Future<String> future;
        future = workingJack.submit(() -> {
            return getRecipeApi(url);
        });
        queue.add(future);
        while (!queue.isEmpty()) {
            Future<String> cpo = queue.poll();
            if (cpo.isDone()) {
                try {
                    System.out.println("inde i getData");
                    data = cpo.get();
                } catch (NullPointerException ex) {
                    System.out.println("NullPointerException: " + ex);
                }
            } else {
                queue.add(cpo);
            }
        }
        workingJack.shutdown();
        System.out.println("data (as string): " + data);
//        }
        return data;
    }


//    public List<String> getAllRecipes() throws IOException, ProtocolException, InterruptedException, ExecutionException {
//        List<String> recipes = new ArrayList();
//        String allrecipes = null;
//        try {
//            allrecipes = getData("http://46.101.217.16:4000/allRecipes");
//        } catch (ExecutionException ex) {
//            Logger.getLogger(RecipeFacade.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String[] arrayString = allrecipes.split(",");
//        
//        for (String str : arrayString) {
//            recipes.add(str);
//        }
//        
//        return recipes;
//    }
    
}
