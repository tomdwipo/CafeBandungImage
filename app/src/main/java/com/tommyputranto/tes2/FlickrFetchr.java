package com.tommyputranto.tes2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems() {

        List<GalleryItem> items = new ArrayList<>();

        try {

            String url = "http://dev.gits.co.id/apicafe/public/list";
            String jsonString = getUrlString(url);

            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e("tes", "Failed to fetch items", ioe);

        } catch (JSONException je) {
            Log.e("tes", "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody)
            throws IOException, JSONException {


        JSONArray photoJsonArray = jsonBody.getJSONArray("DATA");

        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            GalleryItem item = new GalleryItem();
            item.setNomor(photoJsonObject.getString("id"));
            item.setId(photoJsonObject.getString("nama"));
            item.setCaption(photoJsonObject.getString("alamat"));
            item.setLokasi(photoJsonArray.getJSONObject(i).getString("location"));

            String Foodname = photoJsonArray.getJSONObject(i).getString("location");


            if (!photoJsonObject.has("url_foto")) {
                continue;
            }



            item.setUrl(photoJsonObject.getString("url_foto"));
            items.add(item);
        }
    }

}
