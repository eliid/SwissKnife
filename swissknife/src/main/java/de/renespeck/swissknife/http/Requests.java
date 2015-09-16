package de.renespeck.swissknife.http;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;

import de.renespeck.swissknife.cfg.Const;

public class Requests {

    public String postToJSON(String url, Form form) throws ClientProtocolException, IOException {
        Response response = Request
                .Post(url)
                .addHeader("Accept", "application/json;charset=".concat(Const.UTF_8.name()))
                .addHeader("Accept-Charset", Const.UTF_8.name())
                .bodyForm(form.build())
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        HttpEntity entry = httpResponse.getEntity();
        String r = IOUtils.toString(entry.getContent(), Const.UTF_8);
        EntityUtils.consume(entry);
        return r;
    }
}
