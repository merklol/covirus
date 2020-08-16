package com.bilingoal.covirus.models.loaders;

import android.util.Log;
import com.bilingoal.covirus.dto.Article;
import com.bilingoal.covirus.dto.Response;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;

public class ArticlesLoader extends Loader {
    private QuerySnapshot result;
    private final FirebaseFirestore db;
    private final String collectionPath;
    private final List<Article> articles = new ArrayList<>();
    private static final String TAG = ArticlesLoader.class.getSimpleName();
    private static final int EXCERPT_LENGTH = 30;
    private static final String EXCERPT_ELLIPSIS = "...";

    public ArticlesLoader(String collectionPath) {
        this.collectionPath = collectionPath;
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void load(Response response) {
        db.collection(collectionPath).get().addOnCompleteListener(task -> {
            result = task.getResult();
            if(result == null) return;

            createArticlesFromDocuments(result.getDocuments());
            response.setArticleList(articles);
            loadedListener.onLoaded(response);
        }).addOnFailureListener(e -> {
            failureListener.onFailure();
            Log.e(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
        });
    }

    private void createArticlesFromDocuments(List<DocumentSnapshot> documentSnapshots){
        for (DocumentSnapshot snapshot : documentSnapshots) {
            if (snapshot.exists()) {
                parseData(snapshot.getData());
            }
        }
    }


    private void parseData(Map<String, Object> map) {
        String title = "Unknown title", body = "Unknown body", link = "No link";
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equals("title")) title =  entry.getValue().toString();
                if (entry.getKey().equals("body")) body = createExcerpt(entry.getValue().toString());
                if (entry.getKey().equals("link")) link = entry.getValue().toString();
            }
        }
        articles.add(new Article(title, body, link));
    }

    private String createExcerpt(String str) {
        String[] temp = str.split(" ");
        StringBuilder excerpt = new StringBuilder();
        int counter = 0;

        for (String s : temp) {
            if (counter == EXCERPT_LENGTH) break;

            excerpt.append(s).append(" ");
            counter++;
        }
        
        return excerpt.append(EXCERPT_ELLIPSIS).toString();
    }
}
