package com.bilingoal.covirus.models;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VersionLoader {
    private final String VERSION_CODE = "version_code";
    private final String collectionName;
    private final String documentId;
    private OnNewerVersionAvailableListener listener;

    public VersionLoader(String collectionName, String documentId) {
        this.collectionName = collectionName;
        this.documentId = documentId;
    }

    public void setListener(OnNewerVersionAvailableListener listener) {
        this.listener = listener;
    }

    public void getLatestVersionCode() {
        getDocument().get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                DocumentSnapshot snapshot = task.getResult();
                if(snapshot != null) {
                    final Double latestVersionCode = snapshot.getDouble(VERSION_CODE);

                    if(latestVersionCode != null) {
                        listener.onAvailable(latestVersionCode.intValue());
                    }
                }
            }
        });
    }

    private DocumentReference getDocument() {
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        return fireStore.collection(collectionName).document(documentId);

    }

    public interface OnNewerVersionAvailableListener {
        void onAvailable(int versionCode);
    }

    public static class Builder {
        private String collectionName;
        private String documentId;

        public Builder setCollectionName(String collectionName) {
            this.collectionName = collectionName;
            return this;
        }

        public Builder setDocumentId(String documentId) {
            this.documentId = documentId;
            return this;
        }

        public VersionLoader build() {
            return new VersionLoader(collectionName, documentId);
        }
    }
}
