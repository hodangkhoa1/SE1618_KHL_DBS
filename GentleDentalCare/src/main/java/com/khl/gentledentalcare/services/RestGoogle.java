package com.khl.gentledentalcare.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.khl.gentledentalcare.models.GoogleAccount;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author ASUS
 */
public class RestGoogle {

    public static String getGoogleToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(ConstantsGoogle.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", ConstantsGoogle.GOOGLE_CLIENT_ID)
                        .add("client_secret", ConstantsGoogle.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", ConstantsGoogle.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", ConstantsGoogle.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        return jsonObject.get("access_token").toString().replaceAll("\"", "");
    }

    public static GoogleAccount getGoogleUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = ConstantsGoogle.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        return new Gson().fromJson(response, GoogleAccount.class);
    }
}
